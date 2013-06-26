package com.kronos.updater.mqtt;

import static org.fusesource.hawtbuf.Buffer.utf8;

import java.net.URISyntaxException;
import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import com.kronos.updater.mqtt.inf.IPubClient;

public class PublisherClient extends GenericClient implements IPubClient {

	public PublisherClient() {
		
	}
	
	public PublisherClient(List<BlockingConnection> list) {
		this.setConnectionList(list);
	}
	
	public PublisherClient(BlockingConnection connection) {
		this.addToConnectionList(connection);
	}


	

	public  void publish() throws URISyntaxException, Exception {
        publish("foo", "identify");
	}
	
	public  void publish(String topic, String message) throws URISyntaxException, Exception {
		BlockingConnection connection = this.getConnectionList()==null?this.getConnection():this.getConnectionList().get(0);
		Topic[] topics = {new Topic(utf8(topic), QoS.AT_LEAST_ONCE)};
		byte[] qoses = connection.subscribe(topics);
		connection.publish(topic, message.getBytes(), QoS.AT_LEAST_ONCE, false);
	}

}
