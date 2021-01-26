package com.sudo_lab.recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
PersonAdapter personAdapter;

ArrayList<Person> list = new ArrayList<>();
String[] names = new String[]{"KK","MM","TT","JJ"};
String[] ages = new String[]{"age1","age2","age3","age4"};
String[] jobs = new String[]{"job 1","job 2","job 3","job 4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton add = findViewById(R.id.add);
        FloatingActionButton restore = findViewById(R.id.restoreAll);
        FloatingActionButton undo = findViewById(R.id.undo);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        personAdapter = new PersonAdapter(getApplicationContext(),list);

        recyclerView.setAdapter(personAdapter);
            for(int i=0; i < names.length; i++){
                personAdapter.personArrayList.add(new Person(names[i],ages[i],jobs[i]));
                personAdapter.notifyDataSetChanged();
//                personAdapter.notifyItemInserted(personAdapter.getItemCount() - 1);
//                personAdapter.notifyItemRangeChanged(personAdapter.getItemCount()-1,personAdapter.getItemCount());
            }

            undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int size = personAdapter.deleted.size();
                    System.out.println(size);
                    if(size == 0){
                        Snackbar snackbar =  Snackbar.make(v,"Items not yet deleted",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                    else {
                        int index = size - 1;
                        Person person = personAdapter.deleted.get(index);
                        personAdapter.personArrayList.add(person);
                        personAdapter.deleted.remove(index);
                        personAdapter.notifyDataSetChanged();

                    }
//                    personAdapter.notifyItemInserted(personAdapter.getItemCount() - 1);
//                    personAdapter.notifyItemRangeChanged(personAdapter.getItemCount() - 1,personAdapter.getItemCount());
                }
            });

            restore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = personAdapter.getItemCount();
                    personAdapter.personArrayList.addAll(personAdapter.deleted);
                    personAdapter.deleted.clear();
                    personAdapter.notifyDataSetChanged();
//                    personAdapter.notifyItemRangeInserted(pos,personAdapter.getItemCount());
//                    personAdapter.notifyItemRangeChanged(pos,personAdapter.getItemCount());
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPerson();
                }
            });



    }

    public void addPerson(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        EditText name = new EditText(MainActivity.this);
        EditText age = new EditText(MainActivity.this);
        EditText job = new EditText(MainActivity.this);
        name.setHint("Enter name here");
        age.setHint("Enter age here");
        job.setHint("Enter job here");
        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(name);
        linearLayout.addView(age);
        linearLayout.addView(job);

        builder.setTitle("Add a new Person");
        builder.setView(linearLayout);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                personAdapter.personArrayList.add(new Person(name.getText().toString(),age.getText().toString(),job.getText().toString()));
                personAdapter.notifyDataSetChanged();
            }
        });

     builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
         }
     });

     builder.show();

    }

}