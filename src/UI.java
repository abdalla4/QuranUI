import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
//import javax.swing.table.TableModel;

public class UI extends JFrame implements ActionListener, TableModelListener, ChangeListener {
	private static final long serialVersionUID = 1779520078061383929L;
	private JRadioButton btn1;
	private JRadioButton btn2;
	private JSlider slider1A;
	private JSlider slider2A;
	private JSlider slider3A;
	private JSlider slider1B;
	private JSlider slider2B;
	private JSlider slider3B;
	private JButton btnRun;
	private JLabel verse1;
	private JLabel verse2;
	private JLabel DOC_Verse1;
	private JLabel DOC_Verse2;
	private JLabel OrderS_Verse1;
	private JLabel OrderS_Verse2;
	
	private JPanel contentPane;
	//private JPanel pnlContent;
	
	public UI() {
		super("Quran UI");
		createComponents();
		setVisible(true);
	}
	
	private void createComponents() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		setBounds(500, 500, 450, 300);
//		pnlContent = new JPanel();
//		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(pnlContent);
//		pnlContent.setLayout(null);
		
		btn1 = new JRadioButton("Word Based");
		btn1.setBounds(98, 19, 109, 23);
		contentPane.add(btn1);
		
		btn2 = new JRadioButton("Character Based");
		btn2.setBounds(220, 19, 109, 23);
		contentPane.add(btn2);
		
		slider1A = new JSlider();
		slider1A.setBounds(10, 82, 200, 23);
		slider1A.setMajorTickSpacing(20);
		slider1A.setMinorTickSpacing(10);
		slider1A.setPaintTicks(true);
		slider1A.addChangeListener(this);
		contentPane.add(slider1A);
		
		slider2A = new JSlider();
		slider2A.setBounds(10, 135, 200, 23);
		slider2A.setMajorTickSpacing(20);
		slider2A.setMinorTickSpacing(10);
		slider2A.setPaintTicks(true);
		slider2A.addChangeListener(this);
		contentPane.add(slider2A);
		
		slider3A = new JSlider();
		slider3A.setBounds(10, 186, 200, 23);
		slider3A.setMajorTickSpacing(20);
		slider3A.setMinorTickSpacing(10);
		slider3A.setPaintTicks(true);
		slider3A.addChangeListener(this);
		contentPane.add(slider3A);
		
		slider1B = new JSlider();
		slider1B.setBounds(220, 82, 200, 23);
		slider1B.setMajorTickSpacing(20);
		slider1B.setMinorTickSpacing(10);
		slider1B.setPaintTicks(true);
		slider1B.addChangeListener(this);
		contentPane.add(slider1B);
		
		slider2B = new JSlider();
		slider2B.setBounds(220, 135, 200, 23);
		slider2B.setMajorTickSpacing(20);
		slider2B.setMinorTickSpacing(10);
		slider2B.setPaintTicks(true);
		slider2B.addChangeListener(this);
		contentPane.add(slider2B);
		
		slider3B = new JSlider();
		slider3B.setBounds(220, 186, 200, 23);
		slider3B.setMajorTickSpacing(20);
		slider3B.setMinorTickSpacing(10);
		slider3B.setPaintTicks(true);
		slider3B.addChangeListener(this);
		contentPane.add(slider3B);
		
		btnRun = new JButton("RUN");
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = slider1A.getValue();
				int b = slider2A.getValue();
				int c = slider3A.getValue();
				int d = slider1B.getValue();
				int g = slider2B.getValue();
				int h = slider3B.getValue();
				if (btn1.isSelected() && a == b){
					System.out.println("good");
				}
			}
		});
		btnRun.setBounds(169, 228, 89, 23);
		contentPane.add(btnRun);
		
		verse1 = new JLabel("Verse 1");
		verse1.setBounds(93, 57, 46, 14);
		contentPane.add(verse1);
		
		verse2 = new JLabel("Verse 2");
		verse2.setBounds(302, 57, 46, 14);
		contentPane.add(verse2);
		
		DOC_Verse1 = new JLabel("Degree Of Conn");
		DOC_Verse1.setBounds(67, 116, 89, 14);
		contentPane.add(DOC_Verse1);
		
		DOC_Verse2 = new JLabel("Degree Of Conn");
		DOC_Verse2.setBounds(281, 116, 89, 14);
		contentPane.add(DOC_Verse2);
		
		OrderS_Verse1 = new JLabel("Order Sensitivity");
		OrderS_Verse1.setBounds(67, 169, 89, 14);
		contentPane.add(OrderS_Verse1);
		
		OrderS_Verse2 = new JLabel("Order Sensitivity");
		OrderS_Verse2.setBounds(281, 169, 89, 14);
		contentPane.add(OrderS_Verse2);
	}
	
	public static void main(String[] args) {
		UI GUI = new UI();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setMinimumSize(new Dimension(320, 320));
		GUI.setVisible(true);
		//GUI.setBounds(0,0,800,320);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == slider1A ||
			e.getSource() == slider2A ||
			e.getSource() == slider3A ||
			e.getSource() == slider1B ||
			e.getSource() == slider2B ||
			e.getSource() == slider3B){
			
			int a = slider1A.getValue();
			int b = slider2A.getValue();
			int c = slider3A.getValue();
			int d = slider1B.getValue();
			int g = slider2B.getValue();
			int h = slider3B.getValue();
		}
	}
}




























