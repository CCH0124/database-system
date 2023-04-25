package jdbc.cch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Logger;

public class StatementQueryPart {
    private static final Logger LOG = Logger.getLogger(StatementQueryPart.class.getName());
    private static final String url = "jdbc:postgresql://127.0.0.1:5432/account";
    private static final String user = "cch";
    private static final String password = "123456";

    public static void main(String[] args) {
        createStatement(connect());
    }

    public static Connection connect() {
        Connection conn = null;
        try {
             Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn == null) {
                LOG.warning("Failed to make connection");
            }
            LOG.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            LOG.warning(e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }

    public static void createStatement(Connection connection) {
        Statement statement = null;
        ResultSet executeQuery = null;
        try {
            statement = connection.createStatement();
            String sql = """
                        SELECT * FROM accounts;
                    """;
            executeQuery = statement.executeQuery(sql);
            while (executeQuery.next()) {
                String user_id = executeQuery.getString("user_id");
                String username = executeQuery.getString("username");
                String password = executeQuery.getString("password");
                String email = executeQuery.getString("email");
                Timestamp createdDate = executeQuery.getTimestamp("created_on");
                LOG.info(String.format("user_id: %s \t username: %s, \t password: %s \t email: %s", user_id, username,
                        password, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
