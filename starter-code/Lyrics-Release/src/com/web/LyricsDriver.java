package com.web;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileOutputStream;

public class LyricsDriver {
	public static void writeToFile(String fileName, String data){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(data);
			System.out.println("Lyrics data scraped ....");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void getLyrics(String path) {
		URL url;
		try {
			// get URL content
			url = new URL(path);
			URLConnection conn = url.openConnection();
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			//save to this filename
			String inputFile = "../upload/raw.html";
			File infile = new File(inputFile);

			String outputFile = "../upload/lyrics.txt";
			File outfile = new File(outputFile);

			if (!infile.exists()) {
				infile.createNewFile();
			}
			if (outfile.exists()) {
				outfile.delete();
			}
			//use FileWriter to write file
			FileWriter fw = new FileWriter(infile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
			}
			bw.close();
			br.close();
			Document doc = Jsoup.parse(infile, null);
			
			/* add your logic here to get the lyrics*/
			
			infile.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	public static void main(String[] args) {
		long start_time = System.nanoTime();
		String path = "https://www.azlyrics.com/lyrics/michaeljackson/ijustcantstoplovingyou.html";
		getLyrics(path);
		long end_time = System.nanoTime();
		long total_time = end_time - start_time;
		System.out.println(total_time);
	}
}