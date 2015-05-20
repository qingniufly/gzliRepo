package com.simon.spark.helloworld

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.uncommons.maths.statistics.DataSet
import org.apache.spark.rdd.RDD


object HelloWorld {

  def wordCount(data: RDD[(String)]): RDD[(String, Int)] = {
    val words = data.flatMap(_.split("\\s+"));
    println("Total word count : %s".format(words.distinct().count()))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_+_)
    wordCounts.cache()
    wordCounts.saveAsTextFile("d:\\data\\program\\spark\\wordCounts")
    val sortedCounts = wordCounts.map{case(x,y) => (y, x)}.sortByKey(false).map{case(i,j) => (j,i)}
    sortedCounts.saveAsTextFile("d:\\data\\program\\spark\\sortedCounts")
    println("Top 5 words :")
    sortedCounts.take(5).foreach{case(word, cnt) => println("%s\t%s".format(word, cnt))}
    return sortedCounts;
  }
  
  def main(args: Array[String]) {
    val logFile = "d:\\software\\spark-1.3.0\\README.md"  // Should be some file on your system
//    val conf = new SparkConf().setAppName("Hello World Application")
    val conf = new SparkConf().setAppName("Hello World Application").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache();
    val numSpark = logData.filter(line => line.contains("Spark")).count()
    val numBs = logData.filter(line => line.contains("a")).count()
    println("Lines with Spark: %s, Lines with b: %s".format(numSpark, numBs))
    wordCount(logData)
    println("Total chars is : ".format(logData.map (_.length).reduce(_ + _)))
    sc.stop()
    
  }
  
}