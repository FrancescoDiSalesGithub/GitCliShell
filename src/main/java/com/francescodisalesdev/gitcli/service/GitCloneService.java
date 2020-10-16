package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.utility.SystemMessages;

import java.io.File;
import java.io.IOException;

public class GitCloneService
{

    public void cloneRepositoryBranch(String repository,String localPath,String branch) throws IOException,InterruptedException
    {
        String gitRepository = repository+".git";

        if(localPath.contains("\\"))
            localPath.replace("\\","\\\\");

        ProcessBuilder processBuilder = new ProcessBuilder("git","clone","-b",branch,gitRepository);

        processBuilder.directory(new File(localPath));
        Process process = processBuilder.start();

        System.out.println(SystemMessages.CLONING_PROCESS);
        process.waitFor();

        System.out.println(SystemMessages.CLONING_DONE);


    }


}
