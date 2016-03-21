package com.pddstudio.openpocket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
import com.pddstudio.pocketlibrary.models.Date;
import com.pddstudio.pocketlibrary.models.Transaction;
import com.pddstudio.pocketutils.DateUtils;
import com.pddstudio.pocketutils.Preferences;

import java.util.List;

import io.inject.InjectView;
import io.inject.Injector;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener,
        AmountInputFragment.InputCallback, FastAdapter.OnClickListener<CategoryItem>,
        DatePicker.OnDateSelectedListener {

    @InjectView(R.id.coordinatorLayout)
    private CoordinatorLayout coordinatorLayout;

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

    //to store the selected user data
    private Category category;
    private Date date;

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
            //for testing only, remove all non-necessary items as soon as ready for production
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
            fastItemAdapter.add(new CategoryItem(category));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
            datePicker.setDateListener(this);

            // Options
            Pair<Boolean, SublimeOptions> optionsPair = getOptions();


            // Valid options
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
            datePicker.setArguments(bundle);

            datePicker.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            datePicker.show(getSupportFragmentManager(), "SUBLIME_PICKER");

        } else if (v.getId() == R.id.floatingActionButton) {
            //TODO: Add Transaction object to backend
            //TODO: check that everything required is set
            if(category == null) showSnackBar(R.string.snackbar_category_missing);
            else if(date == null) showSnackBar(R.string.snackbar_date_missing);
            else {
                try {
                    float amount = Float.parseFloat(amountText.getText().toString().replace(Preferences.get().getCurrencySymbol(), ""));
                    if(amount != 0) {
                        Transaction transaction = new Transaction();
                        transaction.setMoneyAmount(amount);
                        transaction.setCategory(category);
                        transaction.setTransactionDate(date);
                        transaction.setProfile(OpenPocket.get().getActiveProfile());
                        //OpenPocket.get().getTransactionManager().addTransaction(transaction);
                        //TODO: Add intent with transaction so amount can be added/removed from BalanceView
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        showSnackBar(R.string.snackbar_amount_zero);
                    }
                } catch (NumberFormatException nf) {
                    showSnackBar(R.string.snackbar_amount_missing);
                }
            }
        }
    }

    @Override
    public boolean onClick(View v, IAdapter<CategoryItem> adapter, CategoryItem item, int position) {
        categoryText.setText(item.getCategory().getCategoryName());
        category = item.getCategory();
        return true;
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

    //for showing a info snackbar
    private void showSnackBar(@StringRes int message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {}
                })
                .show();
    }

    @Override
    public void onDateSelected(Date date) {
        this.date = date;
    }

}
