package com.akka.project.AkkaFinal;
import akka.actor.*;

public class AkkaListener extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {
		
		if(message instanceof Results){

//			System.out.println(((Results)message).getTotal().size());
			System.out.println(((Results)message).getSuccess().size() +" |||||||||||successCount");
			getSender().tell("updated", getSelf());
		}
	}

}
