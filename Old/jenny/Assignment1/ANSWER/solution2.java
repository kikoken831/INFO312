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
import org.apache.hadoop.util.GenericOptionsParser;

public class solution2 {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        conf.set("value1", otherArgs[0]);
        conf.set("value2", otherArgs[1]);
        conf.set("value3", otherArgs[2]);
	Job job = new Job(conf, "IN Filter");
	job.setJarByClass(solution2.class);
	job.setMapperClass(FilterMapper.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);
	job.setNumReduceTasks(0); // Set number of reducers to zero
	FileInputFormat.addInputPath(job, new Path(args[3]));
	FileOutputFormat.setOutputPath(job, new Path(args[4]));
	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

 public static class FilterMapper
     extends Mapper<Object, Text, Text, IntWritable>{

     private final static IntWritable counter = new IntWritable(0);
     private Text word = new Text();
     private Integer total;
     private Integer value1;
     private Integer value2;
     private Integer value3;
     public void map(Object key, Text value, Context context
		     ) throws IOException, InterruptedException {
	 StringTokenizer itr = new StringTokenizer(value.toString());

	 value1 = Integer.parseInt( context.getConfiguration().get("value1") );
	 value2 = Integer.parseInt( context.getConfiguration().get("value2") );
	 value3 = Integer.parseInt( context.getConfiguration().get("value3") );

	 while (itr.hasMoreTokens()) {
	     word.set(itr.nextToken());
             total = Integer.parseInt(itr.nextToken());
	     if ( total == value1 || total == value2 || total == value3 )    
	     { counter.set( total );
	       context.write(word, counter); }
	 }
     }
 }

}