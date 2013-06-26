package com.kronos.updater.mqtt.test;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;

import com.kronos.updater.mqtt.AdminClient;

public class AdminClientTest {

	public AdminClientTest() {
		
	}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException, Exception {
		AdminClient ac= new AdminClient();
		BlockingConnection bc=ac.getConnection();
		ac.issueIdentify(bc);
		ac.listenIdentify(bc);

	}

}
