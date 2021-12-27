package id.ub.filkom_195150407111011.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class REact22 extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    recyadapter adapter;
    DAOEmployee dao;
    boolean isloading = false;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react22);
        swipeRefreshLayout = findViewById(R.id.swipe1);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(REact22.this);
        recyclerView.setLayoutManager(manager);
        adapter = new recyadapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOEmployee();
        loaddata();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalitem = layoutManager.getItemCount();
                int lastvisible = layoutManager.findLastCompletelyVisibleItemPosition();
                if(totalitem<lastvisible+3){
                    if(!isloading){
                        isloading=true;
                        loaddata();
                    }
                }
            }
        });
    }

    private void loaddata(){
        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<employ> emps = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren()){
                   employ emp =  data.getValue(employ.class);
                   emp.setKey(data.getKey());
                   emps.add(emp);
                   key = data.getKey();
                   isloading=false;
                }
                adapter.setItem(emps);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}