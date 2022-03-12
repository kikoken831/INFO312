/*
 * Student Name: Liang Leran Jenny
 * Student UOW ID: 7063167
 * solution4.java
 */

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

public class solution4 {

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "account balance");
    job.setJarByClass(solution4.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(BalanceReducer.class);
    job.setReducerClass(BalanceReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable accBalance = new IntWritable(1);
    private Text accNum = new Text();
    private Text date = new Text();
    private Text accDate = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        accNum.set(itr.nextToken());
        date.set(itr.nextToken());
        accBalance.set(Integer.parseInt(itr.nextToken()));
        
        String str_accNum = accNum.toString();
        String str_date = date.toString().substring(7);
        
        accDate.set(str_accNum + ' ' + str_date);
        
        // only print when total deposit made is more than 0
        if(accBalance.get() > 0)
        {
        	context.write(accDate, accBalance);
        }
      }
    }
  }

  public static class BalanceReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
      // only add when val is positive since deposits are positive values
      if(val.get() > 0){
        	sum += val.get();
        }
      }
      result.set(sum);
      context.write(key, result);
    }
  }
}
