package id.ub.filkom_195150407111011.firebaselogin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class employVH extends RecyclerView.ViewHolder {
    public TextView txtname,txtpos,txtoption;
    public employVH(@NonNull View itemview){
        super(itemview);
        txtname = itemview.findViewById(R.id.nameidtx);
        txtpos = itemview.findViewById(R.id.postxtv);
        txtoption = itemview.findViewById(R.id.txt_option);
    }
}
