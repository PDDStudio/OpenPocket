package com.pddstudio.openpocket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.github.clans.fab.FloatingActionButton;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.SlideInOutLeftAnimator;
import com.pddstudio.openpocket.adapters.items.CategoryItem;
import com.pddstudio.openpocket.fragments.AmountInputFragment;
import com.pddstudio.openpocket.model.Action;
import com.pddstudio.openpocket.utils.DatePicker;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.models.Category;
import com.pddstudio.pocketutils.DateUtils;
import com.pddstudio.pocketutils.Preferences;

import java.util.List;

import io.inject.InjectView;
import io.inject.Injector;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener,
        AmountInputFragment.InputCallback, FastAdapter.OnClickListener<CategoryItem>,
        DatePicker.Callback {

    @InjectView(R.id.categoryRecyclerView)
    private RecyclerView recyclerView;

    @InjectView(R.id.floatingActionButton)
    private FloatingActionButton floatingActionButton;

    @InjectView(R.id.transactionToolbar)
    private Toolbar toolbar;

    @InjectView(R.id.amountTextView)
    private TextView amountText;

    @InjectView(R.id.categoryTextView)
    private TextView categoryText;

    @InjectView(R.id.dateButton)
    private Button dateButton;

    private FastItemAdapter<CategoryItem> fastItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Category> categories;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Injector.inject(this);

        //set the toolbar
        toolbar.setTitle(R.string.toolbar_transaction_title);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configure the recyclerview and load the items
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fastItemAdapter);
        recyclerView.setItemAnimator(new SlideInOutLeftAnimator(recyclerView));

        categories = OpenPocket.get().getCategoryManager().getCategoryList();
        for(Category category : categories) {
            fastItemAdapter.add(new CategoryItem(category));
        }

        //set and assign the fab
        floatingActionButton.setImageDrawable(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_check).color(Color.WHITE));
        floatingActionButton.setOnClickListener(this);

        //set the number input field
        AmountInputFragment amountInputFragment = new AmountInputFragment().withInputCallback(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.categoryFragmentPlaceHolder, amountInputFragment)
                .commit();

        //set the amount to 0 by default
        amountText.setText(Preferences.get().getCurrencySymbol() + "0");

        //set the current date to the date picker
        dateButton.setText(DateUtils.getCurrentDate());
        dateButton.setOnClickListener(this);
    }

    @Override
    public void onInput(Action action) {
        Log.d("TransactionActivity", "Action triggered: " + action.name() + " [" + action.getActionString() + "]");
        //parse the action and react on it
        switch (action) {
            case INPUT_DEL:
                if(amountText.getText().length() == 1) {
                    amountText.setText(amountText.getText() + "0");
                } else {
                    amountText.setText(amountText.getText().subSequence(0, amountText.getText().length() -1));
                }
                break;
            default:
                if(amountText.getText().toString().contentEquals(Preferences.get().getCurrencySymbol() + "0")) amountText.setText(Preferences.get().getCurrencySymbol());
                amountText.setText(amountText.getText() + action.getActionString());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dateButton) {
            // DialogFragment to host SublimePicker
            datePicker = new DatePicker();
            datePicker.setCallback(this);

            // Options
            Pair<Boolean, SublimeOptions> optionsPair = getOptions();


            // Valid options
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
            datePicker.setArguments(bundle);

            datePicker.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            datePicker.show(getSupportFragmentManager(), "SUBLIME_PICKER");

        } else if (v.getId() == R.id.floatingActionButton) {
            Log.d("TransactionActivity", "Save Date: " + dateButton.getText() + " | Category: " + categoryText.getText() + " | Amount: " + amountText.getText());
            //TODO: Add Transaction object to backend
        }
    }

    @Override
    public boolean onClick(View v, IAdapter<CategoryItem> adapter, CategoryItem item, int position) {
        categoryText.setText(item.getCategory().getCategoryName());
        return true;
    }

    @Override
    public void onCancelled() {}

    @Override
    public void onDateSelected(String date) {
        dateButton.setText(date);
    }

    // Validates & returns SublimePicker options
    private Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;
        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        options.setDisplayOptions(displayOptions);
        // Enable/disable the date range selection feature
        options.setCanPickDateRange(false);
        return new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }
}
