package com.techfit.mdofbehavior.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by techfit on 2017/2/13.
 */

public class Utils {

    public static final int SOFT_INPUT_HIDDED = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;

    public static final int SOFT_INPUT_DISPLAY = WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;

    public static void closeInputPopup(Activity activity){
        activity.getWindow().setSoftInputMode(SOFT_INPUT_HIDDED);
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromInputMethod(view.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

}
