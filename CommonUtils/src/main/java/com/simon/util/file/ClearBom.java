package com.simon.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class ClearBom {
	
	public static boolean isBomFile(File file) {
		boolean isBom = false;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] top3 = new byte[3];
			fis.read(top3);
			if (top3[0] == -17 && top3[1] == -69 && top3[2] == -65) {
				isBom = true;
			}
			
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fis);
		}
		return isBom;
	}
	
	public static void cleanBom(File file) {
		File tempFile = new File(file.getAbsolutePath() + ".tmp");
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(tempFile);
			fis = new FileInputStream(file);
			byte[] top3 = new byte[3];
			fis.read(top3);
			IOUtils.copy(fis, fos);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(fis);
		}
		if (!file.delete()) {
			System.out.printf("Could not delete file[%s]\n", file.getAbsolutePath());
		}
		if (!tempFile.renameTo(file)) {
			System.out.printf("Could not rename file[%s] to [%s]\n", tempFile.getAbsolutePath(), file.getAbsolutePath());
		}
		System.out.println(file.getAbsolutePath() + ">> clear bom finished!");
	}
	
	public static void cleanBomIn(String path) {
//		File file = new File(path);
//		FilenameFilter nameFilter = new EndWithFilenameFilter(".java", EndWithFilenameFilter.NEVER);
//		File[] javaFiles = file.listFiles(nameFilter);
		List<File> javaFiles = EndsWithFilenameUtil.filter(".java", path);
		int count = 0;
		for (File f : javaFiles) {
			if (isBomFile(f)) {
				++count;
				cleanBom(f);
			}
		}
		System.out.println("BOM file number is " + count);
	}
	
	public static void main(String[] args) {
		ClearBom.cleanBomIn(args[0]);
	}

}
