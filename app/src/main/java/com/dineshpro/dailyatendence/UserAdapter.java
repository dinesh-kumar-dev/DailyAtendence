package com.dineshpro.dailyatendence;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dineshpro.dailyatendence.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements AdapterView.OnItemLongClickListener {
    Context context;
    List<User> userList;
    Activity activity;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    holder.textViewName.setText(userList.get(position).getName());
    holder.textViewContact.setText(userList.get(position).getContact());
    holder.textViewDate.setText(userList.get(position).getDate());
    holder.textViewTime.setText(userList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        User user=userList.get(i);
        showDiglog(user.getId(),user.getName(),user.getContact(),user.getDate(),user.getTime());
        return false;
    }

    private void showDiglog(final String id, String name, String contact,  String date, String time) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

       View view=activity.getLayoutInflater().inflate(R.layout.popup_layout,null);





        final EditText editTextPopName=(EditText)view.findViewById(R.id.popTextViewName);
        final EditText editTextPopContact=(EditText)view.findViewById(R.id.popTextViewContact);
        final EditText editTextPopDate=(EditText)view.findViewById(R.id.popTextViewDate);
        final EditText editTextPopTime=(EditText)view.findViewById(R.id.popTextViewTime);
        Button update=(Button)view.findViewById(R.id.buttonUpdate);
        Button delete=(Button)view.findViewById(R.id.buttonDelete);

        editTextPopName.setText(name);
        editTextPopContact.setText(contact);
        editTextPopDate.setText(date);
        editTextPopTime.setText(time);
        dialogBuilder.setView(view);
        dialogBuilder.setTitle("Updating User");
        AlertDialog dialog=dialogBuilder.create();
        dialog.show();

//update code
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name=editTextPopName.getText().toString().trim();
                String contact=editTextPopContact.getText().toString().trim();
                String date=editTextPopDate.getText().toString().trim();
                String time=editTextPopTime.getText().toString().trim();
                updateUser(id,name,contact,date,time);

            }
        });
//delete code
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return; 
    }

    private boolean updateUser(String id, String name, String contact, String date, String time) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("user").child(id);

        //updating artist
        User user = new User(id, name, contact,date,time);
        dR.setValue(user);
        Toast.makeText(context, "Artist Updated", Toast.LENGTH_LONG).show();
        return true;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,textViewContact,textViewDate,textViewTime;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            textViewName=(TextView)itemView.findViewById(R.id.textViewName);
            textViewContact=(TextView)itemView.findViewById(R.id.textViewContact);
            textViewDate=(TextView)itemView.findViewById(R.id.textViewDate);
            textViewTime=(TextView)itemView.findViewById(R.id.textViewTime);
        }
    }
}
