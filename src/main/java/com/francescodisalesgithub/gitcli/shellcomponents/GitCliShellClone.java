package com.francescodisalesgithub.gitcli.shellcomponents;

import com.francescodisalesgithub.gitcli.service.GitCloneService;
import com.francescodisalesgithub.gitcli.utility.ErrorMessages;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class GitCliShellClone
{

    @ShellMethod("clone the repository given an url")
    public void clone(String repository, String localPath, @ShellOption(defaultValue = "master")String branch)
    {
        GitCloneService gitCloneService = new GitCloneService();

        try
        {
            gitCloneService.cloneRepositoryBranch(repository,localPath,branch);
        }
        catch(IOException | InterruptedException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }




}
