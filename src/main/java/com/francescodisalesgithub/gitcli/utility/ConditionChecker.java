package com.francescodisalesgithub.gitcli.utility;

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
        if(!element.attr("href").contains("mailto") && !element.attr("href").contains("#"))
            return true;

        return false;
    }

    public boolean ConditionParserInfoC(Element element)
    {
        if(element.attr("href").contains("/blob/"))
            return true;

        return false;
    }
}
