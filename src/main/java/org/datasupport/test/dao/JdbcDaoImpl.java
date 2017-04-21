package org.datasupport.test.dao;

import org.datasupport.test.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcDaoImpl {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Circle getCircle(int circleId) {

        Connection conn = null;

        Circle circle = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM CIRCLE WHERE ID = ?");
            ps.setInt(1, circleId);

            circle = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return circle;
    }

    public int getCircleCount() {
        String sql = "SELECT COUNT(*) FROM CIRCLE";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public String getCircleNameById(int circleId) {
        String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, String.class);
    }

    public Circle getCircleById(int circleId) {
        String sql = "SELECT * FROM CIRCLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, new CircleMapper());
    }

    public List<Circle> getAllCircles() {
        String sql = "SELECT * FROM CIRCLE";
        return jdbcTemplate.query(sql, new CircleMapper());
    }

    public void insertCircle(Circle circle){
        String sql = "INSERT INTO CIRCLE(ID, NAME) VALUES (?, ?)";
        jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
    }

    public void insertCircleTwo(Circle circle) {
        String sql = "INSERT INTO CIRCLE(ID, NAME) VALUES (:id, :name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", circle.getId())
                .addValue("name", circle.getName());
        namedParameterJdbcTemplate.update(sql, namedParameters);

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class CircleMapper implements RowMapper<Circle> {

        public Circle mapRow(ResultSet resultSet, int i) throws SQLException {
            Circle circle = new Circle();
            circle.setId(resultSet.getInt("ID"));
            circle.setName(resultSet.getString("NAME"));
            return circle;
        }
    }

}
