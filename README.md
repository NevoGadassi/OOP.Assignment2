# OOP.Assignment2

- Nevo Gadassi - 325545887S

- Shani Basteker - 311318075

## Part1 - Make file's and count their lines Description
![Diagram](https://raw.githubusercontent.com/NevoGadassi/OOP.Assignment2/main/Part1/OOP2part1diagram.png)


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

- ### TestTime
   class to calculate the runtime of each function that retuen the total number of lines of all files.
   ```
    String[] filesname = Ex2_1.createTextFiles( "number of file", "seed", "bound");
            start = System.currentTimeMillis();
            lines = Ex2_1."choose function to runtime"(filesname);
            end = System.currentTimeMillis();
            finaltime=end-start;
            
 if we run this class we can see that 
 
Calculation time using function 2-lines:7376961 time: 1193ms

Calculation time using function 3-lines:7376961 time: 1143ms

Calculation time using function 4-lines:7376961 time: 540ms


 the faster way from the functions is the threadpool, after it the Thread and the slower way is the usal way of loop.

it's clear that the threadpool is the faster because its call to each file in the same time and save the answer
and in the normal way we calculate file finish and start calculate the next one and its waste time


## Part2

The purpose of the assignment

In this assignment-part 2: Create a new type that represents an asynchronous task 

with priority and a new ThreadPool type that supports tasks with priority.

Task - Class implements "Comparable" "Callable" interface. the class is callable class with priority option.

 we made "compareTo" function in order to define to the priorityBlockingQueue to sort by priority.
 
 CustomExecutor - Class extends ThreadPoolExecutor 
 
 Type Enum to descript a Task object's type. Tasks will be :
 * COMPUTATIONAL(1)
 * IO(2)
 * OTHER(3)
