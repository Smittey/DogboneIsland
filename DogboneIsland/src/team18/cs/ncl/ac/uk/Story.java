// Story.java --- 
// 
// Filename: Story.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Sun Apr 22 19:33:28 2012 (+0100)
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
// The Story handler class
// 
// 
// 

// Change Log:
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import android.os.Environment;



public class Story {

private  String WEB_PATH = "https://raw.github.com/Team18-NewcastleUniversity/resources/master/Story.json";

private File ROOT_PATH  = Environment.getExternalStorageDirectory();
private  File STORY_PATH = new File(ROOT_PATH," team18.cs.ncl.ac.uk.story.json");

public Boolean DownloadStoryToLocal()
{
	return ResourceTools.DownloadFile(STORY_PATH, WEB_PATH);
}
public boolean ParseJSONDictionary(String json)
{
	System.out.println("starting parsing");
		try {
			JSONObject job = new JSONObject(json);
			JSONArray ja = job.getJSONArray("dictionary");
			for (int i = 0; i < ja.length(); i++)
			{
		
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return false;
		}
	return true;
	
}
public boolean ReadStory()
{
	try {
		String storyJson =ResourceTools.ReadFromLocal(STORY_PATH);
		ParseJSONDictionary(storyJson);
		return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

}
