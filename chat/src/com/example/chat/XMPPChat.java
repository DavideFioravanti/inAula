package com.example.chat;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class XMPPChat extends Activity implements OnClickListener{

	
	private EditText et;
	private TextView tv;
	private XMPPConnection connection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        et = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.testo);
        
        Button bt = (Button) findViewById(R.id.button1);
        bt.setOnClickListener(this);
        
        
        ConnectionConfiguration config= new ConnectionConfiguration("ppl.eln.uniroma2.it",5222); // Configurazione per connettersi al server (indirizzo e porta)
        
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled); // Sicurezza disabilitata per le nostre prove
        
        connection = new XMPPConnection(config); // Imposto la configurazione nella connessione
        
        try {
        	connection.connect(); // Ci connettiamo alla chat
        
			connection.login("fioravanti", "qwerty"); //Username e password per accedere
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        connection.addPacketListener(new PacketListener() {
			
			@Override
			public void processPacket(Packet arg0) {
				// TODO Auto-generated method stub
				Message msg = (Message) arg0; // Esegue il casting dell'arg0
				final String to = msg.getTo(); // Prende i vari segmenti del messaggio
				final String body = msg.getBody();
				final String[] from = msg.getFrom().split("@"); // Splitta il messaggio per eliminare tutto ciò che c'è dopo la chiocciola
				Log.d("XMPPChat","Hai ricevuto un messaggio: " + from + " " + to +" " + body);
				tv.post(new Runnable() { //Devo inserire tutto in una Runnable perchè viene eseguito in un
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						tv.append(from[0] + ": "+ body +"\n"); // Appende il messaggio alla TextView
					}
				}) ;
				
				
			}
		}, new MessageTypeFilter(Message.Type.normal));
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("XMPPChat","Hai scritto: "+ et.getText());
		
		tv.append("IO: " + et.getText()+"\n");
		
		Message msg = new Message(); // Creo il messaggio
		
		msg.setTo("all@broadcast.ppl.eln.uniroma2.it"); // Imposta come destinatario tutti gli utenti presenti sul server
		
		msg.setBody(et.getText().toString()); // Inserisce il testo della TextView
		
		connection.sendPacket(msg); //Viene spedito il pacchetto
	}
    
}
