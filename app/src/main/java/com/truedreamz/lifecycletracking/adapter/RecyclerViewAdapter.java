package com.truedreamz.lifecycletracking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truedreamz.lifecycletracking.R;
import com.truedreamz.lifecycletracking.db.PersonModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<PersonModel> personModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<PersonModel> borrowModelList, View.OnLongClickListener longClickListener) {
        this.personModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        PersonModel personModel = personModelList.get(position);
        holder.itemTextView.setText(personModel.getItemName());
        holder.nameTextView.setText(personModel.getPersonName());
        holder.dateTextView.setText(personModel.getAddedDate().toLocaleString().substring(0, 11));
        holder.itemView.setTag(personModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return personModelList.size();
    }

    public void addItems(List<PersonModel> borrowModelList) {
        this.personModelList = borrowModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
            dateTextView = view.findViewById(R.id.dateTextView);
        }
    }
}