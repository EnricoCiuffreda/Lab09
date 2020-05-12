package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(int x,Map<Integer,Country> idMap) {

		String sql = "SELECT country.StateAbb,country.CCode,country.StateNme" + 
				" FROM country,contiguity" + 
				" WHERE YEAR<=? AND contiguity.conttype=1 AND country.CCode=contiguity.state1no" + 
				" GROUP BY country.StateAbb,country.CCode,country.StateNme";		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, x);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("country.CCode"))) {
					Country paese=new Country(rs.getString("country.StateAbb"),rs.getInt("country.CCode"),rs.getString("country.StateNme"));
					idMap.put(paese.getCCode(), paese);
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List <Border>loadAllBorders(int x,Map<Integer,Country> idMap) {

		String sql = "SELECT contiguity.dyad,contiguity.state1no,contiguity.state2no,contiguity.year,contiguity.conttype,contiguity.version " + 
				"FROM contiguity " + 
				"WHERE YEAR<=? AND contiguity.conttype=1 " + 
				"GROUP BY contiguity.state1no,contiguity.state2no,contiguity.year";
		List <Border> confini=new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, x);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
					Country paese1=idMap.get(rs.getInt("contiguity.state1no"));
					Country paese2=idMap.get(rs.getInt("contiguity.state2no"));
					confini.add(new Border(rs.getInt("contiguity.dyad"),paese1,paese2,rs.getInt("contiguity.year"),rs.getInt("contiguity.conttype"),rs.getInt("contiguity.version")));
				}
			conn.close();
			return confini;
			}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	
}
