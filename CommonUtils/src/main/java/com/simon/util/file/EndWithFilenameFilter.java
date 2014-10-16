package com.simon.util.file;

import java.io.File;
import java.io.FilenameFilter;

public class EndWithFilenameFilter implements FilenameFilter {

	public final static int ALWAYS = 0;
	public final static int NEVER = 1;
	public final static int MATCH = 2;

	String[] endings = null;
	int accept_dirs;

	public EndWithFilenameFilter(String[] endings, int accept_dirs) {
		this.endings = endings;
		this.accept_dirs = accept_dirs;
	}

	public EndWithFilenameFilter(String ending, int accept_dirs) {
		this.endings = new String[] { ending };
		this.accept_dirs = accept_dirs;
	}

	public boolean accept(File dir, String name) {
		if (accept_dirs != MATCH && new File(dir, name).isDirectory())
			return (accept_dirs == ALWAYS);
		for (int i = endings.length; --i >= 0;)
			if (name.endsWith(endings[i]))
				return true;
		return false;
	}

}
