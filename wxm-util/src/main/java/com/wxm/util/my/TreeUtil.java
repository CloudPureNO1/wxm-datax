package com.wxm.util.my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxm.base.bean.TreeBean;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>树结构工具类</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/9/22 13:34
 * @since 1.0.0
 */
@Slf4j
public class TreeUtil {
    /**
     * <p>构建树形结构数据</p>
     * @param jsonArray          源数据{@link JSONArray}
     * @param parentKey          父节点key
     * @param nodeKey            节点key
     * @param childrenKey        子节点key
     * @param rootParentValue    根节点的父节点value
     * @return list              List<JSONObject> {@link List} {@link JSONObject}
     */
    public static List<JSONObject> buildTree(JSONArray jsonArray, String parentKey, String nodeKey, String childrenKey, String rootParentValue) {
        List<JSONObject> jsonList = jsonArray.toJavaList(JSONObject.class);
        return buildTreeJSONList(jsonList,parentKey,nodeKey,childrenKey,rootParentValue,null,null);
    }

    /**
     * <p>构建树形结构数据</p>
     * @param jsonArray          源数据 {@link JSONArray}
     * @param parentKey          父节点key
     * @param nodeKey            节点key
     * @param childrenKey        子节点key
     * @param rootParentValue    根节点的父节点value
     * @param nodeTypeKey        节点类型key(标识是否为叶子节点)
     * @param leafTypeValue       叶子节点值
     * @return list               List<JSONObject> {@link List} {@link JSONObject}
     */
    public static List<JSONObject> buildTree(JSONArray jsonArray, String parentKey, String nodeKey, String childrenKey, String rootParentValue,String nodeTypeKey, String leafTypeValue){
        List<JSONObject> jsonList = jsonArray.toJavaList(JSONObject.class);
        return buildTreeJSONList(jsonList,parentKey,nodeKey,childrenKey,rootParentValue,nodeTypeKey,leafTypeValue);
    }





    /**
     * <p>构建树形结构数据</p>
     * @param list               源数据 {@link List}
     * @param parentKey          父节点key
     * @param nodeKey            节点key
     * @param childrenKey        子节点key
     * @param rootParentValue    根节点的父节点value
     * @return list               List<JSONObject> {@link List} {@link JSONObject}
     */
    public static List<JSONObject> buildTree(List<?> list, String parentKey, String nodeKey, String childrenKey, String rootParentValue) {
        List<JSONObject> jsonList = toJSONList(list);
        return buildTreeJSONList(jsonList,parentKey,nodeKey,childrenKey,rootParentValue,null,null);
    }

    /**
     * <p>构建树形结构数据</p>
     * @param list                源数据 {@link List}
     * @param parentKey          父节点key
     * @param nodeKey            节点key
     * @param childrenKey        子节点key
     * @param rootParentValue    根节点的父节点value
     * @param nodeTypeKey        节点类型key(标识是否为叶子节点)
     * @param leafTypeValue       叶子节点值
     * @return list               List<JSONObject> {@link List} {@link JSONObject}
     */
    public static List<JSONObject> buildTree(List<?> list, String parentKey, String nodeKey, String childrenKey, String rootParentValue, String nodeTypeKey, String leafTypeValue) {
        List<JSONObject> jsonList = toJSONList(list);
        return buildTreeJSONList(jsonList,parentKey,nodeKey,childrenKey,rootParentValue,nodeTypeKey,leafTypeValue);
    }


    /**
     * 非常牛逼
     * nb plus
     * 优点：
     * <p>
     * 1、代码简洁，可以作为模板遇到组建树结构时直接使用；
     * <p>
     * 2、提高效率，可以不再套用循环组建树结构，如果多层，套用循环的效率是很夸张的；
     * <p>
     * 3、可以不用预先知道几层树结构，有时间树结构有几层是不确定的，这个时候我们如果用循环的话就比较被动，这个就可以根据实际的层级进行组建；
     *
     * @param list  {@link List} {@link TreeBean}
     * @return list  {@link List} {@link TreeBean}
     */
    public static List<TreeBean> buildTree(List<TreeBean> list) {
        Map<String, List<TreeBean>> map = list.stream().filter(tree -> !"0".equals(tree.getParentId())).collect(Collectors.groupingBy(tree -> tree.getParentId()));
        log.info(">>>>map:{}", JSON.toJSONString(map));
        list.forEach(tree -> tree.setChildren(map.get(tree.getNodeId())));
/*
        list.forEach(tree->{
            tree.setChildren(map.get(tree.getNodeId()));
            // {"$ref":"$[0].children[1]"}->
            log.info(">>>>>listRsAll:{}",JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
        });
*/

        return list.stream().filter(tree -> "0".equals(tree.getParentId())).collect(Collectors.toList());
    }


