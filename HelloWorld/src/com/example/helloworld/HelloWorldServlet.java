package com.example.helloworld;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HelloWorldServlet extends HttpServlet {
	//Quando arriva uan get al server viene generata una richiesta req a cui possiamo dare una risposta resp
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//Per capire quale sia la stringa in ingresso
		System.out.println(req.getRequestURI());
		
		//Proviamo a prendere dei parametri
		Enumeration<String> parNames=req.getParameterNames();
		
		//prende i singoli parametri e li stampa
		while (parNames.hasMoreElements()) {
			String key = (String) parNames.nextElement();
			System.out.println(key + ": "+req.getParameter(key));
			
		}
		
		//Rispondiamo in modalità solo testo in questo caso
		resp.setContentType("text/plain");
		//Analogamente al system.out tramite getwriter possiamo scrivere
		resp.getWriter().println("Hello, world");
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		//Per capire quale sia la stringa in ingresso
		System.out.println(req.getRequestURI());
		
		//Proviamo a prendere dei parametri
		Enumeration<String> parNames=req.getParameterNames();
		
		//prende i singoli parametri e li stampa
		while (parNames.hasMoreElements()) {
			String key = (String) parNames.nextElement();
			System.out.println(key + ": "+req.getParameter(key));
			
		}
		
		//Rispondiamo in modalità solo testo in questo caso
		resp.setContentType("text/plain");
		//Analogamente al system.out tramite getwriter possiamo scrivere
		resp.getWriter().println("Hello post!");
	}
}
