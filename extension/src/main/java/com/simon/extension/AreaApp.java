package com.simon.extension;

import java.awt.Rectangle;

public class AreaApp {
	public static void main(String[] args) {
		int width = 10;
        int height = 5;

        Rectangle r = new Rectangle(width, height);
        System.out.println("The rectangle's area is " 
                           + RectangleArea.area(r));
	}
}
