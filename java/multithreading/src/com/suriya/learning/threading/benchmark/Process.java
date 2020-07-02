package com.suriya.learning.threading.benchmark;

public class Process implements Runnable{

	String thread;
	int start = 0;
	int end = 0;
	
	Process(String thread,int start,int end){
		this.thread = thread;
		this.start = start;
		this.end = end;
	}
	@Override
	public void run() {
		tester();
	}
	
	public void tester() {
	//public synchronized void tester() {
		for(int i=start;i<end;i++) {
			Test.data[i] = i+":"+thread;
		}
	}
	

}
