package it.polito.tdp.borders.model;

import java.sql.SQLException;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- ");
		
		System.out.println("Creo il grafo relativo al 2000");
		try {
			model.createGraph(1920);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//List<Country> countries = model.getCountries();
		System.out.println(model.getCountries().toString());
		//System.out.format("Trovate %d nazioni\n", countries.size());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
	}

}
