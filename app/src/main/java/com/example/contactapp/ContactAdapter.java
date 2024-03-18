package com.example.contactapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contactLists;

    public void setFilteredList(ArrayList<Contact> filteredList) {
        this.contactLists = filteredList;
        notifyDataSetChanged();
    }

    public ContactAdapter(ArrayList<Contact> contactLists) {
        this.contactLists = contactLists;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.TvName.setText(contactLists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contactLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;
        public TextView TvName;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            TvName = view.findViewById(R.id.tv_name);
        }

    }
}
