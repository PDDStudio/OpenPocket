package com.pddstudio.openpocket.views;
/*
 * This Class was created by Patrick J
 * on 09.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pddstudio.openpocket.R;

public class CoordinatorBalanceView extends LinearLayout {

    private TextView mTitleView;
    private TextView mBalanceView;
    private Context mContext;

    public CoordinatorBalanceView(Context context) {
        super(context);
        setupView(context, null);
    }

    public CoordinatorBalanceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context, attrs);
    }

    public CoordinatorBalanceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CoordinatorBalanceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView(context, attrs);
    }

    private void setupView(Context context, @Nullable  AttributeSet attributeSet) {
        this.setOrientation(VERTICAL);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mLayoutView = layoutInflater.inflate(R.layout.view_coordinator_balance, this, false);
        this.mContext = context;
        this.mTitleView = (TextView) mLayoutView.findViewById(R.id.balanceTitle);
        this.mBalanceView = (TextView) mLayoutView.findViewById(R.id.balanceAmount);
        this.addView(mLayoutView);
        //get the styled attributes (if set)
        if(attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CoordinatorBalanceView, 0, 0);

            String titleText = typedArray.getString(R.styleable.CoordinatorBalanceView_titleText);
            String balanceText = typedArray.getString(R.styleable.CoordinatorBalanceView_balanceText);
            @ColorInt int textColor = typedArray.getColor(R.styleable.CoordinatorBalanceView_textColor, Color.WHITE);

            typedArray.recycle();

            if(balanceText != null) mBalanceView.setText(balanceText);
            if(titleText != null) mTitleView.setText(titleText);
            setTextColor(textColor);
        }
    }

    public void setTitleText(String titleText) {
        this.mTitleView.setText(titleText);
    }

    public void setTitleText(@StringRes int titleText) {
        this.mTitleView.setText(titleText);
    }

    public void setBalanceText(String balanceText) {
        this.mBalanceView.setText(balanceText);
    }

    public void setTextColor(@ColorInt int textColor) {
        this.mTitleView.setTextColor(textColor);
        this.mBalanceView.setTextColor(textColor);
    }

    public String getTitleText() {
        return mTitleView.getText().toString();
    }

    public String getBalanceText() {
        return mBalanceView.getText().toString();
    }

}
