package com.example.kentec.myapplicationmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText qty, prodName, prodDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*

           URLEncoder.encode(prodName, "UTF-8") + "&" +
                    URLEncoder.encode("prodDescription", "UTF-8") + "=" +
                    URLEncoder.encode(prodDescription, "UTF-8") + "&" +
                    URLEncoder.encode("prodQty", "UTF-8") + "=" +
                    URLEncoder.encode(prodQty, "UTF-8");
         */


    }

    public void viewData(View view){
        Intent start = new Intent(view.getContext(), ViewInfo.class);
        startActivity(start);
    }

    public void addInfo(View view){
        qty = (EditText) findViewById(R.id.editTextQty);
        prodName = (EditText) findViewById(R.id.editTextProdName);
        prodDescription = (EditText) findViewById(R.id.editTextDescription);
        BackgroundWorker b = new BackgroundWorker(this);
        b.execute(prodName.getText().toString(), prodDescription.getText().toString(), qty.getText().toString());
        b.onPostExecute("test");
        

    }
}
