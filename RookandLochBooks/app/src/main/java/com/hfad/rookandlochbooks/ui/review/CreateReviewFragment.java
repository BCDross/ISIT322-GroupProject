package com.hfad.rookandlochbooks.ui.review;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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

import com.hfad.rookandlochbooks.MainActivity;
import com.hfad.rookandlochbooks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.rookandlochbooks.data.RookLochDBOperations;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;
import com.hfad.rookandlochbooks.data.session.SessionManager;
import com.hfad.rookandlochbooks.databinding.FragmentCreateReviewBinding;
import com.hfad.rookandlochbooks.ui.home.HomeFragment;
import com.hfad.rookandlochbooks.ui.home.HomeViewModel;
import com.hfad.rookandlochbooks.ui.search.BookListFragment;
import com.hfad.rookandlochbooks.ui.search.SearchFragment;

public class CreateReviewFragment extends Fragment {

    private SQLiteDatabase db;
    private CreateReviewViewModel createReviewViewModel;
    private FragmentCreateReviewBinding binding;
    private Button btnSubmit;
    private Button btnCancel;

    public CreateReviewFragment() {}

    public static CreateReviewFragment newInstance() {
        return new CreateReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //open the bundle - WHATS INSIDE??? OH BOY
        Bundle bundle = getArguments();
        String bTitle = bundle.getString("bTitle");
        String bDescription = bundle.getString("bDescription");
        Integer bAverageRating = bundle.getInt("bRating");
        String bThumbnail = bundle.getString("bThumbnail");

        //database access stuffs
        RookLochDatabaseHelper dbHelper = new RookLochDatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        String UserID = SessionManager.getUserToken(getContext());
        final int userID = Integer.parseInt(UserID);

        //clear the page, laddy
        container.removeAllViews();

        createReviewViewModel = new ViewModelProvider(this).get(CreateReviewViewModel.class);
        binding = FragmentCreateReviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

        btnSubmit = binding.buttonSubmit2;  //for testing purposes it has been changed to "2" in all locations
        btnCancel = binding.buttonCancel;

        TextView textViewRating = binding.textViewHeading;
        textViewRating.setText("Create a Review for \"" + bTitle + "\"");


        //set a listener on the submit button
        Button reviewButton = (Button) root.findViewById(R.id.buttonSubmit2); //for testing purposes, old method of view finding has been inserted
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFragment(); }

            //press submit button on the page, and you get allllll this for free
            private void openFragment() {
                //read from the text boxes on the page
                String newReviewTitle = binding.editTextTextReviewTitle.getText().toString();
                String newReviewDescription = binding.editTextTextReviewDescription.getText().toString();

                // Verify that the text boxes have text in them.
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


                //check if submission is successful
                Cursor checkSubmission = db.rawQuery("SELECT Description, Title, UserID " +
                                "FROM Review " +
                                "WHERE Description = ? " +
                                "AND Title = ? " +
                                "AND UserID = ?",
                        new String[]{newReviewDescription, newReviewTitle, String.valueOf(userID)});


                //if submission was successful, toast and send back to....
                if (checkSubmission.getCount() == 1) {

                    Context context = getContext();
                    CharSequence text =  "Review Successfully Submitted!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    //pass the book details to the review so they can be passed back
                    Bundle bundle = new Bundle();
                    bundle.putString("bTitle", bTitle);
                    bundle.putString("bDescription", bDescription);
                    bundle.putInt("bAverageRating", bAverageRating);
                    bundle.putString("bThumbnail", bThumbnail);
                    SearchFragment destination = new SearchFragment();  //TODO: WHERE ?? set destination here

                    destination.setArguments(bundle);
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.CreateReview, destination, "bookDetailActivity")
                            .setReorderingAllowed(true)
                            .addToBackStack(null) // name can be null
                            .commit();

                }

                //if not success, toast and do nothing
                else {
                    Context context = getContext();
                    CharSequence text =  "Review Failed!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });


        //set listener on cancel button
        Button cancelButton = (Button) root.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment3();
            }

            private void openFragment3() {
                SearchFragment destination = new SearchFragment();  //TODO: WHERE ?? set destination here

                destination.setArguments(bundle);
                supportFragmentManager.beginTransaction()
                        .replace(R.id.CreateReview, destination, "bookDetailActivity")
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // name can be null
                        .commit();
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