package com.reactnativecommunity.viewpager;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReactPageAdapter extends RecyclerView.Adapter<ReactPageAdapter.ReactAdapterViewHolder> {

    private ArrayList<View> children = new ArrayList<>();

    @NonNull
    @Override
    public ReactAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FrameLayout rootView = new FrameLayout(parent.getContext());
        rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return new ReactAdapterViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReactAdapterViewHolder holder, int position) {
        holder.bind(position);
    }

    public void addChild(View children, int index) {
        this.children.add(index, children);
        notifyDataSetChanged();

    }

    @Override
    public long getItemId(int position) {
        if (children.get(position) != null) {
            return children.get(position).hashCode();
        }
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public View getChildAt(int index) {
        return children.get(index);
    }

    class ReactAdapterViewHolder extends RecyclerView.ViewHolder {

        public ReactAdapterViewHolder(@NonNull FrameLayout itemView) {
            super(itemView);
        }

        public void bind(int position) {
            View reactNativeView = children.get(position);
            FrameLayout rootview = (FrameLayout) itemView;
            Log.d("ReactPageAdapter", "before ChildCount : " + rootview.getChildCount());
            if (reactNativeView.getParent() != null) {
                Log.d("ReactPageAdapter", "bind: " + reactNativeView.toString());
            } else {
                rootview.addView(reactNativeView);
                if (rootview.getChildCount() > 1) {
                    rootview.removeViewAt(0);
                }
                Log.d("ReactPageAdapter", "after ChildCount : " + rootview.getChildCount());
            }
        }

    }
}
