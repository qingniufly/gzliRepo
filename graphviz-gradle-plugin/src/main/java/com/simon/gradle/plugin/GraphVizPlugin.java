//package com.simon.gradle.plugin;
//
//import org.gradle.api.Plugin;
//import org.gradle.api.Project;
//
//import com.simon.gradle.convention.GraphVizConvention;
//import com.simon.gradle.tasks.GraphVizTask;
//
//public class GraphVizPlugin implements Plugin<Project> {
//
//	@Override
//	public void apply(final Project project) {
//		// 引用groovy扩展变量
//		final GraphVizConvention pluginConvention = new GraphVizConvention(project);
//		project.getConvention().getPlugins().put("graphVizPlugin", pluginConvention);
//		
//		// 创建GraphVizTask任务
////		project.getTasks().add("graphViz", GraphVizTask.class);
//		project.getTasks().create("graphViz", GraphVizTask.class);
//	}
//
//}
