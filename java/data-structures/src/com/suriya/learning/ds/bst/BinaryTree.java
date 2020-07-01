package com.suriya.learning.ds.bst;

import java.util.Scanner;

public class BinaryTree {
	
	
	static class Node {
		int value;
		Node left;
		Node right;
		Node(){}
		Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
	private static void printPreOrder(Node node) {
		if(node !=null) {
			System.out.println(node.value);
		}
		if(node.left != null) {
			printPreOrder(node.left);
		} 
		if(node.right != null) {
			printPreOrder(node.right);
		} 
	}
	private static void printInOrder(Node node) {
		if(node.left != null) {
			printInOrder(node.left);
		} 
		if(node !=null) {
			System.out.println(node.value);
		}
		if(node.right != null) {
			printInOrder(node.right);
		} 
	}
	
	private static void printPostOrder(Node node) {
		if(node.left != null) {
			printPostOrder(node.left);
		} 
		if(node.right != null) {
			printPostOrder(node.right);
		} 
		if(node !=null) {
			System.out.println(node.value);
		}
	}
	
	private static void addNode(int value) {
		
	}
	
	private static void setTree() {
		Scanner sc = new Scanner(System.in);
		int noOfNodes = Integer.parseInt(sc.nextLine());
		for(int i =0; i< noOfNodes ; i++) {
			Scanner scNode = new Scanner(System.in);
			addNode(Integer.parseInt(scNode.nextLine()));
		}
	}
	
	private static void setTestTree(Node root) {
		root.value = 25;
		
		Node oneLeft = new Node();
		oneLeft.value = 15;
		Node oneRight = new Node();
		oneRight.value = 50;
		
		root.left = oneLeft;
		root.right = oneRight;
		
		Node two10 = new Node();
		two10.value = 10;
		two10.left = new Node(4,null,null);
		two10.right = new Node(12,null,null);
		
		Node two22 = new Node();
		two22.value = 22;
		two22.left = new Node(18,null,null);
		two22.right = new Node(24,null,null);
		
		oneLeft.left = two10;
		oneLeft.right = two22;
		
		Node two35 = new Node();
		two35.value = 35;
		two35.left = new Node(31,null,null);
		two35.right = new Node(44,null,null);
		
		Node two70 = new Node();
		two70.value = 70;
		two70.left = new Node(66,null,null);
		two70.right = new Node(90,null,null);
		
		oneRight.left = two35;
		oneRight.right = two70;
		
	}
	
	
	public static void main(String[] args) {
		Node root = new Node();
		setTestTree(root);
		System.out.println("Printint pre order:");
		printPreOrder(root);

		System.out.println("Printint in order:");
		printInOrder(root);
		
		System.out.println("Printint post order:");
		printPostOrder(root);
	
	}

}
