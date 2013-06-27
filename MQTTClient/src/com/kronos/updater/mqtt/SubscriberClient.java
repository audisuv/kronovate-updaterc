package com.kronos.updater.mqtt;

import static org.fusesource.hawtbuf.Buffer.utf8;

import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import com.kronos.updater.mqtt.inf.IGenericClient;
import com.kronos.updater.mqtt.inf.ISubClient;

public class SubscriberClient extends GenericClient implements ISubClient{
	
	

	public SubscriberClient() {
		
	}
	
	public SubscriberClient(List<BlockingConnection> list) {
		this.setConnectionList(list);
	}
	
	public SubscriberClient(BlockingConnection connection) {
		this.addToConnectionList(connection);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public void recieve(BlockingConnection connection, IGenericClient gc) throws Exception {
		while(true){
			
	        Message message = connection.receive();
	        gc.update(message);
	        // To let the server know that it has been processed.
	        message.ack();
	        if(gc.isCommandListAvailable()){
	        	gc.execute();
	        }
        }
	}

	public void subscribe(BlockingConnection connection, String topic,
			QoS QOSlevel) throws Exception {
		Topic[] topics = {new Topic(utf8(topic), QOSlevel)};
        byte[] qoses = connection.subscribe(topics);
	}

}
