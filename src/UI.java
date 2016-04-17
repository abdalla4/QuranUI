import java.awt.Dimension;
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
	private JSlider slider3A_V2_DegreeOfSim;
	private JSlider slider1B_V2_DegreeOfConnectedness;
	private JSlider sliderC_OrderSensitivity;
	private JButton btnRun;
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
            "PercentageOfMatchingWordsInSentence2"};
	
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
		super("Quran UI");
		
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
		
		btn1_WordBased = new JRadioButton("Word Based");
		btn1_WordBased.setBounds(98, 19, 109, 23);
		contentPane.add(btn1_WordBased);
		
		btn2_CharBased = new JRadioButton("Character Based");
		btn2_CharBased.setBounds(220, 19, 148, 23);
		contentPane.add(btn2_CharBased);
		
		slider1A_V1_DegreeOfSim = new JSlider();
		slider1A_V1_DegreeOfSim.setBounds(10, 98, 200, 23);
		slider1A_V1_DegreeOfSim.setMajorTickSpacing(20);
		slider1A_V1_DegreeOfSim.setMinorTickSpacing(10);
		slider1A_V1_DegreeOfSim.setPaintTicks(true);
		slider1A_V1_DegreeOfSim.addChangeListener(this);
		contentPane.add(slider1A_V1_DegreeOfSim);
		
		slider2A_V1_DegreeOfConnectedness = new JSlider();
		slider2A_V1_DegreeOfConnectedness.setBounds(10, 173, 200, 23);
		slider2A_V1_DegreeOfConnectedness.setMajorTickSpacing(20);
		slider2A_V1_DegreeOfConnectedness.setMinorTickSpacing(10);
		slider2A_V1_DegreeOfConnectedness.setPaintTicks(true);
		slider2A_V1_DegreeOfConnectedness.addChangeListener(this);
		contentPane.add(slider2A_V1_DegreeOfConnectedness);
		
		slider3A_V2_DegreeOfSim = new JSlider();
		slider3A_V2_DegreeOfSim.setBounds(114, 263, 200, 23);
		slider3A_V2_DegreeOfSim.setMajorTickSpacing(20);
		slider3A_V2_DegreeOfSim.setMinorTickSpacing(10);
		slider3A_V2_DegreeOfSim.setPaintTicks(true);
		slider3A_V2_DegreeOfSim.addChangeListener(this);
		contentPane.add(slider3A_V2_DegreeOfSim);
		
		slider1B_V2_DegreeOfConnectedness = new JSlider();
		slider1B_V2_DegreeOfConnectedness.setBounds(220, 98, 200, 23);
		slider1B_V2_DegreeOfConnectedness.setMajorTickSpacing(20);
		slider1B_V2_DegreeOfConnectedness.setMinorTickSpacing(10);
		slider1B_V2_DegreeOfConnectedness.setPaintTicks(true);
		slider1B_V2_DegreeOfConnectedness.addChangeListener(this);
		contentPane.add(slider1B_V2_DegreeOfConnectedness);
		
		sliderC_OrderSensitivity = new JSlider();
		sliderC_OrderSensitivity.setBounds(220, 173, 200, 23);
		sliderC_OrderSensitivity.setMajorTickSpacing(20);
		sliderC_OrderSensitivity.setMinorTickSpacing(10);
		sliderC_OrderSensitivity.setPaintTicks(true);
		sliderC_OrderSensitivity.addChangeListener(this);
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
					}
					pnlContent.removeAll();
					Word_table = new JTable(Word_data, Word_columnNames);
					//Word_table.getModel().addTableModelListener(this);
					Word_scrollPane = new JScrollPane(Word_table);
					pnlContent.add(Word_scrollPane);
					pnlContent.revalidate();
					//this.repaint();
				} 
				
				if(btn2_CharBased.isSelected() && slider1A_V1_DegreeOfSim.getValue() == 100){
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
				
				if (btn1_WordBased.isSelected() && btn2_CharBased.isSelected()){
					String error_message1 = "Please select one button at a time.";
					JOptionPane.showMessageDialog(new JFrame(), error_message1, "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				
				if (!btn1_WordBased.isSelected() && !btn2_CharBased.isSelected()){
					String error_message2 = "Please select a button.";
					JOptionPane.showMessageDialog(new JFrame(), error_message2, "Error Message", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		btnRun.setBounds(171, 331, 89, 23);
		contentPane.add(btnRun);
		
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
		
		pnlContent = new JPanel();
		pnlContent.setBounds(456, 19, 525, 596);
		Word_table = new JTable(Word_data, Word_columnNames);
		Word_scrollPane = new JScrollPane(Word_table);
		pnlContent.add(Word_scrollPane);
		Word_table.getModel().addTableModelListener(this);
		contentPane.add(pnlContent);
	}
	
	public static void main(String[] args) {
		UI GUI = new UI();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setMinimumSize(new Dimension(1000, 500));
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