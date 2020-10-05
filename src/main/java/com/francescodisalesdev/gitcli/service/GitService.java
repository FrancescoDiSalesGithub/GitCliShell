package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.utility.ConditionChecker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;


import java.io.File;
import java.io.IOException;
import java.util.*;


public class GitService
{

    public List<String> getResult(String param,int page) throws IOException, ParseException
    {

        String URL;

        if(page <0 || page == 0)
            return  null;

        if(page == 1)
            URL="https://github.com/search?q="+param;
        else
            URL="https://github.com/search?p="+page+"&q="+param+"&type=Repositories";

        Document document = Jsoup.connect(URL).get();

        List<String> repo = new ArrayList<String>();
        Elements elementsLinks = document.select("a");

        for(Element link : elementsLinks)
        {
          String repositoryJSON = link.attr("data-hydro-click").toString();
          JSONParser jsonParser = new JSONParser();

          if(!repositoryJSON.isBlank())
          {
              JSONObject jsonObject = (JSONObject) jsonParser.parse(repositoryJSON);

              String payload = jsonObject.get("payload").toString();
              JSONObject result = (JSONObject) jsonParser.parse(payload);

              if(result.containsKey("result"))
              {
                  String resultString = result.get("result").toString();
                  JSONObject three = (JSONObject) jsonParser.parse(resultString);

                  String urlString = three.get("url").toString();
                  repo.add(urlString);
              }

          }

        }

       return repo;
    }

    public void getPages(String param) throws IOException
    {
        String URL = "https://github.com/search?q="+param;
        Document document = Jsoup.connect(URL).get();
        Elements elementsLinks = document.select("em");

        System.out.println(elementsLinks.attr("data-total-pages").toString());

    }

    public void filterPage(String param,List<String> response) throws IOException
    {

        Iterator responseIterator = response.iterator();

        while(responseIterator.hasNext())
        {
            System.out.println(responseIterator.next());
        }

        System.out.println("total pages for this search:");
        this.getPages(param);

    }

    public void cloneRepository(String repository,String localPath) throws IOException, InterruptedException
    {
        String gitRepository = repository+".git";

        ProcessBuilder processBuilder = new ProcessBuilder("git","clone",gitRepository);
        processBuilder.directory(new File(localPath));
        Process process = processBuilder.start();

        System.out.println("cloning ...");
        process.waitFor();

        System.out.println("cloning done!");

    }

    public void cloneRepositoryBranch(String repository,String localPath,String branch) throws IOException,InterruptedException
    {
        String gitRepository = repository+".git";

        ProcessBuilder processBuilder = new ProcessBuilder("git","clone","-b",branch,gitRepository);
        processBuilder.directory(new File(localPath));
        Process process = processBuilder.start();

        System.out.println("cloning ...");
        process.waitFor();

        System.out.println("cloning done!");


    }

    public void getInfoRepository(String repository) throws IOException
    {
        Document document = Jsoup.connect(repository).get();

        Elements elements = document.select("a");
        List<String> info = new ArrayList<String>();

        ConditionChecker conditionChecker = new ConditionChecker();

        for(Element element : elements)
        {
            if(conditionChecker.ConditionParserInfoA(element) && conditionChecker.ConditionParserInfoB(element))
                if(element.attr("href").contains("/blob/") || element.attr("href").contains("/tree/"))
                    info.add(element.attr("href"));

        }

        Iterator iterator = info.iterator();

        while(iterator.hasNext())
            System.out.println(iterator.next());

    }


}
