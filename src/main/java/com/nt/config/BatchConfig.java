package com.nt.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

	private String arr[]= {"Rituraj","Rawat","Puneet","Meet","Amit","Kapil"};
	private int count=0;
	//item reader with lambda
	ItemReader<String> itemReader= ()->{
			System.out.println(" From Item Reader");
				if(count<arr.length) {
						return arr[count++];
				}
				else {
					count=0;
				}
		return null;
		}; 
		//item processor with lambda
		ItemProcessor<String, String> itemProcessor=(i)->{
				System.out.println("From Item Processsor");		
				return "Process: "+i.toUpperCase();
		};		
	
		//item writer with lambda 
		ItemWriter<String> itemWriter=(items)->{
				System.out.println("Item Writer Data Is: "+items);
		};
		
		//JobExecution Listener
		JobExecutionListener listener= new JobExecutionListener() {
			@Override
			public void beforeJob(JobExecution jobExecution) {
				System.out.println("This Job Started  from Before");
			}
			@Override
			public void afterJob(JobExecution jobExecution) {
				System.out.println("After Job :"+jobExecution.getStatus().toString());
				
			}
		}; 
	
		
		
	@Autowired
	private JobBuilderFactory jf;
	
	@Autowired
	private StepBuilderFactory sf;				
	
	@Bean
	public Step s1() {		
		return sf.get("S1")
			.<String,String>chunk(3)
			.reader(itemReader)
			.processor(itemProcessor)
			.writer(itemWriter)
			.build()	
			;	
	}
	
	@Bean
	public Job j1() {
		return  jf.get("JOB")
			.listener(listener)
			.start(s1())
			.build();
	}
	
}