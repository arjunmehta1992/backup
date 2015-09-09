package com.prectise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HashMapDemo {

	public static void main(String[] args) 
	{
		
		Scanner sc =new Scanner(System.in);
		HashMap hm = new HashMap();
		
		
		System.out.println("How many data you want to enter?");
		int Length=sc.nextInt();
		
		for(int i=0;i<Length;i++)
		{
			System.out.println("Enter the data:");
			hm.put(i,sc.next());
		}
		
		for(int i=0;i<Length;i++)
		{
			System.out.println("Data you are entered:"+hm.get(i)+"ID="+i);
			
			 
		}
		
		
		
		System.out.println("Enter the id that you have to search:");
		int ID=sc.nextInt();
		
			if(hm.containsKey(ID))
			{
				System.out.println("Search result"+hm.get(ID));
			}
		
		
			System.out.println("Enter the id that you have to search:");
			int IDs=sc.nextInt();
			
				if(hm.containsKey(IDs))
				{
					System.out.println("Record is exists");
				}
				else
				{
					System.out.println("Record is NOT exists");
				}
				
				
			
				
				
				System.out.println("Enter the id that you have to remove");
				int choice=sc.nextInt();
				
				hm.remove(choice);
				
				
				for(int i=0;i<Length;i++)
				{
					System.out.println("Data you are entered:"+hm.get(i)+"ID="+i);
					
					 
				}
				
				hm.clear();
				
				System.out.println("Data remove from the HashMap"+hm);
				
				
				
		
		
		
		
	      // Put elements to the map
	     
	    /*  hm.put("Rutul", new Double(123.22));
	      hm.put("Satvaraj", new Double(1378.00));
	      hm.put("Bipin sir", new Double(99.22));
	      hm.put("Mitesh sir", new Double(-19.08));
	     
	      Set set = hm.entrySet();
	      
	      System.out.println(hm);
	     
	      Iterator i = set.iterator();
	      
	      while(i.hasNext()) 
	      {
	    	 Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	      System.out.println();
	      
	      double balance = ((Double)hm.get("Arjun")).doubleValue();
	      hm.put("Arjun", new Double(balance + 1000));
	      System.out.println("Arjun's new balance: " +
	      hm.get("Arjun"));

*/	}

}
