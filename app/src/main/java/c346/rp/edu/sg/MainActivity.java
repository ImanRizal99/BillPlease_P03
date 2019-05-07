package c346.rp.edu.sg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText amountInput, peopleInput;
    TextView eachOutput,totalOutput;
    Button submitButton, resetButton;
    Double serviceInt,gstInt;

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.serviceButton:
                if (checked) {
                    serviceInt = 10.0;
                }
                else {
                    serviceInt = 0.0;
                }
                    break;
            case R.id.gstButton:
                if (checked) {
                    gstInt = 7.0;
                }
            else {
                gstInt = 0.0;
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountInput = findViewById(R.id.amountText);
        peopleInput = findViewById(R.id.peopleNumText);

        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        totalOutput = findViewById(R.id.totalText);
        eachOutput = findViewById(R.id.eachText);

        serviceInt = 0.0;
        gstInt = 0.0;

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = Double.parseDouble(amountInput.getText().toString());
                Double people = Double.parseDouble(peopleInput.getText().toString());

                Double total = 0.0;
                Double totalServiceCharge = 0.0;
                Double totalGST = 0.0;

                if (serviceInt == 10.0 && gstInt == 7 ) {
                    totalServiceCharge = amount / serviceInt;
                    totalGST = amount / gstInt;
                    total = amount + totalGST + totalServiceCharge;
                }
                else if (serviceInt == 10.0) {
                    totalServiceCharge = amount / serviceInt;
                    total = amount + totalServiceCharge;
                }
                else if (gstInt == 7) {
                    totalGST = amount / gstInt;
                    total = amount + totalGST + totalServiceCharge;
                }
                else {
                    total = amount;
                }

                String totalOutputt = String.format("$%.2f",total);

                totalOutput.setText(totalOutputt);

                String totaleachperson = String.format("$%.2f", total / people);

                eachOutput.setText(totaleachperson);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountInput.setText("");
                peopleInput.setText("");
                eachOutput.setText("$0.00");
                totalOutput.setText("$0.00");
            }
        });

}
}
