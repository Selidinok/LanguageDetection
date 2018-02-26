package com.example.android.languagedetection;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 20:31 26.02.2018.
 */

public class HistoryListAdaptor extends RecyclerView.Adapter<HistoryListAdaptor.HistoryListAdaptorViewHolder> {
    private Cursor mCursor;
    private final Context mContext;

    public HistoryListAdaptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public HistoryListAdaptorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.history_list_item, parent, false);
        return new HistoryListAdaptorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryListAdaptorViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        String searchText = mCursor.getString(1);
        holder.searchTextView.setText(searchText);

        String language = mCursor.getString(2);
        holder.languageTextView.setText(language);
    }

    @Override
    public int getItemCount() {
        if (null == mCursor) return 0;
        return mCursor.getCount();
    }

    class HistoryListAdaptorViewHolder extends RecyclerView.ViewHolder{
        final TextView searchTextView;
        final TextView languageTextView;

        HistoryListAdaptorViewHolder(View view) {
            super(view);
            searchTextView = (TextView) view.findViewById(R.id.search_text);
            languageTextView = (TextView) view.findViewById(R.id.language);
        }
    }
}
