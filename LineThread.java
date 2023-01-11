
import java.io.BufferedReader;
import java.io.FileReader;

public class LineThread  extends Thread{
    private int line;
    LineThread(String filename){
            super(filename);

        }
        public void run(){
            BufferedReader reader;
            int lines;
            try{
                reader = new BufferedReader(new FileReader(this.getName()));
                lines = 0;
                while (reader.readLine() != null) lines++;
                reader.close();
                this.line=lines;
            } catch (Exception ex){ex.printStackTrace();}

        }

        public int getLine(){return this.line;}

}

