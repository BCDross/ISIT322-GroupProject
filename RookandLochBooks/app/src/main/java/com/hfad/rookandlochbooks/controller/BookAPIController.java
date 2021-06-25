package com.hfad.rookandlochbooks.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hfad.rookandlochbooks.adapter.BookAdapter;
import com.hfad.rookandlochbooks.data.model.Book;
import com.hfad.rookandlochbooks.data.model.IndustryIdentifier;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BookAPIController {
    private static final String TAG = BookAPIController.class.getSimpleName() ;
    private RequestQueue requestQueue;
    private static BookAPIController mInstance;
    private static List<Book> bookList;
    private BookAPIController(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized BookAPIController getmInstance (Context context){
        if (mInstance == null){
            mInstance = new BookAPIController(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
    
    public static JsonObjectRequest getBookList (String url, Context context, RecyclerView recyclerView){

        bookList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonItems = response.getJSONArray("items");
                    // Loop through the array elements
                    for(int i=0;i<jsonItems.length();i++){
                        // Get current json object
                        JSONObject jsonObject = jsonItems.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        JSONObject bookVolumeInfo = jsonObject.getJSONObject("volumeInfo");
                        String title= bookVolumeInfo.getString("title");
                        String description = (bookVolumeInfo.has("description")) ? description = bookVolumeInfo.getString("description"): "None";
                        // Setting default rating for missing rating values.
                        Integer rating = (bookVolumeInfo.has(("averageRating"))) ? rating = bookVolumeInfo.getInt("averageRating"): 3;

                        List<String> authors =new ArrayList<String>();
                        if (bookVolumeInfo.has("authors")) {
                            // Extract the JSONArray for the key called "authors"
                            JSONArray authorsArray = bookVolumeInfo.getJSONArray("authors");
                            for (int j = 0; j < authorsArray.length(); j++) {
                                authors.add(authorsArray.getString(j));
                            }
                        } else {
                            authors.add ("Unknown");
                        }


                        List<IndustryIdentifier> ISBN =new ArrayList<>();
                        if (bookVolumeInfo.has("industryIdentifiers")) {
                            // Extract the JSONArray for the key called "authors"
                            JSONArray ISBNArray = bookVolumeInfo.getJSONArray("industryIdentifiers");
                            for (int j = 0; j < ISBNArray.length(); j++) {
                                IndustryIdentifier d = new IndustryIdentifier();
                                JSONObject _jsonObject = ISBNArray.getJSONObject(j);
                                d.setType(_jsonObject.getString("type")) ;
                                d.setIdentifier(_jsonObject.getString("identifier"));
                                ISBN.add(d);
                            }
                        }
                        JSONObject ImageSource = bookVolumeInfo.getJSONObject("imageLinks");
                        String thumbnails = ImageSource.getString("smallThumbnail");
                        String lrgThumbnails = ImageSource.getString("thumbnail");
                        Book book = new Book(title, authors,description,rating,thumbnails,lrgThumbnails,id,ISBN);
                        bookList.add (book);
                    }
                }catch (JSONException e){
                    Log.d(TAG, "onResponse:  " + e.getMessage());
                    e.printStackTrace();
                }
                //Add to recyclerView
                BookAdapter adapter = new BookAdapter(context,bookList);
                recyclerView.setAdapter(adapter);
                //pagination();
            }

            private void pagination() {
                //Added support for pagenation but didn't complete full implementation.     Save for anotehr day.
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(context.getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return jsonObjectRequest;
        
        
    }
}
