import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;



public class wordCount extends Configured implements Tool  {
// this is the first project in mapreduce
	@Override
	public int run(String[] args) throws Exception {
		if(args.length<2)
		{
			System.out.println(" plz give input and output directory");
			return -1; 
		}
		JobConf conf= new JobConf(wordCount.class);
		FileInputFormat.setInputPaths(conf, new Path (args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		conf.setMapperClass(WordMapper.class);
		conf.setReducerClass(wordReducer.class);
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		JobClient.runJob(conf);
		return 0;
	}
public  static void main (String args[]) throws Exception
{
	// this is atest
	int exitCode = ToolRunner.run(new wordCount(), args);
	System.exit(exitCode);
}
}
