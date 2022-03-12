import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.util.Random;

public class solution1 {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        FSDataInputStream in1 = null; 
        FSDataInputStream in2 = null; 

        FileSystem hdfs  = FileSystem.get(URI.create(args[2]), conf);

        try {
         FileSystem fs = FileSystem.get(conf); 
         Path inFile1 = new Path(args[0]);  
         in1 = fs.open(inFile1); 
 
         Path outFile = new Path(args[2]);
         FSDataOutputStream out = hdfs.create(outFile);
         byte buffer[] = new byte[256];
         int bytesRead = 0;
          while( (bytesRead = in1.read(buffer)) > 0) 
                  out.write(buffer, 0, bytesRead);
         in1.close();
         Path inFile2 = new Path(args[1]); 
         in2 = fs.open(inFile2); 
         while( (bytesRead = in2.read(buffer)) > 0) 
                 out.write(buffer, 0, bytesRead);
         out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
