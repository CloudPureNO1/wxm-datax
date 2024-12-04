package com.wxm.service.biz.sys;

import com.wxm.base.dto.rbac.in.*;
import com.wxm.base.dto.rbac.out.*;
import com.wxm.base.exception.BizSvcException;
import com.wxm.base.exception.DbSvcException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>权限管理系统</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/8 13:35
 * @since 1.0.0
 */
@Validated
public interface IBizRbacService {
    /**
     * 用户列表查询
     * @param rbac11001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    List<Rbac11001Out> service11001(@Valid Rbac11001In rbac11001In) throws BizSvcException,DbSvcException;
    /**
     * 用户列表查询
     * @param rbac11002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac11002Out service11002(@Valid Rbac11002In rbac11002In) throws BizSvcException,DbSvcException;
    /**
     * 用户列表查询
     * @param rbac11003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac11003Out service11003(@Valid Rbac11003In rbac11003In) throws BizSvcException,DbSvcException;
    /**
     * 用户新增
     * @param rbac12001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac12001Out service12001(@Valid Rbac12001In rbac12001In) throws BizSvcException,DbSvcException;
    /**
     * 用户修改
     * @param rbac13001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac13001Out service13001(@Valid Rbac13001In rbac13001In) throws BizSvcException,DbSvcException;
    /**
     * 用户删除
     * @param rbac14001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac14001Out service14001(@Valid Rbac14001In rbac14001In) throws BizSvcException,DbSvcException;
    /**
     * 用户删除(批量)
     * @param rbac14002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac14002Out service14002(@Valid Rbac14002In rbac14002In) throws BizSvcException,DbSvcException;

    /**
     * 用户组列表查询
     * @param rbac21001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    List<Rbac21001Out> service21001(@Valid Rbac21001In rbac21001In) throws BizSvcException,DbSvcException;
    /**
     * 用户组列表查询(分页查询）
     * @param rbac21002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac21002Out service21002(@Valid Rbac21002In rbac21002In) throws BizSvcException,DbSvcException;
    /**
     * 新增用户组
     * @param rbac22001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac22001Out service22001(@Valid Rbac22001In rbac22001In) throws BizSvcException,DbSvcException;
    /**
     * 给用户组添加用户
     * @param rbac22002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac22002Out service22002(@Valid Rbac22002In rbac22002In) throws BizSvcException,DbSvcException;
    /**
     * 给用户组添加角色
     * @param rbac22003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac22003Out service22003(@Valid Rbac22003In rbac22003In) throws BizSvcException,DbSvcException;

    /**
     * 编辑用户组
     * @param rbac23001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac23001Out service23001(@Valid Rbac23001In rbac23001In) throws BizSvcException,DbSvcException;

    /**
     * 删除用户组
     * @param rbac24001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac24001Out service24001(@Valid Rbac24001In rbac24001In) throws BizSvcException,DbSvcException;
    /**
     * 移除用户组中的用户
     * @param rbac24002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac24002Out service24002(@Valid Rbac24002In rbac24002In) throws BizSvcException,DbSvcException;
    /**
     * 移除用户组中的角色
     * @param rbac24003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac24003Out service24003(@Valid Rbac24003In rbac24003In) throws BizSvcException,DbSvcException;
    /**
     * 用户删除（批量）
     * @param rbac24004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac24004Out service24004(@Valid Rbac24004In rbac24004In) throws BizSvcException,DbSvcException;

    /**
     * 角色列表查询
     * @param rbac31001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    List<Rbac31001Out> service31001(@Valid Rbac31001In rbac31001In) throws BizSvcException,DbSvcException;

    /**
     * 角色列表查询(分页)
     * @param rbac31002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac31002Out service31002(@Valid Rbac31002In rbac31002In) throws BizSvcException,DbSvcException;
    /**
     * 角色列表查询(分页)
     * @param rbac31003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac31003Out service31003(@Valid Rbac31003In rbac31003In) throws BizSvcException,DbSvcException;

    /**
     * 获取登录用户的角色
     * @param rbac31004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac31004Out service31004(@Valid Rbac31004In rbac31004In) throws BizSvcException;

    /**
     * 角色新增
     * @param rbac32001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac32001Out service32001(@Valid Rbac32001In rbac32001In) throws BizSvcException,DbSvcException;
    /**
     * 编辑/新增 角色的资源
     * @param rbac32002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac32002Out service32002(@Valid Rbac32002In rbac32002In) throws BizSvcException,DbSvcException;

    /**
     * 给角色添加权限
     * @param rbac32003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac32003Out service32003(@Valid Rbac32003In rbac32003In) throws BizSvcException,DbSvcException;

    /**
     * 给角色添加接口
     * @param rbac32004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac32004Out service32004(@Valid Rbac32004In rbac32004In) throws BizSvcException,DbSvcException;


    /**
     * 编辑/新增 角色的权限
     * @param rbac32005In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac32005Out service32005(@Valid Rbac32005In rbac32005In) throws BizSvcException,DbSvcException;


    /**
     * 编辑/新增 角色的接口
     * @param rbac32006In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac32006Out service32006(@Valid Rbac32006In rbac32006In) throws BizSvcException,DbSvcException;

    /**
     * 角色修改
     * @param rbac33001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac33001Out service33001(@Valid Rbac33001In rbac33001In) throws BizSvcException,DbSvcException;



    /**
     * 角色删除
     * @param rbac34001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac34001Out service34001(@Valid Rbac34001In rbac34001In) throws BizSvcException,DbSvcException;

    /**
     * 移除角色中的权限
     * @param rbac34002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac34002Out service34002(@Valid Rbac34002In rbac34002In) throws BizSvcException,DbSvcException;
    /**
     * 移除角色中的接口
     * @param rbac34003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac34003Out service34003(@Valid Rbac34003In rbac34003In) throws BizSvcException,DbSvcException;
    /**
     * 删除角色（批量)
     * @param rbac34004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac34004Out service34004(@Valid Rbac34004In rbac34004In) throws BizSvcException,DbSvcException;


    /**
     * 资源列表查询
     * @param rbac41001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    List<Rbac41001Out> service41001(@Valid Rbac41001In rbac41001In) throws BizSvcException,DbSvcException;


    /**
     * 资源列表查询(分页)
     * @param rbac41002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac41002Out service41002(@Valid Rbac41002In rbac41002In) throws BizSvcException,DbSvcException;

    /**
     * 资源列表查询(树形结构)
     * @param rbac41003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac41003Out service41003(@Valid Rbac41003In rbac41003In) throws BizSvcException,DbSvcException;


    /**
     * 资源查询（根据Id)
     * @param rbac41004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac41004Out service41004(@Valid Rbac41004In rbac41004In) throws BizSvcException,DbSvcException;


    /**
     * 资源列表查询(树形结构)
     * @param rbac41005In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac41005Out service41005(@Valid Rbac41005In rbac41005In) throws BizSvcException,DbSvcException;

    /**
     * 资源列表查询(树形结构)（根据用户名）
     * @param rbac41006In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac41006Out service41006(@Valid Rbac41006In rbac41006In) throws BizSvcException,DbSvcException;

    /**
     * 资源新增
     * @param rbac42001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac42001Out service42001(@Valid Rbac42001In rbac42001In) throws BizSvcException,DbSvcException;
    /**
     * 资源修改
     * @param rbac43001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac43001Out service43001(@Valid Rbac43001In rbac43001In) throws BizSvcException,DbSvcException;
    /**
     * 资源删除
     * @param rbac44001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac44001Out service44001(@Valid Rbac44001In rbac44001In) throws BizSvcException,DbSvcException;



    /**
     * 权限列表查询
     * @param rbac51001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    List<Rbac51001Out> service51001(@Valid Rbac51001In rbac51001In) throws BizSvcException,DbSvcException;


    /**
     * 权限列表查询(分页)
     * @param rbac51002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac51002Out service51002(@Valid Rbac51002In rbac51002In) throws BizSvcException,DbSvcException;

    /**
     * 权限列表查询(分页),条件
     * @param rbac51003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac51003Out service51003(@Valid Rbac51003In rbac51003In) throws BizSvcException,DbSvcException;

    /**
     * 获取当前登录用户的权限列表
     * @param rbac51004In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac51004Out service51004(@Valid Rbac51004In rbac51004In) throws BizSvcException,DbSvcException;


    /**
     * 获取所有权限和已经授权的权限
     * @param rbac51005In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac51005Out service51005(@Valid Rbac51005In rbac51005In) throws BizSvcException,DbSvcException;



    /**
     * 权限新增
     * @param rbac52001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac52001Out service52001(@Valid Rbac52001In rbac52001In) throws BizSvcException,DbSvcException;





    /**
     * 权限修改
     * @param rbac53001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac53001Out service53001(@Valid Rbac53001In rbac53001In) throws BizSvcException,DbSvcException;


    /**
     * 权限删除
     * @param rbac54001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac54001Out service54001(@Valid Rbac54001In rbac54001In) throws BizSvcException,DbSvcException;
    /**
     * 权限删除(批量)
     * @param rbac54002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac54002Out service54002(@Valid Rbac54002In rbac54002In) throws BizSvcException,DbSvcException;

    /**
     * 字典列表查询
     * @param rbac61001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
     List<Rbac61001Out> service61001(@Valid Rbac61001In rbac61001In) throws BizSvcException, DbSvcException;
    /**
     * 字典列表查询
     * @param rbac61002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    List<Rbac61002Out> service61002(@Valid Rbac61002In rbac61002In) throws BizSvcException, DbSvcException;

    /**
     * 字典列表查询 分页条件查询
     * @param rbac61003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac61003Out service61003(@Valid Rbac61003In rbac61003In) throws BizSvcException, DbSvcException;

    /**
     * 字典新增
     * @param rbac62001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac62001Out service62001(@Valid Rbac62001In rbac62001In) throws BizSvcException, DbSvcException;

    /**
     * 字典编辑
     * @param rbac63001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac63001Out service63001(@Valid Rbac63001In rbac63001In) throws BizSvcException, DbSvcException;

    /**
     * 字典删除
     * @param rbac64001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac64001Out service64001(@Valid Rbac64001In rbac64001In) throws BizSvcException, DbSvcException;

    /**
     * 字典批量删除
     * @param rbac64002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac64002Out service64002(@Valid Rbac64002In rbac64002In) throws BizSvcException, DbSvcException;

    /**
     * 接口列表查询（分页，条件）
     * @param rbac71002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac71002Out service71002(@Valid Rbac71002In rbac71002In) throws BizSvcException, DbSvcException;
    /**
     * 接口列表查询（分页，条件）
     * @param rbac71003In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac71003Out service71003(@Valid Rbac71003In rbac71003In) throws BizSvcException, DbSvcException;

    /**
     *  获取所有接口和已经授权的接口
     * @param rbac71005In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac71005Out service71005(@Valid Rbac71005In rbac71005In) throws BizSvcException, DbSvcException;


    /**
     * 接口新增
     * @param rbac72001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac72001Out service72001(@Valid Rbac72001In rbac72001In) throws BizSvcException, DbSvcException;


    /**
     * 接口修改
     * @param rbac73001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac73001Out service73001(@Valid Rbac73001In rbac73001In) throws BizSvcException, DbSvcException;

    /**
     * 接口删除
     * @param rbac74001In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac74001Out service74001(@Valid Rbac74001In rbac74001In) throws BizSvcException,DbSvcException;
    /**
     * 接口删除(批量)
     * @param rbac74002In
     * @return
     * @throws BizSvcException
     * @throws DbSvcException
     */
    Rbac74002Out service74002(@Valid Rbac74002In rbac74002In) throws BizSvcException,DbSvcException;
}
