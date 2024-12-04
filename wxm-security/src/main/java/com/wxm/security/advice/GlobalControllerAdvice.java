package com.wxm.security.advice;


import com.alibaba.fastjson.JSONException;
import com.wxm.base.constrant.SecurityConstrants;
import com.wxm.base.dto.DataRtn;
import com.wxm.base.enums.SecurityInfoEnum;
import com.wxm.base.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.net.BindException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>异常拦截 </p>
 * <p>本应该使用ResponseMessage，但是为了和其他两套系统统一，使用自定义DataRet</p>
 * wxm-base 中有一个没有包含spring security +oauth2 的全局异常出来，定义顺序未@Order(value= Ordered.LOWEST_PRECEDENCE)
 *
 * @author 王森明
 * @date 2020/12/16 11:10
 * @since 1.0.0
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(DbSvcException.class)
    @ResponseBody
    public DataRtn dbSvnEx(DbSvcException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(BizSvcException.class)
    @ResponseBody
    public DataRtn bizSvcEx(BizSvcException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public DataRtn apiEx(ApiException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(JobException.class)
    @ResponseBody
    public DataRtn apiEx(JobException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(UtilException.class)
    @ResponseBody
    public DataRtn apiEx(UtilException e) {
        return getDataRtn(e);
    }


    @ExceptionHandler(OSSException.class)
    @ResponseBody
    public DataRtn apiEx(OSSException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(EncodeException.class)
    @ResponseBody
    public DataRtn apiEx(EncodeException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(DecodeException.class)
    @ResponseBody
    public DataRtn apiEx(DecodeException e) {
        return getDataRtn(e);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public DataRtn apiEx(BaseException e) {
        return getDataRtn(e);
    }

    private DataRtn getDataRtn(BaseException e) {
        // 自定义的异常，在内部输出
        return new DataRtn<>().setCode(e.getCode()).setMessage(e.getMessage()).setDetailMsg(e.getDetailMsg());
    }

    @ExceptionHandler(JobExecutionException.class)
    @ResponseBody
    public DataRtn dealJobExecutionException(JobExecutionException e) {
        log.error(e.getMessage(), e);
        return new DataRtn().failure().setMessage(e.getMessage()).setDetailMsg(e.getMessage());
    }

    /**
     * 404
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public DataRtn apiEx(NoHandlerFoundException e) {
        String url = e.getRequestURL();
        String method = e.getHttpMethod();
        log.error("没有对应请求地址【" + url + "】【" + method + "】", e);
        return new DataRtn().setCode("404").message("没有对应请求地址【" + url + "】【" + method + "】").detailMsg("404" + e.getMessage());
    }

    @ExceptionHandler(JSONException.class)
    @ResponseBody
    public DataRtn apiEx(JSONException e) {
        log.error("JSON格式有误:{}", e.getMessage(), e);
        return new DataRtn().error().message("JSON格式有误").detailMsg(e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public DataRtn dealRuntime(ArithmeticException e) {
        log.error(e.getMessage(), e);
        return new DataRtn().error().message(e.getMessage()).detailMsg(e.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public DataRtn dealRuntime(RuntimeException re) {
        log.error(">>>>RuntimeException>>>,{}", re.getMessage(), re);
        if (re instanceof HttpMessageNotReadableException) {
            String msg = re.getMessage();
            if (msg.startsWith("Required request body is missing")) {
                return new DataRtn().error().setMessage("缺少请求参数体").setDetailMsg("Required request body is missing");
            }
        }
        if (re instanceof IllegalArgumentException) {
            String msg = re.getMessage();
            if (msg.startsWith("Illegal base64 character")) {
                return new DataRtn().error().setMessage("不是正确的base64字符").setDetailMsg(msg);
            }
        }
        log.error(re.getMessage(), re);
        if (re instanceof DataIntegrityViolationException) {
            return new DataRtn().error().setMessage("数据库异常").setDetailMsg(getFirstCause(re).getMessage());
        }

        // AccessDeniedException   不能使用 re instanceof AccessDeniedException
        // MyAccessDeniedHandler 没有生效
        ;
        if (re.toString().contains("AccessDeniedException")) {
            return new DataRtn()
                    .setCode(SecurityInfoEnum.ACCESS_DENIED_EXC.getCode())
                    .setMessage(SecurityInfoEnum.ACCESS_DENIED_EXC.getMsg())
                    .setDetailMsg(SecurityInfoEnum.ACCESS_DENIED_EXC.toString() + ":" + re.getMessage());
        }
        if (re.toString().contains("RedisConnectionFailureException")) {
            return new DataRtn()
                    .setCode(SecurityInfoEnum.REDIS_CONNECTION_FAILURE.getCode())
                    .setMessage(SecurityInfoEnum.REDIS_CONNECTION_FAILURE.getMsg())
                    .setDetailMsg(SecurityInfoEnum.REDIS_CONNECTION_FAILURE.toString() + ":" + re.getMessage());
        }
        return new DataRtn().error().setMessage(re.getMessage().split(":")[0]).setDetailMsg(re.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DataRtn dealException(Exception e) {
        if (e instanceof BaseException) {
            return getDataRtn((BaseException) e);
        }
        log.error(e.getMessage(), e);
        if (e instanceof SQLSyntaxErrorException || e instanceof SQLException|| e instanceof DataIntegrityViolationException) {
            return new DataRtn().error().setMessage("数据库异常").setDetailMsg(getFirstCause(e).getMessage());
        }
        if (e instanceof MissingServletRequestParameterException) {
            String paramName = ((MissingServletRequestParameterException) e).getParameterName();
            String paramType = ((MissingServletRequestParameterException) e).getParameterType();
            return new DataRtn().error().setMessage("缺少必要参数：" + paramName + "(" + paramType + ")").setDetailMsg(e.getMessage());
        }

        return new DataRtn().error().setMessage(e.getMessage()).setDetailMsg(e.getMessage());
    }


    public Throwable getFirstCause(Throwable e){
        Throwable ex= (Throwable) e.getCause();
        if(ex==null){
            return e;
        }
        return getFirstCause(ex);
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public DataRtn dealException(Throwable e) {
        log.error(e.getMessage(), e);
        if (e instanceof BaseException) {
            return new DataRtn()
                    .error()
                    .setMessage("请不要在代码中直接使用BaseException")
                    .setDetailMsg("【" + ((BaseException) e).getCode() + "】" + e.getMessage());
        }
        if (e instanceof SQLSyntaxErrorException || e instanceof SQLException|| e instanceof DataIntegrityViolationException) {
            return new DataRtn().error().setMessage("数据库异常").setDetailMsg(getFirstCause(e).getMessage());
        }
        log.error(e.getMessage(), e);
        return new DataRtn().error().setMessage(e.getMessage()).setDetailMsg(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public DataRtn handle(ValidationException exception) {
        log.error(exception.getMessage(), exception);
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            return new DataRtn().error().setMessage(violations.stream().map(item -> item.getMessage()).collect(Collectors.joining(";")));
        }
        return new DataRtn().error().setMessage("bad request");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public DataRtn handle(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        return new DataRtn().error().setMessage(violations.stream().map(item -> item.getMessage()).collect(Collectors.joining(";")));
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public DataRtn handleBind(BindException e) {
        log.error(e.getMessage(), e);
        return new DataRtn().error().setMessage("bad request").detailMsg(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public DataRtn handleMtdArg(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new DataRtn().error().setMessage("bad request").detailMsg(e.getMessage());
    }
}
