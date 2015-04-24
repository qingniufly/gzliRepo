package com.simon.rx.op

class Complex(real: Double, imaginary: Double) {

  def rm() = real
  
  def im() = imaginary
  
}

class ComplexS(real: Double, imaginary: Double) {
  def rm = real
  def im = imaginary
  
  override def toString() = 
    "" + rm + (if (im < 0) "" else "+") + im + "i"
}

object ComplexNumbers {
  
  def main(args: Array[String]): Unit = {
    val c = new Complex(1.2, 3.4)
    println("imaginary part is: " + c.im())
    
    val cs = new ComplexS(1.1, 3.3)
    println("imaginary part is: " + cs.im)
    println(cs)
  }
  
}