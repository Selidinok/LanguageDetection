package com.example.android.languagedetection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HistoryListAdaptor mHistoryAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) ;
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mHistoryAdaptor = new HistoryListAdaptor(this);

        mRecyclerView.setAdapter(mHistoryAdaptor);
    }
}
