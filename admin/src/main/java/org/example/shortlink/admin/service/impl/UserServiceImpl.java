package org.example.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.constant.RedisCacheConstant;
import org.example.shortlink.admin.common.convention.exception.ClientException;
import org.example.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.example.shortlink.admin.dao.entity.UserDO;
import org.example.shortlink.admin.dao.mapper.UserMapper;
import org.example.shortlink.admin.dto.req.UserLoginReqDTO;
import org.example.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.example.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.example.shortlink.admin.dto.resq.UserLoginResqDTO;
import org.example.shortlink.admin.dto.resq.UserResqDTO;
import org.example.shortlink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口实现层
 *
 * @author LLY
 * @className UserServiceImpl
 * @date 2024/5/7
 */
@Service
@Slf4j
@RequiredArgsConstructor//注入bean
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public UserResqDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserResqDTO userResqDTO = new UserResqDTO();
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        //log.info("返回NULL");
        BeanUtils.copyProperties(userDO, userResqDTO);
        log.info("返回结果{}", userResqDTO.getUsername());
        return userResqDTO;
    }

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    @Override
    public Boolean hasUserName(String username) {
        /*LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        return userDO == null;*/

        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    /**
     * 注册
     * @param userRegisterReqDTO
     */
    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        if (!hasUserName(userRegisterReqDTO.getUsername())) {
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY+userRegisterReqDTO.getUsername());
        try {
            if (lock.tryLock()) {
                int insert = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
                if (insert < 1) {
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(userRegisterReqDTO.getUsername());
            }
        } finally {
            lock.unlock();
        }


    }

    /**
     * 更新信息
     * @param updateReqDTO
     */
    @Override
    public void update(UserUpdateReqDTO updateReqDTO) {
        //TODO 验证用户名是否为当前用户
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, updateReqDTO.getUsername());
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }

    }

    /**
     * 登录
     * @param userLoginReqDTO
     * @return
     */
    @Override
    public UserLoginResqDTO login(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException("用户名不存在");
        }
        Boolean hassed = stringRedisTemplate.hasKey("login_" + userLoginReqDTO.getUsername());
        if (hassed != null && hassed){
            throw new ClientException("用户已登陆");
        }

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + userLoginReqDTO.getUsername(),uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + userLoginReqDTO.getUsername(),30L, TimeUnit.MINUTES);
        return new UserLoginResqDTO(uuid);
    }

    /**
     * 检查用户是否登录
     * @param token
     */
    @Override
    public Boolean checkLogin(String name,String token) {
        return stringRedisTemplate.opsForHash().get("login_" + name, token)!=null;

    }

    /**
     * 检查用户是否登录
     * @param token
     */
    @Override
    public void logout(String name, String token) {
        if (checkLogin(name,token)){
            stringRedisTemplate.delete("login_" + name);
            return;
        }
        throw new ClientException("用户token不存在或未登录");
    }
}
