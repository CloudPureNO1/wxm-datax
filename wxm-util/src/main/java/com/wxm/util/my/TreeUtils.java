package com.wxm.util.my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wxm.base.dto.TreeNode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/13 17:18
 * @since 1.0.0
 */
public class TreeUtils {
    public static JSONArray buildTree(JSONArray ary, String id, String pid, String child) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < ary.size(); i++) {
            JSONObject jObj = ary.getJSONObject(i);
            Object pValue = jObj.get(pid);
            boolean isRootChild = pValue == null || String.valueOf(pValue).equals("0") || String.valueOf(pValue).equalsIgnoreCase("root");
            if (isRootChild) {
                jsonArray.add(jObj);
            }
        }
        ary.removeAll(jsonArray);
        for(int j=0;j<jsonArray.size();j++){
            buildObject(jsonArray.getJSONObject(j),ary,id,pid,child);
        }

        return jsonArray;
    }

    public static JSONObject buildObject(JSONObject json, JSONArray jsonArray, String id, String pid, String child) {
        Object childValue = json.get(child);
        JSONArray childArray=new JSONArray();
        if(childValue != null){
            childArray=json.getJSONArray(child);
        }
        Object value=json.get(id);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            Object pValue = jObj.get(pid);
            if (!String.valueOf(value).equals(String.valueOf(pValue))) {
                continue;
            }
            childArray.add(jObj);
            buildObject(jObj,jsonArray,id,pid,child);
        }
       // json.remove(child);
        //json.put(child,childArray);
        return json;
    }




    public static List<TreeNode> buildTree(List<TreeNode> list, String root) {
        List<TreeNode> treeList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return treeList;
        }
        treeList = list.stream().filter(node -> !StringUtils.hasLength(node.getParentId()) || root.equals(node.getParentId())).collect(Collectors.toList());
        List<TreeNode> listLowFirst = list.stream().filter(node -> StringUtils.hasLength(node.getParentId()) && !root.equals(node.getParentId())).collect(Collectors.toList());
        return treeList.stream().map(node -> {
            return toTree(node, listLowFirst);
        }).collect(Collectors.toList());
    }

    public static TreeNode toTree(TreeNode node, List<TreeNode> list) {
        for (TreeNode n : list) {
            if (!n.getParentId().equals(node.getNodeId())) {
                continue;
            }

            node.getChildren().add(n);

            if (n.getIsLeaf()) {
                continue;
            }

            toTree(n, list);
        }


        return node;
    }

    /*    nodeId: 'Test3000',
        title: '测试功能3',
        isLeaf: false,
        icon: 'el-icon-menu',
        url: '/testRoot3',
        parent: 'root',
        children: [
        {nodeId: 'Test3001', title: '医保基金收缴3', isLeaf: true, icon: 'el-icon-s-custom', url: '/echartsIn3', parent: 'Test3000'},
        {nodeId: 'Test3002', title: '医保基金支付3', isLeaf: true, icon: 'el-icon-s-custom', url: '/charge3', parent: 'Test3000'}
              ]*/
/*
    @Getter
    @Setter
    static
    class TreeNode {
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private String nodeId;
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private String title;
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private Boolean isLeaf;
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private String icon;
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private String url;
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private String parentId;
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private List<TreeNode> children = new ArrayList<>();
    }
*/


    public static void main(String[] args) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode treeNode = new TreeNode();
        treeNode.setNodeId("1");
        treeNode.setParentId("0");
        treeNode.setTitle("系统管理");
        treeNode.setIsLeaf(false);
        list.add(treeNode);
        treeNode = new TreeNode();
        treeNode.setNodeId("2");
        treeNode.setParentId("0");
        treeNode.setTitle("外部");
        treeNode.setIsLeaf(false);
        list.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setNodeId("3");
        treeNode.setParentId("1");
        treeNode.setTitle("用户管理");
        treeNode.setIsLeaf(false);
        list.add(treeNode);
        treeNode = new TreeNode();
        treeNode.setNodeId("4");
        treeNode.setParentId("1");
        treeNode.setTitle("角色管理");
        treeNode.setIsLeaf(false);
        list.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setNodeId("5");
        treeNode.setParentId("3");
        treeNode.setTitle("用户添加");
        treeNode.setIsLeaf(false);
        list.add(treeNode);

        treeNode = new TreeNode();
        treeNode.setNodeId("6");
        treeNode.setParentId("2");
        treeNode.setTitle("百度");
        treeNode.setIsLeaf(false);
        list.add(treeNode);


        JSONArray jsonArray=buildTree(JSON.parseArray(JSON.toJSONString(list,SerializerFeature.WriteNullStringAsEmpty)),"nodeId","parentId","children");
        System.out.println("*****************************");
        System.out.println(jsonArray.toString(SerializerFeature.DisableCircularReferenceDetect));



        List<TreeNode> list1 = buildTree(list, "0");
        System.out.println(JSON.toJSONString(list1));


        jsonArray=buildTree(JSON.parseArray(JSON.toJSONString(list)),"nodeId","parentId","children");
        System.out.println("*****************************");
        System.out.println(jsonArray.toString(SerializerFeature.DisableCircularReferenceDetect));

    }
}
