package com.nt.runner;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner{

	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private Job job;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("---Batch EX---");
		JobParameters jobParameters=new JobParametersBuilder()
									.addDate("date", new Date())
									.addLong("time",System.currentTimeMillis())
									.toJobParameters();
		
		launcher.run(job, jobParameters);
			
	}
}
