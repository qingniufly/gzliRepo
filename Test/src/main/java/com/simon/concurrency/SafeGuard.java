package com.simon.concurrency;

public class SafeGuard {
	
	private int ACCESS_ALLOWED = 1;
	
	public boolean getAccess() {
		return 42 == ACCESS_ALLOWED;
	}

}
