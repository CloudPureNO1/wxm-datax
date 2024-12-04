package com.wxm.test.main;

import com.wxm.test.main.dto.TestDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/msg3")
    public List<TestDto> msg3(){
        List<TestDto>list=new ArrayList<>();
        list.add(initDto("wangsm","433127198702025814","143434343","0571-45454545","17899999999"));
        list.add(initDto("433127198702025814","433127198702025814","143434343","0571-45454545","17899999999"));
        list.add(initDto("17899999999","433127198702025814","143434343","0571-45454545","17899999999"));
        list.add(initDto("123123123123123121","433127198702025814","143434343","0571-45454545","17899999999"));
        list.add(initDto("wangsm","433127198702025814","143434343","0571-45454545","17899999999"));
        list.add(initDto("wangsm","433127198702025814","143434343","0571-45454545","17899999999"));

        return list;
    }

    private TestDto initDto(String loginname,String idCard,String password,String tel,String phone){
        TestDto testDto=new TestDto();
        testDto.setTel(tel);
        testDto.setLoginname(loginname);
        testDto.setPassword(password);
        testDto.setIdCard(idCard);
        testDto.setPhone(phone);
        return testDto;
    }
}
