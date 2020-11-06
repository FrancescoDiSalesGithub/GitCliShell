package com.francescodisalesgithub.gitcli.service;

import com.francescodisalesgithub.gitcli.database.databaseInstance;
import com.francescodisalesgithub.gitcli.utility.ErrorMessages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GitDatabaseService
{

    public void createDatabase(String fileName,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path+fileName+".db";
        
        databaseInstance databaseInstance = new databaseInstance(url);

        String queryCreation = "create table author( author_id  INTEGER PRIMARY KEY AUTOINCREMENT, username text);";
        String queryRepositoryCreation = "create table repository( author_id INTEGER,repository_id text,name text, primary key(repository_id),foreign key(author_id) references author(id));";

        databaseInstance.createTableStatement(queryCreation);
        databaseInstance.createTableStatement(queryRepositoryCreation);

        databaseInstance.getConnection().close();

    }

    public void insertAuthor(String author,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;
        databaseInstance databaseInstance = new databaseInstance(url);

        String queryInsert = "insert into author(username) values (?);";

        PreparedStatement insertAuthorStatement = databaseInstance.getConnection().prepareStatement(queryInsert);
        insertAuthorStatement.setString(1,author);
        insertAuthorStatement.execute();

        databaseInstance.getConnection().close();
    }

    public void assignRepository(String author,String repository,String urlRepo,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;

        databaseInstance databaseInstance = new databaseInstance(url);

        String selectQuery = "Select author_id from author where username=?";
        PreparedStatement selectAuthorStatement = databaseInstance.getConnection().prepareStatement(selectQuery);
        selectAuthorStatement.setString(1,author);
        ResultSet authorFind = selectAuthorStatement.executeQuery();

        if (authorFind.getFetchSize() < 0)
            System.out.println(ErrorMessages.AUTHOR_GIT_NULL);
        else
        while(authorFind.next())
        {
            String queryInsert = "insert into repository(author_id,repository_id,name) values(?,?,?);";
            PreparedStatement assignRepositoryStatemtent = databaseInstance.getConnection().prepareStatement(queryInsert);

            assignRepositoryStatemtent.setString(1,authorFind.getString(1));
            assignRepositoryStatemtent.setString(2,urlRepo);
            assignRepositoryStatemtent.setString(3,repository);

            assignRepositoryStatemtent.execute();
        }

        databaseInstance.getConnection().close();

    }

    public void searchAuthor(String author,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;
        databaseInstance databaseInstance = new databaseInstance(url);

        String selectQuery;

        if(author.equals("*"))
         selectQuery = "Select * from author;";
        else
         selectQuery = "Select username from author where username=?";

        PreparedStatement selectAuthorStatement = databaseInstance.getConnection().prepareStatement(selectQuery);

        if(!author.equals("*"))
            selectAuthorStatement.setString(1,author);

        ResultSet resultSetAuthor = selectAuthorStatement.executeQuery();

        while(resultSetAuthor.next())
        {
            if(author.equals("*"))
              System.out.println(resultSetAuthor.getString(2));
            else
              System.out.println(resultSetAuthor.getString(1));
        }

        databaseInstance.getConnection().close();
    }

    public void searchRepositoryByAuthor(String author,String path) throws SQLException
    {
        String url = "jdbc:sqlite:"+path;
        databaseInstance databaseInstance= new databaseInstance(url);

        String queryJoinRepository = "Select r.repository_id from author a,repository r where a.author_id=r.author_id and a.username=? ";
        PreparedStatement repositoryAuthorStatement = databaseInstance.getConnection().prepareStatement(queryJoinRepository);
        repositoryAuthorStatement.setString(1,author);

        ResultSet resultSetJOIN = repositoryAuthorStatement.executeQuery();

        while(resultSetJOIN.next())
          System.out.println(resultSetJOIN.getString(1));

        databaseInstance.getConnection().close();

    }


}
