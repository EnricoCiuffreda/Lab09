package it.polito.tdp.borders.model;

import java.util.Set;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
	System.out.println("Creo il grafo relativo al 2000");
	model.creaGrafo(2000);
	System.out.println(model.vertexNumber());
	for(Set<Country> s: model.staticonnessi2()) {
		System.out.println(s.size());
		System.out.println(s);
	}
	System.out.println(model.staticonnessi2());
	System.out.println("i paesi connessi agli usa sono:");
	System.out.println(model.trovaPercorso(new Country("USA",2,"United States of America")));
	System.out.println(model.cerca(new Country("USA",2,"United States of America")));
		
//		List<Country> countries = model.getCountries();
//		System.out.format("Trovate %d nazioni\n", countries.size());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
	}

}
