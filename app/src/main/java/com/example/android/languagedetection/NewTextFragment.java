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
import com.example.android.languagedetection.database.History;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 15:19 27.02.2018.
 */

public class NewTextFragment extends Fragment {
    private static final String FRAGMENT_ID = "fragment-id";
    private static final int ID = 0;
    private static final String API_KEY = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78";
    private static final String TAG = NewTextFragment.class.getSimpleName();
    private final Executor executor = Executors.newFixedThreadPool(2);
    private ProgressBar mLoadingIndicator;
    private EditText mEditText;

    public NewTextFragment() {
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_text, container, false);

        mEditText = (EditText) view.findViewById(R.id.edit_text_view);
        mLoadingIndicator = (ProgressBar) view.findViewById(R.id.pb_loading_indicator);

//      Обработка нажатия кнопки
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
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
                    final Call<LanguageInfo> call = languageApi.getData(API_KEY, text);

                    call.enqueue(new Callback<LanguageInfo>() {
                        @Override
                        public void onResponse(Call<LanguageInfo> call, Response<LanguageInfo> response) {
                            Log.d(TAG, response.toString());
                            if (response.isSuccessful()) {
//                                Если API ответило сообщением об не достатке текста
                                if (response.body().getStatus().equals("ERROR")) {
                                    language = "Не достаточно текста, чтобы точно определить язык";
                                } else {
                                    language = response.body().getLanguage();
                                }
                                openDialog(text, language);

                            } else {
                                ResponseBody errorBody = response.errorBody();
                                try {
                                    Log.d(TAG, errorBody.string());
                                    String language = errorBody.string();
                                    openDialog(text, language);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LanguageInfo> call, Throwable t) {
                            Log.d(TAG, t.getLocalizedMessage());
                            language = t.getLocalizedMessage();
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
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.db.getHistoryDao().add(history);
            }
        });
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putInt(FRAGMENT_ID, ID);
//    }

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
        insert(text, language);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        onCreateDialog(language).show();
    }

}
