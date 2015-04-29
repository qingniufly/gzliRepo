package com.simon.gradle.plugin;

import org.gradle.api.Plugin
import org.gradle.api.Project

class GraphVizPlugin implements Plugin<Project> {

	def rootProject

	def project

	def allProjects = [:]

	def allProjectNames = []

	@Override
	void apply(final Project project) {
		this.project = project
		rootProject = project.rootProject
		rootProject.allprojects.each {
			allProjects.putAt(it.name, it)
		}
		allProjectNames = rootProject.allprojects.collect {it.name}
		project.task("dependenciesGraph") << {
			description = "Create a dependency graph for all projects using Graphviz."
			group = "Dependency Grapher"
//			project.afterEvaluate {generateDependenciesGraph(project)}
			generateDependenciesGraph(project)
		}
	}

	def generateDependenciesGraph(project) {
//		println "Root Project is : " + rootProject.name
//		rootProject.subprojects.each { println "proj in root : " + it.name }
//		println('This is : ' + project.name)
//		project.configurations['compile'].allDependencies.each { println("++++	${it.group}:${it.name}:${it.version}") }

		def graph = new GraphvizDigraph(project.name)
		// 添加project节点
		graph.add("${project.name}")

		def configuration = "compile"

		// 过滤依赖的project
		def dependencies = project.configurations[configuration].resolvedConfiguration.firstLevelModuleDependencies
		if (dependencies.isEmpty()) {
			graph.compile()
			return 
		}
		def deps = []
		dependencies.each {
			if (isProjectDependency(it)) {
//				println(" proj dep is : $it.name")
				deps.add(it)
			}
		}

		addDependenciesToGraph(graph, deps, project, configuration)

		graph.compile()
	}

	def addDependenciesToGraph(graph, dependencies, project,  configuration) {
		while (!dependencies.isEmpty() ) {
			def dependency = dependencies.pop()
			// 添加此项目依赖
			graph.add("${project.group}:${project.name}:${project.version}", dependency.name)

			// 获取此项目的下级项目依赖
			def depName = getDependencyName(dependency)
//			println("depName $depName")
			def depProj = allProjects[depName]
//			println("depProj $depProj")
			def depdeps = depProj.configurations[configuration].resolvedConfiguration.firstLevelModuleDependencies
//			println("depdeps $depdeps")
			def filterDeps = []
			depdeps.each {
				if (isProjectDependency(it)) {
					filterDeps.add(it)
				}
			}
			if (!filterDeps.isEmpty()) {
//				println("$depProj.name [][][][]" + filterDeps)
				addDependenciesToGraph(graph, filterDeps, depProj, configuration)
			}
//			depdeps = depdeps.findAll { isProjectDependency(it) }
//			if (!depdeps.isEmpty()) {
//				depdeps = []<< depdeps
//				depdeps.flatten()
//				dependencies.addAll(depdeps)
//			}
		}
	}

	def getDependencyName(dependency) {
		def depname = dependency.name.split(":")[1]
	}

	def addProjectDependencies(graph, project, configuration) {
		addProjectToGraph(project, graph)
		if (!hasProjectConfiguration(project, configuration)) {
			return ;
		}

		def stack = getFirstLevelDependencies(project, configuration)

		addFirstLevelDependenciesToGraph(stack, graph)
		//		addChildDependenciesToGraph(stack, graph)
	}

	def addProjectToGraph(project, graph) {
		graph.add("${project.name}")
		//		if (!project.group.isEmpty() && project.version != 'unspecified') {
		//			graph.add("${project.group}:${project.name}:${project.version}")
		//		}
	}

	def hasProjectConfiguration(project, configuration) {
		project.configurations.collect{it.name}.contains(configuration)
	}

	def getFirstLevelDependencies(project, configuration) {
		def dependencies = project.configurations[configuration].resolvedConfiguration.firstLevelModuleDependencies
//		println("---First level deps : " )
//		dependencies.each {println("---		" + it.name)}
		//		dependencies = dependencies.findAll{isProjectDependency(it)}
		dependencies = []<< dependencies
		dependencies.flatten()
	}

	def addFirstLevelDependenciesToGraph(stack, graph) {
		def projectNames = rootProject.allprojects.collect {it.name}
		//		println "all proj : ---=== " + projectNames
		stack.each {dependency ->
			//			println("dep group : " + dependency.group + ", dep name : " + dependency.name + ", dep version : " + dependency.version)
			//			println(dependency.name + " is proj? ===" + isProjectDependency(dependency))
			if (isProjectDependency(dependency)) {
				graph.add("${project.group}:${project.name}:${project.version}", dependency.name)
			}
		}
	}

	def addChildDependenciesToGraph(stack, graph) {
		while (!stack.isEmpty() ) {
			def dependency = stack.pop()

			dependency.children.findAll{isProjectDependency(it)}.each {
				graph.add(dependency.name, it.name)
			}

			if (!dependency.children.isEmpty()) {
				stack.addAll(dependency.children)
			}
		}
	}

	def isProjectDependency(dependency) {
//		println(allProjectNames)
//		println(dependency.name.split(":")[1])
		def contains = allProjectNames.contains(dependency.name.split(":")[1])
	}

	class GraphvizDigraph {

		private def data = []

		private def root = ''

		def GraphvizDigraph(name) {
			name = name.replace("-", "_")
			root = name
			data << "digraph $name {"
			data << "node [shape=box]"
			data << "\"$name\""
		}

		def add(node) {
			//			node = node.replace(":", ":\n")
			node = node.replace("-", "_")
			if (root != node) {
				data << "\"$root\" -> \"$node\""
			}
		}

		def add(left, right) {
			left = left.replace("-", "_")
			right = right.replace("-", "_")
			def (lg, ln, lv) = left.tokenize(':')
			def (rg, rn, rv) = right.tokenize(':')
			//			left = left.replace(":", ":\n")
			//			right = right.replace(":", ":\n")
			data << "\"$ln\" -> \"$rn\""
		}

		def compile() {
			data << "}"
//			println(data)
			// TODO make this filename and type user definable.
			def fileName = "/tmp/$root-dependencies.pdf"
			def command = "dot -Tpdf -o$fileName"
			def serr = new StringBuffer(), 	sout = new StringBuffer()
			def process = command.execute()
			process.consumeProcessOutput(sout, serr)
			data = data.unique { a, b -> a <=> b}
			data.each { process << "$it\n"}
			process.getOut().close()
//			process.text.eachLine {println(it)}
			println("out>$sout   err>$serr")
//			println("Dependency graph was successfully written to $fileName")
		}

	}

}
