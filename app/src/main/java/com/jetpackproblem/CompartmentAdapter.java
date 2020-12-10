package com.jetpackproblem;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CompartmentAdapter extends RecyclerView.Adapter<CompartmentAdapter.ViewHolder>  {
    private static List<Integer> hoppingCostList = new ArrayList<>();

    public CompartmentAdapter(List<Integer> hoppingCostList) {
        this.hoppingCostList = hoppingCostList;
    }

    public void setHoppingCostList(List<Integer> hoppingCostList) {
        this.hoppingCostList = hoppingCostList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.compartment_row, parent, false);
        CompartmentAdapter.ViewHolder viewHolder = new CompartmentAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.compartmentNumber.setText("" + (position +1));
        holder.costEditText.setText(hoppingCostList.get(position)> 0? "" + hoppingCostList.get(position): "");
    }

    @Override
    public int getItemCount() {
        return hoppingCostList != null && !hoppingCostList.isEmpty()? hoppingCostList.size(): 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView compartmentNumber;
        private EditText costEditText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            compartmentNumber = itemView.findViewById(R.id.compartment_number);
            costEditText = itemView.findViewById(R.id.hopping_cost_edittext);
            costEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable != null && !TextUtils.isEmpty(editable.toString()) && hoppingCostList != null && !hoppingCostList.isEmpty() && getAdapterPosition() > -1 ) {
                        hoppingCostList.set(getAdapterPosition(), Integer.valueOf(editable.toString()));
                    }

                }
            });
        }
    }
}
