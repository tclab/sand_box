
package com.estudio.gui;

import javax.swing.*;

import sun.security.acl.GroupImpl;

import java.awt.*;

public class Pruebas extends JFrame {
    
	//Elementos - initComponents
	private JLabel celsiusLabel;
    private JButton convertButton;
    private JLabel fahrenheitLabel;
    private JTextField tempTextField;
    
    //Elementos - initComponents2
    private JButton aceptar;
    private JButton salir;
    private JButton prueba;
	
    //Constructor
    public Pruebas() {
        initComponents2();
    }
    
    /**
     * Prueba
     */
    private void initComponents2(){
    	aceptar = new JButton();
    	salir = new JButton();
    	prueba = new JButton();
    	
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	setTitle("Prueba de groupLayout");
    	
    	aceptar.setText("Aceptar");
    	salir.setText("Salir");
    	prueba.setText("PRUEBA");
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
    	getContentPane().setLayout(layout);
    	
    	layout.setAutoCreateContainerGaps(true);
    	layout.setAutoCreateGaps(true);
    	
    	GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();
    	layout.setHorizontalGroup(horizontal
    			.addGroup(layout.createParallelGroup()
    					.addComponent(prueba)
    					.addComponent(aceptar))
    			.addGroup(layout.createParallelGroup()
    					.addComponent(salir))
    	);
    	
    	
    	GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();
    	layout.setVerticalGroup(vertical
    			.addComponent(prueba)
    			.addGroup(layout.createParallelGroup()
    					.addComponent(aceptar)
    					.addComponent(salir))
    	);
    	
    	
    }
    
    /** Inicia y configura los componentes.
     * Es llamado por el constructor. 
     */
    private void initComponents() {
        tempTextField = new JTextField();
        celsiusLabel = new JLabel();
        convertButton = new JButton();
        fahrenheitLabel = new JLabel();

        //Operacion de salida.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Celsius Converter");
        
        //Label celcius
        celsiusLabel.setText("Celsius");

        //Boton
        convertButton.setText("Convert");
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        //Label Farenheit
        fahrenheitLabel.setText("Fahrenheit");

        //Distribucion de los elementos en el frame
        
        //Horizontal
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        
        layout.setHorizontalGroup(
        		
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            
            .addGroup(layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(celsiusLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(convertButton)
                        .addComponent(fahrenheitLabel))))
        );

        //Vertical
        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {convertButton, tempTextField});

        layout.setVerticalGroup(
        		
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(celsiusLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(convertButton)
                    .addComponent(fahrenheitLabel)))
        );
        pack();
    }

    /**
     * Accion realizada al precionar el boton
     * @param evt
     */
    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	//Parse degrees Celsius as a double and convert to Fahrenheit
        int tempFahr = (int)((Double.parseDouble(tempTextField.getText()))
            * 1.8 + 32);
        fahrenheitLabel.setText(tempFahr + " Fahrenheit");
    }
    
    /**
     * @param args Argumentos pasados por linea de comando
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pruebas().setVisible(true);
            }
        });
    }
}


//package com.estudio.gui;
//
//import javax.swing.*;
//
//public class Pruebas extends JFrame{
//	
//	public Pruebas(){
//		initComponents();
//		setVisible(true);
//	}
//	
//	/**
//	 * Se inician y setean componentes
//	 */
//	private void initComponents(){
//		
//		//Componentes a usar
//		JLabel label1 = new JLabel("Primerazo!!");
//		
//		//Se configura componentes y contenedor
//		setSize(200,100);
//		
//		//Se agregan al contenedor
//		getContentPane().add(label1,"Center");
//	}
//	
//	public static void main (String args []){
//		try{
////			UIManager.getSystemLookAndFeelClassName();
//			UIManager.getCrossPlatformLookAndFeelClassName();
//		}catch(Exception e){
//			
//		}
//		new Pruebas();
//	}
//}