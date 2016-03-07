import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuranSQLserverDB {
	private static Connection conn;
	private List<QuranWordSim> list;
	
	public static void createConnection() throws SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
		         "databaseName=QuranV22;user=sa;password=aaa";
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
		String query = "select s1.SurahName, a1.Ayah, s2.SurahName, a2.Ayah, sim.NoOfMatchingWords, sim.PercentageOfMatchingWordsInSentence1, sim.PercentageOfMatchingWordsInSentence2 "
				+ "from dbo.[N1-Ayahs] a1, dbo.[N1-Ayahs] a2, dbo.[N1-Surahs] s1,  dbo.[N1-Surahs] s2, [dbo].[Similarity_Wordbased_NoOrder_NoRepetetion] sim"
				+ "where sim.AyahSerialNo1 = a1.AyahSerialNo"
				+ "and sim.AyahSerialNo2 = a2.AyahSerialNo"
				+ "and a1.SurahSerialNo = s1.SurahSerialNo"
				+ "and a2.SurahSerialNo = s2.SurahSerialNo"
				+ "and sim.PercentageOfMatchingWordsInSentence1 > 60"
				+ "and sim.PercentageOfMatchingWordsInSentence2 > 70";
		
		list = new ArrayList<QuranWordSim>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String SurahName1 = rs.getString("SurahName");
				String Ayah1 = rs.getString("Ayah");
				String SurahName2 = rs.getString("SurahName");
				String Ayah2 = rs.getString("Ayah");
				int NoOfMatchingWords = rs.getInt("NoOfMatchingWords");
				int PercentageOfMatchingWordsInSentence1 = rs.getInt("PercentageOfMatchingWordsInSentence1");
				int PercentageOfMatchingWordsInSentence2 = rs.getInt("PercentageOfMatchingWordsInSentence2");
				
				QuranWordSim x = new QuranWordSim(SurahName1, Ayah1, SurahName2, Ayah2, NoOfMatchingWords, PercentageOfMatchingWordsInSentence1, PercentageOfMatchingWordsInSentence2);
				list.add(x);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}
	
	public List<QuranWordSim> getQuran(String qur) {
		List<QuranWordSim> filterList = new ArrayList<QuranWordSim>();
		try {
			list = getQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (QuranWordSim h : list) {
			if (h.getAyah1().toLowerCase().contains(qur.toLowerCase())) {
				filterList.add(h);
			}
		}
		return filterList;
	}
	
	public void addproduct(QuranWordSim q) {
//		String sql = "insert into dbo.Quran values " + "(?, ?, ?, ?, ?, ?); ";
//
//		PreparedStatement preparedStatement = null;
//		try {
//			preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setInt(1, q.getAyahSerialNo());
//			preparedStatement.setString(2, q.getAyah());
//			preparedStatement.setInt(3, q.getSurahSerialNo());
//			preparedStatement.setInt(4, q.getRubSerialNo());
//			preparedStatement.setInt(5, q.getAyahNoWithSurah());
//			preparedStatement.setInt(6, q.getAyahNoWithinRub());
//			preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println(e);
//			e.printStackTrace();
//		} 
	}
	
	public void update(int row, String columnName, Object data) {
		QuranWordSim qu = list.get(row);
		//String pName = product.getProductName();
		int sNum = qu.getNoOfMatchingWords();
		String sql = "update dbo.[N1-Ayahs] a1, dbo.[N1-Ayahs] a2, dbo.[N1-Surahs] s1,  dbo.[N1-Surahs] s2, [dbo].[Similarity_Wordbased_NoOrder_NoRepetetion] sim set " + columnName + " = ?  where sNum = ? ";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			preparedStatement.setInt(2, sNum);
			preparedStatement.executeUpdate();
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