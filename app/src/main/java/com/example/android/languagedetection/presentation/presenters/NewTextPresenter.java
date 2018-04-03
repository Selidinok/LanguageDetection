package com.example.android.languagedetection.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.languagedetection.model.database.DatabaseModel;
import com.example.android.languagedetection.model.interceptor.NewTextInteractor;
import com.example.android.languagedetection.model.network.LanguageInfo;
import com.example.android.languagedetection.presentation.views.NewTextView;
import com.example.android.languagedetection.ui.Fragments;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

/**
 * Created by User on 15:31 26.03.2018.
 */

@InjectViewState
public class NewTextPresenter extends BasePresenter<NewTextView> {
    private static final String API_KEY = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78";

//    private LanguageApi languageApi;
    private NewTextInteractor textInteractor;

    private Router router;

    @Inject
    public NewTextPresenter(NewTextInteractor textInteractor, Router router) {
        this.textInteractor = textInteractor;
        this.router = router;
    }

//    public NewTextPresenter() {
//        App.getInstance().getAppComponent().inject(this);
//
//    }

//    public void attachView(NewTextView view) {
//        this.view = view;
//
//    }

    public void loadLanguage(String text) {

//                проверка на пустое поле
        if (text.equals("")) {
//            view.showEmptyError();
            router.showSystemMessage("Пожалуйста, введите текст");
        } else {
//                  Показываем индикатор загрузки
            getViewState().showLoading();

//                    Выполняется запрос на сервер
            Disposable disposable = textInteractor.getLanguage(text)
                    .subscribe(languageInfo -> {
                        onComplete(text, languageInfo);
                    }, throwable -> {
                        onFail(text, throwable);
                    });
            addToComposite(disposable);
//                    .subscribe(new DisposableSingleObserver<LanguageInfo>() {
//                        @Override
//                        public void onSuccess(LanguageInfo languageInfo) {
////                                      Если API ответило сообщением об не достатке текста
//                            String language;
//                            if (languageInfo.getStatus().equals("ERROR")) {
//                                language = "Не достаточно текста, чтобы точно определить язык";
//                            } else {
//                                language = languageInfo.getLanguage();
//                            }
//                            DatabaseModel.insert(text, language);
//                            openDialog(language);
//
//                        }
//
//
//                        @Override
//                        public void onError(Throwable e) {
//                            String language = e.getLocalizedMessage();
//                            DatabaseModel.insert(text, language);
//                            openDialog(language);
//                        }
//                    });
        }
    }

    private void onFail(String text, Throwable throwable) {
        String language = throwable.getLocalizedMessage();
        textInteractor.inset(text, language);
        openDialog(language);
    }

    private void onComplete(String text, LanguageInfo languageInfo) {
        String language;
        if (languageInfo.getStatus().equals("ERROR")) {
            language = "Не достаточно текста, чтобы точно определить язык";
        } else {
            language = languageInfo.getLanguage();
        }
//        DatabaseModel.insert(text, language);
        textInteractor.inset(text, language);
        openDialog(language);
    }


    //      Cкрыть индикатор загрузки и открыть диалоговое окно
    public void openDialog(String language) {
        getViewState().hideLoading();
        getViewState().openDialog(language);
    }

    public void goToHistory() {
        router.navigateTo(Fragments.HISTORY_FRAGMENT);
    }

    public void cancelDialog(){
        getViewState().cancelDialog();
    }

}
