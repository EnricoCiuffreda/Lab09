package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;



public class Model {
	
	private SimpleGraph<Country, DefaultEdge> grafo;
	private Map<Integer,Country> idMap;
	private BordersDAO dao;
	private Map<Country,Country> visita=new HashMap<>();
	List <Country> soluzione=new ArrayList<>();
	
	public Model() {
		idMap=new HashMap<>();
		dao=new BordersDAO();
	}
	
	
	public void creaGrafo(int x) {
		dao.loadAllCountries(x,idMap);
		this.grafo=new SimpleGraph<>(DefaultEdge.class);
		//aggiungiamo i vertici
		for(Country c: idMap.values()) {
				grafo.addVertex(c);
		}
		for(Border b:dao.loadAllBorders(x,idMap)) {
			grafo.addEdge(b.getP1(), b.getP2());
		}
		for(Country c: grafo.vertexSet()) {
			int numero=0;
			for(DefaultEdge e: grafo.edgeSet()) {
				if(grafo.getEdgeSource(e).equals(c) || grafo.getEdgeTarget(e).equals(c)) {
					numero++;
				}
			}
			c.setCollegamentifino(numero);
			}
		}
	
	public int staticonnessi(){
		ConnectivityInspector<Country, DefaultEdge>grafocontrollo=new ConnectivityInspector<Country, DefaultEdge>(grafo);
		List<Set<Country>>lista=grafocontrollo.connectedSets();
		return lista.size();
	}
	
	public List<Set<Country>> staticonnessi2(){
		ConnectivityInspector<Country, DefaultEdge>grafocontrollo=new ConnectivityInspector<Country, DefaultEdge>(grafo);
		List<Set<Country>>lista=grafocontrollo.connectedSets();
		return lista;
	}
	
	public int vertexNumber() {
		return this.grafo.vertexSet().size();
	}
	
	public int edgeNumber() {
		return this.grafo.edgeSet().size();
	}
	
	public Collection<Country> getPaesi(){
		return this.grafo.vertexSet();
	}
	
        public List<Country> trovaPercorso(Country c1){
        	visita.clear();
		final List<Country> percorso=new ArrayList<>();
		//BreadthFirstIterator<Country, DefaultEdge> it=new BreadthFirstIterator<>(this.grafo);
		DepthFirstIterator<Country, DefaultEdge> it=new DepthFirstIterator<>(this.grafo);
		visita.put(c1, null);
		it.addTraversalListener(new TraversalListener<Country, DefaultEdge>() {

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
				Country sorgente=grafo.getEdgeSource(e.getEdge());
				Country destinazione=grafo.getEdgeTarget(e.getEdge());
				if(!visita.containsKey(destinazione) && visita.containsKey(sorgente)) {
					visita.put(destinazione,sorgente);
				} else if(!visita.containsKey(sorgente) && visita.containsKey(destinazione)){
					visita.put(sorgente, destinazione);
				}
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void vertexFinished(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
				
			}
		});
		while(it.hasNext()) {
			it.next();
		}
		for(Country c:visita.keySet()) {
			if(c!=null && !c.equals(c1)) {
				percorso.add(c);
			}
		}
		return percorso;
	}
        
        
        
        public List<Country> trovaPercorso2(Country c1){
    		final List<Country> percorso=new ArrayList<>();
    		//BreadthFirstIterator<Country, DefaultEdge> it=new BreadthFirstIterator<>(this.grafo);
    		DepthFirstIterator<Country, DefaultEdge> it=new DepthFirstIterator<>(this.grafo);
    		visita.put(c1, null);
    		while(it.hasNext()) {
    			percorso.add(it.next());
    		}
    		return percorso;
    	}
        
        public List<Country> cerca(Country c1) {
        	List <Country>davisitare=new ArrayList<>(this.grafo.vertexSet());
        	List<Country> percorso=new ArrayList<>();
    		percorso.add(c1);
        	ricorsiva(percorso,davisitare);
        	soluzione.remove(c1);
    		return soluzione;
    	}
    	

    	private void ricorsiva(List<Country> percorso,List <Country>davisitare) {
    		List<Country> prossimi=new ArrayList<>();
    		Country attuale=percorso.get(percorso.size()-1);
    		if(!soluzione.contains(attuale)) {
    			soluzione.add(attuale);
    		}
    		if(davisitare.contains(attuale)){
    			davisitare.remove(attuale);
    		}
    		if(davisitare.size()==0) {
    			return;
    		}
    		Set<DefaultEdge> inuscita = this.grafo.outgoingEdgesOf(attuale);
			Set<DefaultEdge> inentrata = this.grafo.incomingEdgesOf(attuale);
			for(DefaultEdge e : inuscita)
				if(davisitare.contains(grafo.getEdgeTarget(e)))
					prossimi.add(grafo.getEdgeTarget(e));
			
			for(DefaultEdge e : inentrata)
				if(davisitare.contains(grafo.getEdgeSource(e)))
					prossimi.add(grafo.getEdgeSource(e));	
			if(prossimi.size()==0)
				return;
			for(Country c: prossimi) {
    		percorso.add(c);
    		ricorsiva(percorso,davisitare);
    		percorso.remove(c);
			}
    	}
        

}
