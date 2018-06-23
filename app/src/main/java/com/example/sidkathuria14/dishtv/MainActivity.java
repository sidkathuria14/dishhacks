package com.example.sidkathuria14.dishtv;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Retrofit;

public class MainActivity  extends DemoBase implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {
    private PieChart mChart;
    public static final String TAG = "main";
    EditText et;
    String keyword;
    ArrayList<PieEntry> my_entries;
//    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    ArrayList<String> happy_buffer,sad_buffer,fear_buffer,love_buffer,angry_buffer;
    Retrofit retrofit;
    int angry = 20,sad = 10,happy = 20,love = 40,fear = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tvX = (TextView) findViewById(R.id.tvXMax);
//        tvY = (TextView) findViewById(R.id.tvYMax);
//et = (EditText)findViewById(R.id.etInput);

//        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
//        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
//        mSeekBarX.setProgress(4);
//        mSeekBarY.setProgress(10);
//        retrofit = new Retrofit.Builder()
//                .baseUrl()
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        keyword = getIntent().getStringExtra("keyword");
//      final MYApi myApi = retrofit.create(MYApi.class);

        my_entries = new ArrayList<>();
        my_entries.add(new PieEntry(happy, "happy"));
        my_entries.add(new PieEntry(sad, "sad"));
        my_entries.add(new PieEntry(angry, "angry"));
        my_entries.add(new PieEntry(fear, "fear"));
        my_entries.add(new PieEntry(love, "love"));
//        happy_buffer = new ArrayList<>();sad_buffer = new ArrayList<>();fear_buffer = new ArrayList<>();
//                love_buffer = new ArrayList<>(); angry_buffer = new ArrayList<>();

//        ((Button)findViewById(R.id.btnInput)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                keyword = et.getText().toString();
//                myApi.getTweets(keyword).enqueue(new Callback<main>() {
//        @Override
//        public void onResponse (Call < main > call, Response< main > response){
//            Log.d(TAG, "onResponse: ");
//            angry = response.body().getTotal_angry();
//            happy = response.body().getTotal_happy();
//            sad = response.body().getTotal_sad();
//            love = response.body().getTotal_love();
//            fear = response.body().getTotal_fear();
//
//            angry_buffer = response.body().getAngry_buffer();
//            love_buffer = response.body().getLove_buffer();
//            sad_buffer = response.body().getSad_buffer();
//            happy_buffer = response.body().getHappy_buffer();
//            fear_buffer = response.body().getFear_buffer();
//
//            my_entries.add(new PieEntry(happy, "happy"));
//            my_entries.add(new PieEntry(sad, "sad"));
//            my_entries.add(new PieEntry(angry, "angry"));
//            my_entries.add(new PieEntry(fear, "fear"));
//            my_entries.add(new PieEntry(love, "love"));
//        }
//
//        @Override
//        public void onFailure (Call< main > call, Throwable t){
//            Log.d(TAG, "onFailure: ");
//        }
//
//                });

//        });





        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

//        setData(5, 100);

        PieDataSet ds = new PieDataSet(my_entries,"Tweet Results");

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        ds.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(ds);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

//        mSeekBarX.setOnSeekBarChangeListener(this);
//        mSeekBarY.setOnSeekBarChangeListener(this);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);

try {
    JSONObject obj = new JSONObject(readJSONFromAsset());
    Log.d(TAG, "onCreate: " + obj);
    Log.d(TAG, "onCreate: " + obj.get("total_happy"));
}catch(JSONException jsone){
    Log.d(TAG, "onCreate: " + "jsone");
}

    }
    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleIcons: {
                for (IDataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawIcons(!set.isDrawIconsEnabled());

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHole: {
                if (mChart.isDrawHoleEnabled())
                    mChart.setDrawHoleEnabled(false);
                else
                    mChart.setDrawHoleEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionDrawCenter: {
                if (mChart.isDrawCenterTextEnabled())
                    mChart.setDrawCenterText(false);
                else
                    mChart.setDrawCenterText(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleXVals: {

                mChart.setDrawEntryLabels(!mChart.isDrawEntryLabelsEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                // mChart.saveToGallery("title"+System.currentTimeMillis());
                mChart.saveToPath("title" + System.currentTimeMillis(), "");
                break;
            }
            case R.id.actionTogglePercent:
                mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
                mChart.invalidate();
                break;
            case R.id.animateX: {
                mChart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
            }
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//        tvX.setText("" + (mSeekBarX.getProgress()));
//        tvY.setText("" + (mSeekBarY.getProgress()));

//        setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
    }

//    private void setData(int count, float range) {
//
//        float mult = range;
//
//        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
//                    mParties[i % mParties.length],
//                    getResources().getDrawable(R.drawable.ic_launcher_background)));
//        }
//
//        PieDataSet dataSet = new PieDataSet(entries, "Tweet Results");
//
//        dataSet.setDrawIcons(false);
//
//        dataSet.setSliceSpace(3f);
//        dataSet.setIconsOffset(new MPPointF(0, 40));
//        dataSet.setSelectionShift(5f);
//
//        // add a lot of colors
//
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//
//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);
//
//        colors.add(ColorTemplate.getHoloBlue());
//
//        dataSet.setColors(colors);
//        //dataSet.setSelectionShift(0f);
//
//        PieData data = new PieData(dataSet);
//        data.setValueFormatter(new PercentFormatter());
//        data.setValueTextSize(11f);
//        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
//        mChart.setData(data);
//
//        // undo all highlights
//        mChart.highlightValues(null);
//
//        mChart.invalidate();
//    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Tweet Results\n Developed by Linguistic Pandas");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 10, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 17, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 11, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 17, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
        Intent i = new Intent(MainActivity.this,TweetsActivity.class);
        switch(h.getDataSetIndex()){
            case 0:
if(happy_buffer!=null)
                i.putExtra("happy",happy_buffer);
                startActivity(i);
                break;
            case 1:
                if(sad_buffer!=null)
                i.putExtra("sad",sad_buffer);
                startActivity(i);
                break;
            case 2:
                if(angry_buffer!=null)
                i.putExtra("angry",angry_buffer);
                startActivity(i);
                break;
            case 3:
                if(fear_buffer!=null)
                i.putExtra("fear",fear_buffer);
                startActivity(i);
                break;
            case 4:
                if(love_buffer!=null)
                i.putExtra("love",love_buffer);
                startActivity(i);
                break;
            default:


        }
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
    }

