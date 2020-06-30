package com.suriya.learning.ds.ll;

import java.util.Scanner;

public class LinkedList {
	
	static class Node {
		Node prev;
		Node next;
		int value;
		
		Node(Node prev, Node next, int value){
			this.prev = prev;
			this.next = next;
			this.value = value;
		}
	} 
	
	public static void addNode(Node node, int value) {
		Node newNode = new Node(node, null, value);
		node.next = newNode;
	}
	
	public static Node getLastNode(Node start) {
		Node node = start;
		while(node.next != null) {
			node = node.next;
		}
		return node;
	}
	
	public static void printAllNode(Node start) {
		Node node = start;
		while(node != null) {
			System.out.println(node.value);
			node = node.next;
		}
	}
	
	public static void main(String[] args) {
		Node start = new Node(null,null, 1);
		inputAllNode(start);
		printAllNode(start);
	}

	private static void inputAllNode(Node start) {
		System.out.println("No. of nodes:");
		Scanner sc=new Scanner(System.in);
		int noOfNodes = Integer.parseInt(sc.nextLine());
		for(int i=0; i< noOfNodes; i++) {
			Scanner nodesSc=new Scanner(System.in);
			addNode(getLastNode(start), Integer.parseInt(nodesSc.nextLine()));
		}
	}

}
