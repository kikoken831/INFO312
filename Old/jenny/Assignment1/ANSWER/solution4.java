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
    Job job = Job.getInstance(conf, "Total deposit");
    job.setJarByClass(solution4.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(solution4Reducer.class);
    job.setReducerClass(solution4Reducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private Text account = new Text();
    private Text date = new Text();
    private final static IntWritable amount = new IntWritable(0);    
    private Text year = new Text();
    private Text account_year = new Text();
    private Integer int_amount;

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        account.set(itr.nextToken());
        date.set(itr.nextToken());
        String string_account = account.toString();
        String string_year = date.toString().substring(7);  

        account_year.set(string_account+string_year);
        int_amount = Integer.parseInt(itr.nextToken());

        if (int_amount > 0) {
          amount.set(int_amount );
          context.write(account_year, amount);
        }
      }
    }
  }

  public static class solution4Reducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int total = 0;

      for (IntWritable val : values) 
	  total = total + val.get();

      result.set(total);
      context.write(key, result);
    }
  }
}