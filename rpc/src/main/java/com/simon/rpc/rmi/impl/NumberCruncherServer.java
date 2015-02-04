package com.simon.rpc.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import com.simon.rpc.rmi.NumberCruncher;

public class NumberCruncherServer extends UnicastRemoteObject implements NumberCruncher {

	static Logger logger = LoggerFactory.getLogger(NumberCruncherServer.class);

	protected NumberCruncherServer() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int[] factor(int number) throws RemoteException {
		// The client's host is an important source of information.
		try {
			MDC.put("client", NumberCruncherServer.getClientHost());
		} catch (ServerNotActiveException e) {
			logger.warn("Caught unexpected ServerNotActiveException.", e);
		}
		// The information contained within the request is another source
		// of distinctive information. It might reveal the users name,
		// date of request, request ID etc. In servlet type environments,
		// useful information is contained in the HttpRequest or in the
		// HttpSession.
		MDC.put("number", String.valueOf(number));
		logger.info("Beginning to factor.");

		if (number <= 0) {
			throw new IllegalArgumentException(number + " is not a positive integer.");
		} else if (number == 1) {
			return new int[] { 1 };
		}

		Vector<Integer> factors = new Vector<Integer>();
		int n = number;
		for (int i = 2; (i <= n) && (i * i) <= number; i++) {
			// It is bad practice to place log requests within tight loops.
			// It is done here to show interleaved log output from
			// different requests.
			logger.debug("Trying {} as a factor.", i);
			if ((n % i) == 0) {
				logger.info("Found factor {}", i);
				factors.addElement(i);
				do {
					n /= i;
				} while ((n % i) == 0);
			}
			// Placing artificial delays in tight loops will also lead to
			// sub-optimal results. :-)
			delay(100);

		}

		if (n != 1) {
			logger.info("Found factor {}", n);
			factors.add(new Integer(n));
		}

		int len = factors.size();
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			result[i] = factors.elementAt(i).intValue();
		}

		MDC.remove("client");
		MDC.remove("number");
		return result;

	}

	public static void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	static void usage(String msg) {
		System.err.println(msg);
		System.err.println("Usage: java chapters.mdc.NumberCruncherServer configFile\n"
				+ "   where configFile is a logback configuration file.");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage("Wrong number of arguments.");
		}

		String configFile = args[0];

		if (configFile.endsWith(".xml")) {
			try {
				LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
				JoranConfigurator configurator = new JoranConfigurator();
				configurator.setContext(lc);
				lc.reset();
				configurator.doConfigure(args[0]);
			} catch (JoranException je) {
				je.printStackTrace();
			}
		}
		
		NumberCruncherServer ncs = null;
		try {
			ncs = new NumberCruncherServer();
			logger.info("Creating registry.");
			
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			registry.rebind("Factor", ncs);
			logger.info("NumberCruncherServer bound and ready.");
			
			
		} catch (Exception e) {
			logger.error("Could not bind NumberCruncherServer.", e);
			return ;
		}
	}

}
