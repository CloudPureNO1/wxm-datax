package com.wxm.base.dto.quartz.out;

import com.wxm.base.dto.quartz.item.Item11002Out;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/4 11:28
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class Quartz11002Out implements java.io.Serializable{
    private long total;
    private List<Item11002Out>list=new ArrayList<>();
}
