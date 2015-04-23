package com.simon.rx.op

/**
 * @author simon
 */
object TestArray extends App {
  
    var greetStrings = new Array[String](3);

    greetStrings(0) = "Hello"
    // Scala里所以东西都是对象，数组也不例外。数组赋值其实也是一个方法完成的。
    // 上面的代码会被编译器转换成两个参数的方法：
    // greetStrings.update(0, "Hello")
    greetStrings(1) = ", "
    greetStrings(2) = "World"
    // for (i <- 0.to(2))  
    // 当方法参数只有一个的时候，可以不写括号和点
    for (i <- 0 to 2) {
      print(greetStrings(i))
      /**
       * 为什么Scala不用中括号呢？是因为Scala有一套通用规则，会把小括号转换成apply。
       * 所以编译器会得到这样的代码：
       * print(greetStrings.apply(i))
       */
    }
  
}