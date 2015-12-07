package com.njinfotech.lvrexperimentremotecontrol;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hkourtev on 12/7/15.
 */
public class SendData extends AsyncTask<String,Void,String> {

    private Context context;
    boolean postFlag;

    SendData(Context context, boolean useGetNotPost) {
        this.context = context;
        postFlag = useGetNotPost;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        int action = Integer.parseInt(arg0[0]);

        //String link = "http://ruccs.rutgers.edu/~hkourtev/attila/index.php";
        String link = "http://128.6.51.112/~hkourtev/attila/index.php";
        try {
            URL url = new URL(link);
            OutputStream os;
            HashMap<String, String> postData = new HashMap<>();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);

            try {
                os = new BufferedOutputStream(connection.getOutputStream());

                switch (action) {
                    case 0:
                        // clear
                        postData.put("clear", "0");
                        break;
                    case 1:
                        // action 1
                        postData.put("action1", "1");
                        break;
                    case 2:
                        // action 2
                        postData.put("action2", "2");
                        break;
                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postData));
                writer.flush();
                writer.close();
                os.close();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // all good - do nothing
                    /*String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }*/
                } else {
                    // mistake - popup toast to let person know
                    Toast.makeText(context, "Oh oh, something went wrong", Toast.LENGTH_LONG).show();
                }
            } catch (java.io.IOException e) {
                return new String("Exception: " + e.getMessage());
            } finally {
                connection.disconnect();
            }
        } catch (java.io.IOException e) {
            return new String("Exception: " + e.getMessage());
        }

        return "";
    }


    @Override
    protected void onPostExecute(String result){

    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
