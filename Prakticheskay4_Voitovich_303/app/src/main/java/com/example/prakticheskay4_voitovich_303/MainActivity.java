package com.example.prakticheskay4_voitovich_303;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean Error = false;
    Button btCalculate;
    CheckBox[] checkBoxes = new CheckBox[4];
    EditText[] editTextsPrice = new EditText[4];
    EditText[] editTextsCount = new EditText[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        checkBoxes[0] = findViewById(R.id.checkBoxApple);
        checkBoxes[1] = findViewById(R.id.checkBoxStrawberry);
        checkBoxes[2] = findViewById(R.id.checkBoxBlueberry);
        checkBoxes[3] = findViewById(R.id.checkBoxPotatoes);
        editTextsPrice[0] = findViewById(R.id.editTextApple);
        editTextsPrice[1] = findViewById(R.id.editTextStrawberry);
        editTextsPrice[2] = findViewById(R.id.editTextBlueberry);
        editTextsPrice[3] = findViewById(R.id.editTextPotatoes);
        editTextsCount[0] = findViewById(R.id.editTextAppleCount);
        editTextsCount[1] = findViewById(R.id.editTextStrawberryCount);
        editTextsCount[2] = findViewById(R.id.editTextBlueberryCount);
        editTextsCount[3] = findViewById(R.id.editTextPotatoesCount);
        btCalculate = findViewById(R.id.buttonCalculate);





        final Context x = this;

    }

    public void calculate(View view){
        RadioButton dialog = findViewById(R.id.radButDialog);
        RadioButton toast = findViewById(R.id.radButToast);
        double sum = 0;
        String return_sms = "";
        for(int i = 0; i < checkBoxes.length; i++){
            if(checkBoxes[i].isChecked() && !Error){
                try {
                    Double price  = Double.parseDouble(editTextsPrice[i].getText().toString());
                    Double  amount = Double.parseDouble(editTextsCount[i].getText().toString());
                    Double result = amount*price;
                    sum += result;
                    return_sms += String.format("%s: %f * %.2f = %.1f\n", checkBoxes[i].getText().toString(), amount, price, result);
                }
                catch (Exception e){
                    Error = true;

                    AlertDialog error = new AlertDialog.Builder(this).create();
                    error.setTitle("Error!");
                    error.setMessage(e.getMessage());
                    error.show();
                }
            }
        }
        return_sms += String.format("Summa: %.1f", sum);

        if(!Error)
        {
            if (dialog.isChecked())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Result cost");
                builder.setMessage(return_sms);
                builder.setIcon(R.drawable.icona);
                builder.show();
            }
            else if (toast.isChecked())
            {
                int duration = Toast.LENGTH_SHORT;
                Toast result = Toast.makeText(this, String.format("Total cost: %.1f", sum), duration);
                result.show();
            }
        }
        Error = false;
    }
}

