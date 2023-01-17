package com.wxm.base.enums;

/**
 * <p>spirng security 信息校验枚举</p>
 * <p></p>
 *    SUCCESS("0","成功"),
 *     FAILURE("-1" ,"失败"),
 *     SFTP_CHANNEL("SFTP0001" ,"创建SFTP连接异常"),
 *     SFTP_SESSION("SFTP0002" ,"创建SFTP会话异常"),
 *     SFTP_DISCONNECT("SFTP0003" ,"SFTP关闭异常"),
 *     SFTP_MKDIR("SFTP0004", "SFTP创建目录失败"),
 *     SFTP_UPLOAD("SFTP0005", "SFTP文件上传失败"),
 *     SFTP_DOWNLOAD("SFTP0005", "SFTP文件下载失败"),
 *     SIGN_PDF_QRCODE("SIGN-PDF-001", "PDF文件添加二维码失败"),
 *     QRCODE_CREATE("QRCODE-0001", "二维码生成失败"),
 *     SERVICE_EXC("EXC-53-10001", "统一外层服务转换异常"),
 *     SERVICE_EXC_BIZ("EXC-53-10002", "PDF下载失败"),
 *     CLASS_NOTFOUND("EXC-10002", "没有找到对应类"),
 *     NO_SUCH_METHOD("EXC-10003", "没有找到对应方法"),
 *     INVOCATION_TARGET("EXC-10004", "反射执行方法发生异常"),
 *     ILLEGAL_ACCESS("EXC-10005", "反射方法等没有执行权限"),
 *     SECURITY_CHECK_NO_PASS("CHECK-10001", "白名单校验不通过"),
 *     SECURITY_CHECK_FAILURE("CHECK-10002", "安全校验发生异常"),
 *     SECURITY_CHECK_INJECT("CHECK-10003", "发送的请求中含有非法字符"),
 *     SECURITY_CHECK_FRT("CHECK-10004", "报表发送的请求中含有非法字符"),
 *     DB_SAVE_FAILURE("DB-10001", "数据存储失败"),
 *     FILE_TYPE_ERR("FILE-10001", "文件类型错误：请传入pdf 或 PDF 或者 不传入"),
 *     BB_TRAN_EXC("BB-10001", "报表转换失败"),
 *     BB_CLOSE_EXC("BB-10002", "输出流关闭异常"),
 *     FILE_TYPE("FILE-TYPE","pdf"),
 *     FILE_TYPE_C("FILE-TYPE_C",".cpt")
 * @author 王森明
 * @date 2021/4/1 13:19
 * @since 1.0.0
 */
public enum SecurityInfoEnum {
    CLIENT_ID_OR_CLIENT_SECRET_ERROR("SCK-10000","invalid_client/Bad client credentials","client_id 或 client_secret 错误"),
    USER_LOGON_STATUS_INVALID("SCK-10001","NotLoginOrLogonStatusInvalid","未登陆或者登陆状态失效"),
    USER_ACCOUNT_EXPIRED("SCK-10002","AccountExpiredException","账号过期"),
    USER_CREDENTIALS_ERROR("SCK-10003","BadCredentialsException","密码错误"),
    USER_CREDENTIALS_EXPIRED("SCK-10004","CredentialsExpiredException","密码过期"),
    USER_ACCOUNT_DISABLE("SCK-10005","DisabledException","账号不可用"),
    USER_ACCOUNT_LOCKED("SCK-10006","LockedException","账号被锁住"),
    USER_ACCOUNT_NOT_EXIST("SCK-10007","InternalAuthenticationServiceException","用户账号不存在"),
    USER_ACCOUNT_LOGON_BY_OTHERS("SCK-10008","USER_ACCOUNT_LOGON_BY_OTHERS","用户账号被其他人登录"),
    DB_SAVE_FAILURE("SCK-DB-10001", "DBSaveException","数据存储失败"),
    DB_SEARCH_FAILURE("SCK-DB-10002","DBSearchException", "数据查询失败"),
    DB_SQL_SYNTAX_ERROR("SCK-DB-10003","SQLSyntaxErrorException", "SQL语法错误"),
    DB_BAD_SQL_GRAMMAR("SCK-DB-10004","BadSqlGrammarException", "无效SQL"),
    TOKEN_EMPTY_ERROR("SCK-10005","InsufficientAuthenticationException:Full authentication is required to access this resource","token有误或者为空"),
    TOKEN_ERROR("SCK-10009","InsufficientAuthenticationException:Invalid access token","token 不合法"),
    TOKEN_EMPTY("SCK-10010","InsufficientAuthenticationException:empty token","token 为空"),
    TOKEN_INVALID("SCK-10011","InsufficientAuthenticationException:Invalid access token","token 过期"),
    ACCESS_DENIED_EXC("ACCESS-10001","AccessDeniedException","没有访问权限"),
    REDIS_CONNECTION_FAILURE("REDIS-10001","RedisConnectionFailureException:Unable to connect to Redis","Redis连接失败"),
    SECURITY_CHECK_NO_PASS("CHECK-10001","White list verification failed","白名单校验不通过"),
    SECURITY_CHECK_FAILURE("CHECK-10002","Abnormal security verification","安全校验发生异常"),
    SECURITY_CHECK_INJECT_XSS("CHECK-10003","The request sent contains illegal characters","发送的请求中含有非法字符"),

    UN_DEFINED_EXC("SCK-9999","UN_DEFINED_EXC","其他未定义异常"),
    ;


    private String code;
    private String name;
    private String msg;

    SecurityInfoEnum(String code, String name, String msg) {
        this.code = code;
        this.name = name;
        this.msg = msg;
    }

    public static String getMsg(String code){
        for(SecurityInfoEnum s:SecurityInfoEnum.values()){
            if(code.equals(s.getCode())){
                return s.getMsg();
            }
        }
        return "";
    }

    public static boolean isExists(String code){
        for(SecurityInfoEnum s:SecurityInfoEnum.values()){
            if(code.equals(s.getCode())){
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
