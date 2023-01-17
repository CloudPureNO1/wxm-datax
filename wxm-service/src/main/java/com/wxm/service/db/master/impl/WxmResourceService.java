package com.wxm.service.db.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.TreeNode;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmResource;
import com.wxm.druid.mapper.master.WxmResourceMapper;
import com.wxm.service.db.master.IWxmResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:48
 * @since 1.0.0
 */
@Slf4j
@Service("wxmResourceService")
public class WxmResourceService extends ServiceImpl<WxmResourceMapper, WxmResource> implements IWxmResourceService {
    @Autowired
    private WxmResourceMapper wxmResourceMapper;
    @Override
    public List<WxmResource> queryAll() throws DbSvcException {
        try{
            return  wxmResourceMapper.selectAll();
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage());
        }
    }

    @Override
    public List<WxmResource> queryByCon(Long resourceId, String resourceName, String resourceUrl, String resourceType) throws DbSvcException {
        try{
            return wxmResourceMapper.selectByCon(resourceId, resourceName, resourceUrl, resourceType);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage());
        }
    }

    @Override
    public List<WxmResource> queryByUsername(String username) throws DbSvcException {
        try{
            return  wxmResourceMapper.selectByUsername(username);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage());
        }
    }

    @Override
    public List<WxmResource> queryByRoleId(Long roleId) throws DbSvcException {
        try{
            return  wxmResourceMapper.selectByRoleId(roleId);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage());
        }
    }

    @Override
    public PageInfo<WxmResource> queryAllPage(int currentPage, int pageSize) throws DbSvcException {
        try{
            PageHelper.startPage(currentPage,pageSize);
            List<WxmResource>list=wxmResourceMapper.selectAll();
            return new PageInfo<>(list);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源列表"),e.getMessage());
        }
    }

    @Override
    public WxmResource queryById(Long id) throws DbSvcException {
        try{
            return  wxmResourceMapper.selectById(id);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(),DbSvcEnum.DB_SVC_1001.getMessage("资源"),e.getMessage());
        }
    }

    @Override
    public int add(WxmResource resource) throws DbSvcException {
        try{
            return wxmResourceMapper.insert(resource);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1002.toString(),DbSvcEnum.DB_SVC_1002.getMessage("资源"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1002.toString(),DbSvcEnum.DB_SVC_1002.getMessage("资源"),e.getMessage());
        }
    }

    @Override
    public int edit(WxmResource resource) throws DbSvcException {
        try{
            return wxmResourceMapper.updateById(resource);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1003.toString(),DbSvcEnum.DB_SVC_1003.getMessage("资源"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1003.toString(),DbSvcEnum.DB_SVC_1003.getMessage("资源"),e.getMessage());
        }
    }

    @Override
    public int delete(Long resourceId) throws DbSvcException {
        try{
            return wxmResourceMapper.deleteById(resourceId);
        }catch (Exception e){
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1004.toString(),DbSvcEnum.DB_SVC_1004.getMessage("资源"),e.getMessage(),e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1004.toString(),DbSvcEnum.DB_SVC_1004.getMessage("资源"),e.getMessage());
        }
    }
}
