package com.example.android.languagedetection.ui.NewTextFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.languagedetection.R;
import com.example.android.languagedetection.app.App;
import com.example.android.languagedetection.ui.HistoryFragment.HistoryFragment;

import javax.inject.Inject;

/**
 * Created by User on 15:19 27.02.2018.
 */

public class NewTextFragment extends Fragment implements NewTextView {

    private static final String TAG = NewTextFragment.class.getSimpleName();
    private static final String IS_DIALOG_SHOWN = "is-dialog-shown";
    private static final String LANGUAGE_TAG = "language-tag";
    @Inject
    public NewTextPresenter presenter;
    private ProgressBar mLoadingIndicator;
    private EditText mEditText;
    private Dialog mDialog;
    private String language;

    public NewTextFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_text, container, false);

        mEditText = view.findViewById(R.id.edit_text_view);
        mLoadingIndicator = view.findViewById(R.id.pb_loading_indicator);

        App.getInstance().getAppComponent().createNewTextFragmentComponent().inject(this);

        if (getActivity() != null) {
            getActivity().setTitle(getString(R.string.new_text_title));
        }

//      Обработка нажатия кнопки
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> presenter.loadLanguage());
        return view;
    }

    @Inject
    void attachView() {
        presenter.attachView(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d(TAG, savedInstanceState.containsKey(IS_DIALOG_SHOWN) ? "true" : "false");
            if (savedInstanceState.getBoolean(IS_DIALOG_SHOWN)) {
                language = savedInstanceState.getString(LANGUAGE_TAG);
                presenter.openDialog(language);
            }
        }
    }

    @Override
    public String getText() {
        return mEditText.getText().toString();
    }

    @Override
    public void showEmptyError() {
        Toast.makeText(getContext(),
                "Пожалуйста, введите текст",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    public void setDialog(Dialog mDialog, String language) {
        this.mDialog = mDialog;
        this.language = language;
        mDialog.show();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.fragment_id), TAG);
        if (mDialog != null && mDialog.isShowing()) {
            outState.putBoolean(IS_DIALOG_SHOWN, true);
            outState.putString(LANGUAGE_TAG, language);
        }
    }

    //    Создание диалогового окна
    @Override
    public Dialog openDialog(String language) {

        String btn1String = getString(R.string.fist_dialog_btn_text);
        String btn2String = getString(R.string.second_dialog_btn_text);

        mEditText.setText("");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title)
                .setMessage(language)
                .setNegativeButton(btn1String, (dialog, which) -> dialog.cancel())
                .setPositiveButton(btn2String, (dialog, which) -> {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.content_view, new HistoryFragment())
                            .addToBackStack(TAG)
                            .commit();
                    getActivity().setTitle(R.string.history_title);
                });

        return builder.create();
    }

}
