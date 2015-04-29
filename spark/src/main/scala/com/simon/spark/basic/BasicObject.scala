package com.simon.spark.basic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object BasicObject {

  def main(args: Array[String]) {
    val dataFile = "/home/app/spark/README.md";
    val conf = new SparkConf().setAppName("spark-hello-world").setMaster("local[2]").set("spark.executor..memory", "256m");
    val sc = new SparkContext(conf)
    val lines = sc.textFile(dataFile, 2);
    val lineLengths = lines.map(s => s.length)
    val totalLength = lineLengths.reduce(_ + _)
    //  val totalLength = lineLengths.reduce((a, b) => a + b)
    lines.foreach(println)
    println("Total lenth : %s".format(totalLength))
  }

}