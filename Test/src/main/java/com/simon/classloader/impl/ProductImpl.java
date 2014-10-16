package com.simon.classloader.impl;

import com.simon.classloader.Product;

public class ProductImpl implements Product {

	@Override
	public void show() {
		System.out.println("ProductImpl");
	}

}
