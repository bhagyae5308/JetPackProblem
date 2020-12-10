package com.jetpackproblem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int numberOfHops = 0;
    private  CompartmentAdapter adapter;
    private List<Integer> hoppingCostList = new ArrayList<>();
    private EditText destinationCoachIndexEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        RecyclerView compartmentView = findViewById(R.id.compartmentList);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 5);
        compartmentView.setLayoutManager(linearLayoutManager);
        adapter = new CompartmentAdapter(hoppingCostList);
        compartmentView.setHasFixedSize(true);
        compartmentView.setAdapter(adapter);
        findViewById(R.id.plus_btn).setOnClickListener(this::onPlusBtnClicked);
        findViewById(R.id.delete_btn).setOnClickListener(this::onDeleteBtnClicked);
        findViewById(R.id.find_btn).setOnClickListener(this::onFindBtnClicked);
        findViewById(R.id.reset_btn).setOnClickListener(this::onResetBtnClicked);
        findViewById(R.id.delete_btn).setVisibility(hoppingCostList.size() > 0? View.VISIBLE:View.GONE);
        destinationCoachIndexEditText = findViewById(R.id.dest_coach_edit_text);
        resultTextView = findViewById(R.id.result_textView);
    }

    private void onResetBtnClicked(View view) {
        resultTextView.setText("");
        destinationCoachIndexEditText.setText("");
        if (hoppingCostList != null && !hoppingCostList.isEmpty() && hoppingCostList.size() > 1) {
            for (int i= 0; i< hoppingCostList.size(); i++) {
                hoppingCostList.set(i, 0);
            }
            adapter.setHoppingCostList(hoppingCostList);
        }
    }

    private void onFindBtnClicked(View view) {
        numberOfHops = 0;
        resultTextView.setText("");
        String destCoachIndexString = destinationCoachIndexEditText.getText().toString();
        Integer destCoachIndex = !TextUtils.isEmpty(destCoachIndexString) ? Integer.valueOf(destCoachIndexString): 0;
        if (destCoachIndex > 0) {
            if (destCoachIndex <= hoppingCostList.size()) {
                getNumberOfHops(destCoachIndex);
            } else {
                Toast.makeText(this, getString(R.string.err_dest_index_in_range), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.err_dest_index), Toast.LENGTH_LONG).show();
        }
    }

    private void onDeleteBtnClicked(View view) {
        if (hoppingCostList != null && !hoppingCostList.isEmpty() && hoppingCostList.size() > 1) {
            hoppingCostList.remove(hoppingCostList.size()-1);
            adapter.setHoppingCostList(hoppingCostList);
            findViewById(R.id.delete_btn).setVisibility(hoppingCostList.size() > 0? View.VISIBLE:View.GONE);
        }
    }

    private void onPlusBtnClicked(View view) {
        hoppingCostList.add(0);
        adapter.setHoppingCostList(hoppingCostList);
        findViewById(R.id.delete_btn).setVisibility(hoppingCostList.size() > 0? View.VISIBLE:View.GONE);
    }
    private void getNumberOfHops(Integer destCoachIndex) {
        int sourceCoachIndex = 0;
        destCoachIndex = destCoachIndex - 1; // since inside the list the values are stored starting from index 0
        while (sourceCoachIndex< hoppingCostList.size()) {
            //distance between current compartment and destination compartment
            int diffInIndex = destCoachIndex - sourceCoachIndex;
            if (diffInIndex == 0) { //The thesis is present in the first compartment itself
                break;
            } else if (diffInIndex ==  1) {//The thesis is present in the next compartment itself, so increase the hop count by 1 and return
                numberOfHops++;
                break;
            } else if (diffInIndex > 1) {
                Integer nextCompartmentIndex = sourceCoachIndex + hoppingCostList.get(sourceCoachIndex);//sum of current index and cost associated with it
                if (nextCompartmentIndex ==  destCoachIndex) {//using current cost the user can reach the destination, so increase the increase the hop count by 1 and return
                    numberOfHops++;
                    break;
                } else if (nextCompartmentIndex > destCoachIndex) {//move to next compartment
                    sourceCoachIndex++;
                    numberOfHops++;
                } else {
                    //next compartment cost is greater than current compartment cost, so move to next compartment
                    if (hoppingCostList.get(sourceCoachIndex + 1) > hoppingCostList.get(sourceCoachIndex)) {
                        sourceCoachIndex++;
                        numberOfHops++;
                    } else if (hoppingCostList.get(sourceCoachIndex + 1) <= hoppingCostList.get(sourceCoachIndex)) {
                        //next compartment cost is less than equals current compartment cost,
                        // so jump to the compartment number (current compartment number plus cost associated with it)
                        sourceCoachIndex = sourceCoachIndex + hoppingCostList.get(sourceCoachIndex);
                        numberOfHops++;
                    }
                }
            } else {
                sourceCoachIndex++;
                numberOfHops++;
            }
        }
        resultTextView.setText("Number of hops: " +  numberOfHops);
        Log.e("numberOfHops", ""+numberOfHops);
    }
}