//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
////import java.sql.SQLException;
////import java.util.List;
//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
////import javax.swing.table.TableModel;
//
//public class UI extends JFrame implements ActionListener, TableModelListener, ChangeListener {
//	private static final long serialVersionUID = 1779520078061383929L;
//	private JRadioButton btn1;
//	private JRadioButton btn2;
//	private JButton btn3;
//	private JSlider slider1;
//	private JSlider slider2;
//	private JSlider slider3;
//	private JSlider slider4;
////	private JLabel label1;
////	private JLabel label2;
////	private JLabel label3;
////	private JLabel label4;
//	
//	private JPanel pnlButtons;
//	private JPanel pnlSliders;
//	private JPanel pnlContent;;
//	
//	public UI() {
//		super("Quran UI");
//		createComponents();
//		setVisible(true);
//		setSize(320, 320);
//	}
//	
//	private void createComponents() {
//		pnlButtons = new JPanel();
//		pnlSliders = new JPanel();
//		
//		btn1 = new JRadioButton("Word Based");
//		btn2 = new JRadioButton("Character Based");
//		btn3 = new JButton("RUN");
//		slider1 = new JSlider(0, 100, 0);
//		slider2 = new JSlider(0, 100, 0);
//		slider3 = new JSlider(0, 100, 0);
//		slider4 = new JSlider(0, 100, 0);
////		label1 = new JLabel("Verse no. 1");
////		label2 = new JLabel("Verse no. 2");
////		label3 = new JLabel("Order Sensitivity (O. Ins -> O. Sen)");
////		label4 = new JLabel("Degree of Connection");
//		
//		btn1.addActionListener(this);
//		btn2.addActionListener(this);
//		btn3.addActionListener(this);
//		
//		slider1.setBounds(20, 35, 290, 40);
//		slider1.setMajorTickSpacing(20);
//		slider1.setMinorTickSpacing(10);
//		slider1.setPaintTicks(true);
//		slider1.setPaintLabels(true);
//		slider1.addChangeListener(this);
//		
//		slider2.setBounds(20, 35, 290, 40);
//		slider2.setMajorTickSpacing(20);
//		slider2.setMinorTickSpacing(10);
//		slider2.setPaintTicks(true);
//		slider2.setPaintLabels(true);
//		slider2.addChangeListener(this);
//		
//		slider3.setBounds(20, 35, 290, 40);
//		slider3.setMajorTickSpacing(20);
//		slider3.setMinorTickSpacing(10);
//		slider3.setPaintTicks(true);
//		slider3.setPaintLabels(true);
//		slider3.addChangeListener(this);
//		
//		slider4.setBounds(20, 35, 290, 40);
//		slider4.setMajorTickSpacing(20);
//		slider4.setMinorTickSpacing(10);
//		slider4.setPaintTicks(true);
//		slider4.setPaintLabels(true);
//		slider4.addChangeListener(this);
//		
//		pnlButtons.add(btn1);
//		pnlButtons.add(btn2);
//		pnlButtons.add(btn3);
//		pnlSliders.add(slider1);
//		pnlSliders.add(slider2);
//		pnlSliders.add(slider3);
//		pnlSliders.add(slider4);
//		
////		label1.setBounds( 20, 15, 250, 20 );
////		label2.setBounds( 20, 15, 250, 20 );
////		label2.setBounds( 20, 15, 250, 20 );
////		label3.setBounds( 20, 15, 250, 20 );
////		
////		pnlSliders.add(label1);
////		pnlSliders.add(label2);
////		pnlSliders.add(label3);
////		pnlSliders.add(label4);
//
//		add(pnlButtons, BorderLayout.NORTH);
//		add(pnlSliders, BorderLayout.SOUTH);
//		
//		//Quran Panel
//		pnlContent = new JPanel();
//		add(pnlContent, BorderLayout.CENTER);
//	}
//	
//	public static void main(String[] args) {
//		UI GUI = new UI();
//		GUI.pack();
//		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		GUI.setMinimumSize(new Dimension(320, 320));
//		GUI.setVisible(true);
//	}
//	
//
//	@Override
//	public void tableChanged(TableModelEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource() == btn1 && slider1.getValue() == 100) {
//			
//			
//		}
//		
//	}
//
//	@Override
//	public void stateChanged(ChangeEvent arg0) {
//		// TODO Auto-generated method stub
//		if(arg0.getSource() == slider1){
//			//int x = slider1.getValue();
//		}
//	}
//}





























