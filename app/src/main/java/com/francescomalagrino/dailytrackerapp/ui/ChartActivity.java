package com.francescomalagrino.dailytrackerapp.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.francescomalagrino.dailytrackerapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private ArrayList<Integer> historylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mPreferences =  PreferenceManager.getDefaultSharedPreferences(this);

        setupPieChart();
    }

    /**
     * Add data from the history list to the chart, setting the labels to the name of the mood
     */
    private void setupPieChart() {
        List<PieEntry> pieEntryList = new ArrayList<>();
        for (int i = 0; i < historylist.size(); i++) {
            switch (historylist.get(i)) {
                case 0 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.sad_mood)));
                    break;
                case 1 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.disappointed_mood)));
                    break;
                case 2 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.normal_mood)));
                    break;
                case 3 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.happy_mood)));
                    break;
                case 4 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.super_happy_mood)));
                    break;
            }
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Moods of the week");
        PieData pieData = new PieData(pieDataSet);

        ArrayList<Integer> colors = new ArrayList<>();

        // Setting the proper colors for the saved mood
        for (int j = 0; j < historylist.size(); j++) {
            switch (historylist.get(j)) {
                case 0:
                    colors.add(getResources().getColor(R.color.colorSad));
                    break;
                case 1:
                    colors.add(getResources().getColor(R.color.colorDisappointed));
                    break;
                case 2:
                    colors.add(getResources().getColor(R.color.colorNormal));
                    break;
                case 3:
                    colors.add(getResources().getColor(R.color.colorHappy));
                    break;
                case 4:
                    colors.add(getResources().getColor(R.color.colorSuperHappy));
                    break;
            }
        }

        pieDataSet.setColors(colors);

        PieChart pieChart = findViewById(R.id.mood_pie_chart);
        pieChart.setData(pieData);

        // Styling the pie chart
        pieDataSet.setSliceSpace(4f);
        pieChart.animateY(800, Easing.EaseInCirc);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(36f);
        pieChart.setCenterText(getResources().getString(R.string.normal_mood));
        pieChart.setCenterTextRadiusPercent(42f);


        // Disabling the legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        pieChart.invalidate();
    }

}