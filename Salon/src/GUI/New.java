package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import BLL.UserBLL;
import model.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class New {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	
	UserBLL user=new UserBLL();
	/**
	 * Launch the application.
	 */
	public static void NewView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New window = new New();
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
	public New() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 51, 51));
		frame.setBounds(100, 100, 265, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 32, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(90, 29, 108, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(90, 60, 108, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(90, 91, 108, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(90, 122, 108, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(90, 153, 108, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(90, 184, 108, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(90, 215, 108, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 63, 70, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(10, 94, 70, 14);
		frame.getContentPane().add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(10, 125, 70, 14);
		frame.getContentPane().add(lblPrenume);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(10, 156, 70, 14);
		frame.getContentPane().add(lblCnp);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 187, 70, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblNrTel = new JLabel("Nr. tel.");
		lblNrTel.setBounds(10, 218, 70, 14);
		frame.getContentPane().add(lblNrTel);
		User x=new User();
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x.setUsername(textField.getText());
				x.setPassword(textField_1.getText());
				x.setNume(textField_2.getText());
				x.setPrenume(textField_3.getText());
				x.setCnp(textField_4.getText());
				x.setEmail(textField_5.getText());
				x.setNr_tel(textField_6.getText());
				user.insertUser(x);
			}
		});
		btnCreate.setBounds(10, 243, 188, 23);
		frame.getContentPane().add(btnCreate);
	}

}
