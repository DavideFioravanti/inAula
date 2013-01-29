package com.example.helloworld;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class SalvaDato extends HttpServlet {

	/**
	 * @param args
	 */
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	//Prendiamo i dati dai parametri
	String nome = req.getParameter("nome");
	//e li mettiamo dentro entity (i datastore google li gestisce così)
	Entity en = new Entity ("Persona", nome);
	
	int eta = Integer.parseInt(req.getParameter("eta"));
	en.setProperty("eta", eta);
	
	
	
	
	
	//Inizializza i datastore
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	//salva i dati
	
	//Entity en = new Entity("Persona", "Pierpaolo");
	//en.setProperty("eta", 39);
	
	ds.put(en);
	
	//DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	Query q = new Query("Persona");
	PreparedQuery qp= ds.prepare(q);
	List <Entity> result = qp.asList(FetchOptions.Builder.withDefaults()) ;
	for (Entity entity : result) {
		System.out.println("Key: "+ entity.getKey()+" Kind: "+ entity.getKind()+ " Età: "+entity.getProperty("eta"));
	}	
	
	resp.setContentType("text/plain");
	resp.getWriter().println("Salverò il dato");
}

}
