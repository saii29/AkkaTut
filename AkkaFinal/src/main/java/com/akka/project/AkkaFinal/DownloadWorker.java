package com.akka.project.AkkaFinal;

import akka.actor.UntypedActor;

public class DownloadWorker extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof Integer) {

			/*for (long i = 0; i < 1000000000L; i++) {

			}*/

			// System.out.println("---------- DownloadWorker----------" +
			// (Integer) message);

		}
		System.out.println("download worker completed " + getSender());

		getSender().tell("dwnld actor");

	}

}
