package org.example.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkPageResqDTO;

import java.util.HashMap;
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

}
