package com.example.immagininasa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;




public class RssHandler extends DefaultHandler  {
	
	//Per prendere un elemento bisogna creare una variabile booleana per capire se siamo
	//nell'elemento giusto e una variabile che contiene il dato che ci serve.
	//Bisogna creare tante variabili booleane quanti sono i valori che ci servono
	boolean inTitle=false;
	String title=null;
	boolean inItem = false;
	boolean inEnclosure = false;
	String imageUrl=null;
	static Bitmap bitmap_ret;
	
	
	
	private class DownloadImg extends AsyncTask {
   	 
        @Override
        protected void onPostExecute(Object params) {
        	//RssHandler.bitmap_ret=(Bitmap) params;
       }
        @Override
        protected Bitmap doInBackground(Object... params) {
        	
			
        	HttpURLConnection connection;
    		try {
    			connection = (HttpURLConnection) new URL(imageUrl).openConnection();

    			connection.setDoInput(true);
    			connection.connect();
    			InputStream is = connection.getInputStream();
    			Bitmap bitmap =BitmapFactory.decodeStream(is);
    			
    			is.close();
    			RssHandler.bitmap_ret = bitmap;
    			return bitmap;

     			
     		} catch (MalformedURLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
			return null;
        }



 
    }
	
	
	
	public Bitmap getImage(){
		
		//new DownloadImg().execute();
		
		//return bitmap_ret;

    	HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(imageUrl).openConnection();

			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			Bitmap bitmap =BitmapFactory.decodeStream(is);
			
			is.close();

			return bitmap;

 			
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		
		return null;
	}
	
	
	
	
	//QUesto metodo serve per capire quando inizia il file xml 
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("Inizio Documento!");
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("Fine Documento!");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		System.out.println("Inizio Elemento:"+qName);
		if(qName.equals("title")){
			inTitle=true;
		}else if (qName.equalsIgnoreCase("item")){
			inItem=true;
		}else if (qName.equalsIgnoreCase("enclosure")){
			inEnclosure=true;
		}
		
		if (inItem & inEnclosure){
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.println("Attributo:"+attributes.getQName(i)+" valore:"+attributes.getValue(i));
				if (attributes.getQName(i).equalsIgnoreCase("url")){
					imageUrl=attributes.getValue(i);
				}
		}}
		
		
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println("Attributo:"+attributes.getQName(i)+" valore:"+attributes.getValue(i));
			
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		System.out.println("Fine Elemento:"+qName);
		
		if(qName.equals("title")){
			inTitle=false;
		}else if (qName.equalsIgnoreCase("item")){
			inItem=false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String s=new String(ch,start,length);
		System.out.println("Testo:"+s);
		if (inTitle & inItem){
			title=s;
		}
	}
}
