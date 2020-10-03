package com.francescodisalesdev.gitcli.utility;

import org.jsoup.nodes.Element;

public class ConditionChecker
{
    public boolean ConditionParserInfoA(Element element)
    {
        if(!element.attr("href").isEmpty() && !element.attr("href").contains("https://") && !element.attr("href").contains("http://"))
            return true;

        return false;
    }

    public  boolean ConditionParserInfoB(Element element)
    {
        if(!element.attr("href").contains("mailto") && !element.attr("href").contains("#") && element.attr("href").contains("/tree/"))
            return true;

        return false;
    }
}
