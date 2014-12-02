package com.simon.jmeter.test;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class App extends AbstractJavaSamplerClient {

	private double da;
	
	private double db;
	
	public static double compute(double a, double b) {
		return a * b / a * a;
	}
	
	public double compute() {
		return da * db / da * da;
	}

	@Override
	public Arguments getDefaultParameters() {
		Arguments argu = new Arguments();
		argu.addArgument("da", "");
		argu.addArgument("db", "");
		return argu;
	}
	
	//初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
	@Override
	public void setupTest(JavaSamplerContext context) {
		//获取在Jmeter中设置的参数值
		String sda = context.getParameter("da");
		String sdb = context.getParameter("db");
		if (sda != null && sda.length() > 0) {
			da = Double.parseDouble(sda);
		}
		if (sdb != null && sdb.length() > 0) {
			db = Double.parseDouble(sdb);
		}
//		super.setupTest(context);
	}
	
	@Override
	public void teardownTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		super.teardownTest(context);
	}
	
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult results = new SampleResult();
		results.setSamplerData(String.valueOf(da));
		results.setSamplerData(String.valueOf(db));
		results.sampleStart();
//		Random rand = new Random();
		for (int i = 0; i < 1; i++) {
//			System.out.println("da=" + da + ",db=" + db);
			double d = compute();
//			System.out.println("result="+ d);
			results.setSamplerData(String.valueOf(d));
//			compute(rand.nextDouble(), rand.nextDouble());
		}
		results.sampleEnd();
		results.setSuccessful(true);
		return results;
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.da = 2;
		app.db = 2;
		System.out.println(app.compute());
	}

}
	