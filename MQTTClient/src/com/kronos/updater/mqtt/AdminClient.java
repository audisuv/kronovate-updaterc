package com.kronos.updater.mqtt;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
	private List<UserDetails> userList = new ArrayList<UserDetails>();

	/**
	 * 
	 */
	public AdminClient() {

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
	}

	/**
	 * @return the userList
	 */
	public List<UserDetails> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<UserDetails> userList) {
		this.userList = userList;
	}

	@Override
	public void updateIdentifyList(List<UserDetails> list) {
		

	}

}
