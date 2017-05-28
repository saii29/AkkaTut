package com.akka.project.AkkaFinal;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Futures;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.concurrent.duration.Duration;

public class AkkaRouters {

	public static void main(String[] args) {
		ActorSystem _system = ActorSystem.create("ActorRoutersExample");
		System.out.println("created Actor System");

		ActorRef akkaMaster = _system.actorOf(new Props(AkkaMaster.class), "AkkaMaster");
		System.out.println("created AkkaMaster");
		Timeout timeout = new Timeout(Duration.create(30, "seconds"));
		for(long i=0; i<1000000000L; i++ ){
			 
		  }
		System.out.println("timeout was set to 30 seconds");
		Future<Object> future = Patterns.ask(akkaMaster, new Integer(10), timeout);
		System.out.println("Called future object");
		
		try {
			String result = (String) Await.result(future, timeout.duration());
			System.out.println(result + "--result");
		} catch (Exception e) {
			System.out.println("catch block " + e.getMessage());
		}
		
//		timeout.apply(1000000000000L);
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_system.shutdown();
		System.out.println("system shutdown successful " + _system.systemImpl().deadLetterMailbox().suspendCount());
	}

}
