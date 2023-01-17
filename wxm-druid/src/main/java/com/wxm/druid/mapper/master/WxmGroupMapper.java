package com.wxm.druid.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * 用户组 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Mapper
@Repository
public interface WxmGroupMapper extends BaseMapper<WxmGroup> {
    List<WxmGroup> selectAll();
    List<WxmGroup>selectPageByCon(WxmGroup entity);
}
