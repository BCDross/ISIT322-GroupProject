package com.hfad.rookandlochbooks.ui.review;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.hfad.rookandlochbooks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.hfad.rookandlochbooks.data.RookLochDBOperations;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;
import com.hfad.rookandlochbooks.data.session.SessionManager;
import com.hfad.rookandlochbooks.databinding.FragmentCreateReviewBinding;

public class CreateReviewFragment extends Fragment {

    private SQLiteDatabase db;
    private CreateReviewViewModel createReviewViewModel;
    private FragmentCreateReviewBinding binding;
    private Button btnSubmit;
    private Button btnCancel;

    public static CreateReviewFragment newInstance() {
        return new CreateReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RookLochDatabaseHelper dbHelper = new RookLochDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        String UserID = SessionManager.getUserToken(this);
        final int userID = Integer.parseInt(UserID);

        container.removeAllViews();
        createReviewViewModel = new ViewModelProvider(this).get(CreateReviewViewModel.class);
        binding = FragmentCreateReviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

        btnSubmit = binding.buttonSubmit;
        btnCancel = binding.buttonCancel;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFragment(); }
            // Verify that the text boxes have text in them.
            String newReviewTitle = binding.editTextTextReviewTitle.getText().toString();
            String newReviewDescription = binding.editTextTextReviewDescription.getText().toString();

            private void openFragment() {
                if (binding.editTextTextReviewTitle.getText().toString().isEmpty() && binding.editTextTextReviewDescription.getText().toString().isEmpty()) {
                    binding.editTextTextReviewTitle.setError("Please enter review Title.");
                    binding.editTextTextReviewDescription.setError("Please enter review description.");
                    return;
                } else if (binding.editTextTextReviewTitle.getText().toString().isEmpty() || binding.editTextTextReviewDescription.getText().toString().isEmpty()) {
                    if (binding.editTextTextReviewTitle.getText().toString().isEmpty()) {
                        binding.editTextTextReviewTitle.setError("Please enter review Title.");
                        return;
                    } else {
                        binding.editTextTextReviewDescription.setError("Please enter review description.");
                    }
                }

                // Save review to the database now.
                RookLochDBOperations dbOperations = new RookLochDBOperations();
                dbOperations.insertReview(db, newReviewTitle, newReviewDescription, userID);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createReviewViewModel = new ViewModelProvider(this).get(CreateReviewViewModel.class);
        // TODO: Use the ViewModel
    }

}