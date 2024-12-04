package com.wxm.security.validatecode;

import lombok.*;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 14:02:51
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCode {
    private String back;
    private String front;
    private String token;
    private String checkCode;
    private String type;
}
