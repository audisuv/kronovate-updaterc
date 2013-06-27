package com.kronos.updater.mqtt;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;

import com.google.gson.Gson;
import com.kronos.updater.mqtt.inf.IPubClient;
import com.kronos.updater.mqtt.inf.ISubClient;
import com.kronos.updater.mqtt.inf.IUserClient;
import com.kronos.updater.mqtt.test.FTPClientExample;

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
				String msg=new String(message.getPayload());
				System.out.println(msg);
				Gson gs=new Gson();
				MessageFormat mf=gs.fromJson(msg, MessageFormat.class);
				FTPClientExample client = new FTPClientExample();
				System.out.println(mf.getUrl());
				System.out.println(mf.getValues().get(0));
				client.FTPConnection(mf.getValues(),mf.getUrl(),mf.getCommand());
			
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
