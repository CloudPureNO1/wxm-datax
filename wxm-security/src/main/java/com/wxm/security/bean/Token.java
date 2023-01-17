package com.wxm.security.bean;

import lombok.*;

/**
 * @ClassName com.insigma.api.dto.check.Token
 * @Description 文件描述
 * @Author 王森明-${wangsm}
 * @DateTime 2020/9/10 9:21 九月 星期四
 * @Version V1.0.0
 * @Copyright: 2020 www.epsoft.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于浙江网新恩普软件有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String accessToken;
    private long expiration;
}
