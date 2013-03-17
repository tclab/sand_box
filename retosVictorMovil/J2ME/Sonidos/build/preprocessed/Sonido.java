/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.midlet.*;
import javax.microedition.media.*;
import java.io.*;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;

/**
 * @author Victor
 */
public class Sonido extends MIDlet implements CommandListener, ItemStateListener {

    Display display;
    private Command back;
    private Command play;
    private Form form;
    private List lista;
    private Image caballo,  vaca,  elephant,  hen,  wolf,  lamb;

    public Sonido() throws IOException {
        display = Display.getDisplay(this);
        String[] stringElements = {"Horse", "Cow", "Elephant", "Hen", "Wolf", "Lamb"};

        caballo = Image.createImage("caballo.png");
        caballo = createThumbnail(caballo);

        vaca = Image.createImage("vaca.png");
        vaca = createThumbnail(vaca);

        elephant = Image.createImage("elephant.png");
        elephant = createThumbnail(elephant);

        hen = Image.createImage("hen.png");
        hen = createThumbnail(hen);

        wolf = Image.createImage("wolf.png");
        wolf = createThumbnail(wolf);

        lamb = Image.createImage("lamb.png");
        lamb = createThumbnail(lamb);

        Image[] imageElements = {caballo, vaca, elephant, hen, wolf, lamb};
        lista = new List("Animal Sounds", List.IMPLICIT, stringElements, imageElements);
        play = new Command("Play", Command.OK, 1);
        back = new Command("Back", Command.BACK, 2);
//        lista.append("Horse", null);
//        lista.append("Cow", null);
//        lista.append("Elephant", null);
//        lista.append("Hen", null);
//        lista.append("Wolf", null);
//        lista.append("Lamb", null);
        lista.addCommand(back);
        lista.addCommand(play);
        lista.setCommandListener(this);
//        form.addCommand(back);
//        form.addCommand(play);

    }

    private Image createThumbnail(Image image) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int thumbWidth = 35;
        int thumbHeight = -5;

        if (thumbHeight == -5) {
            thumbHeight = thumbWidth * sourceHeight / sourceWidth;
        }
        Image thumb = Image.createImage(thumbWidth, thumbHeight);
        Graphics g = thumb.getGraphics();
        for (int y = 0; y < thumbHeight; y++) {
            for (int x = 0; x < thumbWidth; x++) {


                g.setClip(x, y, 1, 1);

                int dx = x * sourceWidth / thumbWidth;
                int dy = y * sourceHeight / thumbHeight;

                g.drawImage(image, x - dx, y - dy, Graphics.LEFT | Graphics.TOP);

            }

        }
        Image immutableThumb = Image.createImage(thumb);

        return immutableThumb;
    }

    public void startApp() {
//        display.setCurrent(form);
        display.setCurrent(lista);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            destroyApp(false);
            notifyDestroyed();

        }


        if (c == play) {
            if (lista.isSelected(0) == true) {
                try {
                    System.out.println("Horse");
                    InputStream is = getClass().getResourceAsStream("Caballo.wav");
                    Player p = Manager.createPlayer(is, "audio/X-wav");
                    p.start();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (lista.isSelected(1) == true) {
                try {
                    System.out.println("Cow");
                    InputStream is = getClass().getResourceAsStream("Vaca.wav");
                    Player p = Manager.createPlayer(is, "audio/X-wav");
                    p.start();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (lista.isSelected(2) == true) {
                try {
                    System.out.println("Elephant");
                    InputStream is = getClass().getResourceAsStream("Elephant.wav");
                    Player p = Manager.createPlayer(is, "audio/X-wav");
                    p.start();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (lista.isSelected(3) == true) {
                try {
                    System.out.println("Hen");
                    InputStream is = getClass().getResourceAsStream("Gallo.wav");
                    Player p = Manager.createPlayer(is, "audio/X-wav");
                    p.start();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (lista.isSelected(4) == true) {
                try {
                    System.out.println("Wolf");
                    InputStream is = getClass().getResourceAsStream("Wolf.wav");
                    Player p = Manager.createPlayer(is, "audio/X-wav");
                    p.start();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (lista.isSelected(5) == true) {
                try {
                    System.out.println("Lamb");
                    InputStream is = getClass().getResourceAsStream("Oveja.wav");
                    Player p = Manager.createPlayer(is, "audio/X-wav");
                    p.start();
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
    }

    public void itemStateChanged(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
