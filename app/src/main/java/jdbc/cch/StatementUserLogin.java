package jdbc.cch;

import java.sql.*;
import java.util.Scanner;

public class StatementUserLogin {
    public static void main(String[] args) {
        Connection connect = StatementQueryPart.connect();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Account:");
        String account = scanner.nextLine();
        System.out.println("Input Password:");
        String password = scanner.nextLine();



    }

    public  static  void statement(Connection connect, String account, String password) {
        Statement statement = null;
        ResultSet record = null;

        try {
            statement = connect.createStatement();
            var sql = """
                    SELECT * FROM accounts WHERE username = '%s' AND password = '%s';
                    """.formatted(account, password);
            record = statement.executeQuery(sql);
            if (record.next()) {
                var user_id = record.getString(1);
                var username = record.getString("username");
                var pwd = record.getString(3);
                var email = record.getString("email");
                System.out.println(String.format("user_id: %s \t username: %s, \t password: %s \t email: %s", user_id, username,
                        pwd, email));
            } else {
                System.out.println("Login Failed.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                record.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connect.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public  static  void preparestatement(Connection connect, String account, String password) {
        PreparedStatement statement = null;
        ResultSet record = null;

        try {
            var sql = """
                    SELECT * FROM accounts WHERE username = ? AND password = ?;
                    """;
            statement = connect.prepareStatement(sql);

            statement.setObject(1, account);
            statement.setObject(2, password);

            record = statement.executeQuery();
            if (record.next()) {
                var user_id = record.getString(1);
                var username = record.getString("username");
                var pwd = record.getString(3);
                var email = record.getString("email");
                System.out.println(String.format("user_id: %s \t username: %s, \t password: %s \t email: %s", user_id, username,
                        pwd, email));
            } else {
                System.out.println("Login Failed.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                record.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connect.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
