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
public class MoveNone extends AbstractMoveMenu {

    @Override
    public void execute(WxmResource currentNode, WxmResource targetNode, List<WxmResource> list, List<WxmResource>listAll) {
        System.out.println("不执行");
    }

    @Override
    public void before(List<WxmResource> listAll, String currentId, String targetId) {
        System.out.println("不执行");
    }

    @Override
    public void after() {
        System.out.println("不执行");
    }
}
