package com.wxm.pattern.templateMethod.real2;

import com.alibaba.fastjson.JSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:35:21
 */
public class JdbcDaoTest {
        public static void main(String[] args) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://106.54.34.92:8722/wxm", "wxm", "wxm")) {
                EmployeeDao dao = new EmployeeDao(connection);
                Employee employee = dao.findEmployeeById(10001);
                System.out.println(JSON.toJSONString(employee));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
