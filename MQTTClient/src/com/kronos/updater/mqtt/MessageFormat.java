package com.kronos.updater.mqtt;

import java.util.List;

public class MessageFormat {
	
	private String command;
	private String url;
	private List<String> values;
	private String client_Id;
	

	/**
	 * @return the command
	 */
	public final String getCommand() {
		return command;
	}


	/**
	 * @param command the command to set
	 */
	public final void setCommand(String command) {
		this.command = command;
	}


	/**
	 * @return the url
	 */
	public final String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public final void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the values
	 */
	public final List<String> getValues() {
		return values;
	}


	/**
	 * @param values the values to set
	 */
	public final void setValues(List<String> values) {
		this.values = values;
	}


	/**
	 * @return the client_Id
	 */
	public final String getClient_Id() {
		return client_Id;
	}


	/**
	 * @param client_Id the client_Id to set
	 */
	public final void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}


	public MessageFormat() {
		// TODO Auto-generated constructor stub
	}

}
