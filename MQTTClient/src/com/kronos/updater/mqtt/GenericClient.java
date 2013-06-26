package com.kronos.updater.mqtt;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;

import com.kronos.updater.mqtt.inf.IGenericClient;

public class GenericClient implements IGenericClient{
	
	private List<BlockingConnection> connectionList;
	private String clientId;
	private MQTT mqtt;

	public final String getClientId() {
		return clientId;
	}

	public final void setClientId(String clientId) {
		mqtt.setClientId(clientId);
		this.clientId = clientId;
	}

	public GenericClient() {
		init();
	}

	
	protected void init() {
		MQTT mqtt = new MQTT();
		try {
			mqtt.setHost(ip, port);
			this.mqtt=mqtt;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}


	public  int port = 1883;
//	public  String ip = "10.131.21.242";
	public  String ip = "10.131.22.29";
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	}

/**
 * Design Architecture
 *  
 * Create initial "identify" & "identify_reply" topic and maintain a list of identified system on admin client.
 * Any system who connects broadcasts its client id and ip address but only admin will maintain a list.
 * one to one  communication is possible by creating a topic with the help of client id and hash of ip address with any suffix.
 * Before any command admin will issue a isAlive command with response true on smae topic admin will move to next step/command. 
 * 	
 */

	

	

	public  BlockingConnection getConnection()
			throws URISyntaxException, Exception {
		return getConnection("local_sub");
	}

	public  BlockingConnection getConnection(String str)
			throws URISyntaxException, Exception {
		return getConnection(ip, port, str);
	}
	
	public  BlockingConnection getConnection(String ip, int port, String str)
			throws URISyntaxException, Exception {
//		this.setClientId(str+Math.random());
		this.setClientId(str);
		BlockingConnection connection = mqtt.blockingConnection();
		connection.connect();
		addToConnectionList(connection);
		return connection;
	}
	
	public void disconnect(BlockingConnection connection) throws Exception {
		connection.disconnect();
	}
	
	protected void addToConnectionList(BlockingConnection connection) {
		initializeConnectionList();
		getConnectionList().add(connection);
	}

	private void initializeConnectionList() {
		if(getConnectionList()==null)
			this.setConnectionList(new ArrayList<BlockingConnection>());
	}

	

	public List<BlockingConnection> getConnectionList() {
		return connectionList;
	}

	public void setConnectionList(List<BlockingConnection> connectionList) {
		this.connectionList = connectionList;
	}

	@Override
	public void update(Message message) {
		// Subclasses will handle it accordingly.
	}

	

	

}
