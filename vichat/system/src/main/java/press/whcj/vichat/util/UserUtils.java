package press.whcj.vichat.util;

import org.springframework.stereotype.Component;

/**
 * @author xyyxhcj@qq.com
 * @since 2019/12/31
 */
@Component
public class UserUtils {
    /*public static UserVo getOperator() {
        Object userInfo = BaseController.getRequest().getSession().getAttribute(Constant.SessionKey.USER_INFO);
        if (userInfo == null) {
            throw new ServiceException(ResultCode.USER_TOKEN_EXPIRED);
        }
        return JsonUtils.json2Pojo((String) userInfo, UserVo.class);
    }*/
}
