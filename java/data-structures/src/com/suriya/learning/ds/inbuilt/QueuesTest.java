package com.suriya.learning.ds.inbuilt;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueuesTest {
	
	public static void main(String[] args) {
		System.out.println("Queue as linked list:");
		Queue q1 = new LinkedList();
		q1.add(1);
		q1.add(2);
		q1.add(3);
		q1.forEach(System.out::println);
		
		System.out.println("Priority Queue:");
		PriorityQueue q2 = new PriorityQueue<>();
		q2.add(1);
		q2.add(2);
		q2.add(3);
		System.out.println("- object peeked: "+q2.peek());
		q2.forEach(System.out::println);
		q2.poll();
		q2.forEach(System.out::println);
				
	}

}
