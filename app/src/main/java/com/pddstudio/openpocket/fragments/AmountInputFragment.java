package com.pddstudio.openpocket.fragments;

import android.support.v4.app.Fragment;

import com.pddstudio.openpocket.model.Action;

/**
 * This Class was created by Patrick J
 * on 15.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class AmountInputFragment extends Fragment {

    private InputCallback inputCallback;

    public interface InputCallback {
        void onInput(Action action);
    }

    public AmountInputFragment() {}

    public AmountInputFragment withInputCallback(InputCallback inputCallback) {
        this.inputCallback = inputCallback;
        return this;
    }

}
