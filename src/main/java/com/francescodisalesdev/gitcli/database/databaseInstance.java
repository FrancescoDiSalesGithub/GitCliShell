package com.francescodisalesdev.gitcli.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class databaseInstance
{
    private Connection con;

    public databaseInstance(String url) throws SQLException
    {
        con = DriverManager.getConnection(url);

        if(con==null)
            throw new SQLException();

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
