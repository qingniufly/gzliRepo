package com.simon.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EndsWithFilenameUtil {
	
	public static List<File> filter(String subfix, String path) {
		List<File> result = new ArrayList<>();
		Stack<File> stack = new Stack<File>();
		stack.add(new File(path));
		while (!stack.isEmpty()) {
			File pFile = stack.pop();
			if (pFile.isDirectory()) {
				for (File f : pFile.listFiles()) {
					if (f.isDirectory()) {
						stack.add(f);
					} else if (f.getName().endsWith(subfix)) {
						result.add(f);
					}
				}
			} else if (pFile.getName().endsWith(subfix)) {
				result.add(pFile);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String path = "/work/gzliRepo/CommonUtils/src/main/java";
		List<File> javaFiles = filter(".java", path);
		for (File f: javaFiles) {
			System.out.println(f.getAbsolutePath());
		}
	}

}
