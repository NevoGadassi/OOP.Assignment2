# OOP.Assignment2

- Nevo Gadassi - 325545887S

- Shani Basteker - 311318075

## Part1 - Make file's and count their lines Description
![Part1Diagram](https://user-images.githubusercontent.com/118805710/211622143-5cbbc9b1-93ec-4688-84c8-6f4403551ab7.png)


- ### Ex2_1 class

```
    public static String[] createTextFiles(int n, int seed, int bound)
```
create n new files and write in each file contains random lines of "hello world"

the function return string array with the names of the new files
```
    public static int getNumOfLines(String[] fileNames)
```
Calculates the total number of lines of all files in the usual way, each file in order one by one

retuen the total number of lines of all files
```
    public static   int getNumOfLinesThreads(String[] fileNames)
```
Creates a Thread for each file, runs it and with its help calculates the number of lines for each file.

Until one Thread does not finish, the other does not start


retuen the total number of lines of all files
```
    public static int getNumOfLinesThreadPool(String[] fileNames)
```
Creates a Thread for each file,add all Thread to pool and  run all Thread together.

its help calculates the number of lines for each file in the same time .


retuen the total number of lines of all files

- ### LineThread

extends from Thread.
```
          LineThread(String filename)
```
build a new LineThread with the name of file .
```
         public void run()
```
when we run the thread the thread calculating the total lines of this specific file and save it.
and we getting it with auxiliary function getLine().

- ### LineCallAble

implements from Callable
```
        public LineCallAble(String file) 
```
build a new LineCallAble with the  name of file .
```
public Integer call() throws Exception
```
when we run call  the LineCallAble calculating the total lines of this specific file and return it.

In contrast to run , call return value and can throw exceptions


