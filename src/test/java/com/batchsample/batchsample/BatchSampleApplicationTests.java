package com.batchsample.batchsample;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchSampleApplicationTests {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Test
	void contextLoads() throws Exception {
		final JobParameters jobParameter = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.addString("nomeArquivo", "teste.txt")
				.addString("pathArquivo", "C:\\diretorios_git\\spring-batch-sample-techtalks")
				.toJobParameters();

		jobLauncher.run(job, jobParameter);
	}

}
