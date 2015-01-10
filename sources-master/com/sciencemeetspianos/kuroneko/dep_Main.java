package com.sciencemeetspianos.kuroneko;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
@Deprecated
public class dep_Main {

	public static void main(String[] args) throws IOException 
	{
		Logger l = Logger.getLogger("KuroNeko.log");
		FileHandler fh = new FileHandler(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "KuroNeko.log");
		l.addHandler(fh);
		l.setLevel(Level.ALL);
		fh.setFormatter(new LogBasicFormatter());
		l.fine("Purging temp files directory");
		System.out.println("Purging temp files directory...");
		FileUtils.cleanDirectory(new File(System.getProperty("user.home") + "/.kuroneko/tempfiles/"));
		
		l.fine("Setting user agent");
		System.setProperty("http.agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		Download d = new Download(new URL("http://www.kissanime.com/Anime/Angel-Beats"));
		System.out.println("Downloading root webpage");
		d.fileFromURL();
		ArrayList<String> links0 = new ArrayList<String>();
		int var0 = Util.getTableList(d.theFile);
		for(int i = 0; i < var0; i++)
		{
			Download d1 = new Download(new URL(Util.getURLFromRoot(d.theFile, i)), new File(System.getProperty("user.home") + "/.kuroneko/tempfiles/" + "ep" + i + ".html"));
			d1.fileFromURL();
			System.out.println("Downloading element webpages: "+ (i + 1) + " of " + var0);
			String s = Util.convertURL(Util.getDownloadLink(d1.theFile));
			links0.add(s);
			
		}
	}

}
