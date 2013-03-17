package src;

import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.media.control.*;

public class RecordMIDlet extends MIDlet{
    public void startApp(){
        Display.getDisplay(this).setCurrent(new RecordForm());
    }
    
    public void pauseApp(){}
    
    public void destroyApp(boolean unconditional){}
}

class RecordForm extends Form implements CommandListener{
	private StringItem messageItem;
    private StringItem errorItem;
    private final Command recordCommand, playCommand;
    private Player p;
    private byte[] recordedSoundArray = null;
    
    public RecordForm(){
        super("Record Audio");

        recordCommand = new Command("Record", Command.SCREEN, 1);
        playCommand = new Command("Play", Command.SCREEN, 2);

        messageItem = new StringItem("Record", "Click record to start recording.");
        errorItem = new StringItem("","");

        this.append(messageItem);
        this.append(errorItem);

        this.addCommand(recordCommand);
        this.addCommand(playCommand);   
             
        StringBuffer inhalt = new StringBuffer();        
        this.setCommandListener(this);
    }
    
    public void commandAction(Command comm, Displayable disp){
        if(comm==recordCommand){
            try{                
                messageItem.setText("recording...");
                p = Manager.createPlayer("capture://audio");
                p.realize();
                RecordControl rc = (RecordControl)p.getControl("RecordControl");
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                rc.setRecordStream(output);
                p.prefetch();
                rc.startRecord();
                p.start();
                Thread.currentThread().sleep(5000);
                rc.commit();
                recordedSoundArray = output.toByteArray();
                p.close();
                messageItem.setText("done!");
            } catch (IOException ioe) {
                errorItem.setLabel("Error");
                errorItem.setText(ioe.toString());
            } catch (MediaException me) {
                errorItem.setLabel("Error");
                errorItem.setText(me.toString());
            } catch (InterruptedException ie) {
                errorItem.setLabel("Error");
                errorItem.setText(ie.toString());
            }
        } else if(comm == playCommand) {
            try {
                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(recordedSoundArray);
                Player p2 = Manager.createPlayer(recordedInputStream,"audio/X-wav");
                p2.prefetch();
                p2.start();
            }  catch (IOException ioe) {
                errorItem.setLabel("Error");
                errorItem.setText(ioe.toString());
            } catch (MediaException me) {
                errorItem.setLabel("Error");
                errorItem.setText(me.toString());
            }
        }
    }
}