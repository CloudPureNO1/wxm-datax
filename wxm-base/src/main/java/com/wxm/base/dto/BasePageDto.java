package com.wxm.base.dto;

import lombok.Data;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/8 16:36
 * @since 1.0.0
 */
@Data
public class BasePageDto {
    private String TransCode;
    private int currentPage=1;
    private int pageSize=10;
}
