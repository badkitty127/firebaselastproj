package id.ub.filkom_195150407111011.firebaselogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<employ> list = new ArrayList<>();
    public recyadapter(Context con){
        this.context=con;
    }

    public void setItem(ArrayList<employ> emp){
        list.addAll(emp);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.layout_items,parent,false);
       return new employVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        employVH vh = (employVH) holder;
        employ emp = list.get(position);
        vh.txtname.setText(emp.getName());
        vh.txtpos.setText(emp.getPos());
        vh.txtoption.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context,vh.txtoption);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,database1.class);
                        intent.putExtra("EDIT",emp);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOEmployee dao = new DAOEmployee();
                        dao.remove(emp.getKey()).addOnCompleteListener(succ->{
                            Toast.makeText(context, "REMOVED", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(err->{
                            Toast.makeText(context, "Error: "+err, Toast.LENGTH_SHORT).show();
                        });
                        break;

                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
