package com.wxm.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.wxm.base.enums.BizSvcEnum;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import com.wxm.util.my.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/9 14:02
 * @since 1.0.0
 */
@Slf4j
public abstract class TemplateWithParams2<T, E, I> {
    public abstract IPage<?> queryPage(E e) throws DbSvcException;
    public abstract T initOut(List<I>list,long total);

    /**
     *
     * @param e 入参实体
     * @param clazzSource  查询结果源（一般指数据库查询结果的实体class)
     * @param clazzTarget  输出class (转换后的实体class)
     * @param msg  说明信息
     * @return
     * @throws DbSvcException
     * @throws BizSvcException
     */
    public final T service(E e, Class<?> clazzSource, Class<I> clazzTarget, String msg) throws DbSvcException, BizSvcException {
        try {
            IPage<?> page = this.queryPage(e);
            List<?> listSource = page.getRecords();
            List<I> list=new ArrayList<>();
            if (!CollectionUtils.isEmpty(listSource)) {
                // 单例模式
                BeanUtils instance = BeanUtils.getInstance();
                BeanCopier bc = instance.create(clazzSource, clazzTarget, true);
                list = listSource.stream().map(source -> {
                    try {
                        I i = clazzTarget.newInstance();
                        instance.copy(source, i, bc, false);
                        return i;
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }).collect(Collectors.toList());
            }
            return this.initOut(list,page.getTotal());

        } catch (Exception ex) {
            if (e instanceof DbSvcException) {
                throw (DbSvcException) ex;
            }
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage(msg), ex.getMessage(), ex);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage(msg), ex.getMessage());
        }
    }


}
