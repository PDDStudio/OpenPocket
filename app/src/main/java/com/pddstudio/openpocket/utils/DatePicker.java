package com.pddstudio.openpocket.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.pddstudio.openpocket.R;
import com.pddstudio.pocketutils.Preferences;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This Class was created by Patrick J
 * on 18.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class DatePicker extends DialogFragment {
    // Date & Time formatter used for formatting
    // text on the switcher button
    private DateFormat mDateFormatter, mTimeFormatter;

    // Picker
    private SublimePicker mSublimePicker;

    // Selected Date
    private SelectedDate mSelectedDate;

    // Callback to activity
    private Callback mCallback;

    SublimeListenerAdapter mListener = new SublimeListenerAdapter() {

        @Override
        public void onDateTimeRecurrenceSet(SublimePicker sublimeMaterialPicker, SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
            mSelectedDate = selectedDate;
            String date = mSelectedDate.getStartDate().get(Calendar.YEAR)
                    + Preferences.get().getDateSeperator()
                    + mSelectedDate.getStartDate().get(Calendar.MONTH)
                    + Preferences.get().getDateSeperator()
                    + mSelectedDate.getStartDate().get(Calendar.DAY_OF_MONTH);
            mCallback.onDateSelected(date);
            dismiss();
        }

        @Override
        public void onCancelled() {
            if (mCallback!= null) {
                mCallback.onCancelled();
            }
            // Should actually be called by activity inside `Callback.onCancelled()`
            dismiss();
        }
    };

    public DatePicker() {
        // Initialize formatters
        mDateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        mTimeFormatter = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
        mTimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    }

    // Set activity callback
    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mSublimePicker = (SublimePicker) getActivity()
                .getLayoutInflater().inflate(R.layout.sublime_picker, container);

        // Retrieve SublimeOptions
        Bundle arguments = getArguments();
        SublimeOptions options = null;

        // Options can be null, in which case, default
        // options are used.
        if (arguments != null) {
            options = arguments.getParcelable("SUBLIME_OPTIONS");
        }

        mSublimePicker.initializePicker(options, mListener);
        return mSublimePicker;
    }

    // For communicating with the activity
    public interface Callback {
        void onCancelled();
        void onDateSelected(String date);
    }

}
