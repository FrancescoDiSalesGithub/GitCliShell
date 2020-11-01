package com.francescodisalesdev.gitcli.design.pattern.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class databaseSingleton
{
    private static databaseSingleton singletonInstance;
    private Connection con;

    private databaseSingleton(String url) throws SQLException
    {
        con = DriverManager.getConnection(url);

        if(con==null)
            throw new SQLException();

    }

    public static databaseSingleton getSingletonInstance(String url) throws SQLException
    {
        if(singletonInstance == null)
            singletonInstance = new databaseSingleton(url);

        return singletonInstance;
    }

    public Connection getConnection()
    {
        return con;
    }

    public void createTableStatement(String query) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.execute(query);
    }




}
