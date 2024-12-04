package com.wxm.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxm.base.enums.UserStatusEnum;
import com.wxm.core.service.ApiLogService;
import com.wxm.druid.entity.biz.WxmApiLog;
import com.wxm.druid.entity.biz.WxmUser;
import com.wxm.druid.mapper.biz.WxmApiLogMapper;
import com.wxm.druid.mapper.biz.WxmUserMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-06-17 16:28:57
 */
@Service("apiLogServiceForCore")
public class ApiLogServiceImpl implements ApiLogService {
    private final WxmApiLogMapper wxmApiLogMapper;
    private final WxmUserMapper wxmUserMapper;

    public ApiLogServiceImpl(WxmApiLogMapper wxmApiLogMapper, WxmUserMapper wxmUserMapper) {
        this.wxmApiLogMapper = wxmApiLogMapper;
        this.wxmUserMapper = wxmUserMapper;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLog(WxmApiLog apiLog) {
        wxmApiLogMapper.insert(apiLog);
    }

    @Override
    public void setOperatorMsg(WxmApiLog apiLog) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication().getPrincipal() instanceof UserDetails) {
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            QueryWrapper<WxmUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WxmUser::getUsername, userDetail.getUsername())
                    .eq(WxmUser::getUserStatus, UserStatusEnum.NORMAL.toString());
            List<WxmUser> userList = wxmUserMapper.selectList(queryWrapper);
            if(CollectionUtils.isEmpty(userList)){
                throw new RuntimeException("当前用户存不存在正常用户记录，请联系系统管理员");
            }
            if(userList.size()>1){
                throw new RuntimeException("当前用户存在多条正常用户记录，请联系系统管理员");
            }
            apiLog.setOperatorId(userList.get(0).getUserId()+"");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void upLog(WxmApiLog apiLog) {
        wxmApiLogMapper.updateById(apiLog);
    }
}
