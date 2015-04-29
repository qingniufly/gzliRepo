package com.simon.gradle.convention;

import org.gradle.api.Project

public class GraphVizConvention {
	
	String extName;
	
	final Project project;
	
	def rootProject;
	
	def group;
	
	def GraphVizConvention(Project project) {
		this.project = project;
		rootProject = project.rootProject
		extName = 'test';
		group = 'Dependency Grapher';
	}
	
	public String getExtName() {
		return extName;
	}
	
}
