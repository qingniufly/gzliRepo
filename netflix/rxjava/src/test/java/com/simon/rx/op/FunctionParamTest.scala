package com.simon.rx.op

object FunctionParamTest {

  def onePerSec(callback: () => Unit) {
    while (true) {
      callback();
      Thread sleep 1000
    }
  }
  
  def printSth() {
    println("time flies like an arrow...");
  }
  
  def main(args: Array[String]) {
//    onePerSec(printSth)
//    onePerSec ( () => printSth() )
//    onePerSec { () => printSth() }
    // Anonymous Function
     onePerSec( () => println("hello per sec...") )
  }
  
}