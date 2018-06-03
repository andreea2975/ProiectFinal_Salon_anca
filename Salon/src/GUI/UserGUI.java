package GUI;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import model.User;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import BLL.CosBLL;
import BLL.PersoanaBLL;
import BLL.ProgramareBLL;
import BLL.ServiciiBLL;
import BLL.UserBLL;
import model.Cos;
import model.Persoana;
import model.Programare;
import model.Servicii;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class UserGUI {
	NewPersoanaGUI perGUI;
	 public static JFrame frame;
	private JTable table;
	private JTable table_2;
	Double pret=0.0;
	private long time=System.currentTimeMillis();
	private Date data=new Date(time);
	private JTextField textField;
	private JTextField textField_1;
	PersoanaBLL persoane=new PersoanaBLL();
	ProgramareBLL programare=new ProgramareBLL();
	ServiciiBLL servicii=new ServiciiBLL();
	CosBLL cos = new CosBLL();
	UserBLL user=new UserBLL();
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void UserView(User x) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI window = new UserGUI(x);
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
	public UserGUI(User x) {
		initialize(x);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User x) {
		System.out.println(x.toString());
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 51, 102));
		frame.setBounds(100, 100, 1068, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String[] items = {"Cosmetica","Coafor","Manichiura","Masaj"};
		JComboBox comboBox = new JComboBox(items);
		comboBox.setBounds(83, 115, 98, 20);
		frame.getContentPane().add(comboBox);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 1032, 89);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 169, 862, 107);
		frame.getContentPane().add(scrollPane_2);
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		textField_2 = new JTextField();
		textField_2.setBounds(540, 115, 74, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		///// CREARE O PROGRAMARE PENTRU UTILIZATOR \\\\\
		Programare programare_noua=new Programare();
		/////// interfata calendar ///////
		
		
		
		
		
		
		String selected;
		Object[] r2 = new Object[4];
		Object[] r = new Object[8];
		Object[] r1 = new Object[5];
		Object[] r3 = new Object[5];
		Object[] col2= {"Id","Denumire","Bonus","Pret"};
		Object[] col1= {"Id_persoana","Data","Servicii","Total"};
		Object[] col= {"Id", "Nume","Prenume", "Email", "Data_nasterii","Varsta","Adresa","Sex"};
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableModel model1 = new DefaultTableModel();
		DefaultTableModel model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(col2);
		model1.setColumnIdentifiers(col1);
		model.setColumnIdentifiers(col);
		table_2.setModel(model2);
		table.setModel(model);
		ArrayList<Servicii> serv = servicii.getAllServicii();
		ArrayList<Persoana> lst = persoane.getAllPersoane();
		ArrayList<Programare> pr = programare.getAllProgramari();
		ArrayList<Cos> cs = cos.getAllCos();
		
		for(Persoana d:lst) {
			if(d.getUser_id()==x.getIdUser()){
				r[0]=d.getId();
				r[1]=d.getNume();
				r[2]=d.getPrenume();
				r[3]=d.getEmail();
				r[4]=d.getData_nasterii();
				r[5]=d.getVarsta();
				r[6]=d.getAdresa();
				r[7]=d.getSex();
				model.addRow(r);
			}
		}
		int bonus=0;
		int ok_bonus;
		String serviciile=null;
		int rows=table.getRowCount()-1;
		while(rows>-1){
			int id_persoana=(Integer)table.getValueAt(rows, 0);
			r1[0]=id_persoana;
		for(Programare p:pr) {
			serviciile=null;
			bonus=bonus+p.getBonus_points();
			//x.setLoyal(bonus);
			if(id_persoana==p.getId_pers()) {
				bonus=bonus+p.getBonus_points();
				for(Cos c:cs) {
					if(c.getId_programare()==p.getId()) {
						serviciile=serviciile + ", " + servicii.findServiciiById(c.getId_servicii()).getDenumire();
					}
				}
				r1[1]=p.getData_programarii();
				r1[2]=serviciile;
				r1[3]=p.getBonus_points();
				r1[3]=p.getTotal();
				
				model1.addRow(r1);
			}
		}
		x.setLoyal(bonus);
		
		
		rows--;
		}
		JLabel lblMyP = new JLabel("My people:");
		lblMyP.setBounds(10, 5, 85, 14);
		frame.getContentPane().add(lblMyP);
		JButton btnUseBonus = new JButton("USE BONUS");
		btnUseBonus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programare_noua.setTotal(programare_noua.getTotal()-Double.valueOf(textField_2.getText()));
				textField_1.setText(String.valueOf((Double.valueOf(textField_1.getText())-Double.valueOf(textField_2.getText()))));
				x.setLoyal(0);
				for(Persoana dte:lst) {
					if(dte.getUser_id()==x.getIdUser()) {
						for(Programare prg:pr) {
							if(prg.getId_pers()==dte.getId())
								prg.setBonus_points(0);
						}
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               int row=table.getSelectedRow();
               programare_noua.setId_pers((int)table.getValueAt(row, 0));
               programare_noua.setId(programare.insertProgramare(programare_noua));

            }
		});
		table_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
		});
		

		
		
		JLabel lblServiceType = new JLabel("Salon field:");
		lblServiceType.setBounds(10, 118, 85, 14);
		frame.getContentPane().add(lblServiceType);
		
		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setBounds(10, 143, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewCar = new JButton("New person");
		btnNewCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perGUI.NewPersoanaGUI(x);
				frame.setVisible(false);
			}
		});
		btnNewCar.setBounds(932, 114, 110, 23);
		frame.getContentPane().add(btnNewCar);
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 298, 270, 113);
		frame.getContentPane().add(textPane);
		
		JLabel lblMyCart = new JLabel("My cart:");
		lblMyCart.setBounds(10, 281, 85, 14);
		frame.getContentPane().add(lblMyCart);
		textField = new JTextField();
		textField.setBounds(317, 115, 110, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(273, 118, 74, 14);
		frame.getContentPane().add(lblDate);
	
		Cos cosul = new Cos();
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row11=table_2.getSelectedRow();
				int id_serv=(int)table_2.getValueAt(row11,0);
				Servicii ser=new Servicii();
				ser=servicii.findServiciiById(id_serv);
				cosul.setId_servicii(id_serv);
				cosul.setId_programare(programare_noua.getId());
				cosul.setPret(ser.getPret());
				cos.insertCos(cosul);
				programare_noua.setTotal(programare_noua.getTotal()+cosul.getPret());
				programare_noua.setBonus_points(programare_noua.getBonus_points()+servicii.findServiciiById(id_serv).getBonus_value());
				System.out.println(servicii.findServiciiById(cosul.getId_servicii()).getBonus_value());
				textField_1.setText(String.valueOf(programare_noua.getTotal()));
				
			}
		});
		btnAdd_1.setBounds(292, 301, 137, 51);
		frame.getContentPane().add(btnAdd_1);
		
		
		Programare prog = new Programare();
		JButton btnAdd = new JButton("Reserve");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Cos> lista = cos.getAllCos();
				int bonus=0;
				programare_noua.setData_programarii(Date.valueOf(textField.getText()));
				programare.updateProgramare(programare_noua);
				Servicii sr;
				String str="";
				for(Cos c:lista) {
					if(c.getId_programare()==programare_noua.getId()) {
						sr=servicii.findServiciiById(c.getId_servicii());
						str=str+"\n"+sr.getDenumire() + " la pretul de: " + sr.getPret() + " lei si bonus: " + sr.getBonus_value();
					}
				}
				textPane.setText(str);
				
			}
		});
		btnAdd.setBounds(292, 360, 137, 51);
		frame.getContentPane().add(btnAdd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 420, 120, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(12, 423, 61, 14);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblRon = new JLabel("RON");
		lblRon.setBounds(182, 423, 46, 14);
		frame.getContentPane().add(lblRon);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.setRowCount(0);
				String selected = (String)comboBox.getSelectedItem();
					for(Servicii s:serv) {
						if(selected.equals(s.getCategorie())) {
							r2[0]=s.getId();
							r2[1]=s.getDenumire();
							r2[2]=s.getBonus_value();
							r2[3]=s.getPret();
							model2.addRow(r2);
						}
					}
			}
		});
		btnShow.setBounds(191, 114, 69, 23);
		frame.getContentPane().add(btnShow);
		
		
		
		JLabel lblAcumulatedBonus = new JLabel("Acumulated bonus:");
		lblAcumulatedBonus.setBounds(437, 118, 125, 14);
		frame.getContentPane().add(lblAcumulatedBonus);
		
		JLabel lblRon_1 = new JLabel("RON");
		lblRon_1.setBounds(624, 118, 46, 14);
		frame.getContentPane().add(lblRon_1);
		
		
		btnUseBonus.setBounds(659, 114, 104, 23);
		frame.getContentPane().add(btnUseBonus);
		
		JButton btnGetBonus = new JButton("GET BONUS");
		btnGetBonus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.removeAll();
				int rows=table.getRowCount()-1;
				int bonus=0;
				int id_persoana=(Integer)table.getValueAt(rows, 0);
				r1[0]=id_persoana;
			
			for(Persoana pers:lst) {
				if(pers.getUser_id()==x.getIdUser())
					for(Programare p:pr){
						if(p.getId_pers()==pers.getId())
							bonus=bonus+p.getBonus_points();
					}
				
				//x.setLoyal(bonus);
			
			}
			
			textField_2.setText(String.valueOf(bonus));
			}
		});
		btnGetBonus.setBounds(775, 113, 97, 25);
		frame.getContentPane().add(btnGetBonus);
		
		
		
		
	}
}
