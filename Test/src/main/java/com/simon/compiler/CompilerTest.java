package com.simon.compiler;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public class CompilerTest {

	public static void main(String[] args) {
		String className = "Main";
		String source = "public class " + className + " { " + 
							"public static void main(String[] args) { " + 
								"System.out.println(\"Hello world! \"); " + 
							"} " + 
						"}";
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		StringSourceJavaObject sourceObject = new StringSourceJavaObject(className, source);
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject);
		Iterable<String> options = Arrays.asList("-d", CompilerTest.class.getResource("/").getPath());
		CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
		boolean result = task.call();
		if (result) {
			System.out.println("编译成功!");
			ClassLoader loader = CompilerTest.class.getClassLoader();
			try {
				Class<?> clazz = loader.loadClass(className);
				Method main = clazz.getMethod("main", String[].class);
				main.invoke(null, new Object[] {new String[] {}});
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("运行出错!");
			}
		}
		
	}
	
	static class StringSourceJavaObject extends SimpleJavaFileObject {

		private String content;
		
		protected StringSourceJavaObject(String name, String content) {
			super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
			this.content = content;
		}
		
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return content;
		}
		
		
	}
	
}
