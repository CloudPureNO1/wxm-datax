package com.wxm.util.my;


import com.wxm.base.bean.ExceptionBean;
import com.wxm.base.constrant.LogConstant;
import com.wxm.base.exception.BaseException;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-05-17 11:52:21
 */
public class LogStackTraceUtil {
    public static ExceptionBean buildData(Throwable e) {
        ExceptionBean exceptionBean=new ExceptionBean();
        List<StackTraceElement> list = new ArrayList<>();
        String msg = e.getMessage();
        Throwable cause = e.getCause();
        if(cause==null){
            exceptionBean.setMessage(msg);
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (stackTrace == null) {
                exceptionBean.setList(list);
                return exceptionBean;
            }
            List<StackTraceElement> listLocal = Arrays.stream(stackTrace).filter(st -> st.getClassName().startsWith(LogConstant.LOG_PREFIX)).collect(Collectors.toList());
            list.addAll(listLocal);
            if (stackTrace != null && stackTrace.length > 40) {
                stackTrace = Arrays.copyOfRange(stackTrace, 0, 40);
                list.addAll(Arrays.asList(stackTrace));
            }
            exceptionBean.setList(list);

            return exceptionBean;
        }

        if(cause instanceof InvocationTargetException){
            InvocationTargetException ie= (InvocationTargetException) cause;
            Throwable tte=ie.getTargetException();
            if(tte instanceof BaseException){
                StackTraceElement[] stackTrace = tte.getStackTrace();
                List<StackTraceElement> listLocal = Arrays.stream(stackTrace).filter(st -> st.getClassName().startsWith(LogConstant.LOG_PREFIX)).collect(Collectors.toList());
                list.addAll(listLocal);
                if(tte.getCause()!=null){
                    StackTraceElement[] stackTraceCase = tte.getCause().getStackTrace();
                    List<StackTraceElement> listLocalCase = Arrays.stream(stackTraceCase).filter(st -> st.getClassName().startsWith(LogConstant.LOG_PREFIX)).collect(Collectors.toList());
                    list.addAll(listLocalCase);
                }
                if (stackTrace != null && stackTrace.length > 40) {
                    stackTrace = Arrays.copyOfRange(stackTrace, 0, 40);
                    list.addAll(Arrays.asList(stackTrace));
                }
                exceptionBean.setMessage(tte.getMessage());
                exceptionBean.setList(list);
                return exceptionBean;
            }



            msg=cause.getMessage();
        }


        if (!StringUtils.hasLength(msg) || !msg.startsWith(LogConstant.LOG_EXCEPTION)) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            exceptionBean.setMessage(msg);
            if (stackTrace == null) {
                exceptionBean.setList(list);
                return exceptionBean;
            }
            List<StackTraceElement> listLocal = Arrays.stream(stackTrace).filter(st -> st.getClassName().startsWith(LogConstant.LOG_PREFIX)).collect(Collectors.toList());
            list.addAll(listLocal);
            if(e.getCause()!=null){
                StackTraceElement[] stackTraceCase = e.getCause().getStackTrace();
                List<StackTraceElement> listLocalCase = Arrays.stream(stackTraceCase).filter(st -> st.getClassName().startsWith(LogConstant.LOG_PREFIX)).collect(Collectors.toList());
                list.addAll(listLocalCase);
            }
            if (stackTrace != null && stackTrace.length > 40) {
                stackTrace = Arrays.copyOfRange(stackTrace, 0, 40);
            }
            list.addAll(Arrays.asList(stackTrace));
            exceptionBean.setList(list);
            return exceptionBean;
        }

        return buildData(cause);
    }
}
