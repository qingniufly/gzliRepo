package com.simon.rmi.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

import com.simon.rpc.rmi.NumberCruncher;

public class NumberCruncherClient {

	public static void main(String[] args) {
		if (args.length == 1) {
			try {
				String url = "rmi://" + args[0] + "/Factor";
				NumberCruncher nc = (NumberCruncher) Naming.lookup(url);
				loop(nc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			usage("Wrong number of arguments.");
		}
	}

	private static void loop(NumberCruncher nc) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i = 0;

		while (true) {
			try {
				i = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (-1 == i) {
				System.out.println("Exiting loop.");
				return;
			}
			System.out.println("Will attempt to factor " + i);
			try {
				int[] factors = nc.factor(i);
				System.out.println("The factors of " + i + " are");
				for (int k = 0; k < factors.length; k++) {
					System.out.print(" " + factors[k]);
				}
				System.out.println(".");
			} catch (Exception e) {
				System.err.println("Could not factor " + i);
				e.printStackTrace();
			}
		}
	}

	private static void usage(String msg) {
		System.err.println(msg);
		System.err.println("Usage: java com.simon.rmi.client.NumberCruncherClient HOST\n"
				+ "   where HOST is the machine where the NumberCruncherServer is running.");
		System.exit(1);
	}

}
