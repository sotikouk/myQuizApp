package com.example.thequizapp.retrofit;

import com.example.thequizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {
    // Used to define the structure and behaviour of
    // network requests to a REST API.
    // Acts as a bridge between the application and the web service

    @GET("quiz")
    Call<QuestionList> getQuestions();
}
