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

## Part 2

This part was created in order to prioritize the tasks in the threadPool of java.
in order for it to happened we created:

- Task class - Callable task that returns value with priority enum.
- CustomExecutor class - creates priority for all tasks. 
- Taskadpter inner class 

## Task 

The class holds a Callable function and an enum of type TaskType. It is able to compare priority based on the taskType.
The class has two constructors, one that creates a task with a given function and taskType "OTHER" that creates a task with a given function and taskType.

## CustomExecutor

This is a custom thread pool executor class that allows tasks 
to be submitted with priority.
It uses a PriorityBlockingQueue to sort the tasks by priority by creating 
ThreadPoolExecutor with the number of available processors.

## TaskAdapter 

This is an inner class within the CustomExecutor class. It is used to adapt the Task class to the FutureTask class,
which is the type of task that can be executed by the ThreadPoolExecutor. The TaskAdapter class extends the FutureTask class,
and also implements the Comparable interface. 
It allowes the task objects that pass to submit method method of the CustomExecutor class
to use elements in the priority queue. 
It has a one constructor that uses Callable object and disguised as Task object. 
Then it assigns the taskType by priority.
the priority of the task returns by getPriority of the task. 
