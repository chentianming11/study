package com.chen.study.design.pattern.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object[] params) {
        try {
            //1、获取连接
            Connection conn = this.getConnection();
            //2、创建语句集
            PreparedStatement pstm = this.createPrepareStatement(conn, sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm, params);
            //4、处理结果集
            List<T> result = this.paresResultSet(rs, rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStatement(pstm);
            //7、关闭连接
            this.closeConnection(conn);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private final void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    private final void closeStatement(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    private final void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    protected <T> List<T> paresResultSet(ResultSet rs, RowMapper<T> rowMapper) throws Exception {
        List<T> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    private final ResultSet executeQuery(PreparedStatement pstm, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i, params[i]);
        }
        return pstm.executeQuery();

    }

    private final PreparedStatement createPrepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private final Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
