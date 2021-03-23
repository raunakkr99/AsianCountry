package com.example.asia1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.asia1.AdaptersCollect.AdapterCountryAsia;
import com.example.asia1.ModelClasses.ModelAsiaCountry;
import com.example.asia1.R;
import com.example.asia1.Room.DatabaseClient;
import com.example.asia1.Room.Information;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryAsiaInfoActivity extends AppCompatActivity {


    TextView txtAsiaCountry;
    RecyclerView AsiaCountryDetailsRecyclerView;
    AdapterCountryAsia adapterCountryAsia;
    ArrayList<ModelAsiaCountry>modelAsiaCountryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_asia_info);

        AsiaCountryDetailsRecyclerView=findViewById(R.id.AsiaCountryDetailsRecyclerView);
        txtAsiaCountry=findViewById(R.id.txtAsiaCountry);

        modelAsiaCountryArrayList=new ArrayList<>();
        modelAsiaCountryArrayList.clear();

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            fetchAsiaCountryInformation();
        } else {
            fetchFromRoomTheData();
        }

    }

    private void fetchFromRoomTheData() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<Information> infoListNew = DatabaseClient.getInstance(CountryAsiaInfoActivity.this).getAppDatabase().countryAsiaDao().getAllNew();
                modelAsiaCountryArrayList.clear();
                for (Information info: infoListNew) {
                    ModelAsiaCountry repo = new ModelAsiaCountry(
                            ""+info.getName(),
                            ""+info.getCapital(),
                            ""+info.getRegion(),
                            ""+info.getSubregion(),
                            ""+info.getPopulation(),
                            ""+info.getFlag(),
                            ""+info.getBorder(),
                            ""+info.getLanguages()
                    );
                    modelAsiaCountryArrayList.add(repo);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterCountryAsia=new AdapterCountryAsia(CountryAsiaInfoActivity.this,modelAsiaCountryArrayList);
                        AsiaCountryDetailsRecyclerView.setAdapter(adapterCountryAsia);
                    }
                });
            }
        });
        thread.start();

    }


    private void fetchAsiaCountryInformation() {

        String url="https://restcountries.eu/rest/v2/region/asia";

        StringRequest stringRequestNews=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){

                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String onetxt = jsonObject.getString("name");
                            String twotxt = jsonObject.getString("capital");
                            String threetxt = jsonObject.getString("region");
                            String fourtxt = jsonObject.getString("subregion");
                            String fivetxt = jsonObject.getString("population");
                            String sixtxt = jsonObject.getString("flag");
                            String jsonArray1=jsonObject.getString("borders");
                            String border="";
                            for(int p=0;p<jsonArray1.length();p++)
                                border=border+jsonArray1.charAt(p);

                            String Languages="";
                            JSONArray jsonArray2=jsonObject.getJSONArray("languages");
                            for(int u=0;u<jsonArray2.length();u++){
                                JSONObject jsonObject1=jsonArray2.getJSONObject(u);
                                if(u==0) {
                                    Languages = Languages + "" + jsonObject1.getString("name");
                                }
                                else{
                                    Languages = Languages + "," + jsonObject1.getString("name");
                                }
                            }
                            ModelAsiaCountry modelAsiaCountry = new ModelAsiaCountry(
                                    ""+onetxt,
                                    ""+twotxt,
                                    ""+threetxt,
                                    ""+fourtxt,
                                    ""+fivetxt,
                                    ""+sixtxt,
                                    ""+border,
                                    ""+Languages
                            );
                            modelAsiaCountryArrayList.add(modelAsiaCountry);

                        }catch (Exception e){
                            Toast.makeText(CountryAsiaInfoActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    adapterCountryAsia=new AdapterCountryAsia(CountryAsiaInfoActivity.this,modelAsiaCountryArrayList);
                    AsiaCountryDetailsRecyclerView.setAdapter(adapterCountryAsia);
                    adapterCountryAsia.notifyDataSetChanged();
                    saveTask();
                }
                catch (Exception e){
                    Toast.makeText(CountryAsiaInfoActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountryAsiaInfoActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(CountryAsiaInfoActivity.this);
        requestQueue.add(stringRequestNews);


    }

    private void saveTask() {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task

                for (int i = 0; i < modelAsiaCountryArrayList.size(); i++) {
                    Information recipe= new Information();
                    recipe.setName(modelAsiaCountryArrayList.get(i).getName());
                    recipe.setCapital(modelAsiaCountryArrayList.get(i).getCapital());
                    recipe.setSubregion(modelAsiaCountryArrayList.get(i).getSubregion());
                    recipe.setRegion(modelAsiaCountryArrayList.get(i).getRegion());
                    recipe.setPopulation(modelAsiaCountryArrayList.get(i).getPopulation());
                    recipe.setFlag(modelAsiaCountryArrayList.get(i).getFlag());
                    recipe.setLanguages(modelAsiaCountryArrayList.get(i).getLanguages());
                    recipe.setBorder(modelAsiaCountryArrayList.get(i).getBorder());
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().countryAsiaDao().insert(recipe);
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), " Information Saved Successfully To Room Database", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


}