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

public class solution3 {

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(solution3.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper
      extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private Text xs = new Text();
    private Text s = new Text();
    private Text m = new Text();
    private Text l = new Text();
    private Text xl = new Text();
    private Text xxl = new Text();
    
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      xs.set("X short:");
      s.set("short:");
      m.set("medium:");
      l.set("long:");
      xl.set("X long:");
      xxl.set("XX long:");
      //init map of keys but empty
      context.write(xs,IntWritable(0));
      context.write(s,IntWritable(0));
      context.write(m,IntWritable(0));
      context.write(l,IntWritable(0));
      context.write(xl,IntWritable(0));
      context.write(xxl,IntWritable(0));
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());

        if (word.getLength() >= 1 && word.getLength() <= 3)
          context.write(xs, one);
        else if (word.getLength() >= 4 && word.getLength() <= 5)
          context.write(s, one);
        else if (word.getLength() >= 6 && word.getLength() <= 8)
          context.write(m, one);
        else if (word.getLength() >= 9 && word.getLength() <= 12)
          context.write(l, one);
        else if (word.getLength() >= 13 && word.getLength() <= 15)
          context.write(xl, one);
        else
          context.write(xxl, one);
      }
    }
  }

  public static class IntSumReducer
      extends Reducer<Text, IntWritable, Text, Text> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
        Context context) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      Text formatKey = new Text(String.format("%-8s", key.toString()));
      Text formatVal = new Text(Integer.toString(sum) + " words");
      context.write(formatKey, formatVal);
    }
  }
}
