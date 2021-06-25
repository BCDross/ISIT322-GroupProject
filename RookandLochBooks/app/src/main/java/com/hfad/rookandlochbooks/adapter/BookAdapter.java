package com.hfad.rookandlochbooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfad.rookandlochbooks.R;
import com.hfad.rookandlochbooks.controller.AppContext;
import com.hfad.rookandlochbooks.data.RookLochDBOperations;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;
import com.hfad.rookandlochbooks.data.model.Book;
import com.hfad.rookandlochbooks.data.model.IndustryIdentifier;
import com.hfad.rookandlochbooks.ui.review.BookDetailActivity;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.Bookshelf> {

    private static final String TAG = BookAdapter.class.getSimpleName();
    private Context context;
    private List<Book> bookList;
    private Button btn_rvReview;
    private SQLiteDatabase db;

    public BookAdapter (Context context, List<Book> books ){
        this.context =context;
        bookList = books;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Bookshelf onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_book_list_item, parent, false);
        btn_rvReview=view.findViewById(R.id.btn_rvReview);

        return new Bookshelf(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Bookshelf holder, int position) {
        Book book = bookList.get(position);
        Log.d(getClass().getSimpleName(), "Booklist Position: " + position);

        try {
            holder.title.setText(book.getTitle().toString());
            holder.description.setText(book.getDescription().toString());
            holder.rating.setText(book.getAverageRating().toString());
            holder.author.setText(getAllAuthors(book));

            Glide.with(context)
                    .load(book.getImageLinks().getSmallThumbnail())
                    .into(holder.imageView);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title",book.getTitle());
                    bundle.putString("description",book.getDescription());
                    bundle.putInt("rating",book.getAverageRating());
                    bundle.putString("thumbnail",book.getImageLinks().getThumbnail());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            //initial SQLite DB Helder
            RookLochDatabaseHelper dbHelper = new RookLochDatabaseHelper(AppContext.getAppContext());

            //From Book list provide Assign book to list
            btn_rvReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = dbHelper.getWritableDatabase();
                    RookLochDBOperations.insertBook(db, book.getTitle(), getAllAuthors(book), book.getIndustryIdentifiers().get(1).getIdentifier().toString(),book.getId(), book.getDescription());
                }
            });
        } catch (Exception e ){
            Log.d(TAG, "onBindViewHolder: "+ e.getMessage());
            e.getStackTrace();
        }
    }
    //Added code to simplify concatenation of Author names to simplify capturing and storing Author's name
    public String getAllAuthors (Book book){
        String authors="";
        for (int j = 0; j < book.getAuthors().size(); j++) {
            if (j == 0) {
                authors += book.getAuthors().get(j).toString();
            } else {
                authors += ", " + book.getAuthors().get(j).toString();
            }
        }
        return authors;
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
    //Update the List detail
    public class Bookshelf extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,author,rating,description;
        LinearLayout linearLayout;

        //Mapping model class for the Recycler View list details
        public Bookshelf(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.book_image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            rating = itemView.findViewById(R.id.rating);
            description = itemView.findViewById(R.id.description);
            linearLayout = itemView.findViewById(R.id.root);
        }
    }
}
