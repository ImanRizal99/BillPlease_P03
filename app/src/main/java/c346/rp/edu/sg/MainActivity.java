package c346.rp.edu.sg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    EditText amountInput, peopleInput, discountInput;
    TextView eachOutput, totalOutput, errorOutput;
    Button submitButton, resetButton;
    Double serviceInt, gstInt;

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.serviceButton:
                if (checked) {
                    serviceInt = 10.0;
                } else {
                    serviceInt = 0.0;
                }
                break;
            case R.id.gstButton:
                if (checked) {
                    gstInt = 7.0;
                } else {
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
        discountInput = findViewById(R.id.discountText);

        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        totalOutput = findViewById(R.id.totalText);
        eachOutput = findViewById(R.id.eachText);
//        errorOutput = findViewById(R.id.errorText);

        serviceInt = 0.0;
        gstInt = 0.0;

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = 0.0;
                Integer people = 0;
                Integer discount = 0;
                if (amountInput.getText().toString().trim().length() > 0) {
                    amount = Double.parseDouble(amountInput.getText().toString());
                }
                if (peopleInput.getText().toString().trim().length() > 0) {
                    people = Integer.parseInt(peopleInput.getText().toString());
                }
                if (discountInput.getText().toString().trim().length() > 0) {
                    discount = Integer.parseInt(discountInput.getText().toString());
                }

                Double total = 0.0;
                Double totalServiceCharge = 0.0;
                Double totalGST = 0.0;
                Double totalDiscount = 0.0;
                Double totalPayable = 0.0;

                if (people >= 2) {
                    if (serviceInt == 10.0 && gstInt == 7.0) {
                        totalServiceCharge = amount / serviceInt;
                        totalGST = amount / gstInt;
                        total = amount + totalGST + totalServiceCharge;
                    } else if (serviceInt == 10.0) {
                        totalServiceCharge = amount / serviceInt;
                        total = amount + totalServiceCharge;
                    } else if (gstInt == 7.0) {
                        totalGST = amount / gstInt;
                        total = amount + totalGST + totalServiceCharge;
                    } else {
                        total = amount;
                    }
                }

                if (discount >= 1) {
                    totalDiscount = (total * discount) / 100;
                    totalPayable = total - totalDiscount;
                }
                else {
                    totalPayable = total;
                }


                String totalOutputt = String.format("$%.2f", totalPayable);

                totalOutput.setText(totalOutputt);

                String totaleachperson = String.format("$%.2f", totalPayable / people);

                eachOutput.setText(totaleachperson);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountInput.setText("");
                peopleInput.setText("");
                discountInput.setText("");
                eachOutput.setText("$0.00");
                totalOutput.setText("$0.00");
            }
        });

    }
}
