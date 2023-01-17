package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac21002In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmGroup;
import com.wxm.druid.mapper.master.WxmGroupMapper;
import com.wxm.service.db.master.IWxmGroupService;
import com.wxm.util.my.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:41
 * @since 1.0.0
 */
@Slf4j
@Service("wxmGroupService")
public class WxmGroupService extends ServiceImpl<WxmGroupMapper,WxmGroup> implements IWxmGroupService {
    private final WxmGroupMapper wxmGroupMapper;
    public WxmGroupService(WxmGroupMapper wxmGroupMapper){
        this.wxmGroupMapper = wxmGroupMapper;
    }
    @Override
    public List<WxmGroup> queryAll() throws DbSvcException {
        try{
            return  wxmGroupMapper.selectAll();
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户组列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户组列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryAllPage(int currentPage, int pageSize) throws DbSvcException {
        try{
            PageHelper.startPage(currentPage,pageSize);

            List<WxmGroup> list=  wxmGroupMapper.selectAll();
            PageInfo<WxmGroup>pageInfo=new PageInfo<>(list);
            return pageInfo;
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户组列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户组列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo queryPageByCon(Rbac21002In rbac21002In) throws DbSvcException {
        try{
            PageHelper.startPage(rbac21002In.getCurrentPage(),rbac21002In.getPageSize());
            BeanUtils instance=BeanUtils.getInstance();
            BeanCopier bc=instance.create(Rbac21002In.class,WxmGroup.class,true);
            WxmGroup wxmGroup=new WxmGroup();
            instance.copy(rbac21002In,wxmGroup,bc,true);
            List<WxmGroup> list=  wxmGroupMapper.selectPageByCon(wxmGroup);
            PageInfo<WxmGroup>pageInfo=new PageInfo<>(list);
            return pageInfo;
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户组列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("用户组列表"),e.getMessage());
        }
    }

    @Override
    public int add(WxmGroup group) throws DbSvcException {
        try{
            return wxmGroupMapper.insert(group);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1002.toString(),DbSvcEnum.DB_SVC_1002.getMessage("用户组"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1002.toString(),DbSvcEnum.DB_SVC_1002.getMessage("用户组"),e.getMessage());
        }
    }

    @Override
    public int edit(WxmGroup group) throws DbSvcException {
        try{
            return wxmGroupMapper.updateById(group);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1003.toString(),DbSvcEnum.DB_SVC_1003.getMessage("用户组"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1003.toString(),DbSvcEnum.DB_SVC_1003.getMessage("用户组"),e.getMessage());
        }
    }

    @Override
    public int delete(Long groupId) throws DbSvcException {
        try{
            return wxmGroupMapper.deleteById(groupId);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004.toString(),DbSvcEnum.DB_SVC_1004.getMessage("用户组"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004.toString(),DbSvcEnum.DB_SVC_1004.getMessage("用户组"),e.getMessage());
        }
    }
}
