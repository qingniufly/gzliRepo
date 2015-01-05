package com.simon.network;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkInterfaceTest {

	public static void main(String[] args) throws IOException {
		Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
		if (nis != null) {
			while (nis.hasMoreElements()) {
				NetworkInterface ni = nis.nextElement();
				System.out.println("Display name : " + ni.getDisplayName());
				System.out.println("is loopback  : " + ni.isLoopback());
				System.out.println("is up        : " + ni.isUp());
				System.out.println("is virtual   : " + ni.isVirtual());
				for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
					System.out.println("\t" + ia.getAddress().getHostAddress());
					System.out.println("\tany local : " + ia.getAddress().isAnyLocalAddress());
					System.out.println("\tlink local : " + ia.getAddress().isLinkLocalAddress());
					System.out.println("\tloopback addr: " + ia.getAddress().isLoopbackAddress());
					System.out.println("\tmc global: " + ia.getAddress().isMCGlobal());
					System.out.println("\tmc link local: " + ia.getAddress().isMCLinkLocal());
					System.out.println("\tmc node local: " + ia.getAddress().isMCNodeLocal());
					System.out.println("\tmc site local: " + ia.getAddress().isMCSiteLocal());
					System.out.println("\tmulticastaddr: " + ia.getAddress().isMulticastAddress());
					System.out.println("\tsite local: " + ia.getAddress().isSiteLocalAddress());
					System.out.println("\treachable : " + ia.getAddress().isReachable(1000));

				}
			}
		}

		System.out.println("****" + InetAddress.getLocalHost());

		Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			System.out.println(netInterface.getName());
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress()) {
					System.out.println("本机的IP = " + ip.getHostAddress());
					break;
				}
			}
		}
	}

}
