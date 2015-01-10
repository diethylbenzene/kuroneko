package com.sciencemeetspianos.kuroneko;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download 
{
	private URL theURL;
	public File theFile;
	public int theLength;
	public boolean finished;
	
	public Download(String i, String j) throws MalformedURLException
	{
		this.theURL = new URL(i);
		this.theFile = new File(j);
		
	}
	
	public Download(URL i, File j)
	{
		this.theURL = i;
		this.theFile = j;
	}
	
	public Download(URL i)
	{
		this.theURL = i;
		this.theFile = new File(System.getProperty("user.home") + "/.kuroneko/tempfiles/root.html");
	}
	
	public void fileFromURL() throws IOException
	{
		finished = false;
	    ReadableByteChannel rbc = Channels.newChannel(theURL.openStream());
	    HttpURLConnection conn = (HttpURLConnection) theURL.openConnection();
	    FileOutputStream fos = new FileOutputStream(theFile);
	    theLength = conn.getContentLength();
	    fos.getChannel().transferFrom(rbc, 0L, 9223372036854775807L);
	    fos.close();
	    finished = true;
	}
}
