import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuranSQLserverDB {
	private static Connection conn;
	private List<QuranWordSim> list_WordSim;
	private List<QuranCharSim> list_CharSim;
	
	private int V1_Sim_Word_a = 60;
	private int V2_Sim_Word_b = 70;
	private int DOC_Sim_Word_a = 90;
	private int DOC_Sim_Word_b = 90;
	private int Deg_Of_Orderness_Word = 90;
	
	private int V1_Char_Word_a = 90;
	private int V2_Char_Word_b = 70;
	
	public void setV1_Sim_Word_a(int x1) {
		this.V1_Sim_Word_a = x1;
	}

	public void setV2_Sim_Word_b(int x2) {
		this.V2_Sim_Word_b = x2;
	}
	
	public void setDOC_Sim_Word_a(int x3){
		this.DOC_Sim_Word_a = x3;
	}
	
	public void setDOC_Sim_Word_b(int x4){
		this.DOC_Sim_Word_b = x4;
	}
	
	public void setDeg_Of_Orderness_Word(int x5){
		this.Deg_Of_Orderness_Word = x5;
	}
	
	public void setV1_Char_Word_a(int y1) {
		this.V1_Char_Word_a = y1;
	}

	public void setV2_Char_Word_b(int y2) {
		this.V2_Char_Word_b = y2;
	}
	
	public static void createConnection() throws SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
		         "databaseName=QuranV22;user=sa;password=ccc";
		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("Connected to database");
	}
	
	public List<QuranWordSim> getQuery() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		
		Statement stmt = null;
		String query = "select s1.SurahName AS SurahName1, a1.Ayah AS Ayah1, "
					 + "s2.SurahName AS SurahName2, a2.Ayah AS Ayah2, sim.NoOfMatchingWords, "
				     + "sim.PercentageOfMatchingWordsInSentence1, "
				     + "sim.PercentageOfMatchingWordsInSentence2, "
				     + "sim.Connectedness1, "
				     + "sim.Connectedness2, "
				     + "sim.DegreeOfOrderness "
					 + "from dbo.[N1-Ayahs] a1, dbo.[N1-Ayahs] a2, "
					 + "dbo.[N1-Surahs] s1,  dbo.[N1-Surahs] s2, "
					 + "[dbo].[Similarity_Wordbased_NoOrder_NoRepetetion] sim "
					 + "where sim.AyahSerialNo1 = a1.AyahSerialNo "
					 + "and sim.AyahSerialNo2 = a2.AyahSerialNo "
					 + "and a1.SurahSerialNo = s1.SurahSerialNo "
					 + "and a2.SurahSerialNo = s2.SurahSerialNo "
					 + "and sim.PercentageOfMatchingWordsInSentence1 >" + V1_Sim_Word_a 
					 + "and sim.PercentageOfMatchingWordsInSentence2 >" + V2_Sim_Word_b
					 + "and sim.Connectedness1 >" + DOC_Sim_Word_a
					 + "and sim.Connectedness2 >" + DOC_Sim_Word_b
					 + "and sim.DegreeOfOrderness >" + Deg_Of_Orderness_Word;
	
		list_WordSim = new ArrayList<QuranWordSim>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String SurahName1 = rs.getString("SurahName1");
				String Ayah1 = rs.getString("Ayah1");
				String SurahName2 = rs.getString("SurahName2");
				String Ayah2 = rs.getString("Ayah2");
				int NoOfMatchingWords = rs.getInt("NoOfMatchingWords");
				int PercentageOfMatchingWordsInSentence1 = rs.getInt("PercentageOfMatchingWordsInSentence1");
				int PercentageOfMatchingWordsInSentence2 = rs.getInt("PercentageOfMatchingWordsInSentence2");
				int Connectedness1 = rs.getInt("Connectedness1");
				int Connectedness2 = rs.getInt("Connectedness2");
				int DegreeOfOrderness = rs.getInt("DegreeOfOrderness");
				
				QuranWordSim x = new QuranWordSim(SurahName1, Ayah1, SurahName2, Ayah2, 
												  NoOfMatchingWords, 
												  PercentageOfMatchingWordsInSentence1, 
												  PercentageOfMatchingWordsInSentence2,
												  Connectedness1,
												  Connectedness2,
												  DegreeOfOrderness);
				list_WordSim.add(x);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list_WordSim;
	}
	
	public List<QuranCharSim> getQuery2() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		
		Statement stmt2 = null;
		String query2 = "select s1.SurahName AS SurahName1, a1.Ayah AS Ayah1, "
					  + "s2.SurahName AS SurahName2, a2.Ayah AS Ayah2, sim.NoOfMatchingChars, "
					  + "sim.PercentageOfMatchingCharsInSentence1, "
					  + "sim.PercentageOfMatchingCharsInSentence2 "
					  + "from dbo.[N1-Ayahs] a1, dbo.[N1-Ayahs] a2, "
					  + "dbo.[N1-Surahs] s1,  dbo.[N1-Surahs] s2, "
					  + "[dbo].[Similarity_Charbased_NoOrder_NoRepetetion] sim "
					  + "where sim.AyahSerialNo1 = a1.AyahSerialNo "
					  + "and sim.AyahSerialNo2 = a2.AyahSerialNo "
					  + "and a1.SurahSerialNo = s1.SurahSerialNo "
					  + "and a2.SurahSerialNo = s2.SurahSerialNo "
					  + "and sim.PercentageOfMatchingCharsInSentence1 >" + V1_Char_Word_a
					  + "and sim.PercentageOfMatchingCharsInSentence2 >" + V2_Char_Word_b;
		
		list_CharSim = new ArrayList<QuranCharSim>();
		try {
			stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery(query2);
			while (rs.next()) {
				String SurahName1 = rs.getString("SurahName1");
				String Ayah1 = rs.getString("Ayah1");
				String SurahName2 = rs.getString("SurahName2");
				String Ayah2 = rs.getString("Ayah2");
				int NoOfMatchingChars = rs.getInt("NoOfMatchingChars");
				int PercentageOfMatchingCharsInSentence1 = rs.getInt("PercentageOfMatchingCharsInSentence1");
				int PercentageOfMatchingCharsInSentence2 = rs.getInt("PercentageOfMatchingCharsInSentence2");
				
				QuranCharSim y = new QuranCharSim(SurahName1, Ayah1, SurahName2, Ayah2, 
												  NoOfMatchingChars, 
												  PercentageOfMatchingCharsInSentence1, 
												  PercentageOfMatchingCharsInSentence2);
				list_CharSim.add(y);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt2 != null) {
				stmt2.close();
			}
		}
		return list_CharSim;
	}
	
	public List<QuranWordSim> getQuran(String qur) {
		List<QuranWordSim> filterList = new ArrayList<QuranWordSim>();
		try {
			list_WordSim = getQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (QuranWordSim h : list_WordSim) {
			if (h.getAyah1().toLowerCase().contains(qur.toLowerCase())) {
				filterList.add(h);
			}
		}
		return filterList;
	}
	
	public List<QuranCharSim> getQuran_forChar(String qur2) {
		List<QuranCharSim> filterList2 = new ArrayList<QuranCharSim>();
		try {
			list_CharSim = getQuery2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (QuranCharSim z : list_CharSim) {
			if (z.getAyah1().toLowerCase().contains(qur2.toLowerCase())) {
				filterList2.add(z);
			}
		}
		return filterList2;
	}
	
	public void update(int row, String columnName, Object data) {
		QuranWordSim qu = list_WordSim.get(row);
		//String pName = product.getProductName();
		int Word_matching = qu.getNoOfMatchingWords();
		String sql = "update dbo.[N1-Ayahs] a1, dbo.[N1-Ayahs] a2, "
				   + "dbo.[N1-Surahs] s1,  dbo.[N1-Surahs] s2, "
				   + "[dbo].[Similarity_Wordbased_NoOrder_NoRepetetion] sim set " + columnName + " = ?  where Word_matching = ? ";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			preparedStatement.setInt(2, Word_matching);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	public void update_forChar(int row_forChar, String columnName_forChar, Object data_forChar) {
		QuranCharSim qui = list_CharSim.get(row_forChar);
		//String pName = product.getProductName();
		int Char_matching = qui.getNoOfMatchingChars();
		String Char_sql = "update dbo.[N1-Ayahs] a1, dbo.[N1-Ayahs] a2, "
						+ "dbo.[N1-Surahs] s1,  dbo.[N1-Surahs] s2, "
						+ "[dbo].[Similarity_Wordbased_NoOrder_NoRepetetion] sim set " + columnName_forChar + " = ?  where Char_matching = ? ";
		System.out.println(Char_sql);
		PreparedStatement Char_preparedStatement = null;
		try {
			Char_preparedStatement = conn.prepareStatement(Char_sql);
			if (data_forChar instanceof String)
				Char_preparedStatement.setString(1, (String) data_forChar);
			else if (data_forChar instanceof Integer)
				Char_preparedStatement.setInt(1, (Integer) data_forChar);
			Char_preparedStatement.setInt(2, Char_matching);
			Char_preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args){
		try {
			createConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}