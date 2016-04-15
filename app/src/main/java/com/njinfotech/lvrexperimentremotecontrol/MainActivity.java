package com.njinfotech.lvrexperimentremotecontrol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        markUsedButton(-1);
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

    public void markUsedButton(int act) {
        Button btnAct1 = (Button)findViewById(R.id.btnAction1);
        Button btnAct2 = (Button)findViewById(R.id.btnAction2);
        Button btnClear = (Button)findViewById(R.id.btnClear);

        switch (act) {
            case 1:
                btnAct1.setTextColor(getResources().getColor(R.color.colorAccent));
                btnAct2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnClear.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 2:
                btnAct1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnAct2.setTextColor(getResources().getColor(R.color.colorAccent));
                btnClear.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 0:
                btnAct1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnAct2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnClear.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            default:
                btnAct1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnAct2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btnClear.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    private void action(int act) {
        SendData data = new SendData(getApplicationContext(), false);
        data.execute(String.valueOf(act));
        markUsedButton(act);
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
