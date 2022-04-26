package com.xxmassdeveloper.mpchartexample.mytest;
 
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
 
import com.github.mikephil.charting.charts.PieChart;
import com.xxmassdeveloper.mpchartexample.R;

/**
 * @author banbury
 * @version v1.0
 * @created 2018/7/2_14:35.
   * @description Fixing the problem that partial values ​​overlap when the value is displayed externally
 */
 
public class PieChartFixCover extends PieChart {
 
    public PieChartFixCover(Context context) {
        this(context, null);
    }
 
    public PieChartFixCover(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public PieChartFixCover(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getAttrs(attrs);
    }
 
    private void getAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MPAndroidChartUtil);
            String mode = a.getString(R.styleable.MPAndroidChartUtil_mp_chart_out_value_place_mode);
            boolean auto_adapt_text_size = a.getBoolean(R.styleable.MPAndroidChartUtil_mp_chart_auto_adapt_text_size, false);
            a.recycle();
            ((PieChartRendererFixCover) mRenderer).setMode(mode);
            ((PieChartRendererFixCover) mRenderer).setAuto_adapt_text_size(auto_adapt_text_size);
        }
    }
 
 
    @Override
    protected void init() {
        super.init();
                 //Replace mRenderer with our own PieChartRenderer here
        mRenderer = new PieChartRendererFixCover(this, mAnimator, mViewPortHandler);
    }
 
}