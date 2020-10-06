package com.francescodisalesdev.gitcli.utility;

public enum ErrorMessages
{
    INVALID_PATH
            {
                @Override
                public String toString() {
                    return "Invalid path";
                }
            },
    BLOB_ERROR
            {
                @Override
                public String toString() {
                    return "Your request was a blob request. Use check-file command to check the file";
                }
            },
    SOMETHING_BAD
            {
                @Override
                public String toString() {
                    return "Something bad happened";
                }


            }
}
