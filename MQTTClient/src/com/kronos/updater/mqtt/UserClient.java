package com.kronos.updater.mqtt;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;

import com.kronos.updater.mqtt.inf.IPubClient;
import com.kronos.updater.mqtt.inf.ISubClient;
import com.kronos.updater.mqtt.inf.IUserClient;

public class UserClient extends GenericClient implements IUserClient {

	public UserClient() {
	}

	public void registerToDefaultTopics() {
		
		
	}

	public void processIdentify(BlockingConnection bc) {

	}

	@Override
	public void replyTopic(BlockingConnection bc,String topic, String message) throws URISyntaxException,
			Exception {
		IPubClient ipub = new PublisherClient(bc);
		ipub.publish(topic, message);
	}

	@Override
	public void listenTopic(BlockingConnection bc,String topic) throws Exception {
		ISubClient sc = new SubscriberClient(bc);
		QoS QOSlevel = QoS.AT_LEAST_ONCE;
		sc.subscribe(bc, topic, QOSlevel);
		//sc.recieve(bc, this);

	}
	
	@Override
	public void listenTopics(BlockingConnection bc,String[] topics) throws Exception {
		ISubClient sc = new SubscriberClient(bc);
		QoS QOSlevel = QoS.AT_LEAST_ONCE;
		for(int i=0;i<topics.length;i++){
			sc.subscribe(bc, topics[i], QOSlevel);
		}
		sc.recieve(bc, this);
		
	}

	@Override
	public void update(Message message) {
		// handle it accordingly.
		try {
			if (message.getTopic().equals("identify")) {
				System.out.println("got identify");
				this.replyTopic(this.getConnectionList().get(0),"identify_reply", this.getClientId());
			} else {
				System.out.println(new String(message.getPayload()));
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
