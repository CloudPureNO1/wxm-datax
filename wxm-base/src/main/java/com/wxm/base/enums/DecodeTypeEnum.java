package com.wxm.base.enums;

/**
 * @ClassName com.wangxm.mtms.base.enums.EncryptionTypeEnum
 * @Description 加密算法类型
 * @Author 王森明-${wangsm}
 * @DateTime 2020/11/4 14:09 十一月 星期三
 * @Version V1.0.0
 * @Copyright: 2020 github.com/CloudPureNO1. All rights reserved.
 */
public  enum DecodeTypeEnum {
    /**
     * 明文
     */
    PLAIN,

    /**
     * MD5 用的是 哈希函数，它的典型应用是对一段信息产生 信息摘要，以 防止被篡改。严格来说，MD5 不是一种 加密算法 而是 摘要算法。无论是多长的输入，MD5 都会输出长度为 128bits 的一个串 (通常用 16 进制 表示为 32 个字符)。
     */
    MD5,

    /**
     * SHA1 是和 MD5 一样流行的 消息摘要算法，然而 SHA1 比 MD5 的 安全性更强。对于长度小于 2 ^ 64 位的消息，SHA1 会产生一个 160 位的 消息摘要。基于 MD5、SHA1 的信息摘要特性以及 不可逆 (一般而言)，可以被应用在检查 文件完整性 以及 数字签名 等场景。
     */
    SHA1,

    /**
     * HMAC 是密钥相关的 哈希运算消息认证码（Hash-based Message Authentication Code），HMAC 运算利用 哈希算法 (MD5、SHA1 等)，以 一个密钥 和 一个消息 为输入，生成一个 消息摘要 作为 输出。 HMAC 发送方 和 接收方 都有的 key 进行计算，而没有这把 key 的第三方，则是 无法计算 出正确的 散列值的，这样就可以 防止数据被篡改。HMAC 算法实例在 多线程环境 下是 不安全的。但是需要在 多线程访问 时，进行同步的辅助类，使用 ThreadLocal 为 每个线程缓存 一个实例可以避免进行锁操作。
     */
    HMAC,

    /**
     * DES 加密算法是一种 分组密码，以 64 位为 分组对数据 加密，它的 密钥长度 是 56 位，加密解密 用 同一算法。
     * DES 加密算法是对 密钥 进行保密，而 公开算法，包括加密和解密算法。这样，只有掌握了和发送方 相同密钥 的人才能解读由 DES加密算法加密的密文数据。因此，破译 DES 加密算法实际上就是 搜索密钥的编码。对于 56 位长度的 密钥 来说，如果用 穷举法 来进行搜索的话，其运算次数为 2 ^ 56 次。
     * ,//是基于 DES 的 对称算法，对 一块数据 用 三个不同的密钥 进行 三次加密，强度更高。
     */
    DES,

    /**
     * AES 加密算法是密码学中的 高级加密标准，该加密算法采用 对称分组密码体制，密钥长度的最少支持为 128 位、 192 位、256 位，分组长度 128 位，算法应易于各种硬件和软件实现。这种加密算法是美国联邦政府采用的 区块加密标准。
     * AES 本身就是为了取代 DES 的，AES 具有更好的 安全性、效率 和 灵活性。
     */
    AES128,
    AES192,
    /**
     * RSA 加密算法是目前最有影响力的 公钥加密算法，并且被普遍认为是目前 最优秀的公钥方案 之一。RSA 是第一个能同时用于 加密 和 数字签名 的算法，它能够 抵抗 到目前为止已知的 所有密码攻击，已被 ISO 推荐为公钥数据加密标准。
     * RSA 加密算法 基于一个十分简单的数论事实：将两个大 素数 相乘十分容易，但想要对其乘积进行 因式分解 却极其困难，因此可以将 乘积 公开作为 加密密钥。
     */
    RSA,

    /**
     * ECC 也是一种 非对称加密算法，主要优势是在某些情况下，它比其他的方法使用 更小的密钥，比如 RSA 加密算法，提供 相当的或更高等级 的安全级别。不过一个缺点是 加密和解密操作 的实现比其他机制 时间长 (相比 RSA 算法，该算法对 CPU 消耗严重)。
     */
    ECC,

    BASE64,

    数字签名,


    /**
     * 常见加密算法编辑
     * DES（Data Encryption Standard）：对称算法，数据加密标准，速度较快，适用于加密大量数据的场合；
     * 3DES（Triple DES）：是基于DES的对称算法，对一块数据用三个不同的密钥进行三次加密，强度更高；
     * RC2和RC4：对称算法，用变长密钥对大量数据进行加密，比 DES 快；
     * IDEA（International Data Encryption Algorithm）国际数据加密算法，使用 128 位密钥提供非常强的安全性；
     * RSA：由 RSA 公司发明，是一个支持变长密钥的公共密钥算法，需要加密的文件块的长度也是可变的，非对称算法； 算法如下：
     * 首先, 找出三个数, p, q, r,
     * 其中 p, q 是两个不相同的质数, r 是与 (p-1)(q-1) 互为质数的数。
     * p, q, r 这三个数便是 private key。
     * 接着, 找出 m, 使得 rm == 1 mod (p-1)(q-1).....
     * 这个 m 一定存在, 因为 r 与 (p-1)(q-1) 互质, 用辗转相除法就可以得到了。
     * 再来, 计算 n = pq.......
     * m, n 这两个数便是 public key
     * DSA（Digital Signature Algorithm）：数字签名算法，是一种标准的 DSS（数字签名标准），严格来说不算加密算法；
     * AES（Advanced Encryption Standard）：高级加密标准，对称算法，是下一代的加密算法标准，速度快，安全级别高，在21世纪AES 标准的一个实现是 Rijndael 算法。 [4]
     * BLOWFISH，它使用变长的密钥，长度可达448位，运行速度很快；
     * MD5：严格来说不算加密算法，只能说是摘要算法；
     * 对MD5算法简要的叙述可以为：MD5以512位分组来处理输入的信息，且每一分组又被划分为16个32位子分组，经过了一系列的处理后，算法的输出由四个32位分组组成，将这四个32位分组级联后将生成一个128位散列值。
     * 在MD5算法中，首先需要对信息进行填充，使其字节长度对512求余的结果等于448。因此，信息的字节长度（Bits Length）将被扩展至N*512+448，即N*64+56个字节（Bytes），N为一个正整数。填充的方法如下，在信息的后面填充一个1和无数个0，直到满足上面的条件时才停止用0对信息的填充。然后，在这个结果后面附加一个以64位二进制表示的填充前信息长度。经过这两步的处理，如今信息字节长度=N*512+448+64=(N+1)*512，即长度恰好是512的整数倍。这样做的原因是为满足后面处理中对信息长度的要求。（可参见MD5算法词条）
     * PKCS:The Public-Key Cryptography Standards (PKCS)是由美国RSA数据安全公司及其合作伙伴制定的一组公钥密码学标准，其中包括证书申请、证书更新、证书作废表发布、扩展证书内容以及数字签名、数字信封的格式等方面的一系列相关协议。
     * SSF33，SSF28，SCB2(SM1)：国家密码局的隐蔽不公开的商用算法，在国内民用和商用的，除这些都不容许使用外，其他的都可以使用；
     */
}
