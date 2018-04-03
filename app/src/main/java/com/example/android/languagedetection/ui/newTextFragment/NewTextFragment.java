package com.example.android.languagedetection.ui.newTextFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.languagedetection.R;
import com.example.android.languagedetection.app.App;
import com.example.android.languagedetection.presentation.presenters.NewTextPresenter;
import com.example.android.languagedetection.presentation.views.NewTextView;

import javax.inject.Inject;

/**
 * Created by User on 15:19 27.02.2018.
 */

public class NewTextFragment extends MvpAppCompatFragment implements NewTextView {

    private static final String TAG = NewTextFragment.class.getSimpleName();
    private static final String IS_DIALOG_SHOWN = "is-dialog-shown";
    private static final String LANGUAGE_TAG = "language-tag";

    @InjectPresenter
    public NewTextPresenter presenter;


    private ProgressBar mLoadingIndicator;
    private EditText mEditText;
    private Dialog mDialog;
    private String language;

    public NewTextFragment() {
    }

    @ProvidePresenter
    NewTextPresenter providePresenter(){
        return App.getInstance().getAppComponent().createNewTextFragmentPresenter();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_text, container, false);

        App.getInstance().getAppComponent().inject(this);

        mEditText = view.findViewById(R.id.edit_text_view);
        mLoadingIndicator = view.findViewById(R.id.pb_loading_indicator);

        if (getActivity() != null) {
            getActivity().setTitle(getString(R.string.new_text_title));
        }

//      Обработка нажатия кнопки
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> presenter.loadLanguage(mEditText.getText().toString()));
        return view;
    }

//    @Inject
//    void attachView() {
//        presenter.attachView(this);
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        if (savedInstanceState != null) {
//            Log.d(TAG, savedInstanceState.containsKey(IS_DIALOG_SHOWN) ? "true" : "false");
//            if (savedInstanceState.getBoolean(IS_DIALOG_SHOWN)) {
//                language = savedInstanceState.getString(LANGUAGE_TAG);
//                presenter.openDialog(language);
//            }
//        }
//    }

//    @Override
//    public String getText() {
//        return mEditText.getText().toString();
//    }

//    @Override
//    public void showEmptyError() {
//        Toast.makeText(getContext(),
//                "Пожалуйста, введите текст",
//                Toast.LENGTH_LONG).show();
//    }

    @Override
    public void showLoading() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

//    public void setDialog(Dialog mDialog, String language) {
//        this.mDialog = mDialog;
//        this.language = language;
//        mDialog.show();
//    }


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
    public void openDialog(String language) {

        String btn1String = getString(R.string.fist_dialog_btn_text);
        String btn2String = getString(R.string.second_dialog_btn_text);

        mEditText.setText("");

        mDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title)
                .setMessage(language)
                .setNegativeButton(btn1String, (dialog, which) -> dialog.cancel())
                .setPositiveButton(btn2String, (dialog, which) -> {
                    presenter.cancelDialog();
                    presenter.goToHistory();
                    getActivity().setTitle(R.string.history_title);

                }).create();

//        setDialog(builder.create(), language)
//        mDialog = builder.create();
//        presenter.setDialog(builder.create());
        mDialog.show();
    }

    @Override
    public void cancelDialog() {
        if (mDialog != null) {
            mDialog.cancel();
        }
    }

}
