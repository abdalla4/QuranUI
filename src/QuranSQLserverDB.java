import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuranSQLserverDB {
	private static Connection conn;
	private List<QuranClass> list;
	
	public static void createConnection() throws SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QuranV22;integratedSecurity=true;";
		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("Connected to database");
	}
	
	public List<QuranClass> getQuery() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		
		Statement stmt = null;
		String query = "select AyahSerialNo, Ayah, SurahSerialNo, RubSerialNo, AyahNoWithSurah, AyahNoWithinRub "
				+ "from dbo.Quran ";

		list = new ArrayList<QuranClass>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int AyahSerialNo = rs.getInt("AyahSerialNo");
				String Ayah = rs.getString("Ayah");
				int SurahSerialNo = rs.getInt("SurahSerialNo");
				int RubSerialNo = rs.getInt("RubSerialNo");
				int AyahNoWithSurah = rs.getInt("AyahNoWithSurah");
				int AyahNoWithinRub = rs.getInt("AyahNoWithinRub");
				
				QuranClass x = new QuranClass(AyahSerialNo, Ayah, SurahSerialNo, RubSerialNo, AyahNoWithSurah, AyahNoWithinRub);
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
	
	public List<QuranClass> getQuran(String qur) {
		List<QuranClass> filterList = new ArrayList<QuranClass>();
		try {
			list = getQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (QuranClass h : list) {
			if (h.getAyah().toLowerCase().contains(qur.toLowerCase())) {
				filterList.add(h);
			}
		}
		return filterList;
	}
	
	public void addproduct(QuranClass q) {
		String sql = "insert into dbo.Quran values " + "(?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, q.getAyahSerialNo());
			preparedStatement.setString(2, q.getAyah());
			preparedStatement.setInt(3, q.getSurahSerialNo());
			preparedStatement.setInt(4, q.getRubSerialNo());
			preparedStatement.setInt(5, q.getAyahNoWithSurah());
			preparedStatement.setInt(6, q.getAyahNoWithinRub());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	public void update(int row, String columnName, Object data) {
		QuranClass qu = list.get(row);
		//String pName = product.getProductName();
		int sNum = qu.getAyahSerialNo();
		String sql = "update dbo.Quran set " + columnName + " = ?  where sNum = ? ";
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
	
	
	


