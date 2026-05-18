package com.example.tp4_persist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp4_persist.model.AccesLocal;
import com.example.tp4_persist.model.Employe;
import com.example.tp4_persist.model.Serialiser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    AccesLocal accesLocal;
    EditText et_nom, et_prenom, et_age, et_fonc, et_cin, et_doti;
    Button bt_newemp,bt_deser;
    String fname="empdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        recupData();
        bt_newemp.setOnClickListener(v -> {
            Employe emp = new Employe(et_nom.getText().toString(),et_prenom.getText().toString(),et_cin.getText().toString(),et_fonc.getText().toString(),Integer.parseInt(et_age.getText().toString()),Integer.parseInt(et_doti.getText().toString()));
            Serialiser obj_ser = new Serialiser();
            try{
                obj_ser.serialise(fname,emp,MainActivity.this);
            }catch (IOException e){
                Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
            }

        });
        bt_deser.setOnClickListener(v -> recupData());
    }

    private void init(){
        et_nom = findViewById(R.id.et_nom);
        et_prenom = findViewById(R.id.et_prenom);
        et_age = findViewById(R.id.et_age);
        et_cin = findViewById(R.id.et_cin);
        et_fonc = findViewById(R.id.et_fonc);
        et_doti = findViewById(R.id.et_doti);
        bt_newemp = findViewById(R.id.bt_newemp);
        bt_deser = findViewById(R.id.bt_deser);
        accesLocal = new AccesLocal(this);
    }


    private void recupData(){
        Serialiser obj_ser = new Serialiser();
        Employe emp=null;
        try {
            emp = (Employe) obj_ser.deSerialise(fname, this);

        }catch (IOException e){
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
        }
        if (emp != null) {
            et_nom.setText(emp.getNom());
            et_prenom.setText(emp.getPrenom());
            et_age.setText(String.valueOf(emp.getAge()));
            et_fonc.setText(emp.getFonction());
            et_cin.setText(emp.getCin());
            et_doti.setText(String.valueOf(emp.getDoti()));
        }
    }


}