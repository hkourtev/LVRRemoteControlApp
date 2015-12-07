package com.njinfotech.lvrexperimentremotecontrol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void action1(View view) {
        action(1);
    }

    public void action2(View view) {
        action(2);
    }

    public void clear(View view) {
        confirmDialog();
    }

    private void action(int act) {
        SendData data = new SendData(getApplicationContext(), false);
        data.execute(String.valueOf(act));
    }


    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder
                .setMessage("Are you sure?")
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        confirmDialog2();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void confirmDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder
                .setMessage("Are you really really sure?")
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.action(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
