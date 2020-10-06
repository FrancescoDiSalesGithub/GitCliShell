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
 - clone: clones a git repository given an http URL and a local directory
 - clone-branch: same as clone but needs also a branch from the git-repository 
 - get-pages: shows the max page in the research
 - info: gets info of the folders inside a git repository
 - query: searchs for a git repository given a specific term to search and a number of page (example 1=first page, 2=second page and so on...)
 - navigate: checks a path in the repository
 - check-file: shows the content of a file in the repository
 
 Below you can see some examples on how to use these commands.
 
 **clone**
 
 ``` 
 clone https://github.com/FrancescoDiSalesDEV/GitCli /home/myuser/newgitcli
 
 ```
  **clone-branch**
 
 ``` 
 clone-branch https://github.com/FrancescoDiSalesDEV/GitCli /home/myuser/newgitcli branchxyz 
 
 ```
  **get-pages**
 
 ``` 
 get-pages postgresql
 
 ```
 
  **info**
 
 ``` 
 info https://github.com/FrancescoDiSalesDEV/GitCli 
 
 ```
 
  **query**
  
 This command shows only the first page
 ``` 
query postgresql 
 
 ```
 This command shows the second page (actually each integer is a specific page you want to see)
 
 ``` 
query postgresql 2
 
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
