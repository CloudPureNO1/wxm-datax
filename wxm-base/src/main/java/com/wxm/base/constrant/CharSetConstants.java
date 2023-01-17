package com.wxm.base.constrant;

/**
 * 字符集格式
 * @author 王森明
 * @date 2020/11/5 10:48
 * @see String
 * @since 1.0.0
 */
public class CharSetConstants {
    /**
     * 对于单个字符：ISO-8859-1单字节编码，GBK双字节编码，UTF-8三字节编码；
     */
    public static final String ISO88591="ISO-8859-1";
    /**
     * GB2312的扩展，完整包含了GB2312的所有内容。
     */
    public static final String GBK="GBK";
    /**
     * GB2312：中文字符集，包含ASCII字符集。ASCII部分用单字节表示，剩余部分用双字节表示。
     */
    public static final String GB2312="GB2312";
    /**
     * GB18030：GBK字符集的超集，常叫大汉字字符集，也叫CJK（Chinese，Japanese，Korea）字符集，包含了中、日、韩三国语言中的所有字符。
     */
    public static final String GB18030="GB18030";
    /**
     * Unicode 也叫统一字符集，它包含了几乎世界上所有的已经发现且需要使用的字符（如中文、日文、英文、德文等）。
     */
    public static final String Unicode="Unicode";
    /**
     * UTF-8(Unicode字符集的编码方式)
     */
    public static final String UTF8="UTF-8";
    /**
     * UTF-16(Unicode字符集的编码方式)
     */
    public static final String UTF16="UTF-16";
    /**
     * UTF-32(Unicode字符集的编码方式)
     */
    public static final String UTF32="UTF-32";
    /**
     * ASCII：早期的计算机系统只能处理英文，所以ASCII也就成为了计算机的缺省字符集，包含了英文所需要的所有字符。
     */
    public static final String ASCII="ASCII";
}
