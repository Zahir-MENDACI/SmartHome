package com.example.smarthome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.se.omapi.Session;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText Email, Pass;
    Button Connecter, Inscription;
    ImageView btnDis;
    SqliteHelper sqliteHelper;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.Email);
        Pass = findViewById(R.id.Password);
        Connecter = findViewById(R.id.Connecter);
        Inscription = findViewById(R.id.Inscription);

        sqliteHelper = new SqliteHelper(this);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        Intent intent = new Intent(this, MainActivity.class);

        if(pref.contains("Email") && pref.contains("password")){
            startActivity(intent);
        }

        Inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        Connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Check user input is correct or not
                if (validate()) {


                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, Email.getText().toString(), Pass.getText().toString()));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Toast.makeText(Login.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("Email", Email.getText().toString());
                        editor.putString("password", Pass.getText().toString());
                        editor.commit();

                        startActivity(intent);

                        //User Logged in Successfully Launch You home screen activity
                       /* Intent intent=new Intent(LoginActivity.this,HomeScreenActivity.class);
                        startActivity(intent);
                        finish();*/
                    } else {

                        //User Logged in Failed
                        Toast.makeText(Login.this, "Failed to log in , please try again", Toast.LENGTH_SHORT).show();

                    }
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
                Toast.makeText(this, "Email invalide", Toast.LENGTH_LONG).show();
            } else {
                valid = true;
                Email.setBackgroundResource(R.drawable.rounded_corner);
            }
        }


        //Handling validation for Password field
        if (Pass.getText().toString().isEmpty()) {
            valid = false;
            Pass.setBackgroundResource(R.drawable.rounded_corner2);
        }
        else
        {
            valid = true;
            Pass.setBackgroundResource(R.drawable.rounded_corner);
        }

        return valid;
    }
}
