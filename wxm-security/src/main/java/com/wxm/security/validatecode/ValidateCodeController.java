package com.wxm.security.validatecode;

import com.wxm.base.dto.DataRtn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 14:00:42
 */
@RestController
@RequestMapping({"/validateCode"})
public class ValidateCodeController {
    @Value("${validate.code.type}")
    private String validateCodeType;

    /**
     * 自从注入 实现了ValidateCodeService 接口的bean
     * <p>
     * 让Spring框架自动查找并注入所有实现了ValidateCodeService接口的bean到这个listValidateCodeService列表中。
     * 这种方式非常适合于策略模式或者需要处理多个相同类型服务的场景，提供了高度的灵活性和扩展性。
     * 当有新的验证码验证方式实现IValidateCode接口并声明为Spring Bean时，
     * 它们会自动被添加到这个列表里，无需修改现有代码。
     */
    private final List<ValidateCodeService> listValidateCodeService;

    public ValidateCodeController(List<ValidateCodeService> listValidateCodeService) {
        this.listValidateCodeService = listValidateCodeService;
    }


    @GetMapping({"/load"})
    public DataRtn loadValidateCode(HttpServletRequest request) {
        Assert.notNull(validateCodeType, "validateCodeType must not be null");
        ValidateCodeService validateCodeService = this.loadValidateCodeImpl(validateCodeType);
        ValidateCode validateCode = validateCodeService.build(request);
        validateCode.setType(validateCodeType);
        return DataRtn.ok(validateCode);
    }


    @PostMapping({"/check"})
    public DataRtn check(@RequestBody ValidateCode validateCode, HttpServletRequest request) {
        Assert.notNull(validateCode.getToken(), "token不能为空！");
        Assert.notNull(validateCode.getCheckCode(), "checkCode不能为空！");
        ValidateCodeService validateCodeService = this.loadValidateCodeImpl(validateCodeType);
        validateCodeService.check(validateCode, request);
        return DataRtn.ok();
    }

    private ValidateCodeService loadValidateCodeImpl(String type) {
        List<ValidateCodeService> list = listValidateCodeService.stream().filter(item -> item.type().equals(type)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            throw new SecurityException("验证码类型不存在");
        }
        return list.get(0);
    }
}
