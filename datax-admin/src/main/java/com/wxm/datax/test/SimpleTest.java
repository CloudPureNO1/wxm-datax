package com.wxm.datax.test;

import com.wxm.datax.utils.DataXUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/26 11:23
 * @since 1.0.0
 */
@Slf4j
public class SimpleTest {
    public static void main(String [] args){
        String jsonFilePath="M:\\wxm-datax\\datax-jobs\\mysql2mysql-dis_aa01.json";
        String dataXHomePath="M:\\wxm-datax\\datax-home";
        DataXUtil dx = new DataXUtil();
        dx.start(jsonFilePath, dataXHomePath);

        log.info(">>>>>>>>>IK>>>>>");
        log.info(">>>>>>>>>success>>>>>");
    }
}
