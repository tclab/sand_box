package src;

   import javax.microedition.midlet.*;
   import javax.microedition.lcdui.*;
   import java.lang.*;
		
	
    public class Ejemplo1 extends MIDlet{
   
      Display display;
      OrganoEjemplo organillo;
    	 
       public void startApp() {
      
         display = Display.getDisplay(this);
         organillo = new OrganoEjemplo(true);
         display.setCurrent(organillo);
         Thread t = new Thread(organillo);
         t.start();
      }
      // Implementa el metodo pauseApp()
       public void pauseApp() {
      }
   
   	// Implementa el metodo commandApp()     
       public void destroyApp(boolean unconditional) {
      }
   }