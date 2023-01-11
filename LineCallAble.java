
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

    public class LineCallAble  implements Callable<Integer> {
        String filename;

        /**
         *
         * @param file The file name
         */
        public LineCallAble(String file) {
            this.filename = file;
        }

        /**
         * @return The number of lines in the specific file
         * @throws Exception
         */
        @Override
        public Integer call() throws Exception {
            BufferedReader reader;
            int lines=0;
            try{
                reader = new BufferedReader(new FileReader(this.filename));
                lines = 0;
                while (reader.readLine() != null) lines++;
                reader.close();

            } catch (Exception ex){ex.printStackTrace();}



            return lines;
        }
    }



