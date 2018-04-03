package com.example.android.languagedetection.ui.historyFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.languagedetection.R;
import com.example.android.languagedetection.app.App;
import com.example.android.languagedetection.model.database.History;
import com.example.android.languagedetection.presentation.presenters.HistoryPresenter;
import com.example.android.languagedetection.presentation.presenters.NewTextPresenter;
import com.example.android.languagedetection.presentation.views.HistoryView;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class HistoryFragment extends MvpAppCompatFragment implements HistoryView {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String TAG = HistoryFragment.class.getSimpleName();

    @InjectPresenter
    public HistoryPresenter mPresenter;

    @Inject
    public MyHistoryRecyclerViewAdapter mAdapter;

    @ProvidePresenter
    HistoryPresenter providePresenter(){
        return App.getInstance().getAppComponent().createHistoryPresenter();
    }

    private int mColumnCount = 1;
    private RecyclerView mRecyclerView;


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);

        App.getInstance().getAppComponent().inject(this);

        if (getActivity() != null) {
            getActivity().setTitle(getString(R.string.history_title));
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

//            запрос на получение истории из БД

            mPresenter.loadHistory();

        }
        return view;
    }

//    @Inject
//    void attachView() {
//        mPresenter.attachView(this);
//    }

    @Override
    public void showHistory(List<History> histories) {
        mAdapter.setValues(histories);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(getString(R.string.fragment_id), TAG);
    }
}
