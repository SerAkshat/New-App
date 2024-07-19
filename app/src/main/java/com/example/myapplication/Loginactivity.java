package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Loginactivity extends AppCompatActivity {

    EditText email,password;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email=(EditText) findViewById(R.id.signinemail);
        password=(EditText) findViewById(R.id.signinpassword);
        signin=(Button) findViewById(R.id.signinbtn);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                String emaili = email.getText().toString();
                String passwordi = password.getText().toString();
                if(emaili.equals("")||passwordi.equals(""))
                    Toast.makeText(Loginactivity.this,"Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkpass=DB.checklogin(emaili,passwordi);
                    if(checkpass==true)
                    {
                        Toast.makeText(Loginactivity.this,"Logged In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("email", emaili);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Loginactivity.this,"Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }


            }

        });
    }
}