package com.search_image.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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
        imageDetailViewModel = new ViewModelProvider(requireActivity(), factory).get(ImageDetailViewModel.class);
        imageDetailBinding.setViewModel(imageDetailViewModel);

        navController = NavHostFragment.findNavController(this);
        String title = "";

        if (getArguments() != null) {
            String imageUrl = getArguments().getString("imageUrl");
            String imageId = getArguments().getString("imageId");
            title = getArguments().getString("title");

            imageDetailViewModel.setImageData(imageId, imageUrl);
        }

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

        hideKeyboard(imageDetailBinding.commentEditText);
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
