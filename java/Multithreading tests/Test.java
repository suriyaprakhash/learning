package com.ford.gdia.htgs.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	static String[] data = new String[10000];
	public static void main(String[] args) throws InterruptedException {
		
		Arrays.asList(data).replaceAll(e -> (e==null?"empty":"empty"));
		//System.out.println(data[100]);
		long timeStart=System.currentTimeMillis();
		int partition =10;
		int len=data.length/partition;
		int start = 0;
		int end = len;
		List<Thread> tList=new ArrayList<>();
		for(int i=1;i<=partition;i++) {
			Thread t = new Thread(new Process("thread-"+i,start,end));
			t.start();
			start = end;
			end = len * (i+1);
			tList.add(t);
		}
		tList.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		//Thread.sleep(1000);
		//Arrays.asList(data).forEach(System.out::println);
		System.out.println((long)(System.currentTimeMillis()-timeStart));
	}
}
