package com.francescodisalesdev.gitcli.shellcomponents;

import com.francescodisalesdev.gitcli.service.GitService;
import com.francescodisalesdev.gitcli.utility.ErrorMessages;
import com.francescodisalesdev.gitcli.utility.SystemMessages;

import org.json.simple.parser.ParseException;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

@ShellComponent
public class GitCliShellUser
{
    @ShellMethod("search for a specific user")
    public void searchUser(String username,@ShellOption(defaultValue = "1") int page)
    {
        GitService gitService = new GitService();

        try
        {
            Set<String> usersResult = gitService.searchUserService(username,page);

            if(usersResult == null)
                System.out.println(ErrorMessages.USER_NOT_FOUND.toString());
            else
            {
                Iterator iterator = usersResult.iterator();

                while(iterator.hasNext())
                    System.out.println(iterator.next());
            }

            int pages = gitService.getUserPages(username);
            if(pages > 1)
                System.out.println(SystemMessages.TOTAL_PAGES.toString()+pages);

        }
        catch (IOException | ParseException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("gets information about a user")
    public void infoUser(String user)
    {
        GitService gitService = new GitService();

        try
        {
            if(!user.isEmpty())
                gitService.getUserInfo(user);
            else
                System.out.println(ErrorMessages.USER_NOT_FOUND.toString());
        }
        catch(IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }


    }


}