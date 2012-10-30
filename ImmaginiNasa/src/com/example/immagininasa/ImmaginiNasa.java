package com.example.immagininasa;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ImmaginiNasa extends Activity {

    private class Connection extends AsyncTask {
    	 
        @Override
        protected void onPostExecute(Object arg0) {

 			//TextView tv = (TextView) findViewById(R.id.titolo);
 			//tv.setText(handler.title);
 			ImageView iv = (ImageView) findViewById(R.id.imageView1);
 			iv.setImageBitmap((Bitmap) arg0);
 			System.out.print("CIAO");
       }
        @Override
        protected Object doInBackground(Object... arg0) {
        	 String url ="http://www.nasa.gov/rss/image_of_the_day.rss";
     		try {
     			SAXParserFactory factory = SAXParserFactory.newInstance(); 
     			SAXParser parser = factory.newSAXParser();
     			InputStream in = new URL (url).openStream(); //Se non esiste il file crea una eccezione


     			RssHandler handler=new RssHandler();
     			XMLReader reader = parser.getXMLReader();
     			reader.setContentHandler(handler);
     			reader.parse(new InputSource(in));//Questo è il momento in cui scarica veramente e legge il file
     			
     			return handler.getImage();
     			
     		} catch (MalformedURLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (ParserConfigurationException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SAXException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
			return null;
        }
        

 
    }
 
    

    

    private class Downloader extends AsyncTask<String, Void, RssHandler> {
    	 
        @Override
        protected void onPostExecute(RssHandler arg0) {

 			TextView tv = (TextView) findViewById(R.id.titolo);
 			tv.setText(arg0.title);
 			ImageView iv = (ImageView) findViewById(R.id.imageView1);
 			iv.setImageBitmap(pic);
 			System.out.print("CIAO");
       }
         Bitmap pic;
        
        @Override
        protected RssHandler doInBackground(String... arg0) {
        	for (int i = 0; i < arg0.length; i++) {
				
			
        	String url =arg0[i];//.toString();

     		try {
     			SAXParserFactory factory = SAXParserFactory.newInstance(); 
     			SAXParser parser = factory.newSAXParser();
     			InputStream in = new URL (url).openStream(); //Se non esiste il file crea una eccezione


     			RssHandler handler=new RssHandler();
     			XMLReader reader = parser.getXMLReader();
     			reader.setContentHandler(handler);
     			reader.parse(new InputSource(in));//Questo è il momento in cui scarica veramente e legge il file

     			pic= handler.getImage();
     			return handler;
     			
     		} catch (MalformedURLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (ParserConfigurationException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SAXException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
			return null;
        }
			return null;
        }
        

 
    }
 
    

    


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immagini_nasa);
        
        //new Connection().execute();
        new Downloader().execute("http://www.nasa.gov/rss/image_of_the_day.rss");
        

    
    }
    

    public void AzionePosticipata(){

        new Handler().postDelayed(new Runnable() {
           public void run() {
            	
            	 String url ="http://www.nasa.gov/rss/image_of_the_day.rss";
          		try {
          			SAXParserFactory factory = SAXParserFactory.newInstance(); 
          			SAXParser parser = factory.newSAXParser();
          			InputStream in = new URL (url).openStream(); //Se non esiste il file crea una eccezione


          			RssHandler handler=new RssHandler();
          			XMLReader reader = parser.getXMLReader();
          			reader.setContentHandler(handler);
          			reader.parse(new InputSource(in));//Questo è il momento in cui scarica veramente e legge il file
         			TextView tv = (TextView) findViewById(R.id.titolo);
         			tv.setText(handler.title);
         			ImageView iv = (ImageView) findViewById(R.id.imageView1);
         			iv.setImageBitmap(handler.getImage());
          			
          			
          		} catch (MalformedURLException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          		} catch (IOException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          		} catch (ParserConfigurationException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          		} catch (SAXException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          		}
        
            }
        }, 50);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_immagini_nasa, menu);
        return true;
    }
}
