package com.wxm.service.db.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac61003In;
import com.wxm.base.enums.DbSvcEnum;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.biz.WxmDictionary;
import com.wxm.druid.mapper.biz.WxmDictionaryMapper;
import com.wxm.service.db.biz.IWxmDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:50
 * @since 1.0.0
 */
@Slf4j
@Service("wxmDictionaryService")
public class WxmDictionaryService  extends ServiceImpl<WxmDictionaryMapper, WxmDictionary> implements IWxmDictionaryService {
    private final WxmDictionaryMapper wxmDictionaryMapper;

    public WxmDictionaryService(WxmDictionaryMapper wxmDictionaryMapper) {
        this.wxmDictionaryMapper = wxmDictionaryMapper;
    }

    @Override
    public List<WxmDictionary> queryAll() throws DbSvcException {
        try {
            return wxmDictionaryMapper.selectAll();
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("字典列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("字典列表"),e.getMessage());
        }
    }

    @Override
    public List<WxmDictionary> queryList(List<String> typeList) throws DbSvcException {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("dict_status", "1");
            queryWrapper.in("dict_type", typeList);
            queryWrapper.orderByAsc(true, Arrays.asList("dict_type","order_num"));
            return wxmDictionaryMapper.selectList(queryWrapper);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("字典列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("字典列表"));
        }
    }

    @Override
    public PageInfo<WxmDictionary> queryPageByCon(Rbac61003In rbac61003In) throws DbSvcException {
        try {
            PageHelper.startPage(rbac61003In.getCurrentPage(),rbac61003In.getPageSize());
            Long dictId=StringUtils.hasLength(rbac61003In.getDictId())? Long.valueOf(rbac61003In.getDictId()):null;
            List<WxmDictionary>list=wxmDictionaryMapper.selectByCon(dictId,rbac61003In.getDictType(),rbac61003In.getDictStatus(),rbac61003In.getDictLabel());
            return new PageInfo<>(list);
        } catch (Exception e) {
            log.error("【{}】{}:{}", DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("字典列表"), e.getMessage(), e);
            throw new DbSvcException(DbSvcEnum.DB_SVC_1001.toString(), DbSvcEnum.DB_SVC_1001.getMessage("字典列表"));
        }
    }
}
