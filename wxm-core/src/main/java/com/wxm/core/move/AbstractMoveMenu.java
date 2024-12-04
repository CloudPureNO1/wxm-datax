package com.wxm.core.move;

import com.wxm.druid.entity.biz.WxmResource;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-29 9:59:26
 */
public abstract class AbstractMoveMenu {
    private WxmResource currentNode;
    private WxmResource targetNode;
    private List<WxmResource> list;

    public List<WxmResource> move(List<WxmResource> listAll, String currentId, String targetId){
        before(listAll,currentId, targetId);
        execute(currentNode,targetNode,list,listAll);
        after();
        return list;
    }
    public abstract void execute(WxmResource currentNode,WxmResource targetNode,List<WxmResource> list,List<WxmResource> listAll);
    public void before(List<WxmResource> listAll, String currentId, String targetId){
        currentNode=listAll.stream().filter(item->item.getResourceId().equals(currentId)).findFirst().orElse(null);
        targetNode=listAll.stream().filter(item->item.getResourceId().equals(targetId)).findFirst().orElse(null);
        Assert.notNull(currentNode,"当前节点不存在");
        Assert.notNull(targetNode,"目标节点不存在");
        list = listAll.stream().filter(item -> item.getParentId().equals(targetNode.getParentId())).collect(Collectors.toList());

    }

    public void after(){
        if(list.contains(currentNode)){
            list.remove(currentNode);
        }
        list.add(list.indexOf(targetNode),currentNode);
        if(!CollectionUtils.isEmpty(list)){
            for(int i=0;i<list.size();i++){
                list.get(i).setOrderNum(i+1);
            }
        }
    }
}
