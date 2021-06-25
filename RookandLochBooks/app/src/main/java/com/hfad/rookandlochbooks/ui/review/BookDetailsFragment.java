package com.hfad.rookandlochbooks.ui.review;

import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.hfad.rookandlochbooks.R;
import com.hfad.rookandlochbooks.databinding.FragmentSearchBinding;
import com.hfad.rookandlochbooks.ui.search.BookListFragment;
import com.hfad.rookandlochbooks.ui.search.SearchViewModel;

public class BookDetailsFragment extends Fragment {

    private BookDetailsViewModel mViewModel;

    public static BookDetailsFragment newInstance() {
        return new BookDetailsFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        container.removeAllViews();

        View view = inflater.inflate(R.layout.fragment_book_details, container, false);


        //has this fragment has been abandoned?



        //this code was supposed to implement a button to go from a book's details to the review page

//        Button reviewButton = (Button) view.findViewById(R.id.btn_review);
//        reviewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//
//                fragmentManager.beginTransaction()
//                        .replace(R.id.btn_review, CreateReviewFragment.class,null)
//                        .setReorderingAllowed(true)
//                        .addToBackStack("backStack1") // name can be null
//                        .commit();
//            }
//        });
//
//
//        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
//        reviewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFragment();
//            }
//
//            int userID = 69; //gets the session userID to pass it through (test)
//
//            private void openFragment() {
//                Bundle bundle = new Bundle();
//                bundle.putInt("userID",userID); //name, value - saves to the bundle and is referencable on receiving side by: bundle.getInt("userID", whereSaveVarName);
//                //anything else you want to put in the bundle to pass to this next fragment? put it here
//                CreateReviewFragment destination = new CreateReviewFragment();
//                destination.setArguments(bundle);
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.bookDetails, destination, "Fragment_TAG")
//                        .setReorderingAllowed(true)
//                        .addToBackStack(null) // name can be null
//                        .commit();
//            }
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookDetailsViewModel.class);
        // TODO: Use the ViewModel
    }



}