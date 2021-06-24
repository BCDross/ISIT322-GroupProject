package com.hfad.rookandlochbooks.ui.review;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.rookandlochbooks.R;
import com.hfad.rookandlochbooks.ui.search.SearchFragment;

public class CreateReviewFragment extends Fragment {

    private CreateReviewViewModel mViewModel;

    public static CreateReviewFragment newInstance() {
        return new CreateReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_create_review, container, false);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateReviewViewModel.class);
        // TODO: Use the ViewModel

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                SearchFragment searchFragment = new SearchFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.CreateReview, searchFragment);
                fragmentTransaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}