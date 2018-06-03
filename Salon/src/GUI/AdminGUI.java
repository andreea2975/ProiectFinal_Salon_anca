package GUI;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.Admin;
import model.Cos;
import model.Persoana;
import model.Programare;
import model.Servicii;
import model.User;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.CosBLL;
import BLL.PersoanaBLL;
import BLL.ProgramareBLL;
import BLL.ServiciiBLL;
import BLL.UserBLL;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class AdminGUI {
	PersoanaBLL persoane=new PersoanaBLL();
	ProgramareBLL programare=new ProgramareBLL();
	Servicii s=new Servicii();
	ServiciiBLL servicii=new ServiciiBLL();
	CosBLL cos = new CosBLL();
	UserBLL user=new UserBLL();
	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void AdminView(Admin x) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI window = new AdminGUI(x);
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
	public AdminGUI(Admin x) {
		initialize(x);
	}
	
	public void insertServicii()
	{
		String categorie=textField.getText();
		String denumire=textField_1.getText();
		String detalii=textField_2.getText();
		int bonus1=Integer.parseInt(textField_3.getText());
		double pret1=Double.parseDouble(textField_4.getText());
		Servicii s=new Servicii(categorie,denumire,detalii,bonus1,pret1);
		servicii.insertServicii(s);
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Admin x) {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 51, 51));
		frame.setBounds(100, 100, 1180, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 772, 98);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblUsers = new JLabel("Users:");
		lblUsers.setBounds(10, 11, 83, 14);
		frame.getContentPane().add(lblUsers);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 173, 772, 98);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblPers = new JLabel("Persoane:");
		lblPers.setBounds(10, 154, 60, 14);
		frame.getContentPane().add(lblPers);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 316, 772, 98);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		
		Object[] r2 = new Object[6];
		Object[] r = new Object[9];
		Object[] r1 = new Object[8];
		Object[] r3 = new Object[5];
		Object[] col2= {"Id","Username","Password","Nume","Prenume","CNP","Email","Nr_tel","Bonus"};
		Object[] col1= {"Id","Nume","Prenume","Email","Data_nasterii","Varsta","Adresa","Sex"};
		Object[] col= {"Id","Categorie","Servicii","detalii","Bonus","Pret"};
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableModel model1 = new DefaultTableModel();
		DefaultTableModel model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(col2);
		model1.setColumnIdentifiers(col1);
		model.setColumnIdentifiers(col);
		table_2.setModel(model);
		table_1.setModel(model1);
		table.setModel(model2);
		ArrayList<Servicii> serv = servicii.getAllServicii();
		ArrayList<User> usr = user.getAllUsers();
		ArrayList<Persoana> lst = persoane.getAllPersoane();
		ArrayList<Programare> pr = programare.getAllProgramari();
		ArrayList<Cos> cs = cos.getAllCos();
		int bonus=0;
		for(User d:usr) {
				r[0]=d.getIdUser();
				r[1]=d.getUsername();
				r[2]=d.getPassword();
				r[3]=d.getNume();
				r[4]=d.getPrenume();
				r[5]=d.getCnp();
				r[6]=d.getEmail();
				r[7]=d.getNr_tel();
				for(Persoana dt:lst) {
					if(dt.getUser_id()==d.getIdUser()) {
						for(Programare p:pr) {
							bonus=bonus+p.getBonus_points();
						}
					
					}
					r[8]=bonus;
				}
					model2.addRow(r);
		}
		
		for(Persoana p:lst) {
			r1[0]=p.getId();
			r1[1]=p.getNume();
			r1[2]=p.getPrenume();
			r1[3]=p.getEmail();
			r1[4]=p.getData_nasterii();
			r1[5]=p.getVarsta();
			r1[6]=p.getAdresa();
			r1[7]=p.getSex();
			model1.addRow(r1);
	
	}
		
	for(Servicii s:serv)	
	{
		r2[0]=s.getId();
		r2[1]=s.getCategorie();
		r2[2]=s.getDenumire();
		r2[3]=s.getDetalii();
		r2[4]=s.getBonus_value();
		r2[5]=s.getPret();
		model.addRow(r2);
	}
		
		
		
		
		
		
		JLabel lblSalonEntrances = new JLabel("Servicii salon");
		lblSalonEntrances.setBounds(10, 298, 113, 14);
		frame.getContentPane().add(lblSalonEntrances);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				int id=(int)table.getValueAt(row, 0);
				user.deleteUser(id);
				model2.removeRow(row);
				
				
			}
		});
		
		
		
		btnDelete.setBounds(682, 137, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row1=table_1.getSelectedRow();
				int id=(int)table_1.getValueAt(row1, 0);
				persoane.deletePersoana(id);
				model1.removeRow(row1);
				
				
			}
		});
		btnDelete_1.setBounds(682, 280, 89, 23);
		frame.getContentPane().add(btnDelete_1);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int row2=table_2.getSelectedRow();
				int id=(int)table_2.getValueAt(row2, 0);
				servicii.deleteServicii(id);
				model.removeRow(row2);
			}
		});
		btnDelete_2.setBounds(682, 421, 89, 23);
		frame.getContentPane().add(btnDelete_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(581, 137, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.setBounds(581, 280, 89, 23);
		frame.getContentPane().add(btnUpdate_1);
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
		});
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowx=table_2.getSelectedRow();
				s=servicii.findServiciiById((int)table_2.getValueAt(rowx, 0));
				s.setId((int)table_2.getValueAt(rowx, 0));
				s.setCategorie((String)table_2.getValueAt(rowx, 1));
				s.setDenumire((String)table_2.getValueAt(rowx, 2));
				s.setDetalii((String)table_2.getValueAt(rowx, 3));
				s.setBonus_value((int)table_2.getValueAt(rowx, 4));
				s.setPret((Double)table_2.getValueAt(rowx, 5));
				servicii.updateServicii(s);
				
			
			}
		});
		btnUpdate_2.setBounds(581, 421, 89, 23);
		frame.getContentPane().add(btnUpdate_2);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnInsert.setBounds(472, 135, 97, 25);
		frame.getContentPane().add(btnInsert);
		
		JButton btnInsert_1 = new JButton("Insert");
		btnInsert_1.setBounds(472, 279, 97, 25);
		frame.getContentPane().add(btnInsert_1);
		
	
	
		JButton btnInsert_2 = new JButton("Insert");
		btnInsert_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnInsert_2))
				{
					String categorie=textField.getText();
					String denumire=textField_1.getText();
					String detalii=textField_2.getText();
					int bonus1=Integer.parseInt(textField_3.getText());
					double pret1=Double.parseDouble(textField_4.getText());
					Servicii s=new Servicii(categorie,denumire,detalii,bonus1,pret1);
					int id=servicii.insertServicii(s);
					r2[0]=s.getId();
					r2[1]=s.getCategorie();
					r2[2]=s.getDenumire();
					r2[3]=s.getDetalii();
					r2[4]=s.getBonus_value();
					r2[5]=s.getPret();
					model.addRow(r2);
					
					
					
				
				}
			}
		});
		btnInsert_2.setBounds(472, 420, 97, 25);
		frame.getContentPane().add(btnInsert_2);
		
		JLabel lblNewLabel = new JLabel("Categorie");
		lblNewLabel.setBounds(800, 30, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(877, 27, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Denumire");
		lblNewLabel_1.setBounds(800, 72, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(877, 69, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Bonus");
		lblNewLabel_2.setBounds(800, 153, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(877, 106, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Detalii");
		lblNewLabel_3.setBounds(800, 112, 56, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(877, 150, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Pret");
		lblNewLabel_4.setBounds(800, 193, 56, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(877, 190, 116, 22);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
		
		
		
		
		
		
		
	}
}
