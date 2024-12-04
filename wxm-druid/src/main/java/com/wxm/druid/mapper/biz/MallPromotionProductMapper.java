package com.wxm.druid.mapper.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxm.druid.entity.biz.MallPromotionProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 促销活动与商品/变体关联表 Mapper 接口
 * </p>
 *
 * @author wangsm
 * @since 2024-06-03
 */
@DS("biz")
@Mapper
public interface MallPromotionProductMapper extends BaseMapper<MallPromotionProduct> {

}
