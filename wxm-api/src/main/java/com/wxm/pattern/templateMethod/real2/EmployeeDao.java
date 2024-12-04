package com.wxm.pattern.templateMethod.real2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:34:35
 */
// 具体的 EmployeeDao 类
class EmployeeDao extends JdbcDao<Employee> {
    public EmployeeDao(Connection connection) {
        super(connection);
    }


    public Employee findEmployeeById(int id) throws SQLException {
        beforeQuery();
        Employee employee = query("SELECT * FROM wxm_user WHERE user_id = ?", id);
        afterQuery();
        return employee;
    }

    @Override
    protected void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }

    @Override
    protected Employee extractData(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String name = resultSet.getString("username");
            String userDesc = resultSet.getString("user_desc");
            return new Employee(id, name,userDesc);
        }
        return null;
    }
}