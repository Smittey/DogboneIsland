// DictionaryTools.java --- 
// 
// Filename: DictionaryTools.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Mon Apr 16 22:50:57 2012 (+0100)
// Version: 
// Last-Updated: Mon Apr 16 22:51:42 2012 (+0100)
//           By: Sevki Hasirci
//     Update #: 2
// URL: http://sevki.org
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// 
// 
// 
// 

// Change Log:
// 16-Apr-2012    Sevki Hasirci  
//    Last-Updated: Mon Apr 16 22:51:42 2012 (+0100) #2 (Sevki Hasirci)
//    Moved the Dictionary file from github to ncl.sevki.org. it is now generated dynamically at ncl.sevki.org/dictionaryJ.php
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Looper;

public class DogBoneServer {

	protected static void sendUserScoreJson(final long User_id, final int Game_id, final int Word_id,final int Win, final long Duration) {
		final String Url = "http://ncl.sevki.org/newWin.php";
		  
		  final JSONObject json = new JSONObject();
		   try {
			json.put("user_id", User_id);
			json.put("game_id", Game_id);
           json.put("word_id", Word_id);
           json.put("win", Win);
           json.put("duration", Duration);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           
		  sendJson(json,Url);         

}

	protected static void sendNewUserJson(String Access_Token ) {
      
			final String Url = "http://ncl.sevki.org/newUser.php";
		  final JSONObject json = new JSONObject();
		   try {
			json.put("access_token", Access_Token);
       		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           
		  sendJson(json,Url);         

}
	private static void sendJson(final JSONObject json, final String Url) {
		Thread t = new Thread(){
		        public void run() {
		                Looper.prepare(); //For Preparing Message Pool for the child Thread
		                HttpClient client = new DefaultHttpClient();
		                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
		                HttpResponse response;
		                try{
		                    HttpPost post = new HttpPost(Url);
		                    //HttpPost post = new HttpPost("http://10.0.2.2:1337");
		                 
		                    StringEntity se = new StringEntity(  json.toString());  
		                    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		                    post.setEntity(se);
		                    response = client.execute(post);
		      
		                    /*Checking response */
		                    if(response!=null){
		                    	System.out.println("oldu gibi ama");
		                       InputStream in = response.getEntity().getContent(); //Get the data in the entity
		                       int b;
		                       String cq = "";
		                       while ( ( b = in.read() ) != -1 )
		                       {

		                           char c = (char)b;
		                           cq =cq+c;
		                       }
		                       System.out.println(cq);
		                    }
		                    else
		                    {
		                    	System.out.println("olmadi");
		                    }
		                }
		                catch(Exception e){
		                    e.printStackTrace();
		               
		                }
		                Looper.loop(); //Loop in the message queue
		            }
		        };
		        t.start();
	}
}