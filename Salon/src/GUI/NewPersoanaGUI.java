package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import BLL.PersoanaBLL;
import model.Persoana;
import BLL.ProgramareBLL;
import model.User;
import java.awt.Color;

public class NewPersoanaGUI 
{
	private PersoanaBLL persoana=new PersoanaBLL();
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

/**
 * Launch the application.
 */
	public static void NewPersoanaGUI(User x) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPersoanaGUI window = new NewPersoanaGUI(x);
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
	public NewPersoanaGUI(User x) {
		initialize(x);
	}


/**
 * Initialize the contents of the frame.
 */
private void initialize(User x) {
	frame = new JFrame();
	frame.getContentPane().setBackground(new Color(255, 51, 102));
	frame.setBounds(100, 100, 257, 318);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Nume");
	lblNewLabel.setBounds(10, 28, 66, 14);
	frame.getContentPane().add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Prenume");
	lblNewLabel_1.setBounds(10, 53, 66, 14);
	frame.getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("Email");
	lblNewLabel_2.setBounds(10, 78, 66, 14);
	frame.getContentPane().add(lblNewLabel_2);
	
	JLabel lblNewLabel_3 = new JLabel("Data_nasterii");
	lblNewLabel_3.setBounds(10, 103, 66, 14);
	frame.getContentPane().add(lblNewLabel_3);
	
	JLabel lblNewLabel_4 = new JLabel("Varsta");
	lblNewLabel_4.setBounds(10, 128, 66, 14);
	frame.getContentPane().add(lblNewLabel_4);
	
	JLabel lblNewLabel_5 = new JLabel("Adresa");
	lblNewLabel_5.setBounds(10, 153, 66, 14);
	frame.getContentPane().add(lblNewLabel_5);
	
	JLabel lblNewLabel_6 = new JLabel("Sex");
	lblNewLabel_6.setBounds(10, 178, 66, 14);
	frame.getContentPane().add(lblNewLabel_6);
	
	textField = new JTextField();
	textField.setBounds(86, 25, 104, 20);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();
	textField_1.setBounds(86, 50, 104, 20);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	textField_2 = new JTextField();
	textField_2.setBounds(86, 75, 104, 20);
	frame.getContentPane().add(textField_2);
	textField_2.setColumns(10);
	
	textField_3 = new JTextField();
	textField_3.setBounds(86, 100, 104, 20);
	frame.getContentPane().add(textField_3);
	textField_3.setColumns(10);
	
	textField_4 = new JTextField();
	textField_4.setBounds(86, 125, 104, 20);
	frame.getContentPane().add(textField_4);
	textField_4.setColumns(10);
	
	textField_5 = new JTextField();
	textField_5.setBounds(86, 150, 104, 20);
	frame.getContentPane().add(textField_5);
	textField_5.setColumns(10);
	
	textField_6 = new JTextField();
	textField_6.setBounds(86, 175, 104, 20);
	frame.getContentPane().add(textField_6);
	textField_6.setColumns(10);
	Persoana prs=new Persoana();
	JButton btnNewButton = new JButton("ADD");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println(x.toString());
			prs.setUser_id(x.getIdUser());
			prs.setNume(textField.getText());
			prs.setPrenume(textField_1.getText());
			prs.setEmail(textField_2.getText());
			prs.setData_nasterii(Date.valueOf(textField_3.getText()));
			prs.setVarsta(Integer.valueOf(textField_4.getText()));
			prs.setAdresa(textField_5.getText());
			prs.setSex(textField_6.getText());
			persoana.insertPersoana(prs);
			UserGUI.UserView(x);
			frame.setVisible(false);
		}
	});
	btnNewButton.setBounds(10, 203, 180, 23);
	frame.getContentPane().add(btnNewButton);
}



}
