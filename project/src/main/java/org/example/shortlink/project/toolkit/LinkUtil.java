package org.example.shortlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.example.shortlink.project.common.constant.ShortLinkConstant;

import java.util.Date;
import java.util.Optional;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午1:54
 * @className LinkUtil
 * @copyright LLY
 */
public class LinkUtil {
    public static long getLinkCacheValidTime(Date validDate){
        //long between = DateUtil.between(new Date(), validDate, DateUnit.MS);
        return Optional.ofNullable(validDate).map(each -> DateUtil.between(new Date(), each
        , DateUnit.MS)).orElse(ShortLinkConstant.DEFAULT_CACHE_VALID_TIME);

    }
}
