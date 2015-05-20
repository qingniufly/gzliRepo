package com.simon.spark.helloworld

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object CogroupTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Hello World Application").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
    val a = sc.parallelize(List(1,2,1,3), 1)
    val b = a.map((_, "b"))
    val c = a.map((_, "c"))
//    b.cogroup(c).foreach(println)
    b.cogroup(c).foreach{case(i, (x, y)) => {
      print(i + "=>")
      print(x.mkString(","))
      print(" -- ")
      print(y.mkString(",") + "\n\r")
      }
    }
    sc.stop()
  }
}