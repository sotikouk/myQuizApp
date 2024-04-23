package com.example.thequizapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.thequizapp.model.QuestionList;
import com.example.thequizapp.retrofit.QuestionsAPI;
import com.example.thequizapp.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    // Interacts with the API service Interfaces
    // Handling data retrieval and operations
    private QuestionsAPI questionsAPI;

    // Constructor
    public QuizRepository() {
        this.questionsAPI = new RetrofitInstance() // Instance of RetrofitInstance
                .getRetrofitInstance() // getting the configured instance (Base URL)
                .create(QuestionsAPI.class); // Implementation of the QuestionsAPI interface
    }

    // LiveData
    public LiveData<QuestionList> getQuestionsFromAPI() {
        // MutableLiveData to hold and observe data changes
        MutableLiveData<QuestionList> data = new MutableLiveData<>();
        Call<QuestionList> response = questionsAPI.getQuestions();
        // enqueue() method because it runs in the background
        // and its not using the main thread as excecute() does.
        // enqueue() is async. excecute() is sync method.
        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                QuestionList list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable throwable) {

            }
        });

        return data;
    }
}
