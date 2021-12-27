package id.ub.filkom_195150407111011.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView email,password;
    FirebaseAuth firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.buttonlogin);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        firebase = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                login(email1,password1);

            }
        });


    }

  public void login(String email, String password){
        firebase.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Welcome",4).show();
                    Intent intent = new Intent(MainActivity.this,database1.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"AuthFailed: No such ID",4).show();
                }
            }
        });
  }

}