package com.suriya.learning.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("hello world");
		
		String[] strArray = {"test1","test4", "test2","test2","test3", "hellow"};
		//getArray(strArray);
		// solution(1,"1A 1F 1E 1C");
		String[] testArr = {"test1a","test2","test1b","test1c","test3"};
		String[] resArr = {"Wrong answer","OK","Runtime error","OK","Time limit exceeded"};
		Test t=new Test();
		t.solution(testArr, resArr);
	}
	
	
	public static void getArray(String[] strArray) {
//		String[] newArray = Arrays.stream(strArray).filter(ele -> (ele.startsWith("test"))).toArray(String[]::new);
//		List<String> strList = Arrays.stream(newArray).collect(Collectors.toList());
//		strList.forEach(System.out::println);
//		
//		System.out.println("----");
//		
//		Set<String> strSet = new LinkedHashSet<>();
//		strSet = Arrays.stream(newArray).collect(Collectors.toSet());
//		strSet.forEach(System.out::println);
		
		String seat = "40D";
		System.out.println(seat.substring(0, seat.length()-1));
		System.out.println(seat.substring(seat.length()-1,seat.length() ));
	}
	
	
	static class Data{
		public String name;
		public String res;
	}
	
	
	
    public int solution(String[] t, String[] r) {
    	// grp,<res, name>
    	//Map<String,Map<String,String>> groupMap = new HashMap<>();
    	Set<String> testOKSet = new HashSet<>();
    	Set<String> testGrpCountSet = new HashSet<>();
    	Set<String> testNonOKSet = new HashSet<>();
    	String grpNameStr = t[0].split("[0-9]")[0];
 
        for(int i=0;i<t.length;i++) {
            String grpTestNo = t[i].substring(grpNameStr.length(), t[i].length());
            String grpNo = grpTestNo.split("[a-z]")[0];
            if(!"Ok".equalsIgnoreCase(r[i])) {
            	testNonOKSet.add(grpNo);
            }
            if("Ok".equalsIgnoreCase(r[i])){
            	testOKSet.add(grpNo);
            }
            testGrpCountSet.add(grpNo);
        	//String[] grp = t[i].split("[0-9]");
        	//System.out.println(grp);
        //	groupMap.put(arg0, arg1)
        //	t[i];
        	//r[i];
        }
        
        testOKSet.removeAll(testNonOKSet);
        int res = (int) testOKSet.size() * (100/testGrpCountSet.size());
        return res;
    }
	
	
	
	
	////////////////////
	
	public static int getColSeatInt(String colSeat) {
		int colSeatNo = 0;
		if(colSeat.equals("A")) {
			colSeatNo = 1;
		}
		if(colSeat.equals("B")) {
			colSeatNo = 2;
		}
		if(colSeat.equals("C")) {
			colSeatNo = 3;
		}
		if(colSeat.equals("D")) {
			colSeatNo = 4;
		}
		if(colSeat.equals("E")) {
			colSeatNo = 5;
		}
		if(colSeat.equals("F")) {
			colSeatNo = 6;
		}
		if(colSeat.equals("G")) {
			colSeatNo = 7;
		}
		if(colSeat.equals("H")) {
			colSeatNo = 8;
		}
		if(colSeat.equals("J")) {
			colSeatNo = 9;
		}
		if(colSeat.equals("K")) {
			colSeatNo = 10;
		}
		return colSeatNo;
		
	}
	
	
	public static int solution1(int n, String s) {
	       int maxNoOf4PersonFamilySeats = 0;
	       if(s.length() !=0) {
	       //if(!s.isBlank()) {
	    	   List<String> seatList = Arrays.asList(s.split(" "));
	    	   int[][] blockedSeat = new int[n][10];
	    	   seatList.forEach(seat -> {
	    		   int i =Integer.parseInt(seat.substring(0, seat.length()-1)) - 1;
	    		   int j = getColSeatInt(seat.substring(seat.length()-1,seat.length())) -1;
	    		   blockedSeat[i][j] = 1;
	    	   });	  
	    	   
	    	   for(int i =0; i<n; i++){
	    		   maxNoOf4PersonFamilySeats = calcMax4PersonFamilyOnCol(maxNoOf4PersonFamilySeats, blockedSeat, i);
	    	   }
	       } else {
	    	   maxNoOf4PersonFamilySeats = n * 2;
	       }
	       
	       System.out.println(maxNoOf4PersonFamilySeats);
	       return maxNoOf4PersonFamilySeats;
	    }


	private static int calcMax4PersonFamilyOnCol(int maxNoOf4PersonFamilySeats, int[][] blockedSeat, int i) {
		int leftAsile = 0;
		   int leftCenter = 0;
		   int rightCenter = 0;
		   int rightAsile = 0;
		   for(int j=1;j<9;j++){
			   if(j<3 && blockedSeat[i][j] == 0) {
				   leftAsile = leftAsile + 1;
			   }
			   if(j>2 && j<5 && blockedSeat[i][j] == 0) {
				   leftCenter = leftCenter + 1;
			   }
			   if(j>4 && j<7 && blockedSeat[i][j] == 0) {
				   rightCenter = rightCenter + 1;
			   }
			   if(j>6 && blockedSeat[i][j] == 0) {
				   rightAsile = rightAsile + 1;
			   }
		   }
		   // if the center is occupied side asile cannot be calculated else calculating
		   if((leftCenter + rightCenter) == 4) {
			   maxNoOf4PersonFamilySeats = maxNoOf4PersonFamilySeats + 1;
		   } else {
			   if((leftAsile + leftCenter) == 4) {
				   maxNoOf4PersonFamilySeats = maxNoOf4PersonFamilySeats + 1;
			   }
			   if((rightCenter + rightAsile) == 4) {
				   maxNoOf4PersonFamilySeats = maxNoOf4PersonFamilySeats + 1;
			   }
		   }
		return maxNoOf4PersonFamilySeats;
	}

}
