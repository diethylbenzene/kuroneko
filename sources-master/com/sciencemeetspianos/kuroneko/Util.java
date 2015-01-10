package com.sciencemeetspianos.kuroneko;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Util {

	/**
	 * Gets the amount of episodes in the anime page.
	 * @param root the anime page.
	 * @return the amount of episodes.
	 * @throws IOException
	 * @deprecated Replaced by getTableList(). Method will be removed in a later version.
	 */
	@Deprecated
	public static int getAmount(File root) throws IOException
	{
		Document d = Jsoup.parse(root, "UTF-8");
		Element e = d.select("td").first();
		String classes = e.html();
		
		
		int var1 = StringUtils.countMatches(classes, "td");
		
		return var1;
	}
	/**
	 * Gets the amount of elements in the anime page.
	 * @param root the anime page.
	 * @return the amount of table elements.
	 * @throws IOException
	 */
	public static int getTableList(File root) throws IOException
	{
		String str = FileUtils.readFileToString(root);
		str = StringUtils.lowerCase(str);
		int countThe = StringUtils.countMatches(str, "<td>");
		return countThe / 2;
	}
	
	/**
	 * Reads the download link from the page.
	 * @param html the page
	 * @return the link
	 * @throws IOException
	 */
	public static String getDownloadLink(File html) throws IOException
	{
		byte[] read = Files.readAllBytes(Paths.get(html.getCanonicalPath()));
		String var1 = new String(read, "UTF-8");
		int begin = var1.indexOf("fmt_stream_map") + 20;
		int end = var1.indexOf("key%3dlh1") + 9;
		String var2 = var1.substring(begin, end);
		return var2;
	}
	/**
	 * Gets the URLs from the table elements.
	 * 
	 * @param root the root HTML file
	 * @param count the amount of table elements there are
	 * @return the URLs
	 * @throws IOException
	 * @deprecated Replaced with getLinkFromRoot
	 */
	@Deprecated
	public static String getURLFromRoot(File root, int count) throws IOException
	{
		//for(int i = 0; i < count; i++)
		//{
		Document d = Jsoup.parse(root, "UTF-8");
		Element e = d.select("td").get(count * 2);
		String classes = e.html();
		String links = "http://www.kissanime.com/" + classes.substring(classes.indexOf("A"), classes.indexOf("?"));
		
		return links;
	}
	
	/**
	 * Gets the URLs from the table elements.
	 * @param d the root HTML file
	 * @param count the amount of table elements there are
	 * @return the URLs
	 * @throws IOException
	 * @deprecated Will be removed in a later version.
	 */
	@Deprecated
	public static String getURLFromRoot(Document d, int count) throws IOException
	{
		//for(int i = 0; i < count; i++)
		//{
		Element e = d.select("td").get(count * 2);
		String classes = e.html();
		
		String var0 = "http://kissanime.com/Anime/" + classes.substring(classes.indexOf("/Episode-"), classes.indexOf("?"));
			
		//}
		
		return var0;
	}
	
	public static String getLinkFromRoot(File html, int index) throws IOException
	{
		Document d = Jsoup.parse(html, "UTF-8");
			Element e = d.select("td").get(index * 2);
			String classes = e.html();
			Document d1 = Jsoup.parse(classes);
			Element e1 = d1.select("a").first();
			String links = e1.attr("href");
		return "http://www.kissanime.com" + links;
	}
	
	public static String getName(File html, int index, boolean isEpisode) throws IOException
	{
		if(isEpisode)
		{
		Document d = Jsoup.parse(html, "UTF-8");
			Element e = d.select("td").get(index * 2);
			String classes = e.html();
			Document d1 = Jsoup.parse(classes);
			Element e1 = d1.select("a").first();
			String links = e1.attr("href");
			String var0 = links.substring(links.indexOf("Episode-"), links.indexOf("?"));
			String var1 = var0.replace('-', ' ');
			return var1;
		}else
		{
			return null;
		}
	}
	
	
	public static boolean checkIfEpisode(String link)
	{
		if(!link.contains("Episode"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static String convertURL(String url) throws UnsupportedEncodingException
	{
		String var1 = java.net.URLDecoder.decode(url, "ASCII");
		String var2 = var1.replaceAll("%2C", ",");
		return var2;
	}
	
	
}
