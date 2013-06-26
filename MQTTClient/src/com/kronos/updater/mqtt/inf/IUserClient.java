package com.kronos.updater.mqtt.inf;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;

public interface IUserClient {

	public void replyIdentify(BlockingConnection bc) throws URISyntaxException, Exception;
	public void listenIdentify(BlockingConnection bc) throws Exception;
	
}
