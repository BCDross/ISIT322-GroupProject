package com.hfad.rookandlochbooks.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hfad.rookandlochbooks.R;
import com.hfad.rookandlochbooks.controller.BookAPIController;
import com.hfad.rookandlochbooks.data.model.Book;
import com.hfad.rookandlochbooks.databinding.FragmentBooklistListBinding;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class BookListFragment extends Fragment {

    //binding to fragment_booklist_list Layout
    private FragmentBooklistListBinding binding;
    private RequestQueue requestQueue;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=python trick";
    private List<Book> bookList;
    // Require Empty Constructor
    public BookListFragment() {
    }

/*
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BookListFragment newInstance(int columnCount) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        binding = FragmentBooklistListBinding.inflate(inflater,container,false);
        /*View view = inflater.inflate(R.layout.fragment_booklist_list, container, false);*/

        // Set the adapter
        /*if (binding.recycleView instanceof RecyclerView) {*/
            Context context = binding.getRoot().getContext();
            RecyclerView recyclerView = (RecyclerView) binding.recycleView;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        requestQueue = BookAPIController.getmInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = BookAPIController.getBookList(url,context,recyclerView);
        requestQueue.add(jsonObjectRequest);

        /*}*/
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                SearchFragment searchFragment = new SearchFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, searchFragment);
                fragmentTransaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}