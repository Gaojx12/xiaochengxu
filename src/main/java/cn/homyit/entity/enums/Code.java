package cn.homyit.entity.enums;

/**
 * @program:
 * @description:
 * @author: Answer
 * @create: 2024/6/22 23:49
 **/
public enum Code {
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    NOT_FOUND(404, "未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_GATEWAY(502, "错误的网关"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    PARAM_ERROR(1001, "参数错误"),
    PARAM_NULL(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_BIND_ERROR(1004, "参数绑定错误"),
    PARAM_VALID_ERROR(1005, "参数校验错误"),
    PARAM_PARSE_ERROR(1006, "参数解析错误"),
    PARAM_CONVERT_ERROR(1007, "参数转换错误"),
    PARAM_FORMAT_ERROR(1008, "参数格式错误"),
    PARAM_LENGTH_ERROR(1009, "参数长度错误"),
    PARAM_RANGE_ERROR(1010, "参数范围错误"),
    PARAM_REPEAT_ERROR(1011, "参数重复错误"),
    PARAM_ILLEGAL_ERROR(1012, "参数非法错误"),
    PARAM_NOT_EXIST(1013, "参数不存在"),
    PARAM_EXIST(1014, "参数已存在"),
    PARAM_NOT_SUPPORT(1015, "参数不支持"),
    PARAM_NOT_MATCH(1016, "参数不匹配"),
    PARAM_NOT_UNIQUE(1017, "参数不唯一"),
    PARAM_NOT_EMPTY(1018, "参数不为空"),
    PARAM_NOT_NULL(1019, "参数不为null"),
    PARAM_NOT_BLANK(1020, "参数不为空白"),
    PARAM_NOT_ZERO(1021, "参数不为0"),
    PARAM_NOT_NEGATIVE(1022, "参数不为负数"),
    PARAM_NOT_POSITIVE(1023, "参数不为正数"),
    PARAM_NOT_EMPTY_LIST(1024, "参数不为空列表"),
    PARAM_NOT_EMPTY_MAP(1025, "参数不为空映射"),
    PARAM_NOT_EMPTY_ARRAY(1026, "参数不为空数组"),
    PARAM_NOT_EMPTY_STRING(1027, "参数不为空字符串"),
    PARAM_NOT_EMPTY_OBJECT(1028, "参数不为空对象"),
    PARAM_NOT_EMPTY_COLLECTION(1029, "参数不为空集合");

    private final int stateNum;

    private final String msg;

    Code(int stateNum, String msg) {
        this.stateNum = stateNum;
        this.msg = msg;
    }

    public int getStateNum() {
        return stateNum;
    }

    public String getMsg() {
        return msg;
    }
}
