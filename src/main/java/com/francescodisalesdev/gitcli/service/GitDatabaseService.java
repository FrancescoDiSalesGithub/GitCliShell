package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.design.pattern.singleton.databaseSingleton;
import java.sql.SQLException;



public class GitDatabaseService
{

    public void createDatabase(String fileName,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path+fileName+".db";

        databaseSingleton singleton = databaseSingleton.getSingletonInstance(url);

        String queryCreation = "create table author( id  INTEGER PRIMARY KEY AUTOINCREMENT, username text);";
        String queryRepositoryCreation = "create table repository( author_id INTEGER,repository_id int,name text,url text, primary key(repository_id),foreign key(author_id) references author(id));";

        singleton.createTableStatement(queryCreation);
        singleton.createTableStatement(queryRepositoryCreation);
        singleton.closeConnection();

    }

    public void insertAuthor(String author,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;
        databaseSingleton singleton = databaseSingleton.getSingletonInstance(url);

        String queryInsert = "insert into author(username) values ('"+author+"');";
        singleton.insertStatement(queryInsert);

        singleton.closeConnection();
    }

    public void assignRepository(String author,String repository,String urlRepo,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;

        databaseSingleton singleton = databaseSingleton.getSingletonInstance(url);

        String selectQuery = "Select id from author where username='"+author+"';";
        String authorFind=singleton.searchStatementAuthor(selectQuery);

        String queryInsert = "insert into repository(author_id,name,url) values("+authorFind+",'"+repository+"','"+urlRepo+"');";
        singleton.insertStatement(queryInsert);

        singleton.closeConnection();
    }

    public void searchAuthor(String author,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;

        databaseSingleton singleton = databaseSingleton.getSingletonInstance(url);

        String selectQuery = "Select id from author where username='"+author+"';";
        singleton.searchAuthorRepository(selectQuery);

    }

}
