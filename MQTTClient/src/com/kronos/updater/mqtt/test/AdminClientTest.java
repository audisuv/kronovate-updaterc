package com.kronos.updater.mqtt.test;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;

import com.kronos.updater.mqtt.AdminClient;
import com.kronos.updater.mqtt.inf.IAdminClient;

public class AdminClientTest {

	public AdminClientTest() {
		
	}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException, Exception {
		String str = "Admin";
		IAdminClient iAdmin = new AdminClient();
		try {
			BlockingConnection connection = iAdmin.getConnection(str);
			iAdmin.issueIdentify(connection);
			iAdmin.listenIdentify(connection);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
