package com.example.kentec.myapplicationmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewInfo extends AppCompatActivity {

    String id;
    EditText prodId;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;

    ArrayList<Product> products;
    private RecyclerView.Adapter mysqlAdapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getData(View view){
        prodId = findViewById(R.id.editTextProdId);
        id = prodId.getText().toString();
        CALL_DATA();
    }

    public void CALL_DATA(){
        String JSON_URL = "https://unruffled-seals.000webhostapp.com/getproducts.php";
        JSON_URL += "?id=" + id;
        Log.v("testingid", JSON_URL);
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                JSON_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        PARSE_DATA(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("network_error", error.getNetworkTimeMs() + "");
                    }
                }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void PARSE_DATA(JSONArray array){
        for(int i = 0; i < array.length(); i++){
            Product product = new Product();
            JSONObject json = null;
            try{
                json = array.getJSONObject(i);
                product.setName(json.getString("prodName"));
                product.setDescription(json.getString("prodDescription"));
                product.setId(Integer.parseInt(id));
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            products.add(product);
        }
        mysqlAdapter = new RecycleViewProduct(products, this, id);
        recyclerView.setAdapter(mysqlAdapter);
    }

}
