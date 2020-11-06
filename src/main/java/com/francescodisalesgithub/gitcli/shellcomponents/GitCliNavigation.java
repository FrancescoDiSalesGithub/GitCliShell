package com.francescodisalesgithub.gitcli.shellcomponents;

import com.francescodisalesgithub.gitcli.service.GitNavigationService;
import com.francescodisalesgithub.gitcli.utility.ErrorMessages;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class GitCliNavigation
{

    @ShellMethod("check a file")
    public void checkFile(String path)
    {
        GitNavigationService gitNavigationService = new GitNavigationService();

        try
        {
            gitNavigationService.checkFileService(path);
        }
        catch(IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("go to a specific folder")
    public void navigate(String path)
    {
        GitNavigationService gitNavigationService = new GitNavigationService();

        try
        {
           gitNavigationService.navigateService(path);
        }
        catch (IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

}
