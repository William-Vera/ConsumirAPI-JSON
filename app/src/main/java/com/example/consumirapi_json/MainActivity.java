package com.example.consumirapi_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    TextView txtInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();

        WebService ws = new
                WebService("https://dummyjson.com/users",
                datos, MainActivity.this,MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        txtInformacion=(TextView)findViewById(R.id.txtInfo);
        Log.i("datos", (result));
        String lstUsers="";
        JSONObject jObject= new JSONObject(result);
        JSONArray JSONlista = jObject.getJSONArray("users");
        //JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++) {
            JSONObject Users = JSONlista.getJSONObject(i);
            lstUsers = lstUsers + "id: " + Users.getString("id").toString() + "\n" +
                    "Nombre: " + Users.getString("firstName").toString() +"\n"+
                    "Edad: " + Users.getString("age").toString()+ "\n"+
                    "Correo: "+ Users.getString("email").toString()+ "\n";
        }

        if(lstUsers!="" || lstUsers!=null)
        {
            txtInformacion.setText("Lista de Usuarios \n" + lstUsers);
        }else
        {
            Toast.makeText(this.getApplicationContext(),
                    "No existe información para mostrar.",
                    Toast.LENGTH_LONG).show();
        }

    }
}