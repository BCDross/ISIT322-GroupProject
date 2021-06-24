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

        //get the page elements
        ImageView imageViewBook =findViewById(R.id.imageViewBooks);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewRating = findViewById(R.id.textViewRating);
        TextView textViewDescription = findViewById(R.id.textViewDescription);

        //open the bundle - WHATS INSIDE??? OH BOY
        Bundle bundle = getIntent().getExtras();
        String bTitle = bundle.getString("title");
        String bDescription = bundle.getString("description");
        Integer bAverageRating = bundle.getInt("rating");
        String bThumbnail = bundle.getString("thumbnail");

        //put book details on the page
        Glide.with(this).load(bThumbnail).into(imageViewBook);
        textViewTitle.setText(bTitle.toString());
        textViewRating.setText("Rating: " + bAverageRating.toString() + "/5");
        textViewDescription.setText(bDescription.toString());


        //going to BookCreateReviewFragment...
        FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        Button reviewButton = (Button) this.findViewById(R.id.btn_review);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment2();
            }

            //when you press the button to go make a new review
            private void openFragment2() {
                Bundle bundle = new Bundle();
                //pass the book details to the review so they can be passed back
                bundle.putString("bTitle", bTitle);
                bundle.putString("bDescription", bDescription);
                bundle.putInt("bAverageRating", bAverageRating);
                bundle.putString("bThumbnail", bThumbnail);
                CreateReviewFragment destination = new CreateReviewFragment();
                destination.setArguments(bundle);
                supportFragmentManager.beginTransaction()
                        .replace(R.id.bookDetails2, destination, "createBookReviewTag")
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // name can be null
                        .commit();
            }
        });

    }


}