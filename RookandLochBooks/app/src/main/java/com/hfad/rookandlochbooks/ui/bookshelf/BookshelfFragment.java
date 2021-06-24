package com.hfad.rookandlochbooks.ui.bookshelf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.hfad.rookandlochbooks.data.model.Book;
import com.hfad.rookandlochbooks.databinding.FragmentBookshelfBinding;

import java.util.List;


public class BookshelfFragment extends Fragment {

    private BookshelfViewModel bookshelfViewModel;
    private FragmentBookshelfBinding binding;
    private RequestQueue requestQueue;
    private String url;
    private List<Book> bookList;
    // Require Empty Constructor
    public BookshelfFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
        bookshelfViewModel =
                new ViewModelProvider(this).get(BookshelfViewModel.class);

        binding = FragmentBookshelfBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView rvView = binding.rvBookShelf;
        bookshelfViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                rvView.setAdapter();;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}