    /**
     * @param list  {@link List} {@link TreeBean}
     * @param leafValue 叶子节点的值
     * @return {@link List} {@link TreeBean}
     */
    public static List<TreeBean> buildTree(List<TreeBean> list, String leafValue) {
        Map<String, List<TreeBean>> map = list.stream().filter(tree -> !"0".equals(tree.getParentId())).collect(Collectors.groupingBy(tree -> tree.getParentId()));
        log.info(">>>>map:{}", JSON.toJSONString(map));
        list.stream().filter(tree -> !leafValue.equals(tree.getNodeType())).forEach(tree -> tree.setChildren(map.get(tree.getNodeId())));
        return list.stream().filter(tree -> "0".equals(tree.getParentId())).collect(Collectors.toList());
    }


    /**
     * <p>构建树形结构数据</p>
     * @param jsonList            源数据 List<JSONObject> {@link List} {@link JSONObject}
     * @param parentKey          父节点key
     * @param nodeKey            节点key
     * @param childrenKey        子节点key
     * @param rootParentValue    根节点的父节点value
     * @param nodeTypeKey        节点类型key(标识是否为叶子节点)
     * @param leafTypeValue       叶子节点值
     * @return list               List<JSONObject> {@link List} {@link JSONObject}
     */
    public static List<JSONObject> buildTreeJSONList(List<JSONObject> jsonList, String parentKey, String nodeKey, String childrenKey, String rootParentValue,String nodeTypeKey, String leafTypeValue) {
        /**
         * 如果 rootParentValue  没有提供，那么默认为 0 或 空（null,empty,whitespace）
         * 1、排除根节点
         * 2、分组
         */
        Map<String, List<JSONObject>> map = jsonList.stream().filter(node -> !isRootNode(node.getString(parentKey), rootParentValue)).collect(Collectors.groupingBy(node -> node.getString(parentKey)));
        if (org.springframework.util.StringUtils.hasLength(nodeTypeKey) && org.springframework.util.StringUtils.hasLength(leafTypeValue)) {

            /**
             * 1、排除叶子节点
             * 2、设置节点的子节点
             */
            jsonList.stream().filter(node -> !leafTypeValue.equals(node.getString(nodeTypeKey))).forEach(node -> node.put(childrenKey, map.get(node.getString(nodeKey))));
        } else {
            // 设置节点的子节点
            jsonList.forEach(node -> node.put(childrenKey, map.get(node.getString(nodeKey))));
        }
        // 返回父节点（这时候包含下面所有的节点）
        return jsonList.stream().filter(node -> isRootNode(node.getString(parentKey), rootParentValue)).collect(Collectors.toList());
    }

    /**
     * <p>构建树形结构数据</p>
     * @param jsonList            源数据 List<JSONObject> {@link List} {@link JSONObject}
     * @param parentKey          父节点key
     * @param nodeKey            节点key
     * @param childrenKey        子节点key
     * @param rootParentValue    根节点的父节点value
     * @param nodeTypeKey        节点类型key(标识是否为叶子节点)
     * @param leafTypeValue       叶子节点值
     * @param btnTypeValue        按钮节点值
     * @return list               List<JSONObject> {@link List} {@link JSONObject}
     */
    public static List<JSONObject> buildTreeJSONList(List<JSONObject> jsonList, String parentKey, String nodeKey, String childrenKey, String rootParentValue,String nodeTypeKey, String leafTypeValue,String btnTypeValue) {
        /**
         * 如果 rootParentValue  没有提供，那么默认为 0 或 空（null,empty,whitespace）
         * 1、排除根节点
         * 2、分组
         */
        Map<String, List<JSONObject>> map = jsonList.stream().filter(node -> !isRootNode(node.getString(parentKey), rootParentValue)).collect(Collectors.groupingBy(node -> node.getString(parentKey)));
        if (org.springframework.util.StringUtils.hasLength(nodeTypeKey) && org.springframework.util.StringUtils.hasLength(leafTypeValue)) {
            /**
             * 1、排除叶子节点、按钮节点
             * 2、设置节点的子节点
             */
            jsonList.stream().filter(node -> !btnTypeValue.equals(node.getString(nodeTypeKey))||!leafTypeValue.equals(node.getString(nodeTypeKey))).forEach(node -> node.put(childrenKey, map.get(node.getString(nodeKey))));
        } else {
            // 设置节点的子节点
            jsonList.forEach(node -> node.put(childrenKey, map.get(node.getString(nodeKey))));
        }
        // 返回父节点（这时候包含下面所有的节点）
        return jsonList.stream().filter(node -> isRootNode(node.getString(parentKey), rootParentValue)).collect(Collectors.toList());
    }

