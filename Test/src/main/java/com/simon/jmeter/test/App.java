package com.simon.jmeter.test;

import java.util.Random;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class App extends AbstractJavaSamplerClient {

	public static double compute(double a, double b) {
		return a * b / a * a;
	}
	
	@Override
	public void setupTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		super.setupTest(context);
	}
	
	@Override
	public void teardownTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		super.teardownTest(context);
	}
	
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult result = new SampleResult();
		result.sampleStart();
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			compute(rand.nextDouble(), rand.nextDouble());
		}
		result.sampleEnd();
		result.setSuccessful(true);
		return result;
	}

}
	