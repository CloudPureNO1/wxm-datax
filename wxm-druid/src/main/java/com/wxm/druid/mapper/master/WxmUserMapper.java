package com.wxm.druid.mapper.master;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.druid.entity.master.WxmUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 王森明
 * @since 2022-06-01
 */
@Mapper
@Repository
public interface WxmUserMapper extends BaseMapper<WxmUser> {
    List<WxmUser>selectAll();
    List<WxmUser>selectByUserId(@Param("userId")Long userId,@Param("userStatus")String userStatus);

    /**
     * 条件查询在用户组ID中的用户
     * @param userId
     * @param username
     * @param creator
     * @param userType
     * @param userRate
     * @param userStatus
     * @param groupId
     * @return
     */
    List<WxmUser>selectByCon(@Param("userId")Long userId,@Param("username") String username,@Param("creator")String creator,@Param("userType")String userType, @Param("userRate")String userRate,@Param("userStatus")String userStatus,@Param("groupId")Long groupId);

    /**
     * 条件查询，不在用户组ID中的用户
     * @param userId
     * @param username
     * @param creator
     * @param userType
     * @param userRate
     * @param userStatus
     * @param groupId
     * @return
     */
    List<WxmUser>selectByConNonGroup(@Param("userId")Long userId,@Param("username") String username,@Param("creator")String creator,@Param("userType")String userType, @Param("userRate")String userRate,@Param("userStatus")String userStatus,@Param("groupId")Long groupId);
}
