package com.example.recyclevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = new ArrayList<>();
        recyclerView =  findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for ( int i =0 ; i < myData.nameArray.length ; i++){
            dataSet.add(new DataModel(
                    myData.nameArray[i],
                    myData.versionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        adapter = new CustomeAdapter(dataSet, new CustomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataModel dataModel) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_item_details);

                // Initialize the views in the custom dialog layout
                ImageView dialogImageView = dialog.findViewById(R.id.dialogImageView);
                TextView dialogTextViewName = dialog.findViewById(R.id.dialogTextViewName);
                TextView dialogTextViewVersion = dialog.findViewById(R.id.dialogTextViewVersion);

                // Set the data from the clicked item to the dialog views
                dialogImageView.setImageResource(dataModel.getImage());
                dialogTextViewName.setText(dataModel.getName());
                dialogTextViewVersion.setText(dataModel.getVersion());

                // Display the custom dialog
                dialog.show();
            }
        });
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.searchButt);

        // Set onClickListener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = ((EditText) findViewById(R.id.editTextText)).getText().toString().trim();
                adapter.filter(searchQuery);
            }
        });
    }
}








