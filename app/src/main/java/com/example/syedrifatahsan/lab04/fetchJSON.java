package com.example.syedrifatahsan.lab04;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchJSON extends AsyncTask<Void,Void,Void> {
    String fullData="";



    public static ArrayList<Employee> empList=new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url =new URL("https://api.myjson.com/bins/nhn9a");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null){
                line = bufferedReader.readLine();
                fullData=fullData+line;
            }
            JSONArray ja=new JSONArray(fullData);

            for(int i=0;i<ja.length();i++){

                JSONObject jo= (JSONObject) ja.get(i);
//                Log.d("amarMama",jo.toString());

                String name=jo.get("name").toString();
//                Log.d("amarMama",name);
//                Log.d("amarMama","Ekbar holo");

                String locData=jo.get("location").toString();
                Log.d("amarMama",locData);

                if(locData.equals(JSONObject.NULL)) {
                    Log.d("amarMama", locData);
                    //JSONArray loc=new JSONArray(locData);


                    JSONObject locObject = new JSONObject(locData);
//                Log.d("amarMama","ArekbarEkbar holo");
//                Log.d("amarMama",locObject.toString());

                    String loc1 = locObject.get("latitude").toString();
//                Log.d("amarMama",loc1);
//                Log.d("amarMama","Arekbar2Ekbar holo");
                    String loc2 = locObject.get("longitude").toString();
//                Log.d("amarMama",loc2);

                    empList.add(new Employee(name, loc1, loc2));
                }
                else {
                    Log.d("amarMama","loc nu holo");
                    empList.add(new Employee(name, "Null", "Null"));
                }

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

            String viewData="";
            for(int i=0;i<empList.size();i++) {

                viewData = ("Name :" + empList.get(i).name + "\n" + "-----Location-----" + "\n" + "latitude :" + empList.get(i).loc1 + "\n" + "longitude :" + empList.get(i).loc2);


                MainActivity.aListItem.add(viewData);
            }
            MainActivity.lv.setAdapter(MainActivity.aListAdapter);
        }
//        MainActivity.tv.setText(viewData);


    }

