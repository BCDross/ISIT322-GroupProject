package com.hfad.rookandlochbooks.ui.search;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.rookandlochbooks.R;
import com.hfad.rookandlochbooks.databinding.FragmentSearchBinding;
import com.hfad.rookandlochbooks.ui.sensor.CameraFragment;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private Button btnSearch;
    private String srcQuery;
    private EditText srcText;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

        btnSearch = binding.btnSearchDetail;

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }

            private void openFragment() {
                //initiate fragment to open.
                if (binding.searchTitleField.getText().toString().isEmpty()) {
                    binding.searchTitleField.setError("Please enter search Title Detail");
                    return;
                 } else {
                    srcQuery = urlBuilder (binding.searchTitleField.getText().toString());
                }

                Bundle bundle = new Bundle();
                bundle.putString("fedEx",srcQuery);
                BookListFragment destination = new BookListFragment();
                destination.setArguments(bundle);
                supportFragmentManager.beginTransaction()
                        .replace(R.id.searchContraint, destination, "Fragment_TAG")
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // name can be null
                        .commit();
            }

            // added method to simplify URL setup.
            final String urlBuilder (String srcQuery){
                /*
                intitle: Returns results where the text following this keyword is found in the title.
                inauthor: Returns results where the text following this keyword is found in the author.
                inpublisher: Returns results where the text following this keyword is found in the publisher.
                subject: Returns results where the text following this keyword is listed in the category list of the volume.
                */
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.googleapis.com")
                        .appendPath("books")
                        .appendPath("v1")
                        .appendPath("volumes")
                        .appendQueryParameter("q", srcQuery)
                        .appendQueryParameter("orderby", "relevance")
                        .appendQueryParameter("maxResults","40");
                return srcQuery = builder.build().toString();
            }
/*
        final TextView textView = binding.textSearch;
        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }


https://www.googleapis.com/books/v1/volumes?q=python trick
*/
        });
        final Button cameraButton = binding.btnScanBarcode;
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraFragment cameraFragment = new CameraFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, cameraFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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