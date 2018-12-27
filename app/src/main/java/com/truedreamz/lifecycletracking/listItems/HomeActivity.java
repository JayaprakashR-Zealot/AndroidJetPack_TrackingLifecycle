package com.truedreamz.lifecycletracking.listItems;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.truedreamz.lifecycletracking.R;
import com.truedreamz.lifecycletracking.addItem.AddActivity;
import com.truedreamz.lifecycletracking.db.StudentModel;
import com.truedreamz.lifecycletracking.lifecycle.AppLifeCycleObserver;
import com.truedreamz.lifecycletracking.lifecycle.AppLifecycleOwner;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnLongClickListener {

    private StudentListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private AppLifecycleOwner appLifecycleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        viewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);

        // custom lifecycle owner
        appLifecycleOwner=new AppLifecycleOwner();
        appLifecycleOwner.startLifecycleOwner();
        appLifecycleOwner.stopLifecycleOwner();
        // Adding the Observer for default lifecycle
        getLifecycle().addObserver(new AppLifeCycleObserver());

        viewModel.getItemAndPersonList().observe(HomeActivity.this, new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(@Nullable List<StudentModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddActivity.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<StudentModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        Snackbar.make(recyclerView,R.string.chapter_one,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {
        StudentModel borrowModel = (StudentModel) v.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(new AppLifeCycleObserver());
    }
}
