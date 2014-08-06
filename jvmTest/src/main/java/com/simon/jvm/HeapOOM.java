package com.simon.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author  : simon
 * @version : Jun 4, 2014 12:21:24 PM
 *
 **/
public class HeapOOM {

	static class OOMObject{

	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
		}

	}

}
