import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.ArrayWritable;
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
    job.setReducerClass(IntMinMaxSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
	public static class IntArrayWritable extends ArrayWritable {

	    public IntArrayWritable(IntWritable[] values) {
		super(IntWritable.class, values);
	    }

	    @Override
	    public IntWritable[] get() {
		return (IntWritable[]) super.get();
	    }

	    @Override
	    public String toString() {
		IntWritable[] values = get();
		return values[0].toString() + " " + values[1].toString() + " " + values[2].toString();
	    }
	}

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable counter = new IntWritable(0);
    private Text word = new Text();
	private Text dump = new Text();
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString(),",");
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
	dump.set(itr.nextToken());
        counter.set( Integer.parseInt(itr.nextToken()) );
        context.write(word, counter);
      }
    }
  }

  public static class IntMinMaxSumReducer
       extends Reducer<Text,IntWritable,Text,IntArrayWritable> {

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int max = Integer.MIN_VALUE;
      int min = Integer.MAX_VALUE; 
      int sum = 0;


      for (IntWritable val : values)
      {
	      sum += val.get();

		  if ( val.get() > max )
		  {
		      max = val.get();
		  }
	      if ( val.get() < min )
	      {
	          min = val.get();
	      }
      }
      IntWritable[] temp = new IntWritable[3];
      IntArrayWritable output = new IntArrayWritable(temp);
      temp[0] = new IntWritable(sum);
      temp[1] = new IntWritable(max);
      temp[2] = new IntWritable(min);
      output.set(temp);
      context.write(key,new IntArrayWritable(output.get()));

    }
  }
}
