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
import com.hfad.rookandlochbooks.ui.login.LoginFragment;
import com.hfad.rookandlochbooks.ui.search.SearchFragment;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book_details);

        ImageView imageViewBook =findViewById(R.id.imageViewBooks);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewRating = findViewById(R.id.textViewRating);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        Button btnReview = findViewById(R.id.btn_review);

        Bundle bundle = getIntent().getExtras();
        String bTitle = bundle.getString("title");
        String bDescription = bundle.getString("description");
        Integer bAverageRating = bundle.getInt("rating");
        String bThumbnail = bundle.getString("thumbnail");


        Glide.with(this).load(bThumbnail).into(imageViewBook);
        textViewTitle.setText(bTitle.toString());
        textViewRating.setText(bAverageRating.toString());
        textViewDescription.setText(bDescription.toString());

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                supportFragmentManager.beginTransaction()
                        .replace(R.id.bookdetail, new CreateReviewFragment(), "Fragment_Book_TAG")
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // name can be null
                        .commit();

            }
        });
    }
}



