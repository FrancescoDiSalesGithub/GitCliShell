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
import java.util.List;

@ShellComponent
public class GitCliShellRepository
{

    @ShellMethod("search for a git repository")
    public void searchRepository(String repository,@ShellOption(defaultValue = "1") int page)
    {
        try
        {

            GitService gitService = new GitService();
            List<String> result = gitService.getResult(repository,page);

            if(result==null)
                System.out.println("page number must be bigger than 0");
            else
            {
                Iterator responseIterator = result.iterator();

                while(responseIterator.hasNext())
                {
                    System.out.println(responseIterator.next());
                }

                int pagesRepository = gitService.getPages(repository);

                if(pagesRepository>1)
                    System.out.println(SystemMessages.TOTAL_PAGES.toString()+pagesRepository);

            }


        }
        catch(IOException | ParseException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }


    @ShellMethod("see a list of files in a repository")
    public void infoRepository(String repository,String username,@ShellOption(defaultValue = "master") String branch)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.getInfoRepository(repository,username,branch);

        }
        catch (IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }

    }

    @ShellMethod("show all the branches of a repository")
    public void infoBranch(String repository,String username)
    {
        GitService gitService = new GitService();

        try
        {
            gitService.getBranchRepository(repository,username);

        }
        catch (IOException e)
        {
            System.out.println(ErrorMessages.SOMETHING_BAD);
            System.out.println(e.getMessage());
        }
    }

}
