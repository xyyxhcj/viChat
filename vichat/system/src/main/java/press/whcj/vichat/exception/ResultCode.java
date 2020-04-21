package press.whcj.vichat.exception;

import lombok.Getter;

/**
 * request result code
 *
 * @author xyyxhcj@qq.com
 * @since 2019/12/31
 */
@Getter
public enum ResultCode {

    /* ============================ common ========================== **/
    PARAMS_NOT(303, "missing parameter"),
    PARAMS_ERROR(304, "parameter error"),
    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "without authorization"),
    FORBIDDEN(403, "access forbidden"),
    TIME_OUT(408, "connection timeout"),
    INTERNAL_SERVER_ERROR(500, "abnormal operation"),
    OPERATION_FAILURE(507, "operation failure"),

    /* ============================ business code ========================== **/
    RECORD_NOT_EXIST(10001, "record not exist"),
    LOGIN_NAME_EXIST(10002, "loginName exist"),
    PHONE_EXIST(10003, "phone exist"),
    LOGIN_NAME_OR_PASSWORD_ERROR(10003, "loginName or password error"),
    USER_TOKEN_EXPIRED(10004, "user login has expired"),
    OLD_PWD_ERROR(10005, "old password error"),
    ;
    public final int code;
    public String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
