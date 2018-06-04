package com.example.denis.vjetgrouptestapp.ui.dialog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.data.model.Source;
import com.example.denis.vjetgrouptestapp.data.source.SearchProperties;
import com.example.denis.vjetgrouptestapp.ui.adapters.SourcesAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DialogSearchProperties extends AlertDialog implements View.OnClickListener {
    private final String DATE_PATTERN = "yyyy-MM-dd";

    private Context mContext;
    private DialogSearchCallback mCallback;

    private SourcesAdapter mAdapter;
    private Calendar mCurrentDate;
    private Spinner mSpinner;
    private Button mBtnDateFrom;
    private Button mBtnDateTo;

    private SearchProperties mProperties;

    public DialogSearchProperties(@NonNull Context context, DialogSearchCallback dialogSearchCallback) {
        super(context);
        mContext = context;
        mCallback = dialogSearchCallback;
        mCurrentDate = Calendar.getInstance();
        mAdapter = new SourcesAdapter();
        mProperties = new SearchProperties();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_search);

        mSpinner = findViewById(R.id.spinner_sort_by);
        mBtnDateFrom = findViewById(R.id.button_date_from);
        mBtnDateTo = findViewById(R.id.button_date_to);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_sources);
        Button btnStartSearch = findViewById(R.id.button_start_search);
        Button btnCancel = findViewById(R.id.button_cancel);

        mBtnDateFrom.setOnClickListener(this);
        mBtnDateTo.setOnClickListener(this);
        btnStartSearch.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormatServer = new SimpleDateFormat(DATE_PATTERN);
        dateFormatServer.setTimeZone(mCurrentDate.getTimeZone());
        String strCurrentDate = dateFormatServer.format(mCurrentDate.getTime());

        mBtnDateFrom.setText(strCurrentDate);
        mBtnDateTo.setText(strCurrentDate);

        ArrayAdapter<CharSequence> mAdapterSpinner =
                ArrayAdapter.createFromResource(mContext,
                        R.array.spinner_data_sort_by,
                        android.R.layout.simple_spinner_dropdown_item);
        mAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapterSpinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_date_from:
                showDatePicker((view, year, monthOfYear, dayOfMonth) -> {
                    String strDate = (year + "-" + (++monthOfYear) + "-" + dayOfMonth);
                    mBtnDateFrom.setText(strDate);
                });
                break;
            case R.id.button_date_to:
                showDatePicker((view, year, monthOfYear, dayOfMonth) -> {
                    String strDate = (year + "-" + (++monthOfYear) + "-" + dayOfMonth);
                    mBtnDateTo.setText(strDate);
                });
                break;
            case R.id.button_start_search:
                onSearch();
                break;
            case R.id.button_cancel:
                dismiss();
                break;
        }
    }

    private void onSearch() {
        if (mCallback != null && mAdapter != null) {
            List<Source> sources = mAdapter.getSources();
            if (!sources.isEmpty()) {
                mProperties.setPage(1);
                mProperties.setSources(sources);
                mProperties.setSortBy(mSpinner.getSelectedItem().toString());
                mProperties.setFromDate(mBtnDateFrom.getText().toString());
                mProperties.setToDate(mBtnDateTo.getText().toString());
                mCallback.onSearch(mProperties);
                dismiss();
            } else {
                Toast.makeText(mContext, R.string.search_warning, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showDatePicker(DatePickerDialog.OnDateSetListener listener) {
        new DatePickerDialog(mContext,
                listener,
                mCurrentDate.get(Calendar.YEAR),
                mCurrentDate.get(Calendar.MONTH),
                mCurrentDate.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setSources(@NonNull List<Source> sources) {
        mAdapter.setSources(sources);
    }
}
