package com.hfad.rookandlochbooks.ui.search;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.rookandlochbooks.placeholder.PlaceholderContent.PlaceholderItem;
import com.hfad.rookandlochbooks.databinding.FragmentBooklistListBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBookListRecyclerViewAdapter extends RecyclerView.Adapter<MyBookListRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public MyBookListRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       return new ViewHolder(FragmentBooklistListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
/*        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RecyclerView mIdView;
        public final RecyclerView mContentView;
        public PlaceholderItem mItem;







        public ViewHolder(FragmentBooklistListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.getRoot();
            mContentView = binding.recycleView;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "rrrr" + "'";
        }
    }
}