// Copyright (c) 2016 Thomas

// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the "Software"),
// to deal in the Software without restriction, including without limitation
// the rights to use, copy, modify, merge, publish, distribute, sublicense,
// and/or sell copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
// DEALINGS IN THE SOFTWARE.

package org.lembed.bleSerialPort;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings extends Activity implements View.OnClickListener{
    private final String TAG = Settings.class.getSimpleName();

//    private BluetoothAdapter mBluetoothAdapter;
//    private TextView mEmptyList;
//
//    List<BluetoothDevice> deviceList;
//    private DeviceAdapter deviceAdapter;
//    Map<String, Integer> devRssiValues;
//
//    private static final long SCAN_PERIOD = 10000; //10 seconds
//    private Handler mHandler;
//    private boolean mScanning;
    private Button btnSave;
    private Button btnCancel;
    private EditText btnAPressValue;
    private EditText btnAReleaseValue;
    private EditText btnUPPressValue;
    private EditText btnUPReleaseValue;
    private EditText btnBPressValue;
    private EditText btnBReleaseValue;
    private EditText btnLEFTPressValue;
    private EditText btnLEFTReleaseValue;
    private EditText btnCENTERPressValue;
    private EditText btnCENTERReleaseValue;
    private EditText btnRIGHTPressValue;
    private EditText btnRIGHTReleaseValue;
    private EditText btnTLPressValue;
    private EditText btnTLReleaseValue;
    private EditText btnDOWNPressValue;
    private EditText btnDOWNReleaseValue;
    private EditText btnTRPressValue;
    private EditText btnTRReleaseValue;
    private EditText btn1PressValue;
    private EditText btn1ReleaseValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        //
        btnAPressValue = (EditText) findViewById(R.id.btnAPressValue);
        btnAReleaseValue = (EditText) findViewById(R.id.btnAReleaseValue);
        btnUPPressValue = (EditText) findViewById(R.id.btnUPPressValue);
        btnUPReleaseValue = (EditText) findViewById(R.id.btnUPReleaseValue);
        btnBPressValue = (EditText) findViewById(R.id.btnBPressValue);
        btnBReleaseValue = (EditText) findViewById(R.id.btnBReleaseValue);
        btnLEFTPressValue = (EditText) findViewById(R.id.btnLEFTPressValue);
        btnLEFTReleaseValue = (EditText) findViewById(R.id.btnLEFTReleaseValue);
        btnCENTERPressValue = (EditText) findViewById(R.id.btnCENTERPressValue);
        btnCENTERReleaseValue = (EditText) findViewById(R.id.btnCENTERReleaseValue);
        btnRIGHTPressValue = (EditText) findViewById(R.id.btnRIGHTPressValue);
        btnRIGHTReleaseValue = (EditText) findViewById(R.id.btnRIGHTReleaseValue);
        btnTLPressValue = (EditText) findViewById(R.id.btnTLPressValue);
        btnTLReleaseValue = (EditText) findViewById(R.id.btnTLReleaseValue);
        btnDOWNPressValue = (EditText) findViewById(R.id.btnDOWNPressValue);
        btnDOWNReleaseValue = (EditText) findViewById(R.id.btnDOWNReleaseValue);
        btnTRPressValue = (EditText) findViewById(R.id.btnTRPressValue);
        btnTRReleaseValue = (EditText) findViewById(R.id.btnTRReleaseValue);
//        btn1PressValue = (EditText) findViewById(R.id.btn1PressValue);
//        btn1ReleaseValue = (EditText) findViewById(R.id.btn1ReleaseValue);
//        scan_init();
        //
        readPref();
    }

    @Override
    public void onClick(View v) {
        Button bt = (Button) v;
        if (v.getId() == R.id.btnSave) {
            savePref();
            finish();
        }
        if (v.getId() == R.id.btnCancel) {
            finish();
        }
    }


    private void savePref() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0); // 0 - for private mode
        pref.edit()
                .putString("btnAPressValueKey",btnAPressValue.getText().toString())
                .putString("btnAReleaseValueKey",btnAReleaseValue.getText().toString())
                .putString("btnUPPressValueKey",btnUPPressValue.getText().toString())
                .putString("btnUPReleaseValueKey",btnUPReleaseValue.getText().toString())
                .putString("btnBPressValueKey",btnBPressValue.getText().toString())
                .putString("btnBReleaseValueKey",btnBReleaseValue.getText().toString())
                .putString("btnLEFTPressValueKey",btnLEFTPressValue.getText().toString())
                .putString("btnLEFTReleaseValueKey",btnLEFTReleaseValue.getText().toString())
                .putString("btnCENTERPressValueKey",btnCENTERPressValue.getText().toString())
                .putString("btnCENTERReleaseValueKey",btnCENTERReleaseValue.getText().toString())
                .putString("btnRIGHTPressValueKey",btnRIGHTPressValue.getText().toString())
                .putString("btnRIGHTReleaseValueKey",btnRIGHTReleaseValue.getText().toString())
                .putString("btnTLPressValueKey",btnTLPressValue.getText().toString())
                .putString("btnTLReleaseValueKey",btnTLReleaseValue.getText().toString())
                .putString("btnDOWNPressValueKey",btnDOWNPressValue.getText().toString())
                .putString("btnDOWNReleaseValueKey",btnDOWNReleaseValue.getText().toString())
                .putString("btnTRPressValueKey",btnTRPressValue.getText().toString())
                .putString("btnTRReleaseValueKey",btnTRReleaseValue.getText().toString())
