package com.hfad.rookandlochbooks.ui.review;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.hfad.rookandlochbooks.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book_details);

        ImageView imageViewBook =findViewById(R.id.imageViewBooks);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewRating = findViewById(R.id.textViewRating);
        TextView textViewDescription = findViewById(R.id.textViewDescription);

        Bundle bundle = getIntent().getExtras();
        String bTitle = bundle.getString("title");
        String bDescription = bundle.getString("description");
        Integer bAverageRating = bundle.getInt("rating");
        String bThumbnail = bundle.getString("thumbnail");


        Glide.with(this).load(bThumbnail).into(imageViewBook);
        textViewTitle.setText(bTitle.toString());
        textViewRating.setText(bAverageRating.toString());
        textViewDescription.setText(bDescription.toString());



        //going to BookCreateReviewFragment...
        FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        Button reviewButton = (Button) this.findViewById(R.id.btn_review);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment2();
            }

            int userID = 69; //gets the session userID to pass it through (test) this line is basically nonsense, its very late and im out of tea

            //this is clever, I like it. makes a bundle of goodies.
            private void openFragment2() {
                Bundle bundle = new Bundle();
                bundle.putInt("userID",userID); //(name:value) - saves to the bundle and is referencable on receiving side by: bundle.getInt("userID", whereSaveVarName); i think...
                //anything else you want to put in the bundle to pass to this next fragment? put it here
                CreateReviewFragment destination = new CreateReviewFragment();
                destination.setArguments(bundle);
                supportFragmentManager.beginTransaction()
                        .replace(R.id.bookDetails2, destination, "something_here")
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // name can be null
                        .commit();
            }
        });

    }


}