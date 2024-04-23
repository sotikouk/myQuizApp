package com.example.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.thequizapp.databinding.ActivityMainBinding;
import com.example.thequizapp.model.Question;
import com.example.thequizapp.model.QuestionList;
import com.example.thequizapp.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<Question> questionList;

    static int result = 0;
    static int totalQuestions = 0;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Data binding
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        // Resetting the scores
        result = 0;
        totalQuestions = 0;

        // Creating an instance of QuizViewModel
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        // Displaying the first question
        displayFirstQuestion();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNextQuestion();
            }
        });
    }

    private void displayNextQuestion() {
        // Direct the user to the Results activity
        if (binding.btnNext.getText().equals("Finish")){
            Intent i = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(i);
            finish();
        }
        // Displaying the question
        int selectedOption = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedOption != -1){
            RadioButton radioButton = findViewById(selectedOption);

            // More Questions to Display??
            if ((questionList.size() -i) > 0){

                // Getting the number of questions
                totalQuestions = questionList.size();

                // Check if the chosen option is correct
                if(radioButton.getText().toString().equals(
                        questionList.get(i).getCorrectOption()
                )){
                    result++;
                    binding.txtResult.setText(
                            "Correct Answers: "+result
                    );
                }

                if (i ==0){
                    i++;
                }

                // Displaying the next Questions
                binding.txtQuestion.setText("Question "+(i+1)+ " : "+
                        questionList.get(i).getQuestion());

                binding.radio1.setText(questionList.get(i).getOption1());
                binding.radio2.setText(questionList.get(i).getOption2());
                binding.radio3.setText(questionList.get(i).getOption3());
                binding.radio4.setText(questionList.get(i).getOption4());


                // Check if it is the last question
                if(i == (questionList.size() -1)){
                    binding.btnNext.setText("Finish");
                }

                binding.radioGroup.clearCheck();
                i++;


            }else{
                if (radioButton.getText().toString().equals(
                        questionList.get(i-1).getCorrectOption()
                )){
                    result++;
                    binding.txtResult.setText("Correct Answers : "+result);
                }

            }
        }else{
            Toast.makeText(
                    this,
                    "You need to make a selection",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void displayFirstQuestion() {
        // Observing LiveData from a ViewModel
        quizViewModel.getQuestionListLiveData().observe(this,
                new Observer<QuestionList>() { // Observer = what to do when LiveData changes
                    @Override
                    public void onChanged(QuestionList questions) {
                        // Called when the data inside LiveData changes
                        questionList = questions;

                        binding.txtQuestion.setText("Question 1: "+ questions.get(0).getQuestion());
                        binding.radio1.setText(questions.get(0).getOption1());
                        binding.radio2.setText(questions.get(0).getOption2());
                        binding.radio3.setText(questions.get(0).getOption3());
                        binding.radio4.setText(questions.get(0).getOption4());

                    }
                });
    }
}