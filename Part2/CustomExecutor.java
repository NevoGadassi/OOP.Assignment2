package part2;

import java.util.Collection;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.*;
/**
 * A custom thread pool executor that allows to submit tasks with priority.
 * It uses a PriorityBlockingQueue to sort task by priority.
 *
 */
public class CustomExecutor  extends ThreadPoolExecutor{
    //Data members
    private int[] priorities= new int[3];
    /**
     * Constructor that creates the custom executor
     * It creates ThreadPoolExecutor with number of available processors -1 as max pool size, and
     * number of available processors /2 as core pool size
     * Also creates a priority blocking queue as the workQueue
     */
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2
                , Runtime.getRuntime().availableProcessors() - 1
                , 300L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());

    }
    public <T> FutureTask<T> submit(Task<T> task){
        TaskAdapter<T> taskAdapter = new TaskAdapter<>(task);
        this.priorities[task.getPriority()-1]++;
        execute(taskAdapter);

        return taskAdapter;
    }
    public <T> FutureTask<T> submit(Callable<T> callable){
        return submit(new Task<>(callable));
    }

    public <T> FutureTask<T> submit(Callable<T> callable,TaskType taskType){
        return submit(new Task<>(callable,taskType));
    }


    /**
     * Wait until the queue is empty and all the task in thread pool are done before shutting down the ThreadPoolExecutor
     */
    public void gracefullyTerminate() {
        super.shutdown();
        try {
            super.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentMax(){
        if(this.priorities[0]>0){
            return 1;
        }
        else if(this.priorities[1]>0){
            return 2;
        }
        else if(this.priorities[2]>0){
            return 3;
        }
        return 0;
    }

    /**
     * @param t the thread that will run task
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        if(getCurrentMax()!=0){
            this.priorities[getCurrentMax()-1]--;

        }
    }
    public class TaskAdapter<T> extends FutureTask<T> implements Comparable<T>{
        private Task<T> task;
        public TaskAdapter(Task<T> task) {
            super(task);
            this.task = task;
        }

        public Task<T> getTask() {
            return task;
        }

        @Override
        public int compareTo(T o) {
            return this.task.compareTo((T) ((TaskAdapter<T>) o).task);
        }

    }
}