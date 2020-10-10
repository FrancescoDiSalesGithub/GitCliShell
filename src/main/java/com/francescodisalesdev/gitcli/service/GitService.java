package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.utility.ConditionChecker;
import com.francescodisalesdev.gitcli.utility.ErrorMessages;
import com.francescodisalesdev.gitcli.utility.SystemMessages;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.kohsuke.github.*;


import javax.print.Doc;
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

    public int getPages(String param) throws IOException
    {
        String URL = "https://github.com/search?q="+param;
        Document document = Jsoup.connect(URL).get();
        Elements elementsLinks = document.select("em");

        int pagesValue = Integer.parseInt(elementsLinks.attr("data-total-pages").toString());
        return pagesValue;

    }

    public void cloneRepositoryBranch(String repository,String localPath,String branch) throws IOException,InterruptedException
    {
        String gitRepository = repository+".git";

        ProcessBuilder processBuilder = new ProcessBuilder("git","clone","-b",branch,gitRepository);

        if(localPath.contains("\\"))
            localPath.replace("\\","\\\\");

        processBuilder.directory(new File(localPath));
        Process process = processBuilder.start();

        System.out.println(SystemMessages.CLONING_PROCESS);
        process.waitFor();

        System.out.println(SystemMessages.CLONING_DONE);


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

    public void navigateService(String path)throws IOException
    {
        if(path.startsWith("/"))
            path = path.substring(1);

        if(!path.contains("blob") && path.contains("tree"))
        {
            Document document = Jsoup.connect("https://github.com/"+path).get();
            Elements elements = document.select("a");

            for(Element element : elements)
            {
                if(!element.toString().isBlank() && !(element.toString().contains("https")||element.toString().contains("http")))
                    if(element.toString().contains("blob") || element.toString().contains("tree"))
                        System.out.println(element.attr("href"));
            }
        }
        else
        {
            if(path.contains("blob"))
                System.out.println(ErrorMessages.BLOB_ERROR);
            else
                System.out.println(ErrorMessages.INVALID_PATH);
        }

    }

    public void checkFileService(String path) throws IOException
    {
        if(path.startsWith("/"))
            path = path.substring(1);

        if(path.contains("blob") && !path.contains("tree"))
        {
            Document document = Jsoup.connect("https://github.com/"+path).get();
            Elements elements = document.select("td");

            for(Element element : elements)
            {
                if(!element.toString().isBlank() && !(element.toString().contains("https")||element.toString().contains("http")))
                    if(element.attr("class").toString().contains("blob-code"))
                    {
                        System.out.println(element.text());
                    }

            }
        }
        else
        {
            System.out.println(ErrorMessages.INVALID_PATH);
        }
    }

    public Set<String> searchUserService(String username,int page) throws IOException,ParseException
    {

        Set<String> users = new HashSet<String>();

        String URL;

        if(username.isEmpty())
            return null;

        if(page == 0 || page < 0)
            return null;

        if(page == 1)
            URL = "https://github.com/search?q="+username+"&type=users";
        else
            URL = "https://github.com/search?p="+page+"&q="+username+"&type=Users";

        Document document = Jsoup.connect(URL).get();
        Elements elements = document.select("a");

        for(Element element : elements)
        {
            String repositoryJSON = element.attr("data-hydro-click").toString();
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
                    users.add(urlString);
                }

            }

        }

        return users;
    }

    public int getUserPages(String username) throws IOException
    {
        String URL = URL = "https://github.com/search?q="+username+"&type=users";

        Document document = Jsoup.connect(URL).get();
        Elements elementsLinks = document.select("em");

        int value = Integer.parseInt(elementsLinks.attr("data-total-pages").toString());
        return value;

    }

    public void getUserInfo(String username) throws IOException
    {

        GitHub gitHub = new GitHubBuilder().build();
        GHUser user = gitHub.getUser(username);
        String email = user.getEmail();

        if(email == null)
            email = ErrorMessages.MAIL_NOT_SET.toString();

        System.out.println("username: "+username);
        System.out.println("email: "+email );

        System.out.print("followers: ");

        GHPersonSet<GHUser> followers = user.getFollowers();
        for(GHUser myuser : followers)
        {
            System.out.print(myuser.getLogin() + " ");
        }

        System.out.print("\nfollowing: ");
        GHPersonSet<GHUser> follows = user.getFollows();

        for(GHUser myuser : follows)
        {
            System.out.print(myuser.getLogin()+" ");
        }

        System.out.print("\nrepository: ");
        Map<String,GHRepository> repository = user.getRepositories();
        System.out.println(repository.keySet());

        for(Map.Entry<String, GHRepository> valuesFinal : repository.entrySet())
        {
            System.out.println(valuesFinal.getKey() + " " + valuesFinal.getValue().getHtmlUrl());
        }

    }


}
