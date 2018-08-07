package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1;
    Spinner spn2;
    Button btnGo;
    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);

        //Initialise the ArrayList
        alNumbers = new ArrayList<>();
        //Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaNumbers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alNumbers);

        String[] str = getResources().getStringArray(R.array.rp);
        aaNumbers.addAll(Arrays.asList(str));

        //Bind the ArrayAdapter to the Spinner
        spn2.setAdapter(aaNumbers);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        alNumbers.clear();
                        String[] rpname = getResources().getStringArray(R.array.rp);
                        alNumbers.addAll(Arrays.asList(rpname));
                        aaNumbers.notifyDataSetChanged();
                        break;

                    case 1:
                        alNumbers.clear();
                        String[] soiname = getResources().getStringArray(R.array.soi);
                        alNumbers.addAll(Arrays.asList(soiname));
                        aaNumbers.notifyDataSetChanged();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alNumbers.clear();
                int pos = spn1.getSelectedItemPosition();
                int pos2 = spn2.getSelectedItemPosition();

                String[][] sites = {
                        {
                                "https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life",
                        },
                        {
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
                        }
                };
                String theURL = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                Intent intent = new Intent(getBaseContext(), Main2Activity.class);
                intent.putExtra("URL", theURL);
                startActivity(intent);
            }
        });
    }
}