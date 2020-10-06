package com.francescodisalesdev.gitcli;

import com.francescodisalesdev.gitcli.utility.ErrorMessages;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import com.francescodisalesdev.gitcli.service.GitService;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.List;


@ShellComponent
class GitCli
{

    @ShellMethod("search for a git repository")
    public void query(String repository,@ShellOption(defaultValue = "1") int page)
    {
            try
            {

                GitService gitService = new GitService();
                List<String> result = gitService.getResult(repository,page);

                if(result==null)
                    System.out.println("page number must be bigger than 0");
                else
                    gitService.filterPage(repository,result);

            }
            catch(IOException | ParseException e)
            {
                System.out.println(ErrorMessages.SOMETHING_BAD);
                System.out.println(e.getMessage());
            }

    }

    @ShellMethod("counts the total record of your research")
    public void getPages(String repository)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.getPages(repository);
        }
        catch(IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("clone the repository given an url")
    public void clone(String repository,String localPath)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.cloneRepository(repository,localPath);
        }
        catch(IOException | InterruptedException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("clone the repository and the relative branch")
    public void cloneBranch(String repository,String localPath,String branch)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.cloneRepositoryBranch(repository, localPath, branch);
        }
        catch(InterruptedException | IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("see a list of files in a repository")
    public void info(String repository)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.getInfoRepository(repository);
        }
        catch (IOException e)
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


}