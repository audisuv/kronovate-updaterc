/**
 * 
 */
package com.kronos.updater.mqtt.inf;

import java.net.URISyntaxException;
import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;

import com.kronos.updater.mqtt.domain.UserDetails;

/**
 * @author Nishant.Awasthi
 *
 */
public interface IAdminClient extends IGenericClient{
	
	public void issueIdentify(BlockingConnection bc) throws URISyntaxException, Exception;
	public void listenIdentify(BlockingConnection bc) throws Exception;
	public void updateIdentifyList(List<UserDetails> listk);
	

}
