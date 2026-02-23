package com.example.phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText phoneInput;
    private Button dialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements ko initialize kar rahe hain
        phoneInput = findViewById(R.id.phoneInput);
        dialButton = findViewById(R.id.dialButton);

        // Button click event listener set karna
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber();
            }
        });
    }

    private void dialPhoneNumber() {
        // User se phone number ka input lena
        String phoneNumber = phoneInput.getText().toString();

        // Agar phone number khali hai toh error dikhaye
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Agar phone number 'tel:' se start nahi hota toh usko prefix karna
        if (!phoneNumber.startsWith("tel:")) {
            phoneNumber = "tel:" + phoneNumber;
        }

        // Dial intent create karna
        Uri phoneUri = Uri.parse(phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);

        // Check karna ki koi dialer app available hai ya nahi
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No dialer app found", Toast.LENGTH_SHORT).show();
        }
    }
}