package com.wxm.api.controller;

import com.wxm.druid.entity.biz.RsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;

/**
 * 公共配置型API,支持配置SQL型接口
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-03 13:18:10
 */
@Slf4j
@RestController
@RequestMapping("/")
public class ShareController {
    /**
     * 解释：
     * @RequestMapping(value = "/open/api",
     * method = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET},
     * consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,
     * MediaType.TEXT_PLAIN_VALUE,
     * MediaType.APPLICATION_FORM_URLENCODED_VALUE,
     * MediaType.APPLICATION_JSON_VALUE},
     * produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE}
     * )
     *
     * 这段代码是一个Java注解，用于Spring框架中，用来定义控制器类或方法的请求映射。具体来说，这是对一个处理HTTP请求的方法的配置。下面是对该@RequestMapping注解各属性的详细解释：
     *
     * value: 指定了处理请求的URL路径。在这个例子中，值为/open/api，意味着这个方法将处理所有以/open/api开头的HTTP请求。
     *
     * method: 定义了该处理器方法能够处理的HTTP请求方法类型。这里指定了三种方法：
     *
     * RequestMethod.POST: 处理HTTP的POST请求。
     * RequestMethod.OPTIONS: 处理HTTP的OPTIONS请求，通常用于获取服务器支持的HTTP方法等元信息。
     * RequestMethod.GET: 处理HTTP的GET请求。
     * consumes: 指定了请求的内容类型（Content-Type），即客户端发送给服务端的数据格式。服务端只会处理content-type头部匹配这些指定媒体类型其中之一的请求。这里列出了四种可接受的内容类型：
     *
     * MediaType.APPLICATION_JSON_UTF8_VALUE: 接受JSON格式且编码为UTF-8的数据。
     * MediaType.TEXT_PLAIN_VALUE: 接受纯文本格式的数据。
     * MediaType.APPLICATION_FORM_URLENCODED_VALUE: 接受URL编码的表单数据。
     * MediaType.APPLICATION_JSON_VALUE: 同样接受JSON格式的数据，此为Spring 4.3及以上版本推荐使用的方式，因为它自动适应字符编码。
     * produces: 指定了响应的内容类型（Content-Type），即服务端返回给客户端的数据格式。只有当客户端（通过Accept头部）接受的媒体类型与之一致时，才会处理该请求。这里指定了三种可能的响应类型：
     *
     * MediaType.APPLICATION_JSON_UTF8_VALUE: 响应JSON格式且编码为UTF-8的数据。
     * MediaType.APPLICATION_JSON_VALUE: 同上，推荐使用的方式。
     * MediaType.TEXT_PLAIN_VALUE: 响应纯文本格式的数据。
     * 综上所述，这个注解配置了一个处理器方法，它位于/open/api路径下，能处理POST、OPTIONS、GET类型的HTTP请求，接受JSON、纯文本、URL编码表单等多种格式的请求体，并能根据情况返回JSON、纯文本格式的响应数据。
     */


    /**
     * 共享开放统一服务接口 支持post/get和 options（心跳检测）请求
     *
     * @param principal         接口请求应用
     * @param rsApi               接口信息
     * @param requestHeaderMap  请求头内容
     * @param requestParamMap   请求参数 （url/form）
     * @param requestBodyString 请求体
     * @param request           用来存放额外信息贯穿上下文
     * @return
     */
//    @RequestMapping(value = "/open/api",
//            method = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET},
//            consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
//    )
//    public Object getData(Principal principal,
//                          RsApi rsApi,
//                          @RequestHeader MultiValueMap<String, String> requestHeaderMap,
//                          @RequestParam(required = false) LinkedHashMap<String, Object> requestParamMap,
//                          @RequestBody(required = false) String requestBodyString,
//                          HttpServletRequest request) {
//
//        return shareService.getData(principal.getName(), rsApi, requestParamMap, requestBodyString, requestHeaderMap, request);
//    }
}
