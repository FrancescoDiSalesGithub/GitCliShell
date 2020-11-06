package com.francescodisalesgithub.gitcli.utility;

public enum SystemMessages
{
    CLONING_PROCESS
            {
                @Override
                public String toString() {
                    return "Cloning ...";
                }
            },
    CLONING_DONE
            {
                @Override
                public String toString() {
                    return "Clone done!";
                }
            },
    TOTAL_PAGES
            {
                @Override
                public String toString() {
                    return "Total pages for this search:";
                }
            }
}