//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.swing.*;
//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
//import javax.swing.table.TableModel;
//
//public class UI extends JFrame implements ActionListener, TableModelListener {
//	private static final long serialVersionUID = 1779520078061383929L;
//	private JButton btnList;
//	private JPanel pnlButtons, pnlContent;
//	private QuranSQLserverDB db;
//	private List<QuranClass> list;
//	
//	private String[] columnNames = {"AyahSerialNo",
//            "Ayah",
//            "SurahSerialNo",
//            "RubSerialNo",
//            "AyahNoWithSurah",
//            "AyahNoWithinRub"};
//	
//	private Object[][] data;
//	private JTable table;
//	private JScrollPane scrollPane;
//	
//	public UI() {
//		super("Quran UI");
//		
//		db = new QuranSQLserverDB();
//		
//		try {
//			list = db.getQuery();
//			data = new Object[list.size()][columnNames.length];
//			for (int i=0; i<list.size(); i++) {
//				data[i][0] = list.get(i).getAyahSerialNo();
//				data[i][1] = list.get(i).getAyah();
//				data[i][2] = list.get(i).getSurahSerialNo();
//				data[i][3] = list.get(i).getRubSerialNo();
//				data[i][4] = list.get(i).getAyahNoWithSurah();
//				data[i][5] = list.get(i).getAyahNoWithinRub();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		createComponents();
//		setVisible(true);
//		setSize(500, 500);
//	}
//	
//	private void createComponents() {
//		pnlButtons = new JPanel();
//		
//		btnList = new JButton("The Holy Quran");
//		btnList.addActionListener(this);
//		
//		pnlButtons.add(btnList);
//
//		add(pnlButtons, BorderLayout.NORTH);
//		
//		//Quran Panel
//		pnlContent = new JPanel();
//		table = new JTable(data, columnNames);
//		scrollPane = new JScrollPane(table);
//		pnlContent.add(scrollPane);
//		table.getModel().addTableModelListener(this);
//		
//		add(pnlContent, BorderLayout.CENTER);
//	}
//
//	public static void main(String[] args) {
//		UI GUI = new UI();
//		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		GUI.setMinimumSize(new Dimension(500, 500));
//		GUI.setVisible(true);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == btnList) {
//			try {
//				list = db.getQuery();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			data = new Object[list.size()][columnNames.length];
//			for (int i=0; i<list.size(); i++) {
//				data[i][0] = list.get(i).getAyahSerialNo();
//				data[i][1] = list.get(i).getAyah();
//				data[i][2] = list.get(i).getSurahSerialNo();
//				data[i][3] = list.get(i).getRubSerialNo();
//				data[i][4] = list.get(i).getAyahNoWithSurah();
//				data[i][5] = list.get(i).getAyahNoWithinRub();
//			}
//			pnlContent.removeAll();
//			table = new JTable(data, columnNames);
//			table.getModel().addTableModelListener(this);
//			scrollPane = new JScrollPane(table);
//			pnlContent.add(scrollPane);
//			pnlContent.revalidate();
//			this.repaint();
//			
//		}
//	} 
//	
//	@Override
//	public void tableChanged(TableModelEvent e) {
//		int row = e.getFirstRow();
//        int column = e.getColumn();
//        TableModel model = (TableModel)e.getSource();
//        String columnName = model.getColumnName(column);
//        Object data = model.getValueAt(row, column);
//        db.update(row, columnName, data);
//	}
//}


























