package com.wxm.core.move;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-25 16:00:15
 */
public class MoveContext {
    private String type;

    public MoveContext(String type) {
        this.type = type;
    }

    public AbstractMoveMenu get() {
        if (MoveEnum.getName(MoveEnum.BEFORE).equals(type)) {
            return new MoveBefore();
        }
        if (MoveEnum.getName(MoveEnum.AFTER).equals(type)) {
            return new MoveAfter();
        }
        if (MoveEnum.getName(MoveEnum.INNER).equals(type)) {
            return new MoveInner();
        }
        if (MoveEnum.getName(MoveEnum.NONE).equals(type)) {
            return new MoveNone();
        }
        return new MoveNone();
    }
}
