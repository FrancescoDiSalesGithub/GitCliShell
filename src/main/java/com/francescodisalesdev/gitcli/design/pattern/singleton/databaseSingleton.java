package com.francescodisalesdev.gitcli.design.pattern.singleton;

import java.sql.*;

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

    public void insertStatement(String query) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.execute(query);
    }


    public void updateStament(String query) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.execute(query);
    }

    public void deleteStatement(String query) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.execute(query);
    }

    public String searchStatementAuthor(String query) throws SQLException
    {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int value=0;

        while(resultSet.next())
            value = resultSet.getInt("id");

        return String.valueOf(value);
    }

    public void searchAuthorRepository(String query) throws SQLException
    {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int value=0;

        while(resultSet.next())
        {

        }

    }

    public void closeConnection() throws SQLException
    {
        con.close();
    }


}
