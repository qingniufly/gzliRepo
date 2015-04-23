package com.simon.rx.op

object TestMethod {
  def max(a:Int, b:Int):Int = if(a < b) b else a;
  def main(args:Array[String]) {
   var x = max(2,3);
   println(x);
   var y = max _;
   println(y(10, 8));
  }
}