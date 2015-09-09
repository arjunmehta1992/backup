package com.prectise;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
	
	public static void main(String args[])
	{
		
		Scanner sc=new Scanner(System.in);
		
		Set<Integer> set=new HashSet<Integer>();
		System.out.println("How many entries you want to enter?");
		int Length=sc.nextInt();
		
		for(int i=0;i<Length;i++)
		{
			System.out.println("-->Enter the number:");
			set.add(sc.nextInt());
		}
		System.out.print("without sorted elements:"+set);
		
		TreeSet<Integer> sortedSert=new TreeSet<Integer>(set);
		
		System.out.print("with sorted elements:"+sortedSert);
		
		
		
				
	}

}
