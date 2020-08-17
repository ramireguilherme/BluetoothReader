package com.example.bluetoothreader;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class PostHandler {
    /*
    public void accessLocation(){

        FusedLocationProviderClient client;

        client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    location.toString();
                }
            }
        }

    }
*/

    public void makePostRequest(String rawdata, Context context)
    {
        Toast.makeText(context, "I am OK !", Toast.LENGTH_LONG).show();
        String[] separateddata = rawdata.split(";");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Random rand = new Random();
                    String T1 = String.valueOf(rand.nextInt(100));
                    String T2 = String.valueOf(rand.nextInt(100));
                    String T3 = String.valueOf(rand.nextInt(100));
                    String L1 = String.valueOf(rand.nextInt(10));
                    String U1 = String.valueOf(rand.nextInt(30));
                    String BT = String.valueOf(rand.nextInt(1));
                    URL url = new URL("https://lalt.fec.unicamp.br/IoT/ ");
                    //save_data.php?id=ic&T1="+T1+"&T2="+T2+"&T3="+T3+"&L1="+L1+"&U1="+U1+"&BT="+BT);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("id","ic");
                    jsonParam.put("T1",27.1);
                    jsonParam.put("T2",27.2);
                    jsonParam.put("T3",27.3);
                    jsonParam.put("L1",70.1);
                    jsonParam.put("U1",30.3);
                    jsonParam.put("BT",1);
                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.d("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.d("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

}
