# GitCli

Navigate github using the terminal emulator


## How to use

GitCli has the following commands:
 - clone: clones a git repository given an http URL and a local directory
 - clone-branch: same as clone but needs also a branch from the git-repository 
 - get-pages: shows the max page in the research
 - info: gets info of the folders inside a git repository
 - query: searchs for a git repository given a specific term to search and a number of page
 
 **clone example:**
 
 ``` 
 clone https://github.com/FrancescoDiSalesDEV/GitCli /home/myuser/newgitcli
 
 ```
  **clone-branch example:**
 
 ``` 
 clone-branch https://github.com/FrancescoDiSalesDEV/GitCli /home/myuser/newgitcli branchxyz 
 
 ```
  **get-pages:**
 
 ``` 
 get-pages https://github.com/FrancescoDiSalesDEV/GitCli 
 
 ```
 
  **info:**
 
 ``` 
 info https://github.com/FrancescoDiSalesDEV/GitCli 
 
 ```
 
  **query:**
 
 ``` 
query postgresql
 
 ```
 
