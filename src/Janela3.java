import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Font;
import java.awt.Color;



public class Janela3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela3 frame = new Janela3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Gaussianas resultados = new Gaussianas();
	
	public Janela3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseAn = new JLabel("Step 2. Set edge to  learn");
		lblChooseAn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChooseAn.setBounds(129, 23, 187, 23);
		contentPane.add(lblChooseAn);
		
		JLabel lblChooseEdge = new JLabel("Choose edge from");
		lblChooseEdge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChooseEdge.setBounds(34, 64, 109, 14);
		contentPane.add(lblChooseEdge);
		
		textField = new JTextField();
		textField.setBounds(151, 61, 36, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(197, 64, 25, 14);
		contentPane.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(218, 61, 36, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSample = new JLabel("Sample");
		lblSample.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSample.setBounds(35, 105, 46, 14);
		contentPane.add(lblSample);
		
		textField_2 = new JTextField();
		textField_2.setBounds(87, 103, 240, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnBrowser = new JButton("Browser");
		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				    "CSV Files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					textField_2.setText(chooser.getSelectedFile().getAbsolutePath());
				   System.out.println("You chose to open this file: " +
				        chooser.getSelectedFile().getName());
				}
			}
		});
		btnBrowser.setBounds(335, 102, 89, 23);
		contentPane.add(btnBrowser);
		
	
		
		JButton btnLearnForEdge = new JButton("Learn");
		btnLearnForEdge.setForeground(Color.BLUE);
		btnLearnForEdge.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLearnForEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newS = textField.getText();
				int num1 = Integer.parseInt(newS);
				String newS1 = textField_1.getText();
				int num2 = Integer.parseInt(newS1);
				String newS3 = textField_2.getText();
				
				Gaussianas aresta = Janela2.W.gaussno (num1, num2);
				
				
				
				try{
					
					BufferedReader CSVFile = new BufferedReader (new FileReader (newS3));
					CSVFile.readLine ();
					String dataRow = CSVFile.readLine (); //sample in csv
					
					Amostra A = new Amostra(); //sample
					while (dataRow != null ){
					String[] dataArray = dataRow.split (";"); //csv delimeter
					double[] doubleArray =new double[dataArray . length];
					int i=0;
					while (i< dataArray.length){
					doubleArray [i]= Double.parseDouble (dataArray [i]);
					 i++;
					 }
					double []aux = new double[2]; //aux matrix is the sample
					int l = (int)doubleArray[0]; 
					aux[0]=doubleArray[1];
					aux[1]=doubleArray[2];
					A.add(l, aux); //add to l, aux !!!!
					dataRow = CSVFile . readLine (); 
					 
					}
					 CSVFile . close ();
					 //A.apresenta();
					 resultados.mix(EM.algoritmo(A,aresta.theta())); //running the algorithm
					
					 
					}catch(Exception e1){
	                    e1.printStackTrace();
	                }
				JOptionPane.showMessageDialog(null,"Learnt");
			}
		});
		btnLearnForEdge.setBounds(151, 151, 120, 35);
		contentPane.add(btnLearnForEdge);
		
		
		
		JButton btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(23, 213, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setCurrentDirectory(new File("C:\\"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
				jFileChooser.setFileFilter(filter);
				
				

				
				if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					PrintWriter pw=null;
				    try{
				        pw = new PrintWriter(new FileWriter(jFileChooser.getSelectedFile(), true)); 
				        pw.println("Aresta do n� "+textField.getText() + "para o n�" +textField_1.getText());
				        int i=0;
				        double [][]aux= resultados.theta();
				        while(i<aux.length){
				        	
				        	pw.println("Par�metros para Gaussiana " +i);
				        	pw.println("w,sigma,a1,a2,b1,b2: " +aux[i][0]+" ; " +aux[i][1] +" ; " +aux[i][2]+" ; "+aux[i][3]+" ; "+aux[i][4]+" ; "+aux[i][5]);
				        	
				        	i++;
				        }     
				    }
				    catch(IOException e)
				    {
				     e.printStackTrace();
				    }
				finally
				{
				   pw.close();
				} 
				      
				    } 

				JOptionPane.showMessageDialog(null,"Data saved with success");
			    
			}
		});
		btnSave.setBounds(318, 213, 89, 23);
		contentPane.add(btnSave);
	}
}

