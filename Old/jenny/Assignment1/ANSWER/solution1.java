import java.io.IOException;
import java.net.URI;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

// The program merges all files located at a folder on a local file system and
// loads the outcomes of merge to HDFS as a single file                         

public class solution1 {

    public static void main(String[] args) throws IOException {

// The program has  two parameters:
//   a path to folder on a local file system with the files to be merged
//   a path and a name of file that contains the results of merge in HDFS	
        String localStr = args[0];
        String hdfsStr = args[1];

// We start from creation of a an object with HDFS configuration
        Configuration conf = new Configuration();

// Next, we create handles for input folder at a local file system and
// and handle for output file in HDFS
        FileSystem hdfs  = FileSystem.get(URI.create(hdfsStr), conf);
        FileSystem local = FileSystem.getLocal(conf);

// Next, we create a string with a path a name of a folder with input files and ..
        Path inputDir = new Path(localStr);
        String folderName = inputDir.getName();
// ... a path to a file in HDFS 
        Path hdfsFile = new Path(hdfsStr, folderName);

        try {
// Next, we create a list of names of files localed in a folder on a local file system and ...
	    FileStatus[] inputFiles = local.listStatus(inputDir);
// ... and a handle output file in HDFS
            FSDataOutputStream out = hdfs.create(hdfsFile);

// Next, we iterate over the files in a folder on a local file system and we copy the files
// to a buffer and buffer is immediately written to an output file in HDFS
            for (int i=0; i<inputFiles.length; i++) {
                System.out.println(inputFiles[i].getPath().getName());
                FSDataInputStream in = local.open(inputFiles[i].getPath());
                byte buffer[] = new byte[256];
                int bytesRead = 0;
                while( (bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}