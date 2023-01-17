package com.wxm.service.db.master;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wxm.base.dto.rbac.in.Rbac61003In;
import com.wxm.base.dto.rbac.in.Rbac71002In;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmApi;
import com.wxm.druid.entity.master.WxmDictionary;
import com.wxm.service.db.IDbService;

import java.util.List;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/6 10:45
 * @since 1.0.0
 */
public interface IWxmDictionaryService extends IDbService, IService<WxmDictionary> {
    List<WxmDictionary>queryAll() throws DbSvcException;
    List<WxmDictionary> queryList(List<String> typeList) throws DbSvcException;


    /**
     * 条件查询接口
     * @param rbac61003In
     * @return
     * @throws DbSvcException
     */
    PageInfo<WxmDictionary> queryPageByCon(Rbac61003In rbac61003In) throws DbSvcException;
}
