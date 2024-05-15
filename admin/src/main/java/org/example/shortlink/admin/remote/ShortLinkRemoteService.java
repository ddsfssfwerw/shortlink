package org.example.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import org.example.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import org.example.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkGroupCountQueryResqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkPageResqDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/11 上午10:10
 * @className ShortLinkRemoteService
 * @copyright LLY
 */
public interface ShortLinkRemoteService {
    /**
     * 创建
     * @param shortLinkCreateReqDTO
     * @return
     */
    static Result<ShortLinkCreateResqDTO> createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        String post = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create", JSON.toJSONString(shortLinkCreateReqDTO) );
        return JSON.parseObject(post, new TypeReference<Result<ShortLinkCreateResqDTO>>() {
            @Override
            public Result<ShortLinkCreateResqDTO> parseObject(String text) {
                return super.parseObject(text);
            }
        });
    }

    /**
     * 分页查询
     * @param reqDTO
     * @return
     */
    default Result<IPage<ShortLinkPageResqDTO>> pageShortLink(ShortLinkPageReqDTO reqDTO) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("gid", reqDTO.getGid());
        resultMap.put("current", reqDTO.getCurrent());
        resultMap.put("size", reqDTO.getSize());
        String s = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page", resultMap);
        //JSON.parseObject(s,ShortLinkPageResqDTO.class);
        System.out.println(s);
        return JSON.parseObject(s, new TypeReference<Result<IPage<ShortLinkPageResqDTO>>>() {
            @Override
            public Result<IPage<ShortLinkPageResqDTO>> parseObject(String text) {
                return super.parseObject(text);
            }
        });
    }

    /**
     * 查询分组内数量
     * @param gids
     * @return
     */
    default Result<List<ShortLinkGroupCountQueryResqDTO>> listGroupShortLinkCount(List<String> gids) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("gids", gids);
        String s = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count", resultMap);
        return JSON.parseObject(s, new TypeReference<Result<List<ShortLinkGroupCountQueryResqDTO>>>() {
            @Override
            public Result<List<ShortLinkGroupCountQueryResqDTO>> parseObject(String text) {
                return super.parseObject(text);
            }
        });
    }

    /**
     * 修改
     * @param requestParam
     */
    default void updateShortLink(ShortLinkUpdateReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/update", JSON.toJSONString(requestParam));

    }

    default Result<String> getTitleByUrl(String url){
        String s = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/title?url="+url);
        return JSON.parseObject(s, new TypeReference<>() {}
        );
    }

    default void saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/save", JSON.toJSONString(requestParam));
    }

    /**
     * 分页查询回收站
     * @param reqDTO
     * @return
     */
    default Result<IPage<ShortLinkPageResqDTO>> pageRecyclebinShortLink(ShortLinkRecycleBinPageReqDTO reqDTO) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("current", reqDTO.getCurrent());
        resultMap.put("gids", reqDTO.getGids());
        resultMap.put("size", reqDTO.getSize());
        String s = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/page", resultMap);
        //JSON.parseObject(s,ShortLinkPageResqDTO.class);
        return JSON.parseObject(s, new TypeReference<Result<IPage<ShortLinkPageResqDTO>>>() {
            @Override
            public Result<IPage<ShortLinkPageResqDTO>> parseObject(String text) {
                return super.parseObject(text);
            }
        });
    }

    /**
     * 移出
     * @param requestParam
     */
    default void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/recover", JSON.toJSONString(requestParam));
    }

    /**
     * 彻底删除
     * @param requestParam
     */
    default void removreRecycleBin(RecycleBinRemoveReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/remove", JSON.toJSONString(requestParam));

    }
}
