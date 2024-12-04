package com.wxm.core.move;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-26 17:05:06
 */
public class MoveUtil {
    public static String replaceLastOccurrence(String original, String toReplace, String replacement) {
        int pos = original.lastIndexOf(toReplace);
        if (pos > -1) {
            return original.substring(0, pos) + replacement + original.substring(pos + toReplace.length());
        }
        return original;
    }
//    public static void setIdPath(SysMenu currentNode, List<SysMenu> sublist, List<SysMenu>list) {
//        if(!CollectionUtils.isEmpty(sublist)){
//            sublist.forEach(item->{
//                item.setIdpath(currentNode.getIdpath()+"/"+item.getFunctionid());
//                List<SysMenu>children =  list.stream().filter(d->d.getParentid().equals(item.getFunctionid())).collect(Collectors.toList());
//                setIdPath(item,children,list);
//            });
//        }
//    }
//
//    public static void setChildrenIdPath(SysMenu currentNode,List<SysMenu>list) {
//        // 如果一定的节点还有子节点，那么子节点的路径也需要重新设置
//        List<SysMenu> children = list.stream().filter(item -> item.getParentid().equals(currentNode.getFunctionid())).collect(Collectors.toList());
//        setIdPath(currentNode,children,list);
//    }
}
