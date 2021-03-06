import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class solution3 {

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "solution3");
    job.setJarByClass(solution3.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntMinMaxReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable counter = new IntWritable(0);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        counter.set( Integer.parseInt(itr.nextToken()) );
        context.write(word, counter);
      }
    }
  }

  public static class IntMinMaxReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
       private IntWritable result_min_max = new IntWritable();
       private IntWritable result_sum = new IntWritable();
       private IntWritable result_avg = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
	int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int value;
        int total = 0;
        int counter = 0; 

      for (IntWritable val : values) {
          value = val.get();
          total = total + value;

          counter++;

          if ( value > max )
     	       max = value;
          if ( value < min )
               min = value;
      }
      result_sum.set(total);
      context.write(key, result_sum);

      result_avg.set(total/counter);
      context.write(key, result_avg);

      result_min_max.set(max);
      context.write(key, result_min_max);
      result_min_max.set(min);
      context.write(key, result_min_max);
    }
  }
}
