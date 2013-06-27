package com.kronos.updater.mqtt.test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;

import com.google.gson.Gson;
import com.kronos.updater.mqtt.AdminClient;
import com.kronos.updater.mqtt.MessageFormat;
import com.kronos.updater.mqtt.inf.IAdminClient;
import com.kronos.updater.mqtt.thread.AdminListner;

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
		AdminClient iAdmin = new AdminClient();
		
		Thread listner = new Thread(new AdminListner(iAdmin));
		listner.start();
		
		createMessageList(iAdmin);

	}

	private static void createMessageList(IAdminClient iAdmin) {
		MessageFormat mf=new MessageFormat();
		mf.setClient_Id("user_10.131.22.29");
		mf.setCommand("download");
		mf.setUrl("url");
		List<String> list=new ArrayList<String>();
		list.add("value");
		mf.setValues(list);
		iAdmin.addToUserCommandsMap(mf.getClient_Id(), mf);
	}

}
