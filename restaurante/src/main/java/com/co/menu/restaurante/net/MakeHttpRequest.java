package com.co.menu.restaurante.net;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.ksoap2.serialization.SoapObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class MakeHttpRequest {

    private static SoapObject request;

    public MakeHttpRequest(){

    }

    public static String post(){
        try {
            HttpClient httpclient = new DefaultHttpClient();
/*Creamos el objeto de HttpClient que nos permitira conectarnos mediante peticiones http*/
            HttpGet httpGet = new HttpGet("https://www.getpostman.com/collections/2a9f34a396ef5cd2822a");
            HttpPost httppost = new HttpPost("https://www.getpostman.com/collections/2a9f34a396ef5cd2822a");

            HttpResponse resp = httpclient.execute(httpGet);
            HttpEntity ent = resp.getEntity();/*y obtenemos una respuesta*/
            String text = EntityUtils.toString(ent);
            return text;
        }
        catch(Exception e)
        {
            return "error" + e.getMessage();
        }
    }


    public static String readURL() {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://www.getpostman.com/collections/2a9f34a396ef5cd2822a");

        try {
            HttpResponse response = client.execute(httpGet);

            StatusLine statusLine = response.getStatusLine();

            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(SoapObject.class.getName(), "Failed to download data");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();

    }
}
