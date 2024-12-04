package com.wxm.util.my.code;


import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 加密properties 指定配置
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-16 15:13:37
 */
public class EncryptPropertiesUtil {
    /**
     * 元数据 脱敏列表
     */
    private static final String PROPERTY_KEY = "rs.config.meta.sensitive.list";
    /**
     * key,value之间的分隔符
     */
    private static final String SPLIT_CHAR = "=";
    /**
     * key的分隔符
     */
    private static final String SPLIT_CHAR_OF_KEY = "\\.";
    /**
     * key的后缀,用于指定加密的key
     */
    private static final String KEY_SUFFIX = "and";
    /**
     * 文件备份时间
     */
    private static final String FILE_BAK_TIME = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    /**
     * 加密解密类key
     */
    private static final String ENCRYPT_CLASS_KEY = "rs.config.meta.sensitive.encrypt-class";
    /**
     * 加密方法key
     */
    private static final String ENCRYPT_METHOD_KEY = "rs.config.meta.sensitive.encrypt-method";
    /**
     * 解密方法key
     */
    private static final String DECRYPT_METHOD_KEY = "rs.config.meta.sensitive.decrypt-method";


    public static void main(String[] args) throws Exception {
//        encryptFile();
        encryptFileYml();
    }


    /**
     * 加密编辑好的文件
     * 默认是元数据文件：application-rs.properties
     *
     * @return void
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-16 17:41
     * @version 1.0.0
     **/
    public static void encryptFile() {
        try {
            Path projectRoot = Paths.get(System.getProperty("user.dir"));
            Path absolutePath = projectRoot.toAbsolutePath();
            System.out.println("Current project root: " + absolutePath);
            String filePath = "wxm-util\\src\\main\\resources\\application-rs.properties";
            String filePathBak = "wxm-util\\src\\main\\resources\\application-rs-bak" + FILE_BAK_TIME + ".properties";
            copyFile(absolutePath, filePath, filePathBak);
            modifyFile(absolutePath, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密编辑好的文件
     * 默认是元数据文件：application-rs.properties
     *
     * @return void
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-16 17:41
     * @version 1.0.0
     **/
    public static void encryptFileYml() {
        try {
            Path projectRoot = Paths.get(System.getProperty("user.dir"));
            Path absolutePath = projectRoot.toAbsolutePath();
            System.out.println("Current project root: " + absolutePath);
            String filePath = "wxm-util\\src\\main\\resources\\application-rs.yml";
            String filePathBak = "wxm-util\\src\\main\\resources\\application-rs-bak" + FILE_BAK_TIME + ".yml";
            copyFile(absolutePath, filePath, filePathBak);
            modifyFileYml(absolutePath, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AES加密
     *
     * @param value
     * @param lines
     * @return java.lang.String
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-16 17:39
     * @version 1.0.0
     **/
    public static String modifyValue(String value, List<String> lines) throws Exception {
        System.out.println("开始加密：" + value);

        if (CollectionUtils.isEmpty(lines)) {
            return encodeAES(value);
        }
        List<String> listClass = lines.stream().filter(item -> item.startsWith(ENCRYPT_CLASS_KEY)).collect(Collectors.toList());
        List<String> listEncrptMethod = lines.stream().filter(item -> item.startsWith(ENCRYPT_METHOD_KEY)).collect(Collectors.toList());
        List<String> listDecrptMethod = lines.stream().filter(item -> item.startsWith(DECRYPT_METHOD_KEY)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(listClass) || CollectionUtils.isEmpty(listEncrptMethod) || CollectionUtils.isEmpty(listDecrptMethod)) {
            return encodeAES(value);
        }
        String[] splitClass = listClass.get(0).split(SPLIT_CHAR);
        String[] splitEncrptMethod = listEncrptMethod.get(0).split(SPLIT_CHAR);
        String[] splitDecrptMethod = listDecrptMethod.get(0).split(SPLIT_CHAR);
        if (splitClass.length < 2 || splitEncrptMethod.length < 2 || splitDecrptMethod.length < 2) {
            return encodeAES(value);
        }

        String className = splitClass[1];
        String encrptMethodName = splitEncrptMethod[1];
        String decrptMethodName = splitDecrptMethod[1];
        try {
            Class.forName(className).getDeclaredMethod(decrptMethodName, String.class).invoke(null, value);
        } catch (Exception e) {
            // 不能解密的，才进行加密
            Object invokeEncode = Class.forName(className).getDeclaredMethod(encrptMethodName, String.class).invoke(null, value);
            System.out.println("4加密完成:" + invokeEncode);
            return (String) invokeEncode;
        }
        return value;
    }


    /**
     * AES加密
     *
     * @param value
     * @param encryption
     * @return java.lang.String
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-16 17:39
     * @version 1.0.0
     **/
    public static String modifyValueYml(String value, Map<String, String> encryption) throws Exception {
        System.out.println("开始加密：" + value);

        if (encryption == null || encryption.isEmpty()) {
            return encodeAES(value);
        }

        String className = encryption.get("class");
        String encrptMethodName = encryption.get("encrypt_method");
        String decrptMethodName = encryption.get("decrypt_method");

        if (!StringUtils.hasText(className) || !StringUtils.hasText(encrptMethodName) || !StringUtils.hasText(decrptMethodName)) {
            return encodeAES(value);
        }

        try {
            Class.forName(className).getDeclaredMethod(decrptMethodName, String.class).invoke(null, value);
        } catch (Exception e) {
            // 不能解密的，才进行加密
            Object invokeEncode = Class.forName(className).getDeclaredMethod(encrptMethodName, String.class).invoke(null, value);
            System.out.println("4加密完成:" + invokeEncode);
            return (String) invokeEncode;
        }
        return value;
    }

    /**
     * AES加密
     *
     * @param value
     * @return
     * @throws Exception
     */
    private static String encodeAES(String value) throws Exception {
        try {
            AESUtil.decrypt(value);
        } catch (Exception e) {
            System.out.println(">>>>>>>加密完成");
            // 不能解密的，才进行加密
            return AESUtil.encrypt(value);
        }
        return value;
    }


    /**
     * 修改文件
     *
     * @return void
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-16 17:40
     * @version 1.0.0
     **/
    public static void modifyFile(Path absolutePath, String filePath) throws Exception {
        System.out.println("开始修改文件");
        String originalPath = absolutePath + "\\" + filePath;
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(originalPath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // 加密
        for (int i = 0; i < lines.size(); i++) {
            String str = lines.get(i);
            if (!str.trim().startsWith(PROPERTY_KEY)) {
                continue;
            }
            String[] data = str.trim().split(SPLIT_CHAR);
            if (data.length != 2) {
                continue;
            }
            String key = data[0].trim();
            String[] props = key.split(SPLIT_CHAR_OF_KEY);
            if (props.length < 2 || !props[props.length - 1].equals(KEY_SUFFIX)) {
                continue;
            }
            String value = data[1].trim();
            str = key + "=" + modifyValue(value, lines);
            lines.set(i, str);
        }

        // 将修改后的内容写回文件
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write("#========================================================================================================");
            writer.newLine();
            writer.write("# 采用 com.insigma.soft.bigdata.controller.base.config.encrypt.EncryptPropertiesUtil 进行加密");
            writer.newLine();
            writer.write("# 备份文件：application-rs-bak" + FILE_BAK_TIME + ".properties");
            writer.newLine();
            writer.write("# 操作人员：" + System.getProperty("user.name"));
            writer.newLine();
            writer.write("# 更新时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            writer.newLine();
            writer.write("#========================================================================================================");
            writer.newLine();
            writer.newLine();
            writer.newLine();

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("文件修改结束");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    public static void modifyFileYml(Path absolutePath, String filePath) throws Exception {
        // 指定YAML文件路径
        String yamlFilePath = absolutePath + "\\" + filePath;

        // 读取YAML文件
        Map<String, Object> yamlMap = loadYaml(yamlFilePath);

        // 修改sensitive_list下所有的and值
        modifySensitiveList(yamlMap);

        // 将修改后的数据写回文件
        saveYaml(yamlFilePath, yamlMap);
    }

    private static Map<String, Object> loadYaml(String filePath) throws IOException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(filePath);
        return yaml.load(inputStream);
    }

    private static void modifySensitiveList(Map<String, Object> yamlMap) throws Exception {

        // 访问encryption部分的数据
        Map<String, String> encryption = yamlMap.get("encryption") == null ? null : (Map<String, String>) yamlMap.get("encryption");
        if (yamlMap.containsKey("sensitive")) {
            List<Map<String, Object>> sensitiveList = (List<Map<String, Object>>) yamlMap.get("sensitive");
            for (Map<String, Object> item : sensitiveList) {
                if (item.containsKey("and")) {
                    String value = (String) item.get("and");
                    item.put("and", modifyValueYml(value, encryption));
                }
            }
        }
    }

    private static void saveYaml(String filePath, Map<String, Object> yamlMap) throws IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true); // 这个选项可以让输出更美观，可选

        Yaml yaml = new Yaml(options); // 创建Yaml实例并传入options

        Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
        yaml.dump(yamlMap, writer);
        writer.close();
    }


    /**
     * 复制文件（修改方法之前）
     *
     * @param absolutePath
     * @param filePath
     * @param filePathBak
     * @return void
     * @throws
     * @Author 王森明-wangsm
     * @Date 2024-07-16 17:47
     * @version 1.0.0
     **/
    public static void copyFile(Path absolutePath, String filePath, String filePathBak) {
        System.out.println("开始备份文件");
        String originalPath = absolutePath + "\\" + filePath;
        String newPath = absolutePath + "\\" + filePathBak;

        try (BufferedReader reader = new BufferedReader(new FileReader(originalPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // 将行写入新文件
                writer.write(line);
                writer.newLine();
            }

            System.out.println("File has been copied with comments and modified successfully.");
            System.out.println("文件备份完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
