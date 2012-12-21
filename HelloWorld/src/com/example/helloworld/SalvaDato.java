package com.example.helloworld;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


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
	ds.put(en);
	
	
	
	resp.setContentType("text/plain");
	resp.getWriter().println("Salverò il dato");
}

}
