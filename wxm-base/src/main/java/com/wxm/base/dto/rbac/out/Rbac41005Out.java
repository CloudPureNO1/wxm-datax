package com.wxm.base.dto.rbac.out;

import com.wxm.base.dto.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/16 9:21
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rbac41005Out implements java.io.Serializable{
    List<TreeNode>list=new ArrayList<>();
    /**管理角色的资源ID*/
    List<String>listResourceId=new ArrayList<>();
}
