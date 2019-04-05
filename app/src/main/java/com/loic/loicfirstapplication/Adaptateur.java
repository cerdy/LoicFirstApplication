package com.loic.loicfirstapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptateur extends RecyclerView.Adapter<Adaptateur.MyViewHolder>{
    private ArrayList<String> speudo;
    private ArrayList<String> link;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void clickAction(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public Adaptateur(ArrayList<String> speudo,ArrayList<String> link){
        this.speudo=speudo;
        this.link=link;
    }
    public Adaptateur.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler,viewGroup,false);
        return new MyViewHolder(view);
    }
    public void onBindViewHolder(Adaptateur.MyViewHolder myViewHolder, int i) {
        myViewHolder.display(this.speudo.get(i),this.link.get(i));
    }
    public int getItemCount() {
        return this.link.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.Nom);
            image = (ImageView) itemView.findViewById(R.id.Image);
            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    int position = getAdapterPosition();
                    listener.clickAction(position);
                }
            });
        }
        public void display(String speudo,String link){
            Picasso.with(image.getContext()).load(link).resize(128,128).into(image);
            text.setText(speudo);
        }
    }
}