//package com.simon.gradle.tasks;
//
//
//import org.gradle.api.DefaultTask;
//import org.gradle.api.Project;
//import org.gradle.api.Task;
//import org.gradle.api.tasks.TaskAction;
//
//import com.simon.gradle.convention.GraphVizConvention;
//
//public class GraphVizTask extends DefaultTask {
//
//	@TaskAction
//	public void exec() {
//		System.out.println("hello " + getProject().getName());
//		System.out.println("graphVizPlugin ext extName:"
//				+ ((GraphVizConvention) getProject().getConvention()
//						.getPlugins().get("graphVizPlugin")).getExtName());
//		
//		this.doLast(generateDependencyGraph(getProject()));
//		this.doLast(new Action<? super Task>(){});
//		
//		this.doLast(new Closure)
//	}
//	
//	private void generateDependencyGraph(Project project) {
//		
//	}
//
//}
