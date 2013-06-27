package com.kronos.updater.mqtt.test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;

import com.google.gson.Gson;
import com.kronos.updater.mqtt.AdminClient;
import com.kronos.updater.mqtt.MessageFormat;
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
			iAdmin.listenTopic(connection,"identify_reply");
			String[] strArr={"download","install","update"};
			createMessageList(iAdmin);
			iAdmin.listenTopics(connection,strArr);	
			//createMessage();
			//iAdmin.issueCommand(connection, "user_10.131.22.29", "download", );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
