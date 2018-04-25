package be.occam.zoncolan.pi.push.me;

/*
* #%L
* **********************************************************************
* ORGANIZATION  :  Pi4J
* PROJECT       :  Pi4J :: Java Examples
* FILENAME      :  ListenGpioExample.java
*
* This file is part of the Pi4J project. More information about
* this project can be found here:  http://www.pi4j.com/
* **********************************************************************
* %%
* Copyright (C) 2012 - 2017 Pi4J
* %%
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation, either version 3 of the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Lesser Public License for more details.
*
* You should have received a copy of the GNU General Lesser Public
* License along with this program.  If not, see
* <http://www.gnu.org/licenses/lgpl-3.0.html>.
* #L%
*/

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
* - 1 pushbutton to indicate 'right' answer: [0,1,2,3,4] (0=all correct)
* - 4 leds at backend that indicate which answer was selected to be right
* - 4 pushbuttons at the frontend to indicate answer -> listeners
* - 
* @author Sven Gladines
*/
public class PushMe {
	
	protected final String[] answers 
		= new String[] {null, null,null,null,null};
	
	protected int rightAnswer 
		= 0;
	
	protected int giveAnswerIndex
		= 0;
	
	public PushMe( String... answrs ) {
		
		for( int i = 0; i < answrs.length ; i++ ) {
			this.answers[ i + 1 ] = answrs[ i ];
		}
		
	}
	
	public void go( ) {
	
		System.out.println("[start feelin']");

	     // create gpio controller
	     final GpioController gpio 
	     	= GpioFactory.getInstance();

	     // provision gpio pin #04 as an input pin with its internal pull down resistor enabled
	     // connected to button to select answer
	     final GpioPinDigitalInput selectAnswerPin 
	     	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);

	     // set shutdown state for this input pin
	     selectAnswerPin.setShutdownOptions(true);

	     // create and register gpio pin listener
	     selectAnswerPin.addListener(new GpioPinListenerDigital() {
	    	 
	         @Override
	         public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

	        	 if ( PinState.HIGH.equals( event.getState() ) ) {
	        		 
	        		 System.out.println( "[button pushed]");
	        	
	        		 // each time button is pressed, select next answer or reset once looped
	        		 rightAnswer += 1;
	        		 rightAnswer = rightAnswer % answers.length; 
	        		 System.out.println( String.format( "right answer now has index [%x] and value [%s]", rightAnswer, answers[ rightAnswer ] ) );
	        	 }
	             
	         }

	     });
	     
	     System.out.println( "Listener added");
	     
	     System.out.println( String.format( "current state is [%s]", selectAnswerPin.getState() ) );

	     System.out.println( "Push the button ");
		
	}
	
	public void roundRobin( ) {
		
		System.out.println("[round robin]");

	     // create gpio controller
	     final GpioController gpio 
	     	= GpioFactory.getInstance();

	     final GpioPinDigitalOutput answerOnePin 
	     	= gpio.provisionDigitalOutputPin( RaspiPin.GPIO_05, "answer one", PinState.LOW );

	     answerOnePin.high();
	     
	     System.out.println( "High! ");
		
	}

 public static void main(String args[]) throws InterruptedException {
	 
	 PushMe pushMe
	 	= new PushMe( "Koe","Poes" ,"Schaap" , "Kippetje" );
	 
	 // pushMe.roundRobin();
	 pushMe.go();
     
     // keep program running until user aborts (CTRL-C)
     while(true) {
         Thread.sleep(500);
     }

     // stop all GPIO activity/threads by shutting down the GPIO controller
     // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
     // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
 }
}