JDBC 是一個連線資料庫的技術。使用 JDBC 提供的方法可以使用 SQL 語句到資料庫管理軟體執行語句結果。

```
java ----JDBC----> DBMS 軟體 --------> 資料庫
```

JDBC 內部由兩部分組成

1. JAVA 提供的 JDBC 規範
2. 個資料庫(MySQL、Postgres)所實現的 jar 


要與 DBMS 軟體交互大致是以下流程
1. 註冊驅動
2. 建立連線
3. 建立 SQL 語句 statement
4. statement 發送至資料庫並獲取結果
5. 獲取 ResultSet 結果
6. 資源銷毀

```java
// 建立連線
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
```

```java
// 獲取資料，以 ResultSet 物件封裝 SQL 結果
         statement = connection.createStatement();
         String sql = """
                        SELECT * FROM accounts;
                    """;
         executeQuery = statement.executeQuery(sql);
         while (executeQuery.next()) {
           String user_id = executeQuery.getString("user_id"); // 獲取 SQL 回傳對應的欄位值
           String username = executeQuery.getString("username");
           String password = executeQuery.getString("password");
           String email = executeQuery.getString("email");
           Timestamp createdDate = executeQuery.getTimestamp("created_on");
           LOG.info(String.format("user_id: %s \t username: %s, \t password: %s \t email: %s", user_id, username,
           password, email));
         }
```



SQL 分類
- DDL
  - 表的 CREATE、UPDATE、DELETE
- DML
  - 資料的 INSERT、UPDATE、DELETE
- DQL
  - QUERY
- DCL
  - 權限控制
- TPL
  - 事務控制
在 JDBC 中

```java
// SQL 參數是非 DQL
int row = executeUpdate(SQL);
// SQL 參數是 DQL
ResultSet record  = executeQuery(SQL);
```

JDBC API 使用

- 靜態 SQL
  - `Statement`
- 預編譯 SQL
  - `PreparedStatement`
  - 防止注入
  - 動態值部分以 ? 代替
- 執行標準儲存 SQL
  - `CallableStatement`

![](https://2.bp.blogspot.com/-sF4Q_LIbH6g/WpyrMIwOGxI/AAAAAAAAK84/KUYkkvNaVRohQI3qiIY_at3A5KRymCljwCLcBGAs/w580-h315/statement%2Bvs%2Bpreparedstatement%2Bvs%2Bcallablestatement.png)