package com.pddstudio.openpocket.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pddstudio.openpocket.R;
import com.pddstudio.openpocket.model.Action;
import com.pddstudio.openpocket.views.AmountInputView;

/**
 * This Class was created by Patrick J
 * on 15.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class AmountInputFragment extends Fragment {

    private InputCallback inputCallback;
    private AmountInputView amountInputView;

    public interface InputCallback {
        void onInput(Action action);
    }

    public AmountInputFragment() {}

    public AmountInputFragment withInputCallback(InputCallback inputCallback) {
        this.inputCallback = inputCallback;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        View view = layoutInflater.inflate(R.layout.fragment_amount_input, viewGroup, false);
        //assign the view and set the callback info
        amountInputView = (AmountInputView) view.findViewById(R.id.amountInputView);
        if(inputCallback != null) amountInputView.setOnActionListener(inputCallback);
        return view;
    }

}
