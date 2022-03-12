import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one);
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }
}

Min Zhan (Cunt), [12.10.21 00:34]
package Java;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TextFile {
    private static Formatter write;
    private static Scanner read;
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
            String fileName1 = args[0];
            String fileName2 = args[1];
            String inputText = args[2];

            fopen(fileName1);
            fwrite(fileName1, inputText);
            fread(fileName1);
            fclose(fileName1);

        
            fopen(fileName2);
            String inputText2 = inputText + "defg";
            fwrite(fileName2,inputText2);
            fread(fileName2);
            fclose(fileName2);
    }

    private static void fopen(String fileName) {
        try {
            read = new Scanner(Paths.get(fileName));
            System.out.println("Reading " + fileName);
        } catch (IOException e) {
            System.err.println(fileName + " does not exist.\nCreating " + fileName + ". ");
            String dirName = "";

            try {
                System.out.print("Create a new directory? [Y]/[N]: ");
                String str = scan.nextLine();

                if (str.equalsIgnoreCase("Y")) {
                    System.out.print("Enter folder name: ");
                    dirName = scan.nextLine();

                    File fileDir = new File(dirName);
                    fileDir.mkdir();
                    write = new Formatter(fileName);
                    System.out.println(fileName + " created successfully.");
                }
                
                if (str.equalsIgnoreCase("N")) {
                    write = new Formatter(fileName);
                    System.out.println(fileName + " created successfully.");
                }

            } catch (SecurityException | IOException | InputMismatchException e1) {
                System.err.println(e1);
                System.exit(1);
            }
        }
    }

    private static void fclose(String fileName) {
        if (read != null) {
            read.close();
        } else if (write != null) {
            write.close();
        }

        System.out.println(fileName + " closed.");
    }

    private static void fread(String fileName) {
        fclose(fileName);

        try {
            fopen(fileName);
            System.out.println("Reading file " + fileName + ". ");

            while (read.hasNext()) {
                System.out.printf("%s \n", read.nextLine());
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private static void fwrite(String fileName, String text) {
        File file = new File(fileName);

        if (file.length() == 0) {
            try {
                write.format("%s", text);
                System.out.println("Inserted text into " + fileName + ". ");
                fclose(fileName);
            } catch (FormatterClosedException e) {
                System.err.println("Error writing.");
                System.exit(1);
            }
        } else {
            try {
                FileWriter fileWriter = new FileWriter(fileName, true);
                System.out.print("Enter your message in:");
                String str = scan.nextLine();
                fileWriter.write("\n" + str);
                fileWriter.close();
                scan.close();
            } catch (IOException e1) {
                System.err.println(e1);
            }
        }
    }
}