//                .putString("btn1PressValueKey",btn1PressValue.getText().toString())
//                .putString("btn1ReleaseValueKey",btn1ReleaseValue.getText().toString())
                .commit();

    }
    private void readPref() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0); // 0 - for private mode

        if (pref.contains("btnAPressValueKey")) {
            btnAPressValue.setText(pref.getString("btnAPressValueKey",""));
        }
        if (pref.contains("btnAReleaseValueKey")) {
            btnAReleaseValue.setText(pref.getString("btnAReleaseValueKey",""));
        }
        if (pref.contains("btnUPPressValueKey")) {
            btnUPPressValue.setText(pref.getString("btnUPPressValueKey",""));
        }
        if (pref.contains("btnUPReleaseValueKey")) {
            btnUPReleaseValue.setText(pref.getString("btnUPReleaseValueKey",""));
        }
        if (pref.contains("btnBPressValueKey")) {
            btnBPressValue.setText(pref.getString("btnBPressValueKey",""));
        }
        if (pref.contains("btnBReleaseValueKey")) {
            btnBReleaseValue.setText(pref.getString("btnBReleaseValueKey",""));
        }
        if (pref.contains("btnLEFTPressValueKey")) {
            btnLEFTPressValue.setText(pref.getString("btnLEFTPressValueKey",""));
        }
        if (pref.contains("btnLEFTReleaseValueKey")) {
            btnLEFTReleaseValue.setText(pref.getString("btnLEFTReleaseValueKey",""));
        }
        if (pref.contains("btnCENTERPressValueKey")) {
            btnCENTERPressValue.setText(pref.getString("btnCENTERPressValueKey",""));
        }
        if (pref.contains("btnCENTERReleaseValueKey")) {
            btnCENTERReleaseValue.setText(pref.getString("btnCENTERReleaseValueKey",""));
        }
        if (pref.contains("btnRIGHTPressValueKey")) {
            btnRIGHTPressValue.setText(pref.getString("btnRIGHTPressValueKey",""));
        }
        if (pref.contains("btnRIGHTReleaseValueKey")) {
            btnRIGHTReleaseValue.setText(pref.getString("btnRIGHTReleaseValueKey",""));
        }
        if (pref.contains("btnTLPressValueKey")) {
            btnTLPressValue.setText(pref.getString("btnTLPressValueKey",""));
        }
        if (pref.contains("btnTLReleaseValueKey")) {
            btnTLReleaseValue.setText(pref.getString("btnTLReleaseValueKey",""));
        }
        if (pref.contains("btnDOWNPressValueKey")) {
            btnDOWNPressValue.setText(pref.getString("btnDOWNPressValueKey",""));
        }
        if (pref.contains("btnDOWNReleaseValueKey")) {
            btnDOWNReleaseValue.setText(pref.getString("btnDOWNReleaseValueKey",""));
        }
        if (pref.contains("btnTRPressValueKey")) {
            btnTRPressValue.setText(pref.getString("btnTRPressValueKey",""));
        }
        if (pref.contains("btnTRReleaseValueKey")) {
            btnTRReleaseValue.setText(pref.getString("btnTRReleaseValueKey",""));
        }
//        if (pref.contains("btn1PressValueKey")) {
//            btn1PressValue.setText(pref.getString("btn1PressValueKey",""));
//        }
//        if (pref.contains("btn1ReleaseValueKey")) {
//            btn1ReleaseValue.setText(pref.getString("btn1ReleaseValueKey",""));
//        }


    }



    private void showMessage(String msg) {
        String TAG = Settings.class.getSimpleName();
        Log.e(TAG, msg);
    }
}
