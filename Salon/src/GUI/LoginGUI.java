package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import BLL.AdminBLL;
import BLL.UserBLL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.Admin;
import model.User;
public class LoginGUI {
	public static AdminGUI adminGUI;
	UserGUI userGUI;
	New newGUI;
	private UserBLL user=new UserBLL();
	private AdminBLL admin=new AdminBLL();
	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnCreateNewUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void back() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public User getUserDetails() {
		try {
			String username = textField.getText();
			
			int x=user.findUserIdByUN(username);
			System.out.println(x);
			return user.findUserById(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Admin getAdminDetails() {
		//User client = new User(username, password,nume, prenume,cnp,card,adresa, email,0);
		try {
			String username = textField.getText();
			
			int x=admin.findAdminIdByUN(username);
			return admin.findAdminById(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(220, 20, 60));
		frame.setBounds(100, 100, 412, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(new Color(240, 128, 128));
		textField.setBounds(104, 35, 124, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(240, 128, 128));
		textField_1.setBounds(104, 66, 124, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 38, 66, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 69, 66, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User x=getUserDetails();
				Admin y=getAdminDetails();
				
				if(x!=null && (x.getPassword().equals(textField.getText()) && x.getUsername().equals(textField_1.getText()))) {
					userGUI.UserView(x);
					frame.setVisible(false);
				}
				else
					if(y.getUsername().equals(textField.getText()) && y.getPassword().equals(textField_1.getText())) {
						adminGUI.AdminView(y);
						frame.setVisible(false);
					}
					else JOptionPane.showMessageDialog(frame,"Username sau parola gresita!");
			}
		});
		btnLogin.setForeground(new Color(240, 255, 255));
		btnLogin.setBackground(new Color(139, 0, 0));
		btnLogin.setBounds(28, 107, 200, 23);
		frame.getContentPane().add(btnLogin);
		
		btnCreateNewUser = new JButton("Create new user");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGUI.NewView();
				frame.setVisible(false);
			}
		});
		btnCreateNewUser.setBackground(new Color(139, 0, 0));
		btnCreateNewUser.setForeground(new Color(240, 255, 255));
		btnCreateNewUser.setBounds(28, 134, 200, 23);
		frame.getContentPane().add(btnCreateNewUser);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String s=textField.getSelectedText();
				System.out.printf(s);
				
			
			}
		});
		btnNewButton.setBounds(137, 170, 97, 25);
		frame.getContentPane().add(btnNewButton);
	}
}
