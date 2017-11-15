package com.example.kentec.myapplicationmysql;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Kentec on 11/7/2017.
 */
//alt + insert can set up methods you need for a class!
public class BackgroundWorker extends AsyncTask<String, Void, String>{
    Context context;
    //AlertDialog alertDialog;

    public BackgroundWorker(Context context) {
        this.context = context;
    }
    String writeData = "";
    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        String insertURL = "https://unruffled-seals.000webhostapp.com/insertproducts.php";
        String prodName = strings[0];
        String prodDescription = strings[1];
        String prodQty = strings[2];

        try{
            String post_data = URLEncoder.encode("prodName", "UTF-8") + "=" +
                    URLEncoder.encode(prodName, "UTF-8") + "&" +
                    URLEncoder.encode("prodDescription", "UTF-8") + "=" +
                    URLEncoder.encode(prodDescription, "UTF-8") + "&" +
                    URLEncoder.encode("prodQty", "UTF-8") + "=" +
                    URLEncoder.encode(prodQty, "UTF-8");
            URL url = new URL(insertURL);
            Log.v("test", post_data);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                    (outputStream, "UTF-8"));
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            writeData = result;
            bufferedReader.close();
            inputStream.close();

            httpURLConnection.disconnect();

        }catch (MalformedURLException i){
            i.printStackTrace();
        }
        catch (IOException i){
            i.printStackTrace();
        }

        return result;
    }

    public void onPostExecute(String s){
        getWriteData();
    }

    public String getWriteData(){
        return writeData;
    }
    //alt + insert
}
