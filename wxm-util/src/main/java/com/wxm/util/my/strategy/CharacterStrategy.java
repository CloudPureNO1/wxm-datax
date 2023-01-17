package com.wxm.util.my.strategy;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/6/10 14:59
 * @since 1.0.0
 */
public class CharacterStrategy implements Strategy<Character> {
    @Override
    public Character parseToObject(String value) {
        return null;
    }

    @Override
    public String transToString(Character value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return value.toString();
        }
    }
}
