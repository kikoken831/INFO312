//STUDENT NAME: KENDRICK KEE
//UOW ID: 7366814

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileUtil;

public class solution1 {

    public static void main(String[] args) throws Exception {
        // Setting up args as inputs and outputs
        String input = args[0];// input/weather.txt
        String output = args[1];// output/weather2.txt

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        FileUtil.copy(fs, new Path(input), fs, new Path(output), true, conf);

    }
}
