package com.dieznote.internshipprovectus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Item> notesList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView note;
        TextView dot;
        TextView timestamp;

        MyViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.note);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }


    NotesAdapter(Context context, List<Item> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item note = notesList.get(position);

        holder.note.setText(note.results.get(0).name.title+". "+Character.toUpperCase(note.results.get(0).name.first.charAt(0))+note.results.get(0).name.first.substring(1).toLowerCase()+" "+Character.toUpperCase(note.results.get(0).name.last.charAt(0))+note.results.get(0).name.last.substring(1).toLowerCase());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(note.results.get(0).email);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}

