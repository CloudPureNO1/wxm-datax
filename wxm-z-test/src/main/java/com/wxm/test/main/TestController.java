package com.wxm.test.main;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/8/5 16:58
 * @since 1.0.0
 */
@Validated
@RequestMapping("/test")
@RestController
public class TestController {
    @PostMapping("/msg")
    public String msg(@RequestBody String str) {
        return "传入的值：" + str;
    }


    // 如果添加校验 @RequestParam 这个注解需要去掉，否则不走全局异常
    @PostMapping("/msg1/{id}")
    public String msg1(@PathVariable String id, @NotBlank(message = "name不能为空") String name, @RequestParam(value="files", required = false) MultipartFile[] files) {
        return "success" ;
    }

    @PostMapping("/msg2")
    public String msg(@RequestBody String str,@RequestParam("name")String name) {
        return "传入的值：" + str;
    }

}
