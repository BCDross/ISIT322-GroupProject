package com.hfad.rookandlochbooks.ui.review;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.hfad.rookandlochbooks.R;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}