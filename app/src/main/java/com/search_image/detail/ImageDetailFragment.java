package com.search_image.detail;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.search_image.ImageDetailBinding;
import com.search_image.MainActivity;
import com.search_image.base.BaseFragment;

import javax.inject.Inject;

public class ImageDetailFragment extends BaseFragment {

    private NavController navController;

    public static ImageDetailFragment newInstance() {
        Bundle args = new Bundle();

        ImageDetailFragment fragment = new ImageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    ViewModelProvider.Factory factory;
    private ImageDetailBinding imageDetailBinding;
    private ImageDetailViewModel imageDetailViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageDetailBinding = ImageDetailBinding.inflate(inflater, container, false);
        return imageDetailBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageDetailViewModel = new ViewModelProvider(this, factory).get(ImageDetailViewModel.class);
        imageDetailBinding.setViewModel(imageDetailViewModel);

        navController = NavHostFragment.findNavController(this);
        String title = "";

        if (getArguments() != null) {
            String imageUrl = getArguments().getString("imageUrl");
            String imageId = getArguments().getString("imageId");
            title = getArguments().getString("title");
            imageDetailViewModel.setImageData(imageId, imageUrl);

        }
        setupActionbar(title);
        setPostCommentButtonListener();
        setupCommentList();
        hideKeyboard(imageDetailBinding.commentEditText);
    }

    private void setupCommentList() {
        CommentsAdapter commentsAdapter = new CommentsAdapter();
        imageDetailBinding.commentsRecyclerView.setAdapter(commentsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        imageDetailBinding.commentsRecyclerView.setLayoutManager(linearLayoutManager);


        imageDetailBinding.commentsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) { // only when scrolling up
                    int visibleThreshold = 8;
                    LinearLayoutManager layoutManager = linearLayoutManager;
                    int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    int currentTotalCount = layoutManager.getItemCount();
                    Log.e("Image detail","currentTotalCount : "+currentTotalCount);
                    Log.e("Image detail","lastItem : "+lastItem);
                    Log.e("Image detail","visibleThreshold : "+visibleThreshold);
                    if (currentTotalCount <= lastItem + visibleThreshold) {
                        // load more called
                        imageDetailViewModel.getCommentsFromDb();
                    }
                }
            }
        });

        imageDetailViewModel.getComments().observe(getViewLifecycleOwner(), commentsAdapter::setComments);


    }

    private void setupActionbar(String title) {
        ((MainActivity) requireActivity()).setSupportActionBar(imageDetailBinding.toolbarDetail);
        ((MainActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) requireActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageDetailBinding.toolbarDetail.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        imageDetailBinding.toolbarDetail.setTitle(title);
    }

    private void setPostCommentButtonListener() {
        imageDetailBinding.postCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDetailViewModel.postComment(
                        imageDetailBinding.commentEditText.getText()
                                .toString()
                                .trim()
                );
                //clear comment box once it is posted
                imageDetailBinding.commentEditText.setText("");
            }
        });
    }

    public void hideKeyboard(EditText commentEditText) {
        try {
            // use application level context to avoid unnecessary leaks.
            InputMethodManager inputManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputManager != null;
            inputManager.hideSoftInputFromWindow(commentEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
