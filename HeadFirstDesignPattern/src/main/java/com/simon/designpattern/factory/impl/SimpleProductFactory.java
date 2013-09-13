package com.simon.designpattern.factory.impl;

import com.simon.designpattern.factory.Product;

/**
 * @author Simon
 *
 */
public class SimpleProductFactory {

	public static Product getOneProduct(String type) {
		if ("car".equals(type)) {
			return new Car();
		} else if ("mobile".equalsIgnoreCase(type)) {
			return new Mobile();
		}
		return null;
	}

}
