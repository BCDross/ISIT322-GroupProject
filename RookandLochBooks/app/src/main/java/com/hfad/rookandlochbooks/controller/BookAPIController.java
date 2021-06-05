package com.hfad.rookandlochbooks.controller;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hfad.rookandlochbooks.adapter.BookAdapter;
import com.hfad.rookandlochbooks.data.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BookAPIController {
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
                        JSONObject bookVolumeInfo = jsonObject.getJSONObject("volumeInfo");
                        String title= bookVolumeInfo.getString("title");
                        String description = bookVolumeInfo.getString("description");
                        // Setting default rating for missing rating values.
                        Integer rating = 3;
                        if (bookVolumeInfo.has(("averageRating"))) {
                            rating = bookVolumeInfo.getInt("averageRating");
                        }
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
                        JSONObject ImageSource = bookVolumeInfo.getJSONObject("imageLinks");
                        String thumbnails = ImageSource.getString("smallThumbnail");
                        String lrgThumbnails = ImageSource.getString("thumbnail");
                        Book book = new Book(title, authors,description,rating,thumbnails,lrgThumbnails);
                        bookList.add (book);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                //Add to recyclerView
                BookAdapter adapter = new BookAdapter(context,bookList);
                recyclerView.setAdapter(adapter);
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
