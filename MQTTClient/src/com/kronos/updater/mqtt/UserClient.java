package com.kronos.updater.mqtt;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;

import com.kronos.updater.mqtt.inf.IPubClient;
import com.kronos.updater.mqtt.inf.ISubClient;
import com.kronos.updater.mqtt.inf.IUserClient;

public class UserClient extends GenericClient implements IUserClient {

	public UserClient() {

	}

	public void processIdentify(BlockingConnection bc) {

	}

	@Override
	public void replyIdentify(BlockingConnection bc) throws URISyntaxException,
			Exception {
		IPubClient ipub = new PublisherClient(bc);
		ipub.publish("identify_reply", this.getClientId());

	}

	@Override
	public void listenIdentify(BlockingConnection bc) throws Exception {
		ISubClient sc = new SubscriberClient(bc);
		String topic = "identify";
		QoS QOSlevel = QoS.AT_LEAST_ONCE;
		sc.subscribe(bc, topic, QOSlevel);
		sc.recieve(bc, this);

	}

	@Override
	public void update(Message message) {
		// handle it accordingly.
		try {
			if (message.getTopic().equals("identify")) {
				System.out.println("got identify");
				this.replyIdentify(this.getConnectionList().get(0));
			} else {

			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
