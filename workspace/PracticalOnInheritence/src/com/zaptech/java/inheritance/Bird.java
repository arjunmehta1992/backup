package com.zaptech.java.inheritance;


interface First
{
	public void methodFirst();
	
}

interface Second
{
	public void methodSecond();
	
}

interface Third extends First,Second
{
	public void methodFirst();
	
}





public class Bird extends Animal {
	public Bird() {
		super();
		System.out.println("A new bird has been created!");
	}
	
	@Override
	public void sleep() {
		System.out.println("A bird sleeps...");
	}
	
	@Override
	public void eat() {
		System.out.println("A bird eats...");
	}
}
