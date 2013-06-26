/**
 * 
 */
package com.kronos.updater.mqtt.inf;

import java.util.List;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;

/**
 * @author Nishant.Awasthi
 * 
 */
public interface ISubClient extends IGenericClient {
	public void recieve(BlockingConnection connection, IGenericClient gc) throws Exception;

	public void subscribe(BlockingConnection connection, String topic,
			QoS QOSlevel) throws Exception;

	public List<BlockingConnection> getConnectionList();

	public void setConnectionList(List<BlockingConnection> connectionList);
}
