import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.fs.FileStatus;

public class solution1 {

    public static void main(String[] args) throws Exception {
        // Setting up args as inputs and outputs
    	String input1 = args[0];
        String input2 = args[1];
        String output = args[2];

        // Make new configuration
        Configuration conf = new Configuration();

        // Setting up input files
        Path inputDir1 = new Path(input1);
        Path inputDir2 = new Path(input2);
        String input1Name = inputDir1.getName();
        String input2Name = inputDir2.getName();
        FileSystem fsInput1 = FileSystem.get(URI.create(input1Name), conf);
        FileSystem fsInput2 = FileSystem.get(URI.create(input2Name), conf);

        // Setting up output file
        Path outputDir = new Path(output);
        String outputName = outputDir.getName();
        FileSystem fsOutput = FileSystem.get(URI.create(outputName), conf);
        
        // Creating output filesystem and set for creation
        FSDataOutputStream fsOutputStream = fsOutput.create(outputDir, true);
        
        // Add first file to output
        FileStatus[] inputFile1 = fsInput1.listStatus(inputDir1);
        for (int i = 0; i < inputFile1.length; i++)
        {
        	FSDataInputStream in = fsInput1.open(inputFile1[i].getPath());
        	byte buffer[] = new byte[256];
        	int bytesRead = 0;
        	while ((bytesRead = in.read(buffer)) > 0)
        	{
        		fsOutputStream.write(buffer, 0, bytesRead);
        	}
        	in.close();
        }
        // Close output filesystem
        fsOutputStream.close();

        // Set output file system for appending
        fsOutputStream = fsOutput.append(outputDir);
        // Append second file to output
        FileStatus[] inputFile2 = fsInput2.listStatus(inputDir2);
        for (int i = 0; i < inputFile2.length; i++)
        {
        	FSDataInputStream in = fsInput1.open(inputFile2[i].getPath());
        	byte buffer[] = new byte[256];
        	int bytesRead = 0;
        	while ((bytesRead = in.read(buffer)) > 0)
        	{
        		fsOutputStream.write(buffer, 0, bytesRead);
        	}
        	in.close();
        }
                
        // Closing all
        fsOutputStream.close();
        fsOutput.close();
        fsInput1.close();
        fsInput2.close();
        
    }
}
