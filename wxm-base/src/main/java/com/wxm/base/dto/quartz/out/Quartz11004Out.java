package com.wxm.base.dto.quartz.out;

import com.wxm.base.bean.QuartzForm;
import com.wxm.base.bean.datax.DataXDbSource;
import com.wxm.base.bean.datax.DataXDbTarget;
import com.wxm.base.bean.datax.DataXErrorLimit;
import com.wxm.base.bean.datax.DataXSpeed;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/7 15:54
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class Quartz11004Out implements java.io.Serializable{
    private DataXSpeed speed;
    private DataXErrorLimit errorLimit;
    private DataXDbSource dbSource;
    private DataXDbTarget dbTarget;
    private QuartzForm quartzForm;
}
