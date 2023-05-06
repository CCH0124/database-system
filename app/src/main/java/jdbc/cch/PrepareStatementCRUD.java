package jdbc.cch;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PrepareStatementCRUD {
    private static Logger log = Logger.getLogger(PrepareStatementCRUD.class.getName());
    static Connection connect = StatementQueryPart.connect();
    public static void main(String[] args) {

    }

    public void insert() throws SQLException {
        String sql = """
                    INSERT INTO accounts(username, password, email) VALUES(? ,?, ?);
                """;
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setObject(1, "madara");
        preparedStatement.setObject(2, "123456");
        preparedStatement.setObject(3, "123@gmail.com");

        int row = preparedStatement.executeUpdate();

        if (row > 0) {
            log.info("Insert success.");
        } else {
            log.warning("Insert Failed.");
        }

        preparedStatement.close();
        connect.close();
    }

    public void update() throws SQLException {
        String sql = """
                UPDATE accounts SET email = ? WHERE username = ?;
                """;
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setObject(1, "asd@gmail.com");
        preparedStatement.setObject(2, "madara");

        int row = preparedStatement.executeUpdate();
        if (row > 0) {
            log.info("Update success.");
        } else {
            log.warning("Nothing update ");
        }

        preparedStatement.close();
        connect.close();
    }

    public void delete() throws SQLException {
        String sql = """
                DELETE FROM accounts WHERE username = ?;
                """;
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setObject(1, "madara");

        int row = preparedStatement.executeUpdate();
        if (row > 0) {
            log.info("Delete success.");
        } else {
            log.warning("Nothing delete.");
        }

        preparedStatement.close();
        connect.close();

    }

    public void select() throws SQLException {
        var sql = """
                SELECT id, username, password, email FROM accounts;
                """;
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 獲取列的訊息名稱
        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map> list = new ArrayList<>();

        while (resultSet.next()) {
            Map map = new HashMap<>();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Object val = resultSet.getObject(i);
                // getColumnLabel 可獲取別名；getColumnName 只能獲取列的名稱
                String columnLabel = metaData.getColumnLabel(i);
                map.put(columnLabel, val);


            }
            list.add(map);
        }

        log.info(String.format("%s", list));

        resultSet.close();
        preparedStatement.close();
        connect.close();

    }
}
