package com.prectise;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppletDemo extends Applet implements ActionListener {

		
	Button b1,b,c;
	   String Add, Subtract;
	   int i = 10, j = 20, sum =0,Sub=0;
	   public void init(){
		  b1=new Button("Red");
		  add(b1,"center");
	      b= new Button("Green");
	      c= new Button("Black");
	      b1.addActionListener(this);
	      b.addActionListener(this);
	      c.addActionListener(this);
	      add(b1);
	      add(b);
	      add(c);
	   }
	   public void actionPerformed(ActionEvent e){
	      if(e.getSource() == b1)
	      {
	    	  setBackground(Color.RED);
	      }
	      if(e.getSource() == b)
	      {
	    	  setBackground(Color.GREEN);
	      }
	      if(e.getSource() == c)
	      {
	    	  setBackground(Color.BLACK);
	      }
	   }

	
	
}
