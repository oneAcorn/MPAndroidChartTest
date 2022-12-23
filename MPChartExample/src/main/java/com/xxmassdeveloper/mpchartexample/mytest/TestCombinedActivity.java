package com.xxmassdeveloper.mpchartexample.mytest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.R;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * Created by acorn on 2022/11/4.
 */
public class TestCombinedActivity extends DemoBase {
    private CombinedChart chart;
    private Button testBtn;
    private LineDataSet set1, set2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);
        chart = findViewById(R.id.chart1);
        testBtn = findViewById(R.id.testBtn);

        initChart();
        initData();

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set1.addEntry(new Entry(getRandom(-10, 10), getRandom(-199, 200)));
                set2.addEntry(new Entry(getRandom(-100, 100), getRandom(-100, 100)));
                chart.getData().notifyDataChanged();
                // let the chart know it's data has changed
                chart.notifyDataSetChanged();
                // move to the latest entry(this automatically refreshes the chart (calls invalidate()))
                chart.moveViewToX(chart.getData().getEntryCount());
            }
        });
    }

    private void initChart() {
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        chart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.SCATTER, CombinedChart.DrawOrder.LINE
        });

        Legend l = chart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
//        xAxis.setAxisMinimum(0f);
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return months[(int) value % months.length];
//            }
//        });
    }

    private void initData() {
        LineData lineData = new LineData();
        set1 = generateLineSet("a", Color.GREEN);
        set2 = generateLineSet("b", Color.BLUE);
        CombinedData data = new CombinedData();
        data.setData(lineData);
//        chart.getXAxis().setAxisMaximum(data.getXMax() + 0.25f);
        chart.setData(data);
        chart.getLineData().addDataSet(set1);
        chart.getLineData().addDataSet(set2);

        chart.invalidate();
    }

    private LineDataSet generateLineSet(String lable, int color) {
        ArrayList<Entry> entries = new ArrayList<>();

        for (int index = 0; index < 22; index++)
            entries.add(new Entry(index + 0.5f, getRandom(15, 5)));
        LineDataSet set = new LineDataSet(entries, lable);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(color);
        set.setLineWidth(2f);
        set.setDrawValues(false);
        return set;
    }

    @Override
    protected void saveToGallery() {

    }
}
