package com.kronos.updater.mqtt;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;

import com.google.gson.Gson;
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
	private Map<String,List<MessageFormat>> userCommandsMap = new HashMap<String,List<MessageFormat>>();

	/**
	 * @return the userCommandsMap
	 */
	@Override
	public final Map<String, List<MessageFormat>> getUserCommandsMap() {
		return userCommandsMap;
	}
	
	@Override
	public final void addToUserCommandsMap(String clientId,MessageFormat mf) {
		List<MessageFormat> mfList=userCommandsMap.get(clientId);
		if(mfList==null){
			mfList=new ArrayList<MessageFormat>();
		}
		mfList.add(mf);
		userCommandsMap.put(clientId, mfList);
	}

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
			iAdmin.listenTopic(connection,"identify_reply");
			
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
	public void listenTopic(BlockingConnection bc,String topic) throws Exception {
		ISubClient sc = new SubscriberClient(bc);
		QoS QOSlevel = QoS.AT_LEAST_ONCE;
		sc.subscribe(bc, topic, QOSlevel);
		//sc.recieve(bc,this);
	}
	

	@Override
	public void listenTopics(BlockingConnection bc,String[] topics) throws Exception {
		ISubClient sc = new SubscriberClient(bc);
		QoS QOSlevel = QoS.AT_LEAST_ONCE;
		for(int i=0;i<topics.length;i++){
			sc.subscribe(bc, topics[i], QOSlevel);
		}
		sc.recieve(bc, this);
		
	}
	
	@Override
	public void update(Message message) {
		// handle it accordingly.
		String str=new String(message.getPayload());
		if (message.getTopic().equals("identify")) {
			
		}else
		if (message.getTopic().equals("identify_reply")) {
			System.out.println(new String(message.getPayload()));
			UserDetails ud= new UserDetails();
			ud.setClientId(str);
			ud.setConnected(true);
			ud.setIpAddress(str.split("_")[1]);
			this.getUserMap().put(str, ud);
			System.out.println(this.getUserMap());
			
		}else{
			System.out.println(new String(message.getPayload())+ " executed");
			try {
				execute(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	private void execute(String message) throws URISyntaxException, Exception {
		Gson gson = new Gson();
		System.out.println("message: "+ message);
		MessageFormat mf =gson.fromJson(message, MessageFormat.class);
		executeUserCommandsMap(userCommandsMap, mf.getClient_Id());		
	}

	private void executeUserCommandsMap(
			 Map<String, List<MessageFormat>> userCommandsMap, String clientId) throws URISyntaxException, Exception {
		List<MessageFormat> userCommandsList=userCommandsMap.get(clientId);
		if(userCommandsList!=null && userCommandsList.size()>0){
			issueCommand(userCommandsList);			
		}
		
	}

	private void issueCommand(List<MessageFormat> userCommandsList)
			throws URISyntaxException, Exception {
		MessageFormat mf=userCommandsList.get(0);
		Gson gson = new Gson();
		String message =gson.toJson(mf);
		userCommandsList.remove(0);
		this.issueCommand(this.getConnectionList().get(0), mf.getClient_Id(), mf.getCommand(), message);
	}

	@Override
	public void updateIdentifyList(List<UserDetails> list) {
		

	}

	@Override
	public void issueCommand(BlockingConnection bc, String clientId,
			String command, String message) throws URISyntaxException, Exception {
		IPubClient ipub=new PublisherClient(bc);
		ipub.publish(command, message);
		
	}
	
	@Override
	public boolean isCommandListAvailable() {
		if(userCommandsMap.size()>0){
			Set<String> set=this.userCommandsMap.keySet();
			for (String clientId : set) {
				List<MessageFormat> list=this.userCommandsMap.get(clientId);
				if(list!=null && list.size()>0){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void execute() {
		Set<String> set=this.userCommandsMap.keySet();
		for (String clientId : set) {
			List<MessageFormat> list=this.userCommandsMap.get(clientId);
			if(list!=null && list.size()>0){
				try {
					issueCommand(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
