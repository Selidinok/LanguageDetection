package com.example.android.languagedetection;

import android.app.Dialog;
import android.content.DialogInterface;
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

import com.example.android.languagedetection.Network.LanguageApi;
import com.example.android.languagedetection.Network.LanguageInfo;
import com.example.android.languagedetection.Network.RetrofitUtils;
import com.example.android.languagedetection.database.DatabaseCreator;
import com.example.android.languagedetection.database.History;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 15:19 27.02.2018.
 */

public class NewTextFragment extends Fragment {
    private static final String FRAGMENT_ID = "fragment-id";
    private static final int ID = 0;
    private static final String API_KEY = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78";
    private static final String TAG = NewTextFragment.class.getSimpleName();
    private static final String IS_DIALOG_SHOWN = "is-dialog-shown";
    private static final String LANGUAGE_TAG = "language-tag";
    private static boolean isShowDialog = false;
    private String language;
    private ProgressBar mLoadingIndicator;
    private EditText mEditText;
    private Dialog mDialog = null;

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

        if (savedInstanceState != null && savedInstanceState.getBoolean(IS_DIALOG_SHOWN)) {
            language = savedInstanceState.getString(LANGUAGE_TAG);
            mDialog = onCreateDialog(language);
            mDialog.show();
        }

//      Обработка нажатия кнопки
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            private String language;

            @Override
            public void onClick(View view) {
                final String text = mEditText.getText().toString();

//                проверка на пустое поле
                if (text.equals("")) {
                    Toast.makeText(getContext(),
                            "Пожалуйста, введите текст",
                            Toast.LENGTH_LONG).show();
                } else {
//                  Показываем индикатор загрузки
                    mLoadingIndicator.setVisibility(View.VISIBLE);

//                    Выполняется запрос на сервер
                    LanguageApi languageApi = RetrofitUtils.getRetrofit().create(LanguageApi.class);
                    languageApi.getData(API_KEY, text)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableSingleObserver<LanguageInfo>() {
                                @Override
                                public void onSuccess(LanguageInfo languageInfo) {
                                    Log.d(TAG, languageInfo.toString());

//                                      Если API ответило сообщением об не достатке текста
                                    if (languageInfo.getStatus().equals("ERROR")) {
                                        language = "Не достаточно текста, чтобы точно определить язык";
                                    } else {
                                        language = languageInfo.getLanguage();
                                    }
                                    openDialog(text, language);

                                }


                                @Override
                                public void onError(Throwable e) {
                                    Log.d(TAG, e.getLocalizedMessage());
                                    language = e.getLocalizedMessage();
                                    openDialog(text, language);
                                }
                            });
                }
            }
        });
        return view;
    }

    //    Добавление записи в БД
    private void insert(String text, String language) {
        final History history = new History(text, language);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                DatabaseCreator.getPersonDatabase(getContext()).getHistoryDao().add(history);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mDialog != null && mDialog.isShowing()) {
            isShowDialog = true;
            outState.putBoolean(IS_DIALOG_SHOWN, isShowDialog);
            outState.putString(LANGUAGE_TAG, language);
        }
    }

    //    Создание диалогового окна
    public Dialog onCreateDialog(String language) {

        String btn1String = "Остаться";
        String btn2String = "В историю";

        mEditText.setText("");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Ваш язык:")
                .setMessage(language)
                .setNegativeButton(btn1String, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(btn2String, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.content_view, new HistoryFragment())
                                .addToBackStack("text-fragment")
                                .commit();
                        getActivity().setTitle(R.string.history_title);
                    }
                });

        return builder.create();
    }


    //    Вставить запись в базу, потом скрыть индикатор загрузки и открыть диалоговое окно
    private void openDialog(String text, String language) {
        this.language = language;
        insert(text, language);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mDialog = onCreateDialog(language);
        mDialog.show();
    }

}
