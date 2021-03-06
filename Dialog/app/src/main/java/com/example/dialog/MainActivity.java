package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button alertBtn;
    Button listBtn;
    Button progressBtn;
    Button dateBtn;
    Button timeBtn;
    Button customDialogBtn;

    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertBtn = (Button)findViewById(R.id.btn_alert);
        listBtn = (Button)findViewById(R.id.btn_list);
        progressBtn = (Button)findViewById(R.id.btn_progress);
        dateBtn = (Button)findViewById(R.id.btn_date);
        timeBtn = (Button)findViewById(R.id.btn_time);
        customDialogBtn = (Button)findViewById(R.id.btn_custom);

        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customDialogBtn.setOnClickListener(this);
    }
    // #1 show Toast
    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
    // #2 dialog Listener
    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int i) {
            if(dialog==customDialog && i==DialogInterface.BUTTON_POSITIVE){
                showToast("custom dialog ?????? click.....");
            }
            else if(dialog==listDialog){
                String[] datas = getResources().getStringArray(R.array.dialog_array);
                showToast(datas[i]+" ?????? ???????????????.");
            }
            else if(dialog==alertDialog && i==DialogInterface.BUTTON_POSITIVE){
                showToast("alert dialog ok click.....");
            }
        }
    };
    // # onClick Listener
    @Override
    public void onClick(View view) {
        if(view==alertBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("??????");
            builder.setMessage("?????? ?????? ???????????????????");
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("NO", null);

            alertDialog = builder.create();
            alertDialog.show();
        }
        else if(view==listBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("?????? ?????????");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);

            builder.setPositiveButton("??????", null);
            builder.setNegativeButton("??????", null);
            listDialog=builder.create();
            listDialog.show();
        }
        else if(view==progressBtn){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIcon(android.R.drawable.ic_dialog_alert);
            progressDialog.setTitle("Wait..");
            progressDialog.setMessage("?????? ??????????????????. ????????? ???????????????.");

            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);

            progressDialog.show();
        }
        else if(view==dateBtn){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    showToast(y+":"+(m+1)+":"+d);
                }
            }, year, month, day);

            dateDialog.show();
        }
        else if(view==timeBtn){
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {
                    showToast(h + ":" + m);
                }
            }, hour, minute, false);

            timeDialog.show();
        }
        else if (view==customDialogBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.dialog_layout, null);
            builder.setView(v);

            builder.setPositiveButton("??????", dialogListener);
            builder.setNegativeButton("??????", null);

            customDialog = builder.create();
            customDialog.show();
        }
    }
}