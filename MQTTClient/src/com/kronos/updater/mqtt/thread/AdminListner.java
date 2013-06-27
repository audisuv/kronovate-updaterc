package com.kronos.updater.mqtt.thread;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;

import com.kronos.updater.mqtt.AdminClient;

public class AdminListner implements Runnable {


	private AdminClient adminclient;
	public AdminListner(AdminClient adminclient) {
		// TODO Auto-generated constructor stub
		this.adminclient =adminclient; 
	}

	@Override
	public void run() {
		String str = "Admin";
		try {
			BlockingConnection connection = adminclient.getConnection(str);
			adminclient.issueIdentify(connection);
			adminclient.listenTopic(connection,"identify_reply");
			String[] strArr={"download","install","update"};
			adminclient.listenTopics(connection,strArr);	
			//createMessage();
			//iAdmin.issueCommand(connection, "user_10.131.22.29", "download", );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
