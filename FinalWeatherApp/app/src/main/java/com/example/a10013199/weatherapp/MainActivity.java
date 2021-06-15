package com.example.a10013199.weatherapp;

import android.graphics.Color;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    URL url;
    URLConnection urlConnection;
    InputStream inputStream;
    String zipCode;
    ArrayList<JSONObject> jsonArr=new ArrayList<>();
    ArrayList<ImageView> images=new ArrayList<>();
    ArrayList<TextView> textArr=new ArrayList<>();
    ArrayList<String> quotes=new ArrayList<>();
    JSONObject place = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        final EditText editText=findViewById(R.id.editText);
        final ImageView pic1=findViewById(R.id.imageView);
        final ImageView pic2=findViewById(R.id.imageView2);
        final ImageView pic3=findViewById(R.id.imageView3);
        final ImageView pic4=findViewById(R.id.imageView4);
        final ImageView pic5=findViewById(R.id.imageView5);
        final ImageView pic6=findViewById(R.id.imageView6);
        final TextView textView=findViewById(R.id.textView);
        TextView wScenario2=findViewById(R.id.textView2);
        TextView wScenario3=findViewById(R.id.textView3);
        TextView wScenario4=findViewById(R.id.textView4);
        TextView wScenario5=findViewById(R.id.textView5);
        TextView wScenario6=findViewById(R.id.textView6);
        TextView wScenario7=findViewById(R.id.textView7);
        TextView wScenario8=findViewById(R.id.textView8);
        TextView wScenario9=findViewById(R.id.textView9);
        TextView low10=findViewById(R.id.textView10);
        final TextView areaName=findViewById(R.id.textView11);
        TextView low12=findViewById(R.id.textView12);
        TextView low13=findViewById(R.id.textView13);
        TextView low14=findViewById(R.id.textView14);
        TextView low15=findViewById(R.id.textView15);
        TextView high16=findViewById(R.id.textView16);
        TextView high17=findViewById(R.id.textView17);
        TextView high18=findViewById(R.id.textView18);
        TextView high19=findViewById(R.id.textView19);
        TextView high20=findViewById(R.id.textView20);
        TextView high21=findViewById(R.id.textView21);
        final TextView currentTemp=findViewById(R.id.textView23);
        textArr.add(textView);
        textArr.add(wScenario2);
        textArr.add(wScenario3);
        textArr.add(wScenario4);
        textArr.add(wScenario5);
        textArr.add(wScenario6);
        textArr.add(wScenario7);
        textArr.add(wScenario8);
        textArr.add(wScenario9);
        textArr.add(low10);
        textArr.add(low12);
        textArr.add(low13);
        textArr.add(low14);
        textArr.add(low15);
        textArr.add(high16);
        textArr.add(high17);
        textArr.add(high18);
        textArr.add(high19);
        textArr.add(high20);
        textArr.add(high21);
        images.add(pic1);
        images.add(pic2);
        images.add(pic3);
        images.add(pic4);
        images.add(pic5);
        images.add(pic6);
        button.setBackgroundColor(Color.YELLOW);
        button.setTextColor(Color.RED);
        editText.setTextColor(Color.GREEN);
        RelativeLayout layout=findViewById(R.id.layout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipCode=editText.getText().toString();
                AsyncThread thread=new AsyncThread();
                try {
                    thread.execute().get();
                } catch (Exception e) {

                }
                for(int x=0;x<jsonArr.size();x++) {

                    try {
                        if (Integer.parseInt(jsonArr.get(x).getString("dt_txt").substring(11, 13)) - 5 > 0) {
                            int hourTime = (Integer.parseInt(jsonArr.get(x).getString("dt_txt").substring(11, 13))) - 5;
                            //set picture to day time
                            if (hourTime > 12) {
                                hourTime -= 12;
                                //set picture to night time
                            }
                            textArr.get(x).setText(hourTime + ":00");
                        } else {
                            int hourTime = (Integer.parseInt(jsonArr.get(x).getString("dt_txt").substring(11, 13))) + 19;
                            textArr.get(x).setText(hourTime + ":00");
                        }
                        areaName.setText("City: " + place.getString("name"));
                        currentTemp.setText("Current Temp: " + Math.round(((9 / 5) * (Double.parseDouble(jsonArr.get(0).getJSONObject("main").getString("temp")) - 273) + 32) * 100.0) / 100.0 + "°F");
                        textArr.get(x + 10).setText("Low: " + Math.round(((9 / 5) * (Double.parseDouble(jsonArr.get(x).getJSONObject("main").getString("temp_min")) - 273) + 32) * 100.0) / 100.0 + "");
                        textArr.get(x + 15).setText("High: " + Math.round(((9 / 5) * (Double.parseDouble(jsonArr.get(x).getJSONObject("main").getString("temp_max")) - 273) + 32) * 100.0) / 100.0 + "");
                        textArr.get(x + 5).setText(jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main"));

                           if (x == 0) {
                                if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {

                                    // imageView2.setImageResource(R.drawable.clear);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {

                                    //   imageView2.setImageResource(R.drawable.cloudy);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {


                                    // imageView2.setImageResource(R.drawable.rain);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {

                                    // imageView2.setImageResource(R.drawable.snow);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunder")) {

                                    //  imageView2.setImageResource(R.drawable.thunder);
                                }
                            }
                           if (x == 1) {
                                if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                                    //   imageView3.setImageResource(R.drawable.clear);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                                    //  imageView3.setImageResource(R.drawable.cloudy);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                                    // imageView3.setImageResource(R.drawable.rain);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                                    // imageView3.setImageResource(R.drawable.snow);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunder")) {
                                    // imageView3.setImageResource(R.drawable.thunder);
                                }
                            }
                            if (x == 2) {
                                if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                                    // imageView4.setImageResource(R.drawable.clear);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                                    //imageView4.setImageResource(R.drawable.cloudy);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                                    // imageView4.setImageResource(R.drawable.rain);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                                    //   imageView4.setImageResource(R.drawable.snow);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunder")) {
                                    // imageView4.setImageResource(R.drawable.thunder);
                                }
                            }
                            if (x == 3) {
                                if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                                    // imageView5.setImageResource(R.drawable.clear);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                                    // imageView5.setImageResource(R.drawable.cloudy);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                                    // imageView5.setImageResource(R.drawable.rain);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                                    // imageView5.setImageResource(R.drawable.snow);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunder")) {
                                    //imageView5.setImageResource(R.drawable.thunder);
                                }
                            }
                            if (x == 4) {
                                if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clear")) {
                                    // imageView6.setImageResource(R.drawable.clear);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Clouds")) {
                                    //imageView6.setImageResource(R.drawable.cloudy);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Rain")) {
                                    //  imageView6.setImageResource(R.drawable.rain);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Snow")) {
                                    // imageView6.setImageResource(R.drawable.snow);
                                } else if (jsonArr.get(x).getJSONArray("weather").getJSONObject(0).getString("main").equals("Thunder")) {
                                    // imageView6.setImageResource(R.drawable.thunder);
                                }
                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }

                }
            }
        });
    }
    public class AsyncThread extends AsyncTask<Void,Void,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
        @Override
        protected String doInBackground(Void... voids) {
            JSONArray array=new JSONArray();
            JSONObject weatherObj=new JSONObject();
            JSONObject holder=new JSONObject();
            jsonArr=new ArrayList<>();
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/forecast?zip="+zipCode+"&appid=fc796ebc0c12b27e705347fcf4720cd1");
                urlConnection = url.openConnection();
                inputStream = urlConnection.getInputStream();
            }catch (Exception e){
                e.printStackTrace();
            }
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            try {
                String str;
                while((str=bufferedReader.readLine())!=null){
                    weatherObj = new JSONObject(str);
                    place=(weatherObj.getJSONObject("city"));

                    array=(weatherObj.getJSONArray("list"));
                    for(int x=0;x<=4;x++){
                        holder=array.getJSONObject(x);
                        jsonArr.add(holder);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
