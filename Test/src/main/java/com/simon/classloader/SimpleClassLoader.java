package com.simon.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SimpleClassLoader extends ClassLoader {
	
	private String[] dirs;
	
	public SimpleClassLoader(String path) {
		dirs = path.split(System.getProperty("path.separator"));
		String[] _dirs = dirs.clone();
		for (String dir : _dirs) {
//			extendClasspath(dir);
			getParentClasspath(dir);
		}
	}

	@SuppressWarnings("unused")
	private void extendClasspath(String path) {
		String[] segments = path.split(File.separator);
		String[] exDirs = new String[segments.length];
		for (int i = 0; i < (segments.length); i++) {
			exDirs[i] = popd(segments, i);
		}
		String[] newDirs = new String[dirs.length + exDirs.length];
		System.arraycopy(dirs, 0, newDirs, 0, dirs.length);
		System.arraycopy(exDirs, 0, newDirs, dirs.length, exDirs.length);
		dirs = newDirs;
	}
	
	private void getParentClasspath(String path) {
		String[] segments = path.split(File.separator);
		int size = path.startsWith(File.separator) ? segments.length : segments.length - 1;
		String[] exDirs = new String[size];
		if (path.endsWith(File.separator)) {
			path = path.substring(0, path.length() - 1);
		}
		int i = size - 1;
		while (path.contains(File.separator)) {
			exDirs[i--] = path.substring(0, path.lastIndexOf(File.separatorChar)) + File.separatorChar;
			path = path.substring(0, path.lastIndexOf(File.separatorChar));
		}
		String[] newDirs = new String[dirs.length + exDirs.length];
		System.arraycopy(exDirs, 0, newDirs, 0, exDirs.length);
		System.arraycopy(dirs, 0, newDirs, exDirs.length, dirs.length);
		dirs = newDirs;
	}
	
	private String popd(String[] pathSegments, int level) {
		StringBuffer path = new StringBuffer();
		for (int i = 0; i < level; i++) {
			path.append(pathSegments[i]).append(File.separatorChar);
		}
		return path.toString();
	}

	@Override
	protected synchronized Class<?> findClass(String name) throws ClassNotFoundException {
		for (String dir : dirs) {
			byte[] buff = getClassData(dir, name);
			if (buff != null) {
				System.out.printf("load class[%s] from dir[$s]\n", name, dir);
				return defineClass(name, buff, 0, buff.length);
			}
		}
		throw new ClassNotFoundException();
	}
	
	private byte[] getClassData(String dir, String name) {
		String[] tokens = name.split("\\.");
		String classFile = dir + File.separatorChar + tokens[tokens.length - 1] + ".class";
		File f = new File(classFile);
		int clsSize = new Long(f.length()).intValue();
		byte[] buf = new byte[clsSize];
		try {
			FileInputStream fis = new FileInputStream(f);
			fis.read(buf);
			fis.close();
		} catch (FileNotFoundException fe) {
			return null;
		} catch (IOException ioe) {
			return null;
		}
		return buf;
	}

	public static void main(String[] args) throws ClassNotFoundException {
//		String path = "/usr/local/data/";
//		System.out.println(path.substring(0, path.length() - 1));
//		System.out.println(Arrays.deepToString(new SimpleClassLoader("/usr/local/data/").dirs));
		
		ClassLoader loader = new SimpleClassLoader("target/classes/com/simon/classloader");
		loader.loadClass("SimpleClassLoader");
	}
	
}
