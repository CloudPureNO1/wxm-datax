package com.wxm.core.move;

import com.wxm.druid.entity.biz.WxmResource;

import java.util.List;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-25 15:57:08
 */
public class MoveInner extends  AbstractMoveMenu {
    @Override
    public void execute(WxmResource currentNode, WxmResource targetNode, List<WxmResource> list, List<WxmResource>listAll) {
        //  首先 更改 当前节点的父节点，
        currentNode.setParentId(targetNode.getResourceId());
//        currentNode.setIdpath(targetNode.getIdpath()+"/"+currentNode.getFunctionid());
//
//        // 如果一定的节点还有子节点，那么子节点的路径也需要重新设置
//        MoveUtil.setChildrenIdPath(currentNode,listAll);
    }


//    @Override
//    public  List<SysMenu>  excute(List<SysMenu> list, String currentId, String targetId, String type) {
//        SysMenu currentNode=list.stream().filter(item->(item.getFunctionid()+"").equals(currentId)).findFirst().orElse(null);
//        SysMenu targetNode=list.stream().filter(item->(item.getFunctionid()+"").equals(targetId)).findFirst().orElse(null);
//        Assert.notNull(currentNode,"当前节点不存在");
//        Assert.notNull(targetNode,"目标节点不存在");
//        List<SysMenu> collect = list.stream().filter(item -> item.getParentid().equals(targetNode.getFunctionid())).collect(Collectors.toList());
//        //  首先 更改 当前节点的父节点，
//        currentNode.setParentid(targetNode.getFunctionid());
//        currentNode.setIdpath(targetNode.getIdpath()+"/"+currentNode.getFunctionid());
//
//        if(collect.contains(currentNode)){
//            collect.remove(currentNode);
//        }
//        collect.add(currentNode);
//        if(!CollectionUtils.isEmpty(collect)){
//            for(int i=0;i<collect.size();i++){
//                collect.get(i).setFunorder(i+1);
//            }
//        }
//        return collect;
//    }

}
