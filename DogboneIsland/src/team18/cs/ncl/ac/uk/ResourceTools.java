// ResourceTools.java --- 
// 
// Filename: ResourceTools.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Fri Apr 27 16:09:07 2012 (+0100)
// Version: 
// Last-Updated: 
//           By: 
//     Update #: 0
// URL: http://sevki.org
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// Handles all the downloading and I/O operations of the APP
// 
// 
// 

// Change Log:
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

public class ResourceTools {
    
    public static Boolean DownloadFile(File Path, String Url) {
	String reString = ReadWebStream(Url);
	try {
	    File f = Path;
	    
	    if (!f.exists()) {
		f.createNewFile();
	    }
	    System.out.print("about to write"+Url);
	    FileOutputStream fos = new FileOutputStream(Path);
	    fos.write(reString.getBytes("UTF-8"));
	    System.out.print("wrote"+reString);
	    
	    fos.close();
			
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
			
	    e.printStackTrace();
	}
	catch (NullPointerException e) {
		
	}
	return true;
    }
    
    public static String ReadWebStream(String url) {
    	StringBuilder builder = new StringBuilder();
	HttpClient client = new DefaultHttpClient();
	HttpGet httpGet = new HttpGet(url);
	try {
	    HttpResponse response = client.execute(httpGet);
	    StatusLine statusLine = response.getStatusLine();
	    int statusCode = statusLine.getStatusCode();
	    if (statusCode == 200) {
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(
							   new InputStreamReader(content, "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
		    builder.append(line);
		}
	    } else {
	    }
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return builder.toString();
    }
    public static String ReadFromLocal(File Path) throws  IOException, SAXException
    {
	FileInputStream fIn;
	System.out.println("now opening");
	fIn = new FileInputStream(Path);
	         
	StringBuffer sBuffer = new StringBuffer("");
	         
	int ch;
	System.out.println("now reading");
	while( (ch = fIn.read()) != -1)
	    sBuffer.append((char)ch);
	         
	System.out.println("now parsing" + sBuffer +"wrote sbuffer");
	// Deprecated, switched to JSON Instead of XML.
	// Xml.parse(sBuffer.toString(), new MyXmlContentHandler());
	         
	     
	return sBuffer.toString();
    }
}
