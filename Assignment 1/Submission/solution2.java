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
    Job job = Job.getInstance(conf, "Weather Values");

    job.setJarByClass(solution2.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntMinMaxReducer.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>{

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

	String[] arr = value.toString().split(",");
	
	Text word = new Text(arr[0]);
	IntWritable val = new IntWritable(Integer.parseInt(arr[2]));
	context.write(word , val);	

    }
  }

  public static class IntMinMaxReducer
       extends Reducer<Text,IntWritable,Text,Text> {
    private IntWritable result = new IntWritable(0);

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
	int max = Integer.MIN_VALUE;
	int min = Integer.MAX_VALUE; 
	int sum = 0;

	for (IntWritable val : values) {
		sum += val.get();

		if ( val.get() > max ){
			max = val.get();
		}
		if ( val.get() < min ){
			 min = val.get();
		}
	}
	String apnd = Integer.toString(sum) + "\t" + Integer.toString(max) + "\t" + Integer.toString(min);
	Text formattedKey = new Text( String.format("%-20s" ,key.toString()) );
	Text formattedRes = new Text(apnd);
	context.write(formattedKey, formattedRes);
    }
  }
}
