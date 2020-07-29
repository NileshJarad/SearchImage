package com.search_image.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.search_image.CommentItemBinding;
import com.search_image.domain.search.CommentResponse;

import java.util.ArrayList;
import java.util.List;

class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private List<CommentResponse> comments = new ArrayList<>();

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CommentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        holder.bind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(List<CommentResponse> commentResponses) {
        comments.clear();
        comments.addAll(commentResponses);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CommentItemBinding commentItemBinding;

        public ViewHolder(CommentItemBinding commentItemBinding) {
            super(commentItemBinding.getRoot());
            this.commentItemBinding = commentItemBinding;
        }

        public void bind(CommentResponse commentResponse) {
            commentItemBinding.setComment(commentResponse);
            commentItemBinding.executePendingBindings();
        }
    }
}
