

    public class TestTime {
        public static void main(String[] args)  {
            long start,end,finaltime ;
            int lines;

            String[] filesname = Ex2_1.createTextFiles( 1500, 2, 10000);
            start = System.currentTimeMillis();
            lines = Ex2_1.getNumOfLines(filesname);
            end = System.currentTimeMillis();
            finaltime=end-start;
            System.out.println("Calculation time using function 2-lines:"+ lines +" time: "+finaltime+"ms");

            start = System.currentTimeMillis();
            lines = Ex2_1.getNumOfLinesThreads(filesname);
            end = System.currentTimeMillis();
            finaltime=end-start;
            System.out.println("Calculation time using function 3-lines:"+ lines +" time: "+finaltime+"ms");

            start = System.currentTimeMillis();
            lines = Ex2_1.getNumOfLinesThreadPool(filesname);
            end = System.currentTimeMillis();
            finaltime=end-start;
            System.out.println("Calculation time using function 4-lines:"+ lines +" time: "+finaltime+"ms");


        }
    }

