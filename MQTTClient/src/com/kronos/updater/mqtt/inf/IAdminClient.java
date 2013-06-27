/**
 * 
 */
package com.kronos.updater.mqtt.inf;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.fusesource.mqtt.client.BlockingConnection;

import com.kronos.updater.mqtt.MessageFormat;
import com.kronos.updater.mqtt.domain.UserDetails;

/**
 * @author Nishant.Awasthi
 *
 */
public interface IAdminClient extends IGenericClient{
	
	public void issueIdentify(BlockingConnection bc) throws URISyntaxException, Exception;
	public void issueCommand(BlockingConnection bc,String clientId,String topic, String command) throws URISyntaxException, Exception;
	public void updateIdentifyList(List<UserDetails> listk);
	public void listenTopic(BlockingConnection bc, String topic) throws Exception;
	void listenTopics(BlockingConnection bc, String[] topics) throws Exception;
	public void addToUserCommandsMap(String clientId, MessageFormat mf);
	Map<String, List<MessageFormat>> getUserCommandsMap();
	

}
