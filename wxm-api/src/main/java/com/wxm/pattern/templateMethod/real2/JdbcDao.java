package com.wxm.pattern.templateMethod.real2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 抽象类，包含数据库操作的公共部分
abstract class JdbcDao<T> {
    private Connection connection;

    public JdbcDao(Connection connection) {
        this.connection = connection;
    }

    // 模板方法
    public T query(String sql, Object... params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParameters(preparedStatement, params);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return extractData(resultSet);
            }
        }
    }

    // 抽象方法，由子类实现
    protected abstract void setParameters(PreparedStatement statement, Object... parameters) throws SQLException;
    protected abstract T extractData(ResultSet resultSet) throws SQLException;

    // 可选的钩子方法
    protected void beforeQuery() {}
    protected void afterQuery() {}
}
