package com.ualr.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapter.AdapterList;
import com.ualr.recyclerviewassignment.databinding.ActivityListMultiSelectionBinding;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected.
//  Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity implements AdapterList.OnItemClickListener{

    private FloatingActionButton mFAB;
    private ActivityListMultiSelectionBinding mBinding;
    private ConstraintLayout mParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListMultiSelectionBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initComponent();
    }

    private void initComponent() {
        // Generated the item list to be displayed using the DataGenerator class
        List<Inbox> items = DataGenerator.getInboxData(this);
        items.addAll(DataGenerator.getInboxData(this));

        // Did the setup of a new RecyclerView instance to display the item list properly
        // Define the layout of each item in the list
        RecyclerView inboxListView = mBinding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        inboxListView.setLayoutManager(layoutManager);

        // Created a new instance of the created Adapter class and bind it to the RecyclerView instance created in step 03
        AdapterList adapter = new AdapterList(items);
        inboxListView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 10. Invoke the method created to a new item to the top of the list so it's
                //  triggered when the user taps the Floating Action Button
            }
        });
    }

    //
    @Override
    public void onItemClick(View view, Inbox obj, int position) {
        mParent = findViewById(R.id.inbox_single_parent);
        mParent.setBackgroundColor(getResources().getColor(R.color.grey_20));
    }




}