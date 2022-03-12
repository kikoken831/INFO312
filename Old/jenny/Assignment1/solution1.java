/*
 * Student Name: Liang Leran Jenny
 * Student UOW ID: 7063167
 * solution1.java
 */

/*
 * This class reads files from a folder and prints out the file names,
 * if exception caught, prints error message
 */

// import classes needed
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class solution1 {

    public static void main(String[] args) throws IOException {

	// create and initialise strings localStr and hdfStr
	// localStr initialised as first item in args
        String localStr = args[0];
	// hdfStr initialised as second item in args
        String hdfsStr = args[1];
	
	// create and initialise new Configuration class object conf
        Configuration conf = new Configuration();

	// create and initialise new FileSystem class objects hdfs and local
	// hdfs gets the FileSystem's URI's scheme and authority based on hdfsStr and conf
        FileSystem hdfs  = FileSystem.get(URI.create(hdfsStr), conf);
	// local gets the configured FileSystem implementation
        FileSystem local = FileSystem.getLocal(conf);
	
	/* create new Path object inputDir
	 * and construct it from localStr */
        Path inputDir = new Path(localStr);

	/* create string folderName
	 * and initialise it
	 * as Path inputDir's name */
        String folderName = inputDir.getName();

	/* create new Path hdfsFile
	 * and initialise it
	 * based on child path (folderName) resolved against parent path (hdfsStr) */
        Path hdfsFile = new Path(hdfsStr, folderName);

	// try-catch block
	// try this
        try {
		/* create FileStatus class object inputFiles
		 * and initialise it as
		 * local's list of statuses of the files in the given path (inputDir) */
            FileStatus[] inputFiles = local.listStatus(inputDir); //may throw exception

		/* create and intialise FSDataOutputStream class object out
		 * based on the indicated path (hdfsFile) */
            FSDataOutputStream out = hdfs.create(hdfsFile); //may throw exception

		// for loop through inputFiles
            for (int i=0; i<inputFiles.length; i++) {

		//print current name of item, i, of inputFiles
                System.out.println(inputFiles[i].getPath().getName());

		/* create and initialise FSDataInputStream in
		 * as the FSDataInputStream at the indicated path
		 * of current item, i, of inputFiles */
                FSDataInputStream in = local.open(inputFiles[i].getPath());
		
		// create new byte array buffer[] with a specified length of array of 256
                byte buffer[] = new byte[256];

		// create new integer bytesRead and initialise it as 0
                int bytesRead = 0;
		
		/* assign the value of in.read(buffer) to bytesRead
		 * while bytesRead is more than 0, proceed */
                while( (bytesRead = in.read(buffer)) > 0) {
                	/* writes bytes (bytesRead) from byte array (buffer)
			 * starting at offset 0 to the output stream (out) */
			 out.write(buffer, 0, bytesRead); 

			/* close input stream (in)
		 	* and release any system resources associated with the stream */
                	in.close();
                }
            }
            // close output stream (out)
            out.close();
        } 
	// catch and handle exception e	
	catch (IOException e) {
		/* print throwable class along with line numbers and class names
		 * to pinpoint exact location of the exception */
		 e.printStackTrace();
        }
    }
}
