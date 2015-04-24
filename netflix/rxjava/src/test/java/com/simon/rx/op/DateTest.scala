package com.simon.rx.op

import java.util.{Date, Locale}
import java.text.DateFormat
import java.text.DateFormat._
import java.util.Locale

object DateTest extends App{

  val now = new Date
  val df = getDateInstance(LONG, Locale.CHINA)
  println(df format now)
  
  
}