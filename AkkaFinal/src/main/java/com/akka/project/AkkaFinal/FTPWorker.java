package com.akka.project.AkkaFinal;

import akka.actor.*;
public class FTPWorker extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {

		if(message instanceof String){
			
			System.out.println("ftp done");
			getSender().tell(true,getSelf());
			
			
		}
	}

}
