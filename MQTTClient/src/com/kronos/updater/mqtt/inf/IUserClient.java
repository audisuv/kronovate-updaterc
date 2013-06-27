package com.kronos.updater.mqtt.inf;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;

public interface IUserClient {

	public void listenTopic(BlockingConnection bc,String topic) throws Exception;
	void replyTopic(BlockingConnection bc, String topic, String message)
			throws URISyntaxException, Exception;
	void listenTopics(BlockingConnection bc, String[] topic) throws Exception;
	
}
