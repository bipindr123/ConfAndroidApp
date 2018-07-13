package com.example.bipin.conftest6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {
    EditText subjectInput, FeedbackInput;
    Button sendFeedback;
    String to, subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        to = "bipindr123@gmail.com";
        subjectInput = (EditText) findViewById(R.id.subjectInput);
        FeedbackInput = (EditText) findViewById(R.id.FeedbackInput);

        sendFeedback = (Button) findViewById(R.id.sendFeedback);

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject = "Android App FeedBack: " + subjectInput.getText().toString();
                message = FeedbackInput.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose Email client :"));
            }
        });
    }
}
