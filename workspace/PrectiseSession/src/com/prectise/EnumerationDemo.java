package com.prectise;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

public class EnumerationDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Enumeration Days;
		Vector NameOfDay = new Vector();
		Scanner sc=new Scanner(System.in);
		String name;
		System.out.println("How many number of data you want to enter?");
		int Length=sc.nextInt();
		for(int i=0;i<Length;i++)
		{
			System.out.println("Enter the string");
			name=sc.next();
			NameOfDay.add(name);
		}
		
		Days = NameOfDay.elements();
	      
		while (Days.hasMoreElements())
	      {
	         System.out.println(Days.nextElement()); 
	      }
		
		
	}

}
