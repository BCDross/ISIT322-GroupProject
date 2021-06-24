package com.hfad.rookandlochbooks.ui.review;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateReviewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CreateReviewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Create Review page fragment");
    }

    public LiveData<String> getText() { return mText; }
}