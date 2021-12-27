package id.ub.filkom_195150407111011.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class database1 extends AppCompatActivity {
    Button btnsub,btnopen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database1);
        btnsub = findViewById(R.id.submit_btn);
        btnopen = findViewById(R.id.btn_open);
        //btnupdate = findViewById(R.id.btn_update);
        final EditText edtname= findViewById(R.id.edit_name);
        final EditText edtpos=findViewById(R.id.edit_pos);
        btnopen.setOnClickListener( view -> {
            Intent intent = new Intent(database1.this,REact22.class);
            startActivity(intent);
        });
        DAOEmployee dao = new DAOEmployee();
        employ emp_edit=(employ)getIntent().getSerializableExtra("EDIT");

        if(emp_edit!=null){
            btnsub.setText("UPDATE");
            edtname.setText(emp_edit.getName());
            edtpos.setText(emp_edit.getPos());
            btnopen.setVisibility(View.GONE);

        } else {
            btnsub.setText("SUBMIT");
            btnopen.setVisibility(View.VISIBLE);
        }

        btnsub.setOnClickListener(v ->
        {
            employ emp = new employ(edtname.getText().toString(),edtpos.getText().toString());
            if(emp_edit==null) {
                dao.add(emp).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "RECORDED", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "Error " + er, Toast.LENGTH_SHORT).show();
                });
            } else{
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",edtname.getText().toString());
                hashMap.put("pos",edtpos.getText().toString());
                dao.update(emp_edit.getKey(),hashMap).addOnCompleteListener(success->{
                    Toast.makeText(database1.this, "UPDATED", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(err->{
                    Toast.makeText(database1.this, "Error: "+err, Toast.LENGTH_SHORT).show();
                });
                /*dao.remove(emp_edit.getKey()).addOnCompleteListener(succ->{
                    Toast.makeText(database1.this, "REMOVED", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(err->{
                    Toast.makeText(database1.this, "Error: "+err, Toast.LENGTH_SHORT).show();
                });*/

            }
        });
       /* btnupdate.setOnClickListener(view -> {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("name",edtname.getText().toString());
            hashMap.put("pos",edtpos.getText().toString());
            dao.update("-Mpljl47pfRavgjv_Fky",hashMap).addOnCompleteListener(success->{
                    Toast.makeText(database1.this, "UPDATED", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(err->{
                    Toast.makeText(database1.this, "Error: "+err, Toast.LENGTH_SHORT).show();
                });
            dao.remove("-Mpljp9H_zixaa1Tux5W").addOnCompleteListener(succ->{
                Toast.makeText(database1.this, "REMOVED", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(err->{
                Toast.makeText(database1.this, "Error: "+err, Toast.LENGTH_SHORT).show();
            });

            }); */


        }

}
