package com.kronos.updater.mqtt.inf;

import java.net.URISyntaxException;
import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;

public interface IPubClient extends IGenericClient {

	public List<BlockingConnection> getConnectionList();

	public void setConnectionList(List<BlockingConnection> connectionList);

	public void publish() throws URISyntaxException, Exception;
	
	public  void publish(String topic, String message) throws URISyntaxException, Exception;

}
