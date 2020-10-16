package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.utility.ErrorMessages;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GitUserService
{

    public Set<String> searchUserService(String username, int page) throws IOException, ParseException
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
        Map<String, GHRepository> repository = user.getRepositories();
        System.out.println(repository.keySet());

        for(Map.Entry<String, GHRepository> valuesFinal : repository.entrySet())
        {
            System.out.println(valuesFinal.getKey() + " " + valuesFinal.getValue().getHtmlUrl());
        }

    }


    public int getUserPages(String username) throws IOException
    {
        String URL = URL = "https://github.com/search?q="+username+"&type=users";

        Document document = Jsoup.connect(URL).get();
        Elements elementsLinks = document.select("em");

        if(elementsLinks.attr("data-total-pages").toString().isEmpty())
            return 0;

        return Integer.parseInt(elementsLinks.attr("data-total-pages").toString());

    }

}
