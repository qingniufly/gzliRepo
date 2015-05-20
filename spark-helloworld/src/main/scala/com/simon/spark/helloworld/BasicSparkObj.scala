package com.simon.spark.helloworld

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BasicSparkObj {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Hello World Application").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
		val arr = Array(1,2,3,4,5)
    val distData = sc.parallelize(arr);
    distData.foreach(println) 
    println(distData.reduce(_ + _))
    }
  
}