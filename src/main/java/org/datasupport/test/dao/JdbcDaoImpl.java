package org.datasupport.test.dao;

import org.datasupport.test.model.Circle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDaoImpl {


    public Circle getCircle(int circleId) {

        Connection conn = null;

        Circle circle = null;

        try {
            String driver = "org.apache.derby.jdbc.ClientDriver";
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM CIRCLE WHERE ID = ?");
            ps.setInt(1, circleId);

            circle = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }

            rs.close();
            ps.close();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

}
