package it.polito.tdp.borders.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	BordersDAO bd = new BordersDAO();
	List<Country> countries = new ArrayList<Country>();
	UndirectedGraph<Country, Border> graph = new SimpleGraph<Country, Border>(Border.class) ;
	public Model() {
	
	}

	public String createGraph(int anno) throws SQLException {
		
		countries.addAll(bd.loadAllCountries());
		/*for (Country c : countries){
			graph.addVertex(c);
		}*/
		List<Border> borders = new ArrayList<Border>();
		//List<Border> borders2 = new ArrayList<Border>();
		//System.out.println();
		borders.addAll(bd.getCountryPairs(anno));
		/*System.out.println(borders.toString());
		for (Border b : borders){
			if(countries.contains(b.getC1())&&countries.contains(b.getC2())){
				graph.addEdge(b.getC1(), b.getC2());
				/*bo.setC1(c1);
				bo.setC2(c2);
				//borders2.add(bo);
				}
			
		}*/
		//System.out.println(graph);
		for(Border b : borders){
			Country c1 = b.getC1();
			Country c2 = b.getC2();
			if(!graph.vertexSet().contains(c1))
				graph.addVertex(c1);
			if(!graph.vertexSet().contains(c2))
				graph.addVertex(c2);
			if(!graph.edgeSet().contains(b))
				graph.addEdge(c1, c2, b);
}
		return graph.toString();
	}

	public List<Country> getCountries() {
		
		return countries;
	}
	public String numeroStatiConfinanti(){
		String risultato="";
		for (Country c : graph.vertexSet()){
			//risultato+=c.getStateAbb()+"\t";
			List<Country> confinanti = new LinkedList<Country>();
			confinanti.addAll(Graphs.neighborListOf(graph, c));
			
				risultato += c.getStateAbb()+" confina con: "+confinanti.size()+"stati \n";
			
			System.out.println(risultato);
		}
		return risultato;
		
	}
public String calcolaComponentiConnesse(){
		
		String ris = "";
		
		ConnectivityInspector<Country,Border> ci = new ConnectivityInspector<Country,Border>(graph);
		
		int numComponentiConnesse = ci.connectedSets().size();
		
		ris += "Numero componenti connesse nel grafo: " + numComponentiConnesse+"\t"+ci.connectedSets().toString();
		System.out.println(ris);
		return ris;
}
}
