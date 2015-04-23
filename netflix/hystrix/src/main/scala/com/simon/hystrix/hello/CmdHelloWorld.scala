//package com.simon.hystrix.hello
//
//import rx.Observable
//import com.netflix.hystrix.HystrixCommand
//import com.netflix.hystrix.HystrixCommandGroupKey
//
//class CmdHelloWorld extends HystrixCommand[String] {
//  val name: String
//  
//  def CmdHelloWorld(name: String) {
//    HystrixCommandGroupKey.Factory.asKey("ExampleGroup");
//    super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
//    this.name = name;
//  }
//  def run(): String = {
//    ???
//  }
//}