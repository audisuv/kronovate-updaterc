package com.kronos.updater.mqtt;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;

import com.kronos.updater.mqtt.domain.UserDetails;
import com.kronos.updater.mqtt.inf.IAdminClient;
import com.kronos.updater.mqtt.inf.IPubClient;
import com.kronos.updater.mqtt.inf.ISubClient;

/**
 * @author nishant.awasthi
 * 
 */
public class AdminClient extends GenericClient implements IAdminClient {
	private Map<String,UserDetails> userMap = new HashMap<String,UserDetails>();

	/**
	 * @return the userMap
	 */
	public final Map<String, UserDetails> getUserMap() {
		return userMap;
	}

	/**
	 * 
	 */
	public AdminClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

	@Override
	public void issueIdentify(BlockingConnection bc) throws URISyntaxException, Exception {
		IPubClient ipub=new PublisherClient(bc);
		ipub.publish("identify", "identify");
	}

	@Override
	public void listenIdentify(BlockingConnection bc) throws Exception {
		ISubClient sc = new SubscriberClient(bc);
		String topic = "identify_reply";
		QoS QOSlevel = QoS.AT_LEAST_ONCE;
		sc.subscribe(bc, topic, QOSlevel);
		sc.recieve(bc,this);
	}
	
	@Override
	public void update(Message message) {
		// handle it accordingly.
		if (message.getTopic().equals("identify_reply")) {
			String str=new String(message.getPayload());
			System.out.println(new String(message.getPayload()));
			UserDetails ud= new UserDetails();
			ud.setClientId(str);
			ud.setConnected(true);
			ud.setIpAddress(str.split("_")[1]);
			this.getUserMap().put(str, ud);
			System.out.println(this.getUserMap());
		}
	}



	@Override
	public void updateIdentifyList(List<UserDetails> list) {
		

	}

}
