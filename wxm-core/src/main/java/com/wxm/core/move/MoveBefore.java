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
public class MoveBefore  extends AbstractMoveMenu {

//
//
//    @Override
//    public  List<SysMenu>  excute(List<SysMenu> list, String currentId, String targetId, String type) {
//        SysMenu currentNode=list.stream().filter(item->(item.getFunctionid()+"").equals(currentId)).findFirst().orElse(null);
//        SysMenu targetNode=list.stream().filter(item->(item.getFunctionid()+"").equals(targetId)).findFirst().orElse(null);
//        Assert.notNull(currentNode,"当前节点不存在");
//        Assert.notNull(targetNode,"目标节点不存在");
//        List<SysMenu> collect = list.stream().filter(item -> item.getParentid().equals(targetNode.getParentid())).collect(Collectors.toList());
//        // 如果 两个节点的父节点不一致，把当前节点的父节点设置为目标节点的父节点
//        if(!currentNode.getParentid().equals(targetNode.getParentid())){
//            currentNode.setParentid(targetNode.getParentid());
//
//            // 如果目标节点是根节点，则当前节点也设置为根节点
//            // 可能出现空指针
//            SysMenu targetParentNode = list.stream().filter(item -> (item.getFunctionid() + "").equals(targetNode.getParentid())).findFirst().orElse(null);
//            String parentIdPath="0";
//            if(targetParentNode==null||targetParentNode.getParentid()==null){
//                if(targetNode.getIdpath()!=null &&  targetNode.getIdpath().endsWith("/"+targetNode.getFunctionid())){
//                    parentIdPath=MoveUtil.replaceLastOccurrence(targetNode.getIdpath(),"/"+targetNode.getFunctionid(),"");
//                }
//            }else{
//                parentIdPath=targetParentNode.getIdpath();
//            }
//            currentNode.setIdpath(parentIdPath+"/"+currentNode.getFunctionid());
//        }
//        if(collect.contains(currentNode)){
//            collect.remove(currentNode);
//        }
//        collect.add(collect.indexOf(targetNode),currentNode);
//        if(!CollectionUtils.isEmpty(collect)){
//            for(int i=0;i<collect.size();i++){
//                collect.get(i).setFunorder(i+1);
//            }
//        }
//        return collect;
//    }

    @Override
    public void execute(WxmResource currentNode, WxmResource targetNode, List<WxmResource> list, List<WxmResource>listAll) {
        // 如果 两个节点的父节点不一致，把当前节点的父节点设置为目标节点的父节点
        if(!currentNode.getParentId().equals(targetNode.getParentId())){
            currentNode.setParentId(targetNode.getParentId());

            // 如果目标节点是根节点，则当前节点也设置为根节点
            // 可能出现空指针
//            SysMenu targetParentNode = list.stream().filter(item -> (item.getFunctionid() + "").equals(targetNode.getParentid())).findFirst().orElse(null);
//            String parentIdPath="0";
//            if(targetParentNode==null||targetParentNode.getParentid()==null){
//                if(targetNode.getIdpath()!=null &&  targetNode.getIdpath().endsWith("/"+targetNode.getFunctionid())){
//                    parentIdPath=MoveUtil.replaceLastOccurrence(targetNode.getIdpath(),"/"+targetNode.getFunctionid(),"");
//                }
//            }else{
//                parentIdPath=targetParentNode.getIdpath();
//            }
//            currentNode.setIdpath(parentIdPath+"/"+currentNode.getFunctionid());
//
//            // 如果一定的节点还有子节点，那么子节点的路径也需要重新设置
//            MoveUtil.setChildrenIdPath(currentNode,listAll);
        }
    }
}
