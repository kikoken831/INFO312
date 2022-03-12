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

public class solution2 {

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(solution2.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(MapReducer.class);
    job.setReducerClass(MapReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

	private final static IntWritable speed = new IntWritable(0);
	private Text carRego = new Text();
	private Text incDate = new Text();

	public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        carRego.set(itr.nextToken());
	incDate.set(itr.nextToken());
	speed.set(Integer.parseInt(itr.nextToken()));
        context.write(carRego, speed);
      }
    }

  }

  public static class MapReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      int counter = 0;
      int speed = 0;
      for (IntWritable val : values) {
        speed = val.get();
	if (speed > 60) {
           sum += val.get();
           counter++;
	}
      }
      if (counter > 0) {
      	result.set(sum/counter);
      }
      context.write(key, result);
    }
  }
}