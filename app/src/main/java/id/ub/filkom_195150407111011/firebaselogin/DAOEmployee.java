package id.ub.filkom_195150407111011.firebaselogin;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmployee
{
    private DatabaseReference dbfr;
    public DAOEmployee(){

        FirebaseDatabase fbdb=FirebaseDatabase.getInstance();
        dbfr = fbdb.getReference(employ.class.getSimpleName());

    }
    public Task<Void> add(employ emp1){

       return dbfr.push().setValue(emp1);
    }

    public Task<Void> update(String key, HashMap<String, Object> hash){
      return  dbfr.child(key).updateChildren(hash);

    }

    public Task<Void> remove(String key){
        return dbfr.child(key).removeValue();
    }

    public Query get(String key){
        if(key==null){
            return dbfr.orderByKey().limitToFirst(8);
        }
        return dbfr.orderByKey().startAfter(key).limitToFirst(8);
    }
}
