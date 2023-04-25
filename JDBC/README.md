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