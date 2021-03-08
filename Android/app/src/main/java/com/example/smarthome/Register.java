package com.example.smarthome;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText Email, Pass, CPass;
    Button Inscription;
    ImageView btnDis;
    SqliteHelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = findViewById(R.id.Email);
        Pass = findViewById(R.id.Password);
        CPass = findViewById(R.id.CPassword);
        Inscription = findViewById(R.id.Inscrire);
        btnDis =  findViewById(R.id.Dis);

        sqliteHelper = new SqliteHelper(this);

        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(Email.getText().toString())) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, Email.getText().toString(), Pass.getText().toString()));
                        Toast.makeText(Register.this, "Utilisateur créé, veuillez vous connecter", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Toast.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Toast.makeText(Register.this, "Utilisateur existe déja!! ", Toast.LENGTH_SHORT).show();
                    }


                }
                else
                {
                    Toast.makeText(Register.this, "Entrées invalides", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        if (Email.getText().toString().isEmpty())
        {
            valid = false;
            Email.setBackgroundResource(R.drawable.rounded_corner2);
        }
        else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                valid = false;
                Email.setBackgroundResource(R.drawable.rounded_corner2);
                Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show();
            } else {
                valid = true;
                Email.setBackgroundResource(R.drawable.rounded_corner);
            }
        }


        //Handling validation for Password field
        if (Pass.getText().toString().isEmpty() || CPass.getText().toString().isEmpty()) {
            valid = false;
            Pass.setBackgroundResource(R.drawable.rounded_corner2);
            CPass.setBackgroundResource(R.drawable.rounded_corner2);
        }
        else
        {
            if (Pass.getText().toString().equals(CPass.getText().toString()))
            {
                Pass.setBackgroundResource(R.drawable.rounded_corner);
                CPass.setBackgroundResource(R.drawable.rounded_corner);
            }
            else
            {
                Pass.setBackgroundResource(R.drawable.rounded_corner2);
                CPass.setBackgroundResource(R.drawable.rounded_corner2);
                Toast.makeText(this, "Mot de passe differents", Toast.LENGTH_SHORT).show();
            }
        }

        return valid;
    }
}
