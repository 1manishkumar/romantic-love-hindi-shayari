package com.Mk_Manish.Romantic_love;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Array_Adapter_Recycler extends RecyclerView.Adapter<Array_Adapter_Recycler.viewHolder> {

    Context context;
    ArrayList<Contact_Model_Row> arraycontact;
    private int lastposition = -1;
    public Array_Adapter_Recycler(Context context, ArrayList<Contact_Model_Row> arraycontact){
        this.context=context;
        this.arraycontact=arraycontact;


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.desing_row, parent, false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.shayari.setText(arraycontact.get(position).getShayari());

        holder.whatsapp.setOnClickListener(view -> {
            Intent whatsapp =new Intent(Intent.ACTION_SEND);
            whatsapp.setType("text/*");
            whatsapp.setPackage("com.whatsapp");
            whatsapp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            whatsapp.putExtra(Intent.EXTRA_TEXT,(arraycontact.get(position).getShayari()));
            context.startActivity(whatsapp);



        });


        holder.share.setOnClickListener(view -> {
            Intent shares =new Intent(Intent.ACTION_SEND);
            shares.setType("text/*");
            shares.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shares.putExtra(Intent.EXTRA_TEXT,(arraycontact.get(position).getShayari()));
            context.startActivity(shares);



        });

        holder.copy.setOnClickListener(view -> {
            ClipboardManager clipboardManager =(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData data = (ClipData) ClipData.newPlainText("text",arraycontact.get(position).getShayari());
            clipboardManager.setPrimaryClip(data);

            Toast.makeText(context, "copy", Toast.LENGTH_SHORT).show();
        });

       holder.edit.setOnClickListener(view -> {
           Intent intent = new Intent(context,EditActivity.class);
           intent.putExtra("shayari",arraycontact.get(position).getShayari());
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           context.startActivity(intent);


       });

      setanimation(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return arraycontact.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView shayari;
        ImageView whatsapp, share, copy, edit;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            shayari= itemView.findViewById(R.id.shayari);
            whatsapp=itemView.findViewById(R.id.whatsapp);
            share=itemView.findViewById(R.id.share);
            copy=itemView.findViewById(R.id.copy);
            edit=itemView.findViewById(R.id.edit);
        }
    }

    private void setanimation(View viewanimation, int position){

       if (position>lastposition){


        Animation slide= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewanimation.startAnimation(slide);
        lastposition = position;


           }

    }

}
