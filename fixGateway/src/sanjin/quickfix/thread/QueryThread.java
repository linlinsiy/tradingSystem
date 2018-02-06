package sanjin.quickfix.thread;

import sanjin.quickfix.SanjinApplication;
import sanjin.quickfix.TraderInterface;

public class QueryThread implements Runnable {
	private SanjinApplication sanjinApplication;
	public QueryThread() {
		super();
	}
	
	public QueryThread(SanjinApplication sanjinApplication) {
		this.sanjinApplication = sanjinApplication;
	}

	@Override
	public void run() {
		
			while(true){
				try {
					if(sanjinApplication.isLogon()) {
						sanjinApplication.queryPosition(TraderInterface.get().account);
						sanjinApplication.queryBalance(TraderInterface.get().account);
					}
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
	}

}