    /**
     * 是否为根节点
     *
     * @param parentKeyValue
     * @param rootParentValue
     * @return
     */
    private static boolean isRootNode(String parentKeyValue, String rootParentValue) {
        if (org.springframework.util.StringUtils.hasLength(rootParentValue)) {
            return rootParentValue.equals(parentKeyValue);
        }
        return !org.springframework.util.StringUtils.hasLength(parentKeyValue) || "0".equals(parentKeyValue);
    }

    /**
     * <p>List<Object> 转 List<{@link JSONObject}</p>
     * @param list
     * @return
     */
    private static List<JSONObject> toJSONList(List<?> list) {
        return list.stream().map(node -> JSON.parseObject(JSON.toJSONString(node))).collect(Collectors.toList());
    }






    public static void main(String[] args) {
        List<TreeBean> list=getData(true);
        test1(list);

        test2(list);

        test3(list);
    }

    private static void test3(List list) {
        JSONArray jsonArray=new JSONArray(list);
        List<JSONObject> rsList=buildTree(jsonArray,"parentId","nodeId","children","0");
        //List<JSONObject> rsList=buildTree(jsonArray,"parentId","nodeId","children","0","nodeType","1");
        log.info(">test3>>rsList:{}", JSON.toJSONString(rsList));
    }

    private static void test2(List<TreeBean>list){
        long time=System.currentTimeMillis();
        List<JSONObject> rsList=buildTree(list,"parentId","nodeId","children","0");
        //List<JSONObject> rsList=buildTree(list,"parentId","nodeId","children","0","nodeType","1");
        long rsTime=System.currentTimeMillis();
        log.info(">test2:time:{} ms>>rsList:{}", JSON.toJSONString(rsList),rsTime-time);

    }

    private static void test1(List<TreeBean>list){
        list=buildTree(list);
        //list = buildTree(list, "1");
        log.info(">test1>>rsList:{}", JSON.toJSONString(list));
    }

    private static List<TreeBean> getData(boolean isLeaf){
        List<TreeBean> list = new ArrayList<>();
        TreeBean tree = new TreeBean();
        tree.setNodeId("1");
        tree.setParentId("0");
        tree.setNodeName("菜单1");
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("2");
        tree.setParentId("1");
        tree.setNodeName("菜单2");
        tree.setNodeType(isLeaf?"1":null);
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("3");
        tree.setParentId("1");
        tree.setNodeName("菜单3");
        tree.setNodeType(isLeaf?"1":null);
        list.add(tree);


        tree = new TreeBean();
        tree.setNodeId("4");
        tree.setParentId("1");
        tree.setNodeName("菜单4");
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("5");
        tree.setParentId("2");
        tree.setNodeName("菜单5");
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("6");
        tree.setParentId("2");
        tree.setNodeName("菜单6");
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("7");
        tree.setParentId("3");
        tree.setNodeName("菜单7");
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("8");
        tree.setParentId("4");
        tree.setNodeName("菜单8");
        list.add(tree);

        tree = new TreeBean();
        tree.setNodeId("9");
        tree.setParentId("5");
        tree.setNodeName("菜单9");
        list.add(tree);


        tree = new TreeBean();
        tree.setNodeId("10");
        tree.setParentId("6");
        tree.setNodeName("菜单10");
        list.add(tree);
        return list;
    }

}
