package com.ualr.recyclerviewassignment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter {

//  Created a new Adapter class and the corresponding ViewHolder class in a different file. The adapter will be used to populate
//  the recyclerView and manage the interaction with the items in the list

    private List<Inbox> mItems;
    private Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Inbox obj, int position);
    }

    public AdapterList(Context context, List<Inbox> items) {
        this.mItems = items;
        this.mContext = context;
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.mListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lyInflater = LayoutInflater.from(parent.getContext());
        View itemView = lyInflater.inflate(R.layout.inbox_single_line_layout, parent, false);
        RecyclerView.ViewHolder vh = new InboxViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InboxViewHolder inboxViewHolder = (InboxViewHolder) holder;
        Inbox i = mItems.get(position);

        inboxViewHolder.name.setText(i.getFrom());
        inboxViewHolder.address.setText(i.getEmail());
        inboxViewHolder.date.setText(i.getDate());
        inboxViewHolder.message.setText(i.getMessage());

        if (i.isSelected()) {
            inboxViewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.grey_10));
        }
        else {
            inboxViewHolder.lyt_parent.setBackgroundColor((mContext.getResources().getColor(R.color.overlay_light_0)));
        }
    }

    public void toggleItemState(int position) {
        this.mItems.get(position).toggleSelection();
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    private class InboxViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView address;
        public TextView date;
        public TextView letter;
        public TextView message;
        public View lyt_parent;

        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail_image);
            name = itemView.findViewById(R.id.sender_name);
            address = itemView.findViewById(R.id.sender_address);
            date = itemView.findViewById(R.id.the_date);
            letter = itemView.findViewById(R.id.thumbnail_letter);
            message = itemView.findViewById(R.id.message_body);
            lyt_parent = itemView.findViewById(R.id.inbox_single_parent);

            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });

        }
    }

}
