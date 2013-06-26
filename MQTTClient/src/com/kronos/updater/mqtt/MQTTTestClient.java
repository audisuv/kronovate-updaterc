package com.kronos.updater.mqtt;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;

import com.kronos.updater.mqtt.inf.IGenericClient;

public class MQTTTestClient {

	public MQTTTestClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SubscriberClient sc=new SubscriberClient();
		
		BlockingConnection connection = sc.getConnection();
		String topic="foo";
		QoS QOSlevel=QoS.AT_LEAST_ONCE;
        sc.subscribe(connection, topic, QOSlevel);
        IGenericClient gc=new AdminClient();
        sc.recieve(connection,gc);
       // disconnect(connection);
        
		

	}

}
