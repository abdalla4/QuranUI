import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class UI extends JFrame implements ActionListener, TableModelListener, ChangeListener {
	private static final long serialVersionUID = 1779520078061383929L;
	private JRadioButton btn1_WordBased;
	private JRadioButton btn2_CharBased;
	private JSlider slider1A_V1_DegreeOfSim;
	private JSlider slider2A_V1_DegreeOfConnectedness;
	private JSlider slider1B_V2_DegreeOfSim;
	private JSlider slider2B_V2_DegreeOfConnectedness;
	private JSlider sliderC_OrderSensitivity;
	private JButton btnRun;
	private JButton help_button;
	private JLabel verse1_DegreeOfSim;
	private JLabel verse2_DegreeOfSim;
	private JLabel DOC_Verse1;
	private JLabel DOC_Verse2;
	private JLabel OrderS_Verse1;
	
	private JPanel contentPane;
	private JPanel pnlContent;
	
	private QuranSQLserverDB db;
	
	private List<QuranWordSim> list_WordSim;
	private List<QuranCharSim> list_CharSim;
	
	private String[] Word_columnNames = {"SurahName1",
            "Ayah1",
            "SurahName2",
            "Ayah2",
            "NoOfMatchingWords",
            "PercentageOfMatchingWordsInSentence1",
            "PercentageOfMatchingWordsInSentence2",
            "Connectedness1",
            "Connectedness2",
            "DegreeOfOrderness"};
	
	private Object[][] Word_data;
	private JTable Word_table;
	private JScrollPane Word_scrollPane;
	
	private String[] Char_columnNames = {"SurahName1",
            "Ayah1",
            "SurahName2",
            "Ayah2",
            "NoOfMatchingChars",
            "PercentageOfMatchingCharsInSentence1",
            "PercentageOfMatchingCharsInSentence2"};
	
	private Object[][] Char_data;
	private JTable Char_table;
	private JScrollPane Char_scrollPane;
	
	public UI() {
		super("Quran Interface For Detecting Verse Similarity (V1)");
		
		db = new QuranSQLserverDB();
		
		try {
			list_WordSim = db.getQuery();
			Word_data = new Object[list_WordSim.size()][Word_columnNames.length];
			for (int i=0; i<list_WordSim.size(); i++) {
				Word_data[i][0] = list_WordSim.get(i).getSurahName1();
				Word_data[i][1] = list_WordSim.get(i).getAyah1();
				Word_data[i][2] = list_WordSim.get(i).getSurahName2();
				Word_data[i][3] = list_WordSim.get(i).getAyah2();
				Word_data[i][4] = list_WordSim.get(i).getNoOfMatchingWords();
				Word_data[i][5] = list_WordSim.get(i).getPercentageOfMatchingWordsInSentence1();
				Word_data[i][6] = list_WordSim.get(i).getPercentageOfMatchingWordsInSentence2();
				Word_data[i][7] = list_WordSim.get(i).getConnectedness1();
				Word_data[i][8] = list_WordSim.get(i).getConnectedness2();
				Word_data[i][9] = list_WordSim.get(i).getDegreeOfOrderness();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			list_CharSim = db.getQuery2();
			Char_data = new Object[list_CharSim.size()][Char_columnNames.length];
			for (int i=0; i<list_CharSim.size(); i++) {
				Char_data[i][0] = list_CharSim.get(i).getSurahName1();
				Char_data[i][1] = list_CharSim.get(i).getAyah1();
				Char_data[i][2] = list_CharSim.get(i).getSurahName2();
				Char_data[i][3] = list_CharSim.get(i).getAyah2();
				Char_data[i][4] = list_CharSim.get(i).getNoOfMatchingChars();
				Char_data[i][5] = list_CharSim.get(i).getPercentageOfMatchingCharsInSentence1();
				Char_data[i][6] = list_CharSim.get(i).getPercentageOfMatchingCharsInSentence2();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createComponents();
		setVisible(true);
	}
	
	private void createComponents() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlContent = new JPanel();
		pnlContent.setBounds(456, 19, 525, 596);
		Word_table = new JTable(Word_data, Word_columnNames);
		Word_scrollPane = new JScrollPane(Word_table);
		pnlContent.add(Word_scrollPane);
		Word_table.getModel().addTableModelListener(this);
				
		pnlContent = new JPanel();
		pnlContent.setBounds(456, 19, 525, 596);
		Char_table = new JTable(Char_data, Char_columnNames);
		Char_scrollPane = new JScrollPane(Char_table);
		pnlContent.add(Char_scrollPane);
		Char_table.getModel().addTableModelListener(this);
		
		contentPane.add(pnlContent);
				
		ButtonGroup buttonGroup = new ButtonGroup();
		
		btn1_WordBased = new JRadioButton("Word Based");
		btn1_WordBased.setBounds(98, 19, 109, 23);
		contentPane.add(btn1_WordBased);
		buttonGroup.add(btn1_WordBased);
		
		btn2_CharBased = new JRadioButton("Character Based");
		btn2_CharBased.setBounds(220, 19, 148, 23);
		contentPane.add(btn2_CharBased);
		buttonGroup.add(btn2_CharBased);
		
		slider1A_V1_DegreeOfSim = new JSlider();
		slider1A_V1_DegreeOfSim.setBounds(10, 98, 200, 23);
		slider1A_V1_DegreeOfSim.setMajorTickSpacing(20);
		slider1A_V1_DegreeOfSim.setMinorTickSpacing(10);
		slider1A_V1_DegreeOfSim.setPaintTicks(true);
		slider1A_V1_DegreeOfSim.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source1 = (JSlider) e.getSource();
				db.setV1_Sim_Word_a(source1.getValue());
				db.setV1_Char_Word_a(source1.getValue());
			}
		});
		contentPane.add(slider1A_V1_DegreeOfSim);
		
		slider2A_V1_DegreeOfConnectedness = new JSlider();
		slider2A_V1_DegreeOfConnectedness.setBounds(10, 173, 200, 23);
		slider2A_V1_DegreeOfConnectedness.setMajorTickSpacing(20);
		slider2A_V1_DegreeOfConnectedness.setMinorTickSpacing(10);
		slider2A_V1_DegreeOfConnectedness.setPaintTicks(true);
		slider2A_V1_DegreeOfConnectedness.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source2 = (JSlider) e.getSource();
				db.setDOC_Sim_Word_a(source2.getValue());
			}
		});
		contentPane.add(slider2A_V1_DegreeOfConnectedness);
		
		slider1B_V2_DegreeOfSim = new JSlider();
		slider1B_V2_DegreeOfSim.setBounds(220, 98, 200, 23);
		slider1B_V2_DegreeOfSim.setMajorTickSpacing(20);
		slider1B_V2_DegreeOfSim.setMinorTickSpacing(10);
		slider1B_V2_DegreeOfSim.setPaintTicks(true);
		slider1B_V2_DegreeOfSim.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source3 = (JSlider) e.getSource();
				db.setV2_Sim_Word_b(source3.getValue());
				db.setV2_Char_Word_b(source3.getValue());
			}
		});
		contentPane.add(slider1B_V2_DegreeOfSim);
		
		slider2B_V2_DegreeOfConnectedness = new JSlider();
		slider2B_V2_DegreeOfConnectedness.setBounds(220, 173, 200, 23);
		slider2B_V2_DegreeOfConnectedness.setMajorTickSpacing(20);
		slider2B_V2_DegreeOfConnectedness.setMinorTickSpacing(10);
		slider2B_V2_DegreeOfConnectedness.setPaintTicks(true);
		slider2B_V2_DegreeOfConnectedness.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source4 = (JSlider) e.getSource();
				db.setDOC_Sim_Word_b(source4.getValue());
			}
		});
		contentPane.add(slider2B_V2_DegreeOfConnectedness);
		
		sliderC_OrderSensitivity = new JSlider();
		sliderC_OrderSensitivity.setBounds(114, 263, 200, 23);
		sliderC_OrderSensitivity.setMajorTickSpacing(20);
		sliderC_OrderSensitivity.setMinorTickSpacing(10);
		sliderC_OrderSensitivity.setPaintTicks(true);
		sliderC_OrderSensitivity.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source5 = (JSlider) e.getSource();
				db.setDeg_Of_Orderness_Word(source5.getValue());
			}
		});
		contentPane.add(sliderC_OrderSensitivity);
		
		btnRun = new JButton("RUN");
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btn1_WordBased.isSelected()) {
					try {
						list_WordSim = db.getQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Word_data = new Object[list_WordSim.size()][Word_columnNames.length];
					for (int i=0; i<list_WordSim.size(); i++) {
						Word_data[i][0] = list_WordSim.get(i).getSurahName1();
						Word_data[i][1] = list_WordSim.get(i).getAyah1();
						Word_data[i][2] = list_WordSim.get(i).getSurahName2();
						Word_data[i][3] = list_WordSim.get(i).getAyah2();
						Word_data[i][4] = list_WordSim.get(i).getNoOfMatchingWords();
						Word_data[i][5] = list_WordSim.get(i).getPercentageOfMatchingWordsInSentence1();
						Word_data[i][6] = list_WordSim.get(i).getPercentageOfMatchingWordsInSentence2();
						Word_data[i][7] = list_WordSim.get(i).getConnectedness1();
						Word_data[i][8] = list_WordSim.get(i).getConnectedness2();
						Word_data[i][9] = list_WordSim.get(i).getDegreeOfOrderness();
					}
					pnlContent.removeAll();
					Word_table = new JTable(Word_data, Word_columnNames);
					//Word_table.setPreferredSize(new Dimension(500, 500));
					//Word_table.getModel().addTableModelListener(this);
					Word_scrollPane = new JScrollPane(Word_table);
					pnlContent.add(Word_scrollPane);
					pnlContent.revalidate();
					//this.repaint();
				} 
				
				if(btn2_CharBased.isSelected()){
					try {
						list_CharSim = db.getQuery2();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Char_data = new Object[list_CharSim.size()][Char_columnNames.length];
					for (int i=0; i<list_CharSim.size(); i++) {
						Char_data[i][0] = list_CharSim.get(i).getSurahName1();
						Char_data[i][1] = list_CharSim.get(i).getAyah1();
						Char_data[i][2] = list_CharSim.get(i).getSurahName2();
						Char_data[i][3] = list_CharSim.get(i).getAyah2();
						Char_data[i][4] = list_CharSim.get(i).getNoOfMatchingChars();
						Char_data[i][5] = list_CharSim.get(i).getPercentageOfMatchingCharsInSentence1();
						Char_data[i][6] = list_CharSim.get(i).getPercentageOfMatchingCharsInSentence2();
					}
					pnlContent.removeAll();
					Char_table = new JTable(Char_data, Char_columnNames);
					//Char_table.getModel().addTableModelListener(this);
					Char_scrollPane = new JScrollPane(Char_table);
					pnlContent.add(Char_scrollPane);
					pnlContent.revalidate();
					//this.repaint();
				}
				
				if(btnRun.isEnabled()){
					JOptionPane.showMessageDialog(null, "Execution Done");
				}
			}
		});
		btnRun.setBounds(171, 331, 89, 23);
		contentPane.add(btnRun);
		
		help_button = new JButton("User`s Guidlines");
		help_button.setFont(new Font("Serif", Font.BOLD, 12));
		help_button.setForeground(Color.BLUE);
		help_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, 
				  "In the name of God, the Most Gracious, the Most Merciful (بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ) \n"
				+ "This interface was created for the purpose of helping those who memorize \n"
				+ "the holy Quran realize the similarity between verses.\n"
				+ "In the Quran, \n"
				+ "there are words and characters that are repeated more than once \n"
				+ "depending on the situation being discussed.\n"
				+ "Allah (SWT) did this to remind us of what we should and should not do.\n"
				+ "Using this interface will help you study these similarities.\n"
				+ "Thus for those who memorize the Quran, \n"
				+ "this application will guide them recognize \n"
				+ "the reason why Allah (SWT) chose these \n"
				+ "words or these characters to be indicated in this particular verse as they memorize.\n"
				+ "As you memorize, \n"
				+ "this application will help you notice the similarity so that \n"
				+ "you can avoid mistakes when it is time for reciting.\n"
				+ "There are five sliders provided on the left side of the interface \n"
				+ "to help you view certian verses based on similarity conditions.\n"
				+ "Similarity will be determined through \n"
				+ "the degree of similarity on each verse, \n"
				+ "the degree of connectedness on each verse,\n"
				+ "and the order sensitivity in general.\n"
				+ "To use this interface, \n"
				+ "choose between word based similrity and character based similrity \n"
				+ "using the two radio buttons on the top, \n"
				+ "then place numbers on the sliders provided on the left side, \n"
				+ "then click the 'RUN' button to get the results \n"
				+ "on the table provided on the right side.\n"
				+ "This table will include similiar verses depending on the numbers you placed on the sliders. \n"
				+ "This application is camparing each verse of the Quran \n"
				+ "to every other verse to detect the similrity between \n"
				+ "that certain verse and the rest of the verses. \n"
				+ "May Allah (SWT) guide us to memorize and understand the Holy Quran.\n"
				+ "Peace be upon you (السلام عليكم‎)");
			}
		});
		help_button.setBounds(120, 420, 200, 23);
		contentPane.add(help_button);
		
		verse1_DegreeOfSim = new JLabel("V1 Degree of Similarity");
		verse1_DegreeOfSim.setBounds(60, 73, 148, 14);
		contentPane.add(verse1_DegreeOfSim);
		
		verse2_DegreeOfSim = new JLabel("V2 Degree of Similarity");
		verse2_DegreeOfSim.setBounds(264, 73, 140, 14);
		contentPane.add(verse2_DegreeOfSim);
		
		DOC_Verse1 = new JLabel("Degree Of Connectedness");
		DOC_Verse1.setBounds(46, 148, 162, 14);
		contentPane.add(DOC_Verse1);
		
		DOC_Verse2 = new JLabel("Degree Of Connectedness");
		DOC_Verse2.setBounds(250, 148, 156, 14);
		contentPane.add(DOC_Verse2);
		
		OrderS_Verse1 = new JLabel("Order Sensitivity");
		OrderS_Verse1.setBounds(171, 238, 109, 14);
		contentPane.add(OrderS_Verse1);
		
//		pnlContent = new JPanel();
//		pnlContent.setBounds(456, 19, 525, 596);
//		Word_table = new JTable(Word_data, Word_columnNames);
//		Word_scrollPane = new JScrollPane(Word_table);
//		pnlContent.add(Word_scrollPane);
//		Word_table.getModel().addTableModelListener(this);
//		contentPane.add(pnlContent);
	}
	
	public static void main(String[] args) {
		UI GUI = new UI();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setMinimumSize(new Dimension(1000, 500));
		ImageIcon img = new ImageIcon("QURAN_IMAGE.png");
		GUI.setIconImage(img.getImage());
		GUI.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	} 
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel)e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		db.update(row, columnName, data);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	}
}