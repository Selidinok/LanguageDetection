package com.example.android.languagedetection;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.languagedetection.database.History;
import com.example.android.languagedetection.database.HistoryDb;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

/**
 * Created by User on 15:19 27.02.2018.
 */

public class NewTextFragment extends Fragment {
    private static final String FRAGMENT_ID = "fragment-id";
    private static final int ID = 0;

    public static String mLanguage = "Unknown";

    private final Executor executor = Executors.newFixedThreadPool(2);
    private static EditText mEditText;

    public NewTextFragment() {
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_text, container, false);

        mEditText = (EditText) view.findViewById(R.id.edit_text_view);
        getFragmentManager();


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEditText.getText().toString();
                if (text.equals("") || text.length() < 5){
                    Toast.makeText(getContext(),
                            "Пожалуйста, введите как минимум 5 символов",
                            Toast.LENGTH_LONG).show();
                } else {
                    final History history = new History(text, mLanguage);

                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.db.getHistoryDao().add(history);
                        }
                    });
                    MyDialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.show(getFragmentManager(), "dialog");
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(FRAGMENT_ID, ID);
    }


    /*
    * Класс описывающий диалоговое окно
    * */
    public static class MyDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String message = "Ваш язык: " + mLanguage;
            String btn1String = "Остаться";
            String btn2String = "В историю";

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setMessage(message)
                    .setNegativeButton(btn1String, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mEditText.setText("");
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton(btn2String, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.content_view, new HistoryFragment()).commit();
                            getActivity().setTitle(R.string.history_title);
                        }
                    });

            return builder.create();
        }
    }
}
