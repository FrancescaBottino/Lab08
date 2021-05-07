package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	Graph<Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao;
	Map<Integer, Airport> airportMap;
	String s="";
	
	public Model() {
		
		dao= new ExtFlightDelaysDAO();
		airportMap=new HashMap<>();
		
	}
	
	public void creaGrafo(double distanzaMin) {
		
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		//aggiungo vertici
		List<Airport> airports= dao.loadAllAirports();
		
		Graphs.addAllVertices(this.grafo, airports);
		
		//aggiungo archi
		for(Airport a: airports) {
			airportMap.put(a.getId(), a);
		}
		
		//ottengo tutte le tratte con: aeroporto 1-aeroporto 2-numero di voli per quella tratta
		//già filtrate con la condizione sulla distanza minima 
		
		List<Tratta> tratte= dao.getTratte(airportMap, distanzaMin);
		
		for(Tratta t: tratte) {
			
			if(this.grafo.containsVertex(t.getA1()) && this.grafo.containsVertex(t.getA2())) {
				
				DefaultWeightedEdge e=this.grafo.getEdge(t.getA1(), t.getA2());
				
				if(e==null) {
					Graphs.addEdgeWithVertices(this.grafo, t.getA1(), t.getA2(), t.getDistance());
				}
				
			}
		}
		
		
		
	}
	
	public int getNVertici(){
		
		if(grafo.vertexSet().size() != 0) {
			return grafo.vertexSet().size();
		}
		return 0;
	}
	
	public int getNArchi(){
		
		if(grafo.edgeSet().size() != 0) {
			return grafo.edgeSet().size();
		}
		
		return 0;
	}
	
	
	public String stampa() {
	
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
		
			s += grafo.getEdgeSource(e)+" - "+grafo.getEdgeTarget(e)+" : "+grafo.getEdgeWeight(e)+"\n";
		}
		
		return s;
	
	}
	
	

}
