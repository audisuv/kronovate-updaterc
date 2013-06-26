package com.kronos.updater.mqtt.inf;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;

public interface IGenericClient {
	
	public  BlockingConnection getConnection()
			throws URISyntaxException, Exception;
	public  BlockingConnection getConnection(String str)
			throws URISyntaxException, Exception;
	public  BlockingConnection getConnection(String ip, int port, String str)
			throws URISyntaxException, Exception;
	public void disconnect(BlockingConnection connection) throws Exception;
	public String getClientId();
	public void setClientId(String clientId);
	public void update(Message message);
}
