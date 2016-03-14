package com.pddstudio.openpocket;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.pddstudio.openpocket.fragments.BottomSheetFragment;

import io.inject.InjectView;
import io.inject.Injector;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Injector.inject(this);
        //assign and set the bottom sheet fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bottomSheetLayout, new BottomSheetFragment())
                .commit();
        //TODO finish layout
    }
}
