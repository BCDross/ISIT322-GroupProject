package com.hfad.rookandlochbooks.ui.review;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hfad.rookandlochbooks.R;
import com.hfad.rookandlochbooks.databinding.FragmentCreateReviewBinding;

public class CreateReviewFragment extends Fragment {

    private CreateReviewViewModel createReviewViewModel;
    private FragmentCreateReviewBinding binding;
    private Button btnSubmit;
    private Button btnCancel;
    private String strTitle;
    private String strDescription;
    private EditText strText;

    public static CreateReviewFragment newInstance() {
        return new CreateReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        createReviewViewModel = new ViewModelProvider(this).get(CreateReviewViewModel.class);



        View view = inflater.inflate(R.layout.fragment_book_details, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createReviewViewModel = new ViewModelProvider(this).get(CreateReviewViewModel.class);
        // TODO: Use the ViewModel
    }

}