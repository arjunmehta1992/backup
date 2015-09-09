package com.prectise;
import java.util.*;


class ReferenceA
{
	void ref()
	{
		System.out.println("Display from class refA");
	}
	
	
}


class ReferenceB extends ReferenceA
{
	void ref()
	{
		System.out.println("Display from class refB");
	}
	
	
}






class Overloading
{
	int s=25;
	
	void printRandom(int a)
	{
			
		
		System.out.println("a"+a);
			
	}
	
	void printRandom(String s)
	{
			System.out.println("s"+s);
	}
	
	
	void printRandom()
	{
		System.out.println("a"+s);
	}
	
}

class Override extends Overloading
{
	int s=20;
	void printRandom()
	{
			
		
		
		System.out.println("a"+s);
			
	}
	
	
}










class A
{
	
	int a=10;
	
	void methodA()
	{
		
		System.out.print("This is method of class A"+a);
		
	}
	
	
	
}


class B extends A 
{
	
	void methodB()
	{
		System.out.print("This is method of class B");
		
	}
}



class C extends B
{
	
	void methodC()
	{
		System.out.print("This is method of class C");
		
	}
}





public class InheritanceDemo {

	public static void main(String[] args) 
	{
		
		
		
		C objc=new C();
		B objb=new B();
		System.out.println("Value of a fetch form the class:"+objc.getClass());
		Overloading obj=new Overloading();
		
		obj.printRandom(10);
		obj.printRandom("Arjun");
		Override objOver=new Override();
		objOver.printRandom();
		ReferenceA a;
		ReferenceB b;
		ReferenceA aa=new ReferenceB();
		aa.ref();	
		
	}

}
