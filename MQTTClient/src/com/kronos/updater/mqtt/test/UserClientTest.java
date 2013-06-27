package com.kronos.updater.mqtt.test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.fusesource.mqtt.client.BlockingConnection;

import com.kronos.updater.mqtt.UserClient;

public class UserClientTest {

	public UserClientTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getIpAddress());
		UserClient uc= new UserClient();
		BlockingConnection bc=uc.getConnection("user_"+getIpAddress());
		String[] strArr={"identify","download","install","update"};
		uc.listenTopics(bc,strArr);
		
	}

	private static String getIpAddress() throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()){
		    NetworkInterface current = interfaces.nextElement();
		 //   System.out.println(current);
		    if (!current.isUp() || current.isLoopback() || current.isVirtual()) continue;
		    Enumeration<InetAddress> addresses = current.getInetAddresses();
		    while (addresses.hasMoreElements()){
		        InetAddress current_addr = addresses.nextElement();
		        if (current_addr.isLoopbackAddress()) continue;
		        if (current_addr instanceof Inet4Address){
		        	//System.out.println(current_addr.getHostAddress());
		        	return current_addr.getHostAddress();
		        }
		        else if (current_addr instanceof Inet6Address){
		        	//System.out.println("v6: "+  current_addr.getHostAddress());
		        }
		    }
		}
		return null;
	}

}
