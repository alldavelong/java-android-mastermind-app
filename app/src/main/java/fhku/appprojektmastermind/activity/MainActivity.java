package fhku.appprojektmastermind.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

import fhku.appprojektmastermind.R;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_start = findViewById(R.id.btn_start);
        btn_guide = findViewById(R.id.btn_guide);


        btn_start.setOnClickListener(view -> showCustomDialog());


        btn_guide.setOnClickListener(view ->{
            Intent intent = new Intent(view.getContext(), GuideActivity.class);
            startActivity(intent);
        });
    }

    // displaying the difficulty Dialog
    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_difficulty);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_kids = dialog.findViewById(R.id.btn_difficulty_kids);
        Button btn_easy = dialog.findViewById(R.id.btn_difficulty_easy);
        Button btn_hard = dialog.findViewById(R.id.btn_difficulty_hard);
        Button btn_master = dialog.findViewById(R.id.btn_difficulty_master);

        btn_kids.setEnabled(false);
        btn_easy.setEnabled(true);
        btn_hard.setEnabled(false);
        btn_master.setEnabled(false);

        btn_kids.setOnClickListener(view -> {
            //  TODO: Intent Kids Mode
            openGameTemporaryHackForTesting();
        });
        btn_easy.setOnClickListener(view -> {
            //  TODO: Intent Easy Mode
            openGameTemporaryHackForTesting();
        });
        btn_hard.setOnClickListener(view -> {
            //  TODO: Intent Hard Mode
            openGameTemporaryHackForTesting();
        });
        btn_master.setOnClickListener(view -> {
            //  TODO: Intent Master Mode
            openGameTemporaryHackForTesting();
        });

        dialog.show();
    }

    private void openGameTemporaryHackForTesting() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}