package com.francescodisalesdev.gitcli.shellcomponents;

import com.francescodisalesdev.gitcli.service.GitService;
import com.francescodisalesdev.gitcli.utility.ErrorMessages;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class GitCliNavigation
{

    @ShellMethod("check a file")
    public void checkFile(String path)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.checkFileService(path);
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
        GitService gitService = new GitService();

        try
        {
            gitService.navigateService(path);
        }
        catch (IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

}
