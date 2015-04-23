package com.simon.hystrix.hello

import rx.lang.scala.Observable
import rx.lang.scala.Subscriber

object CommandObservableHelloWorld extends App {
  
  def customObservableNonBlocking(): Observable[String] = {
    Observable(
        (subscriber: Subscriber[String]) => {
        new Thread(new Runnable() {
          def run() {
            for (i <- 0 until 75) {
              if (subscriber.isUnsubscribed) {
                return ;
              }
              subscriber.onNext("value_" + i)
            }
            if (!subscriber.isUnsubscribed) {
              subscriber.onCompleted();
            }
          }
        }).start();
      }    
    )
  }
  
  customObservableNonBlocking().subscribe(println(_));
  
}