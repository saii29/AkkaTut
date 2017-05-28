package com.akka.project.AkkaFinal;

import java.util.ArrayList;
import java.util.Vector;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.routing.RoundRobinRouter;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class AkkaMaster extends UntypedActor {

	final ActorRef workRouter;
	static ActorRef parent;
	final ActorRef ftpRouter;
	final ActorRef listener;
	static final int collectionSize = 15;
	static Results results = new Results(new Vector(), new Vector());

	// static Results results = new Results();
	public AkkaMaster() {
		this.workRouter = getContext().actorOf(new Props(DownloadWorker.class).withRouter(new RoundRobinRouter(4)),
				"myRoundRobinRouter");

		this.ftpRouter = getContext().actorOf(new Props(FTPWorker.class).withRouter(new RoundRobinRouter(3)),
				"ftpWorker");

		this.listener = getContext().actorOf(new Props(AkkaListener.class));

		// parent= getContext().self();
		// parent = getSender();

		System.out.println("AkkaMaster########## " + getContext().sender() + " && " + workRouter.path().name());

	}

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof Integer) {

			parent = getSender();
			for (int i = 1; i <= collectionSize; i++) {
				workRouter.tell(i, getSelf());
				// System.out.println("********Master Actor*********" +
				// (Integer) message + " " +getSender());
			}

			System.out.println("********Master Actor*********" + " -----" + parent);
			// getSender().tell("Done");

		}

		if (message instanceof String) {
			System.out.println("String Instance returned from " + (String) message);
			ftpRouter.tell("do ftp ", getSelf());
			// parent.tell("from akka master ");
		}

		if (message instanceof Boolean) {
			System.out.println();
			System.out.println("final block: " + getSender().path().name());
			Vector<Integer> al = new Vector<Integer>();
			al.add(1);

//			adding(al);
			
			results.getSuccess().addAll(al);
			System.out.println("added result to result vector: ");
			// results.setSuccess(success);;
			Timeout timeout = new Timeout(Duration.create(30, "seconds"));
			Future<Object> future = Patterns.ask(listener, results, timeout);
			
			try {
				String result = (String) Await.result(future, timeout.duration());
				System.out.println(result + "-- from listener");
			} catch (Exception e) {
				System.out.println("catch block " + e.getMessage());
			}
			
//			listener.tell(results, getSelf());
			if (results.getSuccess().size() >= collectionSize) {

				parent.tell("from akka master ");

			}

		}
	}

	/*private synchronized void adding(Vector<Integer> al) {

		results.getSuccess().addAll(al);
		// results.setSuccess(success);;
		Timeout timeout = new Timeout(Duration.create(30, "seconds"));
		Future<Object> future = Patterns.ask(listener, results, timeout);
		
		try {
			String result = (String) Await.result(future, timeout.duration());
			System.out.println(result + "-- from listener");
		} catch (Exception e) {
			System.out.println("catch block " + e.getMessage());
		}
		
//		listener.tell(results, getSelf());
		if (results.getSuccess().size() >= collectionSize) {

			parent.tell("from akka master ");

		}
	}*/
}
