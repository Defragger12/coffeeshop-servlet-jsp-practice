package com.scand.coffeeshop.repository.sql.util;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Hashtable;

public class SqlConnectionManager {

    private static DataSource dataSource;

    private static void init() throws Exception {

        Hashtable env = new Hashtable();

        InitialContext initialContext = new InitialContext(env);
        dataSource = (javax.sql.DataSource) initialContext.lookup("java:comp/env/jdbc/coffeeshop");
    }

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        if (dataSource == null) {
            init();
        }

        return dataSource.getConnection();
    }
}
