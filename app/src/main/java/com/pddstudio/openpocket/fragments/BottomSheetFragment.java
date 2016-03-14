package com.pddstudio.openpocket.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pddstudio.openpocket.R;

/**
 * This Class was created by Patrick J
 * on 14.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return layoutInflater.inflate(R.layout.fragment_transaction, viewGroup, false);
    }

}
