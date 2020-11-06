package com.francescodisalesgithub.gitcli.shellcomponents;

import com.francescodisalesgithub.gitcli.service.GitDatabaseService;
import com.francescodisalesgithub.gitcli.utility.ErrorMessages;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.sql.SQLException;

@ShellComponent
public class GitCliDatabaseLocal
{

    @ShellMethod("creates the local database")
    public void createDatabase(String name,String path) throws IOException
    {
        try
        {
            GitDatabaseService gitDatabaseService = new GitDatabaseService();
            gitDatabaseService.createDatabase(name,path);
        }
        catch (SQLException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD.toString());
            e.printStackTrace();
        }
    }

    @ShellMethod("inserts an author")
    public void insertAuthor(String name,String path)
    {

        try
        {
            GitDatabaseService gitDatabaseService = new GitDatabaseService();
            gitDatabaseService.insertAuthor(name,path);
        }
        catch(SQLException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("assign a repository to an author")
    public void assignRepository(String author,String repository,String urlRepo,String path)
    {

        try
        {
            GitDatabaseService gitDatabaseService = new GitDatabaseService();
            gitDatabaseService.assignRepository(author,repository,urlRepo,path);
        }
        catch(SQLException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("loads the local database")
    public void searchAuthor(String author,String path)
    {
        try
        {
            GitDatabaseService gitDatabaseService = new GitDatabaseService();
            gitDatabaseService.searchAuthor(author, path);
        }
        catch (SQLException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            e.printStackTrace();
        }
    }

    @ShellMethod("gets repository by author")
    public void searchRepositoryAuthor(String author,String path)
    {
        try
        {
            GitDatabaseService gitDatabaseService = new GitDatabaseService();
            gitDatabaseService.searchRepositoryByAuthor(author, path);
        }
        catch (SQLException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            e.printStackTrace();
        }

    }

}
