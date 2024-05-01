package com.example.czcionka;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSizeLabel, textViewQuote, textViewProperties;
    private SeekBar fontSizeSeekBar;
    private Button changeQuoteButton;

    private String[] quotes = {"Dzień dobry", "Good morning", "Buenos dias"};
    private int currentQuoteIndex = 0;

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


        textViewProperties = findViewById(R.id.textViewProperties);
        textViewSizeLabel = findViewById(R.id.textViewSizeLabel);
        textViewQuote = findViewById(R.id.textViewQuote);
        fontSizeSeekBar = findViewById(R.id.fontSizeSeekBar);
        changeQuoteButton = findViewById(R.id.changeQuoteButton);

        textViewQuote.setTextSize(fontSizeSeekBar.getProgress());

        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewQuote.setTextSize(progress);
                textViewSizeLabel.setText("Rozmiar: " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        changeQuoteButton.setOnClickListener(v -> {
            // Changing displayed quote to the next one in the array
            currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
            textViewQuote.setText(quotes[currentQuoteIndex]);
        });
    }
}