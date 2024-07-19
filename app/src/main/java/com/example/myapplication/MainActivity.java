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

public class MainActivity extends AppCompatActivity {

    EditText name,email,password;
    Button signin,signup;
    DBHelper DB;

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

        name=(EditText) findViewById(R.id.fullname);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        signin=(Button) findViewById(R.id.userbtn);
        signup=(Button) findViewById(R.id.signupbtn);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {

                String namei = name.getText().toString();
                String emaili = email.getText().toString();
                String passwordi = password.getText().toString();
                if(namei.equals("")||emaili.equals("")||passwordi.equals(""))
                    Toast.makeText(MainActivity.this,"Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkemail(emaili);
                    if(checkuser==false)
                    {
                        Boolean insert=DB.insertData(namei,emaili,passwordi);
                        if(insert==true) {
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            intent.putExtra("email", emaili);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"User Already Exist", Toast.LENGTH_SHORT).show();
                    }

                }


            }

        });

        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),Loginactivity.class);
                startActivity(intent);

            }

        });




    }
}