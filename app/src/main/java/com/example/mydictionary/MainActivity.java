package com.example.mydictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "dictionary.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onSearch(View view) {
        EditText word = findViewById(R.id.word);
        String theword = word.getText().toString();
        String definition = findDefention (theword);

        TextView thedef = findViewById(R.id.def);

        if (definition!=null)
            thedef.setText(definition);
        else
            thedef.setText("Word not found");

    }

    private String findDefention (String theword) {
        InputStream input = getResources().openRawResource(R.raw.dictionary);

        Scanner scan = new Scanner(input);

        while (scan.hasNext())
        {
            String line = scan.nextLine();

            String [] pieces = line.split("=");

            if (pieces [0].equalsIgnoreCase(theword.trim())){
                return pieces [1];
            }
        }
        return null;

    }
    public void Insert (View v) {
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            String text = "dictionary.txt";
            fos.write(text.getBytes());


            Toast.makeText(this, "Inserted to" + getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
