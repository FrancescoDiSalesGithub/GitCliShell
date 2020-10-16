package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.utility.ConditionChecker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GitRepositoryService
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

    public int getPages(String param) throws IOException
    {
        String URL = "https://github.com/search?q="+param;
        Document document = Jsoup.connect(URL).get();
        Elements elementsLinks = document.select("em");
        String pages = elementsLinks.attr("data-total-pages").toString();

        if(pages.isEmpty())
            return 0;

        int pagesValue = Integer.parseInt(pages);
        return pagesValue;

    }

    public void getInfoRepository(String repository,String username,String branch) throws IOException
    {

        String URL;

        if(branch.equals("master"))
            URL = "https://github.com/"+username+"/"+repository;
        else
            URL = "https://github.com/"+username+"/"+repository+"/tree/"+branch;

        Document document = Jsoup.connect(URL).get();

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

    public void getBranchRepository(String repository,String username) throws IOException
    {
        GitHub gitHub = new GitHubBuilder().build();
        GHUser user = gitHub.getUser(username);
        Map<String, GHBranch> branches = user.getRepository(repository).getBranches();

        for(Map.Entry<String,GHBranch> branchMap : branches.entrySet())
        {
            System.out.println(branchMap.getValue());
        }

    }
}
