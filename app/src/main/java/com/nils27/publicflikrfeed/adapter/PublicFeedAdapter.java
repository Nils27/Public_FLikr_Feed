package com.nils27.publicflikrfeed.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nils27.publicflikrfeed.R;
import com.nils27.publicflikrfeed.databinding.ViewholderMainBinding;
import com.nils27.publicflikrfeed.model.Item;

import java.util.List;

public class PublicFeedAdapter extends RecyclerView.Adapter<PublicFeedAdapter.FeedImages_ViewHolder>{
    private static final String TAG = PublicFeedAdapter.class.getSimpleName();

    private Context mContext;
    private List<Item> mItems;

    final private ImageOnClickHandler mClickHandler;

    public interface ImageOnClickHandler {
        void onImageClick(int position, String title);
    }

    //constructor
    public PublicFeedAdapter(Context mContext, List<Item> mItems, ImageOnClickHandler clickHandler) {
        this.mContext = mContext;
        this.mItems = mItems;
        this.mClickHandler = clickHandler;
    }


    @NonNull
    @Override
    public FeedImages_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.mContext = viewGroup.getContext();
        ViewholderMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.viewholder_main, viewGroup, false);

        return new FeedImages_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedImages_ViewHolder holder, int i) {
        holder.binding.setItemData(mItems.get(i));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        }
        return mItems.size();
    }

    public void changeItemList(List<Item> itemsNew) {
        Log.i(TAG, "changeItemsList: New Items List Provided: pre change");
        mItems = itemsNew;
        notifyDataSetChanged();
        Log.i(TAG, "changeItemsList: list should have updated");
    }


    public class FeedImages_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ViewholderMainBinding binding;

        FeedImages_ViewHolder(ViewholderMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick: Image clicked - " + binding.tvTitle + " was clicked");
            mClickHandler.onImageClick(getAdapterPosition(), binding.tvTitle.getText().toString());
        }

    }

}
