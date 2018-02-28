package com.example.android.languagedetection;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.languagedetection.database.History;
import com.example.android.languagedetection.database.HistoryDb;
import com.example.android.languagedetection.dummy.DummyContent;
import com.example.android.languagedetection.dummy.DummyContent.DummyItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class HistoryFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private static final String FRAGMENT_ID = "fragment-id";
    private static final int ID = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HistoryFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

//        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);

        HistoryDb db = MainActivity.db;

//      Рыбный контент, не забыть удалить
        final History h1 = new History("111gaergeargeargngfx", "english");
        final History h2 = new History("222онеовеовеноевноонев", "russian");
        final History h3 = new History("333gaergeaолвеноargngfx", "unknown");


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            db.getHistoryDao().getAll()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<History>>() {
                        @Override
                        public void accept(List<History> histories) throws Exception {
//                            histories.add(h1);
//                            histories.add(h2);
//                            histories.add(h3);

                            recyclerView.setAdapter(new MyHistoryRecyclerViewAdapter(histories));
                        }
                    });

        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(FRAGMENT_ID, ID);
    }
}
