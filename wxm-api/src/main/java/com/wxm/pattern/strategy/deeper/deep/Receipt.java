package com.wxm.pattern.strategy.deeper.deep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:35:45
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receipt {
    private String message; //回执信息

    private String type; //回执类型(MT1101、MT2101、MT4101、MT8104)
}
