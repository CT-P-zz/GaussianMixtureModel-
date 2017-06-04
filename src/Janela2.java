import java.io.*;
import java.util.Arrays;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Font;
import java.awt.Color;



public class Janela2 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela2 window = new Janela2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	static Grafos W = new Grafos();
	Grafos G = new Grafos();

	private void initialize() {
		frame = new JFrame("PharmaK Application");
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(null);
		
		JLabel lblCreateA = new JLabel("Step 1. Create a compartments' graph ");
		lblCreateA.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblCreateA.setBounds(94, 11, 248, 26);
		frame.getContentPane().add(lblCreateA);
		
		JLabel lblInsertNumberOf = new JLabel("Number of nodes:");
		lblInsertNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInsertNumberOf.setBounds(31, 48, 130, 14);
		frame.getContentPane().add(lblInsertNumberOf);
		
		textField = new JTextField();
		textField.setBounds(139, 48, 46, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newS = textField.getText();
				int num = Integer.parseInt(newS);
				G.grafoo(num);
			}
		});
		btnCreate.setBounds(235, 45, 89, 23);
		frame.getContentPane().add(btnCreate);
		
		
		JLabel lblAddEdgeFrom = new JLabel("Edge from ");
		lblAddEdgeFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddEdgeFrom.setBounds(182, 79, 62, 14);
		frame.getContentPane().add(lblAddEdgeFrom);
		
		textField_1 = new JTextField();
		textField_1.setBounds(255, 77, 34, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblToNode = new JLabel("to");
		lblToNode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblToNode.setBounds(299, 79, 16, 14);
		frame.getContentPane().add(lblToNode);
		
		textField_2 = new JTextField();
		textField_2.setBounds(325, 77, 34, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(100, 139, 231, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(160, 108, 40, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
		final JRadioButton rdbtnAdd = new JRadioButton("Add",true);
		rdbtnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAdd.setBounds(31, 75, 62, 23);
		frame.getContentPane().add(rdbtnAdd);
		
		
		final JRadioButton rdbtnRemove = new JRadioButton("Remove",false);
		rdbtnRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRemove.setBounds(95, 75, 81, 23);
		frame.getContentPane().add(rdbtnRemove);
		
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnAdd);
		bgroup.add(rdbtnRemove);
		
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				G = new Grafos();
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnReset.setBounds(226, 227, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				W = G;
				Janela3 novo = new Janela3();
				novo.setVisible(true);
				
			}
		});
		btnNext.setBounds(325, 227, 89, 23);
		frame.getContentPane().add(btnNext);
		
		JLabel Plabel = new JLabel("Parameters");
		Plabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Plabel.setBounds(31, 141, 89, 14);
		frame.getContentPane().add(Plabel);
		
	
		
		JButton Bbutton = new JButton("Browser");
		Bbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				    "CSV Files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					textField_3.setText(chooser.getSelectedFile().getAbsolutePath());
				   System.out.println("You chose to open this file: " +
				        chooser.getSelectedFile().getName());
				}
			}
		});
		Bbutton.setBounds(335, 138, 89, 23);
		frame.getContentPane().add(Bbutton);
		
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newS1 = textField_1.getText();
				int num1 = Integer.parseInt(newS1);
				String newS2 = textField_2.getText();
				int num2 = Integer.parseInt(newS2);
				String newS4 = textField_4.getText();
				int num4 = Integer.parseInt(newS4);
				String newS3 = textField_3.getText();
				
				try{
				
				Gaussianas E = new Gaussianas();
				BufferedReader CSVFile = new BufferedReader (new FileReader (newS3));
				String dataRow = CSVFile.readLine ();
				int j = 1; 
				while (dataRow != null ){
				String[] dataArray = dataRow.split (",");
				double[] doubleArray =new double[dataArray. length];
				int i=0;
				while (i< dataArray.length){
					doubleArray[i]= Double.parseDouble(dataArray [i]);
					 i++;
					 }
				double []aux = new double[6];
				aux[0]=Math.pow(num4, -1);
				aux[1]=1;
				aux[2]=doubleArray[0];
				aux[3]=-doubleArray[0];
				aux[4]=doubleArray[1];
				aux[5]=doubleArray[2];
				
					E.mix2(j, aux); // Add samples from .csv
					j++;
					dataRow = CSVFile . readLine (); 	 
				}
				 CSVFile . close ();
				 
				if(rdbtnAdd.isSelected()){
					 G.add_edge(num1, num2,E);};
				if(rdbtnRemove.isSelected()){
					G.remove_edge(num1, num2);
				}
				}catch(Exception e){
                    e.printStackTrace();
                }
				G.apresenta();	 
			     
				
				JOptionPane.showMessageDialog(null,"Graph created with success");
			}}
		);
		btnOk.setBounds(170, 186, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JLabel lblNumberOfGaussians = new JLabel("Number of  Gaussians");
		lblNumberOfGaussians.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumberOfGaussians.setBounds(31, 110, 140, 14);
		frame.getContentPane().add(lblNumberOfGaussians);
		
		
	}
}

