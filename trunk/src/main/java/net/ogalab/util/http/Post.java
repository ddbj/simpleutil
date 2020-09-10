/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ogalab.util.http;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import net.ogalab.util.os.FileIO;

/**
 *
 * @author oogasawa
 */
public class Post {
    
    public static void main(String[] args) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, FileNotFoundException, IOException {

    URL url = new URL("http://localhost:8080/datacell/test");
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    connection.setDoOutput(true);
    //connection.setUseCashes(false);
    connection.setRequestMethod("POST");
    
    String s= URLEncoder.encode(FileIO.readFile("/home/oogasawa/test.py"));
    
    
    String parameterString = new String("tableDef=" + s);
    PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
    printWriter.print(parameterString);
    printWriter.close();
    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "JISAutoDetect"));
    String httpSource = new String();
    String str;
    while ( null != ( str = bufferReader.readLine() ) ) {
        httpSource = httpSource + str;
    }
    System.out.println(httpSource);
    bufferReader.close();
    connection.disconnect();
    }


}
