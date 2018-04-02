package com.example.android.languagedetection.ui.newTextFragment;

import android.app.Dialog;

import com.example.android.languagedetection.app.App;
import com.example.android.languagedetection.database.DatabaseModel;
import com.example.android.languagedetection.network.LanguageApi;
import com.example.android.languagedetection.network.LanguageInfo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 15:31 26.03.2018.
 */

public class NewTextPresenter {
    private static final String API_KEY = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78";
    private NewTextView view;

    @Inject
    public LanguageApi languageApi;

    public NewTextPresenter() {
        App.getInstance().getAppComponent().inject(this);
    }

    public void attachView(NewTextView view) {
        this.view = view;

    }

    public void loadLanguage() {
        final String text = view.getText();

//                проверка на пустое поле
        if (text.equals("")) {
            view.showEmptyError();
        } else {
//                  Показываем индикатор загрузки
            view.showLoading();

//                    Выполняется запрос на сервер
            languageApi.getData(API_KEY, text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<LanguageInfo>() {
                        @Override
                        public void onSuccess(LanguageInfo languageInfo) {
//                                      Если API ответило сообщением об не достатке текста
                            String language;
                            if (languageInfo.getStatus().equals("ERROR")) {
                                language = "Не достаточно текста, чтобы точно определить язык";
                            } else {
                                language = languageInfo.getLanguage();
                            }
                            DatabaseModel.insert(text, language);
                            openDialog(language);

                        }


                        @Override
                        public void onError(Throwable e) {
                            String language = e.getLocalizedMessage();
                            DatabaseModel.insert(text, language);
                            openDialog(language);
                        }
                    });
        }
    }


    //      Cкрыть индикатор загрузки и открыть диалоговое окно
    public void openDialog(String language) {
        view.hideLoading();
        Dialog dialog = view.openDialog(language);
        view.setDialog(dialog, language);
    }
}
