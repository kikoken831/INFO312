/*
 * Student Name: Liang Leran Jenny
 * Student UOW ID: 7063167
 * solution2.java
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
import org.apache.hadoop.util.GenericOptionsParser;

public class solution2 {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
	// old code
        //conf.set("limit", otherArgs[0]);
	// new code
	// take in three values as arguments instead of just one
        conf.set("limit1", otherArgs[0]);
        conf.set("limit2", otherArgs[1]);
        conf.set("limit3", otherArgs[2]);
	
	Job job = new Job(conf, "Distributed Filter");
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
     // old code
     //private Integer limit;
     // new code
     // initialise for the three limit values
     private Integer limit1;
     private Integer limit2;
     private Integer limit3;
     public void map(Object key, Text value, Context context
		     ) throws IOException, InterruptedException {
	 StringTokenizer itr = new StringTokenizer(value.toString());
	 // old code
	 //limit = Integer.parseInt( context.getConfiguration().get("limit") );
	 // new code
	 limit1 = Integer.parseInt( context.getConfiguration().get("limit1") );
	 limit2 = Integer.parseInt( context.getConfiguration().get("limit2") );
	 limit3 = Integer.parseInt( context.getConfiguration().get("limit3") );
	 
	 while (itr.hasMoreTokens()) {
	     word.set(itr.nextToken());
             total = Integer.parseInt(itr.nextToken());
             // old code
	     //if(total > limit)
	     // new code
	     if ( total == limit1 || total == limit2 || total == limit3 )    
	     { counter.set( total );
	       context.write(word, counter); }
	 }
     }
 }

}
