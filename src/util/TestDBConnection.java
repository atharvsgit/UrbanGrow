package util;

import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to connect!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
