package com.simon.spark.avro

import org.apache.spark.sql._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object AvroFile {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Hello World Application").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    
  }
  
}