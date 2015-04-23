package com.simon.rx.op

import scala.collection;

object TestForeach {
  def main(args:Array[String]) {
    val a = Traversable(1,2,3);
    a.foreach((x: Int) => println(x) );
    a.foreach(println);
    for (x <- a) {
    	println(x);
    }
    
    for (a <- 0 until 10
        if a != 2; if a % 2 == 0)
      println(a);

    
    val arr = for (a <- 0 until 10
        if a != 2; if a % 2 == 0)yield a;
    
    arr.foreach(println)
    
  }
}