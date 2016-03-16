package com.pddstudio.openpocket.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.pddstudio.openpocket.R;
import com.pddstudio.openpocket.fragments.AmountInputFragment;
import com.pddstudio.openpocket.model.Action;

/**
 * This Class was created by Patrick J
 * on 15.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class AmountInputView extends RelativeLayout implements View.OnClickListener {

    //to store the context
    private Context mContext;
    //the optional listener
    private AmountInputFragment.InputCallback inputCallback;
    //the root layout
    private GridLayout mGridLayout;
    //the other UI stuff
    private Button mActionButton1;
    private Button mActionButton2;
    private Button mActionButton3;
    private Button mActionButton4;
    private Button mActionButton5;
    private Button mActionButton6;
    private Button mActionButton7;
    private Button mActionButton8;
    private Button mActionButton9;
    private Button mActionButton0;
    private Button mActionButtonDot;
    private Button mActionButtonDel;

    public AmountInputView(Context context) {
        super(context);
        buildLayout(context);
    }

    public AmountInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        buildLayout(context);
    }

    public AmountInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildLayout(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmountInputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        buildLayout(context);
    }

    private void buildLayout(Context context) {
        mContext = context;
        //inflate the layout
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mLayoutView = layoutInflater.inflate(R.layout.view_amount_input, this, false);
        mGridLayout = (GridLayout) mLayoutView.findViewById(R.id.view_grid);
        //assign the buttons inside the layout
        mActionButton0 = (Button) mLayoutView.findViewById(R.id.key_btn_0);
        mActionButton1 = (Button) mLayoutView.findViewById(R.id.key_btn_1);
        mActionButton2 = (Button) mLayoutView.findViewById(R.id.key_btn_2);
        mActionButton3 = (Button) mLayoutView.findViewById(R.id.key_btn_3);
        mActionButton4 = (Button) mLayoutView.findViewById(R.id.key_btn_4);
        mActionButton5 = (Button) mLayoutView.findViewById(R.id.key_btn_5);
        mActionButton6 = (Button) mLayoutView.findViewById(R.id.key_btn_6);
        mActionButton7 = (Button) mLayoutView.findViewById(R.id.key_btn_7);
        mActionButton8 = (Button) mLayoutView.findViewById(R.id.key_btn_8);
        mActionButton9 = (Button) mLayoutView.findViewById(R.id.key_btn_9);
        mActionButtonDot = (Button) mLayoutView.findViewById(R.id.key_btn_dot);
        mActionButtonDel = (Button) mLayoutView.findViewById(R.id.key_btn_del);
        //set the onclick listeners
        mActionButton0.setOnClickListener(this);
        mActionButton1.setOnClickListener(this);
        mActionButton2.setOnClickListener(this);
        mActionButton3.setOnClickListener(this);
        mActionButton4.setOnClickListener(this);
        mActionButton5.setOnClickListener(this);
        mActionButton6.setOnClickListener(this);
        mActionButton7.setOnClickListener(this);
        mActionButton8.setOnClickListener(this);
        mActionButton9.setOnClickListener(this);
        mActionButtonDot.setOnClickListener(this);
        mActionButtonDel.setOnClickListener(this);
        //add the view to the layout
        this.addView(mLayoutView);
    }

    private void onActionEvent(Action action) {
        if(inputCallback != null) inputCallback.onInput(action);
    }

    public void setOnActionListener(AmountInputFragment.InputCallback inputCallback) {
        this.inputCallback = inputCallback;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_btn_0:
                onActionEvent(Action.INPUT_0);
                break;
            case R.id.key_btn_1:
                onActionEvent(Action.INPUT_1);
                break;
            case R.id.key_btn_2:
                onActionEvent(Action.INPUT_2);
                break;
            case R.id.key_btn_3:
                onActionEvent(Action.INPUT_3);
                break;
            case R.id.key_btn_4:
                onActionEvent(Action.INPUT_4);
                break;
            case R.id.key_btn_5:
                onActionEvent(Action.INPUT_5);
                break;
            case R.id.key_btn_6:
                onActionEvent(Action.INPUT_6);
                break;
            case R.id.key_btn_7:
                onActionEvent(Action.INPUT_7);
                break;
            case R.id.key_btn_8:
                onActionEvent(Action.INPUT_8);
                break;
            case R.id.key_btn_9:
                onActionEvent(Action.INPUT_9);
                break;
            case R.id.key_btn_del:
                onActionEvent(Action.INPUT_DEL);
                break;
            case R.id.key_btn_dot:
                onActionEvent(Action.INPUT_DOT);
                break;
        }
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }

}
