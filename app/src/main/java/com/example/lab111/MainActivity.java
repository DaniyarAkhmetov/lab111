package com.example.lab111;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView TextView;
    TextView AttemptView;
    int attempts = 0;
    final int maxAttempts = 3;  // Corrected variable name to match convention

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

        editText = findViewById(R.id.eText);
        button = findViewById(R.id.btn);
        TextView = findViewById(R.id.tResult);
        AttemptView = findViewById(R.id.pAttempts);

        Random random = new Random();
        int secretKey = random.nextInt(20) + 1;
        Log.i("Result", secretKey + "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();

                try {
                    int intValue = Integer.parseInt(value);
                    attempts++;

                    if (intValue == secretKey) {
                        TextView.setText("You win!");
                        Toast.makeText(getApplicationContext(), "Correct! You guessed the right number.", Toast.LENGTH_LONG).show();
                        button.setEnabled(false);
                    } else {
                        TextView.setText("You lost!");

                        if (intValue < secretKey) {
                            Toast.makeText(getApplicationContext(), "Try higher number", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Try lower number", Toast.LENGTH_LONG).show();
                        }
                        AttemptView.setText("Attempts: " + attempts + "/" + maxAttempts); // Corrected here

                        if (attempts >= maxAttempts) {
                            TextView.setText("Game Over! You have reached maximum attempts.");
                            Toast.makeText(getApplicationContext(), "No more attempts, the correct number was " + secretKey, Toast.LENGTH_LONG).show();
                            button.setEnabled(false);
                        }
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter a valid integer.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
