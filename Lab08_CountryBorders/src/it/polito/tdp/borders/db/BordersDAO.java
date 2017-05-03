package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";
		List<Country> countries = new LinkedList<Country>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country (rs.getString("StateAbb"), rs.getInt("ccode"));
				countries.add(c);
				System.out.println(c.toString());
			}

			conn.close();
			return countries;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		String sql = "SELECT state1no, state1ab, state2no, state2ab FROM contiguity where  year<? and conttype = 1";
		List<Border> borders = new ArrayList<Border>(); 

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c1 = new Country(rs.getString("state1ab"), rs.getInt("state1no"));
				Country c2 = new Country(rs.getString("state2ab"), rs.getInt("state2no"));
				Border b = new Border(c1,c2);
				//b.setC1(c1);
				//b.setC2(c2);
				System.out.println(b.toString());
				borders.add(b);
				
				
			}

			//conn.close();
			return borders;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- ");
			throw new RuntimeException("Database Error");
		}
		
	}
	/*public Country getCountry (int codice){
		String sql = "SELECT StateAbb, StateNme FROM country WHERE CCode = ? ";
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,codice);
			ResultSet rs = st.executeQuery();

			
			Country c = new Country(rs.getString("StateAbb"), rs.getString("StateNme"), codice);
		

			conn.close();
			return c;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- getCountries");
			throw new RuntimeException("Database Error");
		}
	}*/
}
