package com.openclassrooms.netapp.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Philippe on 19/12/2017.
 */

public class MyHttpURLConnection {

    public static String startHttpRequest(String urlString){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            // 1. Declare a URL Connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 2. Open InputStream to connection
            conn.connect();
            InputStream in = conn.getInputStream();
            // 3. Download and decode the string response using builder
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (MalformedURLException exception){

        } catch (IOException exception) {

        } catch (Exception e){

        }

        return stringBuilder.toString();
    }

}
