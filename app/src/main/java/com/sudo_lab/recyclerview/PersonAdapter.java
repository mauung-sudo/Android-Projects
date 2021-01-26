package com.sudo_lab.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {

    Context context;
    ArrayList<Person> personArrayList = new ArrayList<>();
    ArrayList<Person> deleted = new ArrayList<>();

    public PersonAdapter(Context context, ArrayList<Person> personArrayList) {
        this.context = context;
        this.personArrayList = personArrayList;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {

        Person person = personArrayList.get(position);
        holder.setName(person.getName());
        holder.setAge(person.getAge());
        holder.setJob(person.getJob());
        holder.setImage(person.getImage());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleted.add(person);
                personArrayList.remove(person);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount());
            }
        });

    }

    @Override
    public int getItemCount() {

        return personArrayList == null ? 0: personArrayList.size() ;
    }

    public class PersonHolder extends RecyclerView.ViewHolder{
        TextView name ,age ,job;
        ImageView image ;
        Button delete ;

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setAge(String age) {
            this.age.setText(age);
        }

        public void setJob(String job) {
            this.job.setText(job);
        }

        public void setImage(int image) {
            this.image.setImageResource(image);
        }

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
             name = itemView.findViewById(R.id.name);
             age = itemView.findViewById(R.id.age);
             job = itemView.findViewById(R.id.job);
             image = itemView.findViewById(R.id.itemImage);
             delete = itemView.findViewById(R.id.button);

        }
    }

}
