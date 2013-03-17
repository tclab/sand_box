package src;

   import javax.microedition.lcdui.*;
   import javax.microedition.lcdui.game.*;
   import javax.microedition.media.*;
   import javax.microedition.media.control.*;
   import java.lang.*;
	
	
    public class OrganoEjemplo extends GameCanvas implements Runnable {
      int width;
      int height;  
   	   
   	// Notas Musicales	
      private int[] notas = {68,70,72,74,76,78};
   
      // Creacion de objetos
      Graphics g;
      Player player;
      String salida;
      String salida2;
      int nota;
      int num_nota;
      int color_RGB;
      int volumen = 50;
      ToneControl c;
      VolumeControl vol; 		 
      boolean mute = false;
   				 
   	// Constructor
       public OrganoEjemplo(boolean b) {
      
         super(b);
      	
      	// Configuracion del entorno grafico
         width = getWidth();
         height = getHeight();
         g = getGraphics();
         g.setFont(Font.getDefaultFont());
         nota = 0;
         color_RGB = 0;
      	
      	// Creacion de notas
         byte d = 8; 
         byte C4 = ToneControl.C4;
         byte D4 = ToneControl.C4 + 2; 
         byte E4 = ToneControl.C4 + 4; 
         byte G4 = ToneControl.C4 + 7; 
         byte rest = ToneControl.SILENCE; 
      
      	
      	// Creacion de la secuencia de tonos
         byte[] mySequence = new byte[] {
               ToneControl.VERSION, 1,
               ToneControl.TEMPO, 30,
               ToneControl.BLOCK_START, 0,
               E4,d,D4,d,C4,d,D4,d,E4,d,E4,d,E4,d,rest,d, 
               ToneControl.BLOCK_END,0,
               ToneControl.PLAY_BLOCK,0,
               D4,d,D4,d,D4,d,rest,d,E4,d,G4,d,G4,d,rest,d, 
               ToneControl.PLAY_BLOCK,0,  
               D4,d,D4,d,E4,d,D4,d,C4,d,rest,d 
               };
      
         // Creacion del objeto Player
         try{     
            player = Manager.createPlayer(Manager.TONE_DEVICE_LOCATOR);
            player.realize();
         }
             catch(Exception e){
            }
      
         // Obtencion del controlador para la reproduccion de secuencias
      
         ToneControl c = (ToneControl)player.getControl("ToneControl");
         c.setSequence(mySequence);
         salida = "Secuencia de tonos simples";
         salida2 = "";
      	
      }
   	
       public void run() {
         try{
            player.start();
         		
            Thread.sleep(8000);
            player.stop();
         
         }
             catch(Exception e){
            }
         salida = "1 - 6: Tonos simples";
         salida2 = "7 y 9: Bajar/Subir volumen";
         while (true) {
            try{ 
            // Capturar pulsacion de tecla
               int keyState = this.getKeyStates();
               if (keyState!=0) {
                  this.nota(keyState);
               	// Reproducir si se ha pulsado una tecla correspondiente a una nota
                  if (nota!=0)
                     Manager.playTone(nota, 75/*ms*/,volumen);
               }
            	// Representacion grafica
               this.paint(g);
               flushGraphics();
               Thread.sleep(125);
            }
                catch(Exception e){
                  System.out.println(e);
               }
         }
      }
   
   	// Metodo para determinar la nota a reproducir   
       public void nota(int key){
         switch (key)
         {
            case 512:
               nota = notas[0];
               num_nota=1;
               break;
         		
            case 2:
               nota = notas[1];				
               num_nota=2;
               break;
         
            case 1024:
               nota = notas[2];				
               num_nota=3;
               break;
         
            case 4:
               nota = notas[3];
               num_nota=4;
               break;
         
         
            case 256:
            
               nota = notas[4];				
               num_nota=5;
               break;
         
         
            case 32:
               nota = notas[5];
               num_nota=6;
               break;
         
            case 2048:
            
               volumen-=5;
               nota=0;
               break;
         
         
            case 4096:
            
               volumen+=5;
               nota=0;
               break;
         }
      }
   
   	// Representacion grafica de la nota reproducida
       public void paint(Graphics g){
       
         g.setColor(0x00FFFF88);
         g.fillRect(0,0,width,height);
         g.setColor(0x00000000);
         g.drawString(salida,5,5,0);
         g.drawString(salida2,5,15,0);
         if (nota!=0){
            g.setColor(nota);
            g.fillRect(5,height/2,19*num_nota,30);
            nota=0;
            num_nota=0;
         }
      }
   }