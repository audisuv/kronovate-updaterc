/**
 * 
 */
package com.kronos.updater.mqtt.domain;

/**
 * @author nishant.awasthi
 *
 */
public class UserDetails {
	
	private String clientId;
	private String ipAddress;
	private String lastCommandIssued;
	private boolean isConnected;
	
	
	
	public final String getClientId() {
		return clientId;
	}
	public final void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public final String getIpAddress() {
		return ipAddress;
	}
	public final void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public final String getLastCommandIssued() {
		return lastCommandIssued;
	}
	public final void setLastCommandIssued(String lastCommandIssued) {
		this.lastCommandIssued = lastCommandIssued;
	}
	public final boolean isConnected() {
		return isConnected;
	}
	public final void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	

}
