package com.wxm.base.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>树形结构</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/16 9:20
 * @since 1.0.0
 */
@Data
public class TreeNode {
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
