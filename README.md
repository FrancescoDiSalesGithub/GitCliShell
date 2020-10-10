# GitCliShell

Navigate github using the terminal emulator

## How to build
execute this command in your terminal:
 ``` 
mvn package -Dmaven.test.skip=true
 
 ```
after that the jar file should be in the target folder.

## How to use it

launch the jar by doing:
 ``` 
java -jar gitCliShell-0.2.1.jar
 
 ```

GitCli has the following commands:
 - clone: clones a git repository given a repository, a username and a branch
 - info-branch: gets informations about all the possible branches in a git repository
 - info-repository: gets informations of the folders inside a git repository
 - info-user: gets informations about a specific user such as followers or personal repositories
 - search-repository: searchs for a git repository given a specific term to search and a number of page (example 1=first page, 2=second page and so on...)
 - search-user: search for a specific user
 - navigate: checks a path in the repository
 - check-file: shows the content of a file in the repository
 
 Below you can see some examples on how to use these commands.
 
 **clone**
 
 ``` 
 clone GitCli FrancescoDiSalesDEV /home/myuser/newgitcli test
 
 ```
 If the branch argument is omitted then the project will be cloned from the master branch
 
 **info-branch**
 
 ``` 
 info-branch GitCli FrancescoDiSalesDEV 
 
 ```
  
 **info-repository**
 
 If you want to know the content of the files and folder in a specific branch do:
 
 ``` 
 info-repository GitCliShell FrancescoDiSalesDev test
 
 ```
 Otherwise like the clone command omitt the branch argument
 
 **info-user**
 
 ``` 
 info-user FrancescoDiSalesDEV
 
 ```
 
  **search-repository**
  
 This command shows only the first page
 ``` 
search-repository postgresql 
 
 ```
 This command shows the second page (actually each integer is a specific page you want to see)
 
 ``` 
search-repository postgresql 2
 
 ```

**search-user**
  
 This command shows only the first page
 ``` 
search-user johndoe
 
 ```
 This command shows the second page (actually each integer is a specific page you want to see)
 
 ``` 
search-user johndoe 2
 
 ```
  
  **navigate**
  
 Supposing you have already checked the main page of the repository with the info command and you want to check a folder
   
   ``` 
navigate FrancescoDiSalesDEV/GitCliShell/tree/master/.github
 
 ```
 
 **check-file**
 
``` 
check-file /FrancescoDiSalesDEV/GitCliShell/blob/master/LICENSE
 
 ```
