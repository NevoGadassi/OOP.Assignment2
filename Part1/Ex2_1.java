import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class Ex2_1  {


    /**
     * create n new files and write in each file contains random lines of "hello world"
     * @param n amount of file
     * @param seed seed to random
     * @param bound bound to random
     * @return string array with the names of the new files
     */
    public static String[] createTextFiles(int n, int seed, int bound)
    {
        Random rand = new Random(seed);
        String[]filearr=new String[n];
        FileWriter f;
        char c;
        String filename="file_";
        for (int i = 1; i <= n; i++) {

            filename+=String.valueOf(i);
            filename+=".txt";
            try{
                int x = rand.nextInt(bound);
                f=new FileWriter(filename,true);
                for (int m=0;m<x-1;m++){
                    f.write("hello world \n");

                }
                f.write("hello world ");
                f.close();
            }catch (Exception ex){ex.printStackTrace();}
            filearr[i-1]=filename;
            filename="file_";
        }
        return filearr;
    }

    /**
     * Calculates the total number of lines of all files in the usual way, each file in order one by one
     * @param fileNames string array with the names of the new files
     * @return the total number of lines of all files
     */
    public static int getNumOfLines(String[] fileNames)
    {
        int sum=0;
        BufferedReader reader;
        int lines=0;
        for (int i=0;i<fileNames.length;i++){
            try{
                 reader = new BufferedReader(new FileReader(fileNames[i]));
                 lines = 0;
                while (reader.readLine() != null) lines++;
                reader.close();
                } catch (Exception ex){ex.printStackTrace();}
            sum=sum+lines;
        }
            return sum;
    }

    /**
     * Creates a Thread for each file, runs it and with its help calculates the number of lines for each file.
     * Until one Thread does not finish, the other does not start
     * @param fileNames string array with the names of the new files
     * @return the total number of lines of all files
     */
    public static   int getNumOfLinesThreads(String[] fileNames)
    {
       LineThread specificfile;
       int sum=0;
       for(int i=0;i< fileNames.length;i++){
           specificfile=new LineThread(fileNames[i]);
            specificfile.run();
           while ((specificfile.isAlive())) {
           }
            sum=sum+specificfile.getLine();
    }
        return sum;
    }
    /**
     Creates a Thread for each file,add all Thread to pool and run all Thread together.
     its help calculates the number of lines for each file in the same time .
     * @param fileNames string array with the names of the new files
     * @return the total number of lines of all files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames)
    {

        int sum = 0;
        try {
            List<Future<Integer>> Linefile = new ArrayList<>();
            Executor executor = Executors.newFixedThreadPool(fileNames.length);//All thread run together

            for (int i = 0; i < fileNames.length; i++) {
                Future<Integer> task = ((ExecutorService) executor).submit(new LineCallAble(fileNames[i]));
                Linefile.add(task);
            }
            for (int t=0;t<fileNames.length;t++){
                sum = sum + (int)Linefile.get(t).get();
            }
            ((ExecutorService) executor).shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }




    public static void main(String[] args) {
    }


}
