package com.francescodisalesdev.gitcli.service;

import com.francescodisalesdev.gitcli.utility.ErrorMessages;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GitNavigationService
{

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


}
