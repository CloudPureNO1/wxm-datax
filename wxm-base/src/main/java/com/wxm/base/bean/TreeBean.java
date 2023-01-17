package com.wxm.base.bean;

import lombok.Data;

import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/9/22 15:06
 * @since 1.0.0
 */
@Data
public class TreeBean implements java.io.Serializable{
    /**
     * 实体类中子集合的字段名称
     */
    private String parentId;
    /**
     * 节点ID 或实体类主键
     */
    private String nodeId;
    private String nodeName;
    private String nodeUrl;
    private String nodeLevel;
    private String nodeType;
    private String nodeNum;
    /**
     * 父类id，当为0时是根节点
     */
    private List<TreeBean> children;
}
