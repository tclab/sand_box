import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class Ejemplo4MIDlet extends MIDlet implements CommandListener, ItemStateListener  {
    
    private Display display;
    private Alert alerta;

    private Form form;
    private TextField titulo;
    private TextField texto; 
    private ChoiceGroup tipo;
    private ChoiceGroup tiempo;
    private Gauge segundos;
    

    private Command continuar; 
    private Command salir; 

    private int segant=2;
 
    //Constructor
    public Ejemplo4MIDlet(  ) {

	//Obtenemos el display
	display=Display.getDisplay(this);

        //Creamos los items Form
 	  //un item para preguntar el titulo del alert
          titulo= new TextField("Titulo","Alerta",10,TextField.ANY);
   	  //un item para preguntar el texto del alert
          texto= new TextField("","Este es el texto del Alert",100,TextField.ANY);

   	  //un item para preguntar que tipo del grupo se escoger para el alert
	  tipo=new ChoiceGroup("Tipo",ChoiceGroup.EXCLUSIVE);
          tipo.append("Alarm",null);
          tipo.append("Confirmation",null);
          tipo.append("Error",null);
          tipo.append("Info",null);
          tipo.append("Warning",null);
	  tipo.setSelectedIndex(0,true);

   	  //un item para preguntar que tiempo escoge para el alert
	  tiempo=new ChoiceGroup("Tiempo",ChoiceGroup.EXCLUSIVE);
	  tiempo.append("Siempre",null);
	  tiempo.append("Un rato",null);
	  tiempo.setSelectedIndex(0,true);

   	  //un item para preguntar que tiempo en segundos escoge para el alert
	  segundos=new Gauge("Segundos (0=Siempre) (Max=20seg=",true, 20, segant);

        //Creamos el form y le añadimos los items
        form = new Form ("Ejemplo Alert");
        form.append(titulo); 
        form.append(texto);
        form.append(tipo);
        form.append(tiempo);
        form.append(segundos);

	//Creamos los comandos del form
	continuar=new Command("Continuar",Command.OK, 0);
	salir=new Command("Salir",Command.EXIT, 3);

        //añadimos los comandos al form y activamos el oyente
	form.addCommand(continuar);
	form.addCommand(salir);
        form.setCommandListener(this);
        form.setItemStateListener(this);
    }
 
    //Método que se llama cuando pasamos de Pausado a Activo
    protected void startApp(  ) {
	System.out.println("Paso a estado Activo");
        display.setCurrent(form);
    }
 
    //Método que se llama cuando pasamos de Activo a Pausado
    protected void pauseApp(  ) {
	System.out.println("Paso a estado Pausado");
    }
 
    //Método que se llama cuando se destruye el midlet
    protected void destroyApp(boolean incondicional) {
	System.out.println("Paso a estado Destruido");
    }

    //Método para el tratamiento de datos de teclado 
    public void commandAction(Command c, Displayable d) {
	//Miramos si nos salimos o mostramos la alerta
	if (c ==salir) {
	   destroyApp(true);
	   notifyDestroyed();
        } else if (c == continuar) {
	    //creamos la laerta con el titulo, el texto y el tipo escogido
	    switch (tipo.getSelectedIndex()) {
		case 0: alerta=new Alert(titulo.getString(),texto.getString(),null, AlertType.ALARM); break;
		case 1: alerta=new Alert(titulo.getString(),texto.getString(),null, AlertType.CONFIRMATION); break;
		case 2: alerta=new Alert(titulo.getString(),texto.getString(),null, AlertType.ERROR); break;
		case 3: alerta=new Alert(titulo.getString(),texto.getString(),null, AlertType.INFO); break;
		case 4: alerta=new Alert(titulo.getString(),texto.getString(),null, AlertType.WARNING); break;	    
	     default:
  		   alerta=new Alert(titulo.getString(),texto.getString(),null, AlertType.INFO); 
	    }
	   
	    //Le ponemos el tiempo a la alerta
	    if ((tiempo.getSelectedIndex() == 0) || (segundos.getValue()==0) )
		alerta.setTimeout(Alert.FOREVER);
	    else
		alerta.setTimeout(segundos.getValue()*1000);

	    //Mostramos la alerta
	    display.setCurrent(alerta);
        } else System.out.println("Otro comando pulsado");
        
   } 

    //Método para el tratamiento de cambios en los items 
    public void itemStateChanged(Item item) {
	//Si cambia el tiemp-> cambiamos los segundos
	if (item == tiempo) {
	    if (tiempo.getSelectedIndex() == 0) {
		segant=segundos.getValue();
		segundos.setValue(0);
	    } else 
		segundos.setValue(segant);
		//Si cambia los segundos-> cambiamos el tiempo
	} else if (item == segundos) {
	    if (segundos.getValue() == 0) 
		tiempo.setSelectedIndex(0,true);
	    else 
		tiempo.setSelectedIndex(1,true);
	    
	} else System.out.println("Otro item cambiado pulsado");
    }
}

