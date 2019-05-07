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
//
// org.dahai.UniversalUARTSenderBLE
//
package org.lembed.bleSerialPort;

import android.app.*;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.MotionEvent;
import android.content.SharedPreferences;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static org.lembed.bleSerialPort.R.id.VBar;
import static org.lembed.bleSerialPort.R.id.btnSettings;


public class MainActivity extends Activity implements BLeSerialPortService.Callback, View.OnClickListener, View.OnTouchListener {

    // UI elements
    private TextView messages;
    private EditText input;
    private Button   connect;
    private Button   btn1sender;
    private Button   btn2sender;
    private Button   btn3sender;
//    private Button   btn4sender;
//    private Button   btn5sender;
//    private Button   btn6sender;
//    private Button   btn7sender;
//    private Button   btn8sender;
//    private Button   btn9sender;
    private Button   btn10sender;
    private Button   btn11sender;
//    private Button   btn12sender;
    private Button   btnSettingsAct;
    private String   btnAPressValue;
    private String   btnAReleaseValue;
    private String   btnUPPressValue;
    private String   btnUPReleaseValue;
    private String   btnBPressValue;
    private String   btnBReleaseValue;
    private String   btnLEFTPressValue;
    private String   btnLEFTReleaseValue;
    private String   btnCENTERPressValue;
    private String   btnCENTERReleaseValue;
    private String   btnRIGHTPressValue;
    private String   btnRIGHTReleaseValue;
    private String   btnTLPressValue;
    private String   btnTLReleaseValue;
    private String   btnDOWNPressValue;
    private String   btnDOWNReleaseValue;
    private String   btnTRPressValue;
    private String   btnTRReleaseValue;
    private String   btn1PressValue;
    private String   btn1ReleaseValue;
    private String   btn2PressValue;
    private String   btn2ReleaseValue;
    private String   btn3PressValue;
    private String   btn3ReleaseValue;
    private String   btn4PressValue;
    private String   btn4ReleaseValue;
    private String   btn5PressValue;
    private String   btn5ReleaseValue;
    private String   btn6PressValue;
    private String   btn6ReleaseValue;
    private String   btn7PressValue;
    private String   btn7ReleaseValue;
    private String   btn8PressValue;
    private String   btn8ReleaseValue;
    private String   btn9PressValue;
    private String   btn9ReleaseValue;
    private String   btn0PressValue;
    private String   btn0ReleaseValue;
    private String   btnCPressValue;
    private String   btnCReleaseValue;
    private String   btnDPressValue;
    private String   btnDReleaseValue;
    private SeekBar  sb_vertical;
    private SeekBar  sb_horizontal;
    private String   connectedDeviceName;

    // BLE serial port instance.  This is defined in BLeSerialPortService.java.
    private BLeSerialPortService serialPort;
    private final int REQUEST_DEVICE = 3;
    private final int REQUEST_ENABLE_BT = 2;
    private  int rindex = 0;
//    private boolean receive_occur = true;
//    private boolean receive_first_wait = false;
    long beginMillis = System.currentTimeMillis();
    //
    SharedPreferences sharedpreferences;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder rawBinder) {
            serialPort = ((BLeSerialPortService.LocalBinder) rawBinder).getService()

                         //register the application context to service for callback
                         .setContext(getApplicationContext())
                         .registerCallback(MainActivity.this);
            Log.i(MainActivity.class.getSimpleName(), "========> mServiceConnection onServiceConnected");
        }

        public void onServiceDisconnected(ComponentName classname) {
            serialPort.unregisterCallback(MainActivity.this)
            //Close the bluetooth gatt connection.
            .close();
        }
    };

    // Write some text to the messages text view.
    private void writeLine(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messages.append("\n"+text);
//                messages.append("\n");
            }
        });
    }

    // Handler for mouse click on the send button.
    public void sendView(View view) {
        String message = input.getText().toString();
        serialPort.send(message);
    }

    //
    private void readPref() {
        Boolean changed = false;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        //
        if (pref.contains("btnAPressValueKey")) {
            btnAPressValue = pref.getString("btnAPressValueKey","");
        } else {
            editor
                    .putString("btnAPressValueKey","A");
            changed = true;
        }
        if (pref.contains("btnAReleaseValueKey")) {
            btnAReleaseValue = pref.getString("btnAReleaseValueKey","");
        } else {
            editor
                    .putString("btnAReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnUPPressValueKey")) {
            btnUPPressValue = pref.getString("btnUPPressValueKey","");
        } else {
            editor
                    .putString("btnUPPressValueKey","1");
            changed = true;
        }
        if (pref.contains("btnUPReleaseValueKey")) {
            btnUPReleaseValue = pref.getString("btnUPReleaseValueKey","");
        } else {
            editor
                    .putString("btnUPReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnBPressValueKey")) {
            btnBPressValue = pref.getString("btnBPressValueKey","");
        } else {
            editor
                    .putString("btnBPressValueKey","B");
            changed = true;
        }
        if (pref.contains("btnBReleaseValueKey")) {
            btnBReleaseValue = pref.getString("btnBReleaseValueKey","");
        } else {
            editor
                    .putString("btnBReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnLEFTPressValueKey")) {
            btnLEFTPressValue = pref.getString("btnLEFTPressValueKey","");
        } else {
            editor
                    .putString("btnLEFTPressValueKey","3");
            changed = true;
        }
        if (pref.contains("btnLEFTReleaseValueKey")) {
            btnLEFTReleaseValue = pref.getString("btnLEFTReleaseValueKey","");
        } else {
            editor
                    .putString("btnLEFTReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnCENTERPressValueKey")) {
            btnCENTERPressValue = pref.getString("btnCENTERPressValueKey","");
        } else {
            editor
                    .putString("btnCENTERPressValueKey","0");
            changed = true;
        }
        if (pref.contains("btnCENTERReleaseValueKey")) {
            btnCENTERReleaseValue = pref.getString("btnCENTERReleaseValueKey","");
        } else {
            editor
                    .putString("btnCENTERReleaseValueKey","0");
            changed = true;
        }
        //
        if (pref.contains("btnRIGHTPressValueKey")) {
            btnRIGHTPressValue = pref.getString("btnRIGHTPressValueKey","");
        } else {
            editor
                    .putString("btnRIGHTPressValueKey","5");
            changed = true;
        }
        if (pref.contains("btnRIGHTReleaseValueKey")) {
            btnRIGHTReleaseValue = pref.getString("btnRIGHTReleaseValueKey","");
        } else {
            editor
                    .putString("btnRIGHTReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnTLPressValueKey")) {
            btnTLPressValue = pref.getString("btnTLPressValueKey","");
        } else {
            editor
                    .putString("btnTLPressValueKey","6");
            changed = true;
        }
        if (pref.contains("btnTLReleaseValueKey")) {
            btnTLReleaseValue = pref.getString("btnTLReleaseValueKey","");
        } else {
            editor
                    .putString("btnTLReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnDOWNPressValueKey")) {
            btnDOWNPressValue = pref.getString("btnDOWNPressValueKey","");
        } else {
            editor
                    .putString("btnDOWNPressValueKey","7");
            changed = true;
        }
        if (pref.contains("btnDOWNReleaseValueKey")) {
            btnDOWNReleaseValue = pref.getString("btnDOWNReleaseValueKey","");
        } else {
            editor
                    .putString("btnDOWNReleaseValueKey","4");
            changed = true;
        }
        //
        if (pref.contains("btnTRPressValueKey")) {
            btnTRPressValue = pref.getString("btnTRPressValueKey","");
        } else {
            editor
                    .putString("btnTRPressValueKey","8");
            changed = true;
        }
        if (pref.contains("btnTRReleaseValueKey")) {
            btnTRReleaseValue = pref.getString("btnTRReleaseValueKey","");
        } else {
            editor
                    .putString("btnTRReleaseValueKey","4");
            changed = true;
        }
        //
//        if (pref.contains("btn1PressValueKey")) {
//            btn1PressValue = pref.getString("btn1PressValueKey","");
//        } else {
//            editor
//                    .putString("btn1PressValueKey","0");
//            changed = true;
//        }
//        if (pref.contains("btn1ReleaseValueKey")) {
//            btn1ReleaseValue = pref.getString("btn1ReleaseValueKey","");
//        } else {
//            editor
//                    .putString("btn1ReleaseValueKey","0");
//            changed = true;
//        }

        if (changed) editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab references to UI elements.
        messages = (TextView) findViewById(R.id.messages);
        input = (EditText) findViewById(R.id.input);

        // Enable auto-scroll in the TextView
        messages.setMovementMethod(new ScrollingMovementMethod());

        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(this);
        btnSettingsAct = (Button) findViewById(R.id.btnSettings);
        btnSettingsAct.setOnClickListener(this);

//        findViewById(R.id.button0).setOnTouchListener(this);
//        btn1sender = (Button) findViewById(R.id.button1);
//        btn1sender.setOnTouchListener(this);
//        btn2sender = (Button) findViewById(R.id.button2);
//        btn2sender.setOnTouchListener(this);
//        btn3sender = (Button) findViewById(R.id.button3);
//        btn3sender.setOnTouchListener(this);
//        btn4sender = (Button) findViewById(R.id.button4);
//        btn4sender.setOnTouchListener(this);
//        btn5sender = (Button) findViewById(R.id.button5);
//        btn5sender.setOnTouchListener(this);
//        btn6sender = (Button) findViewById(R.id.button6);
//        btn6sender.setOnTouchListener(this);
//        btn7sender = (Button) findViewById(R.id.button7);
//        btn7sender.setOnTouchListener(this);
//        btn8sender = (Button) findViewById(R.id.button8);
//        btn8sender.setOnTouchListener(this);
//        btn9sender = (Button) findViewById(R.id.button9);
//        btn9sender.setOnTouchListener(this);
        btn10sender = (Button) findViewById(R.id.button10);
        btn10sender.setOnTouchListener(this);
        btn11sender = (Button) findViewById(R.id.button11);
        btn11sender.setOnTouchListener(this);
//        btn12sender = (Button) findViewById(R.id.button12);
//        btn12sender.setOnTouchListener(this);
        findViewById(R.id.buttonUp).setOnTouchListener(this);
        findViewById(R.id.buttonLeft).setOnTouchListener(this);
        findViewById(R.id.buttonCenter).setOnTouchListener(this);
        findViewById(R.id.buttonRight).setOnTouchListener(this);
        findViewById(R.id.buttonTurnLeft).setOnTouchListener(this);
        findViewById(R.id.buttonDown).setOnTouchListener(this);
        findViewById(R.id.buttonTurnRight).setOnTouchListener(this);

        // bind and start the bluetooth service
        Intent bindIntent = new Intent(this, BLeSerialPortService.class);
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

        //
        readPref();

        //
        sb_vertical = (SeekBar) findViewById(R.id.VBar);
        sb_vertical.setOnTouchListener(this);
        sb_vertical.setProgress(sb_vertical.getMax()/2);
        sb_horizontal = (SeekBar) findViewById(R.id.HBar);
        sb_horizontal.setOnTouchListener(this);
        sb_horizontal.setProgress(sb_horizontal.getMax()/2);

    }

    public int old_vbar_progress = -1;
    public int old_hbar_progress = -1;
//    public int vbar_delay_send_counter = 0;
//    public int hbar_delay_send_counter = 0;

    // OnResume, called right before UI is displayed.  Connect to the bluetooth device.
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Button bt = (Button) v;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showMessage("touch down");
                if (v.getId() == R.id.button10) {
                    serialPort.send(btnAPressValue + "\n");
                }
                if (v.getId() == R.id.buttonUp) {
                    serialPort.send(btnUPPressValue + "\n");
                }
                if (v.getId() == R.id.button11) {
                    serialPort.send(btnBPressValue + "\n");
                }
                if (v.getId() == R.id.buttonLeft) {
                    serialPort.send(btnLEFTPressValue + "\n");
                }
                if (v.getId() == R.id.buttonCenter) {
                    serialPort.send(btnCENTERPressValue + "\n");
                }
                if (v.getId() == R.id.buttonRight) {
                    serialPort.send(btnRIGHTPressValue + "\n");
                }
                if (v.getId() == R.id.buttonTurnLeft) {
                    serialPort.send(btnTLPressValue + "\n");
                }
                if (v.getId() == R.id.buttonDown) {
                    serialPort.send(btnDOWNPressValue + "\n");
                }
                if (v.getId() == R.id.buttonTurnRight) {
                    serialPort.send(btnTRPressValue + "\n");
                }
//                if (v.getId() == R.id.button1) {
//                    serialPort.send(btn1PressValue + "\n");
//                }
                if (v.getId() == R.id.VBar) {
                    old_vbar_progress = -1;
                }
                    break;
            case MotionEvent.ACTION_MOVE:
//                showMessage("touch move");
                if (v.getId() == R.id.VBar) {
                    if(System.currentTimeMillis() - beginMillis > 100){
                    if(old_vbar_progress != sb_vertical.getProgress()) {
                        old_vbar_progress = sb_vertical.getProgress();
                        beginMillis = System.currentTimeMillis();

                            serialPort.send("V" + sb_vertical.getProgress() + "\n");
                    }
                    return false;
                }}
                if (v.getId() == R.id.HBar) {
                    if(System.currentTimeMillis() - beginMillis > 100){
                    if(old_hbar_progress != sb_horizontal.getProgress()) {
                        old_hbar_progress = sb_horizontal.getProgress();
                        beginMillis = System.currentTimeMillis();

                            serialPort.send("H" + sb_horizontal.getProgress() + "\n");
                    }
                    return false;
                }}
                break;
//            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                showMessage("touch up");
                if (v.getId() == R.id.button10) {
                    serialPort.send(btnAReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonUp) {
                    serialPort.send(btnUPReleaseValue + "\n");
                }
                if (v.getId() == R.id.button11) {
                    serialPort.send(btnBReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonLeft) {
                    serialPort.send(btnLEFTReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonCenter) {
                    serialPort.send(btnCENTERReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonRight) {
                    serialPort.send(btnRIGHTReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonTurnLeft) {
                    serialPort.send(btnTLReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonDown) {
                    serialPort.send(btnDOWNReleaseValue + "\n");
                }
                if (v.getId() == R.id.buttonTurnRight) {
                    serialPort.send(btnTRReleaseValue + "\n");
                }
//                if (v.getId() == R.id.button1) {
//                    serialPort.send(btn1ReleaseValue + "\n");
//                }
                if (v.getId() == R.id.VBar) {
                    sb_vertical.setProgress(sb_vertical.getMax()/2);
                    serialPort.send("V"+ sb_vertical.getProgress() + "\n");
                }
                if (v.getId() == R.id.HBar) {
                    sb_horizontal.setProgress(sb_horizontal.getMax()/2);
                    serialPort.send("H"+ sb_horizontal.getProgress() + "\n");
                }
                break;
            default:
                break;
        }
        return true;
//        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();

        // set the screen to portrait
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        // if the bluetooth adatper is not support and enabled
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            finish();
        }

        // request to open the bluetooth adapter
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        //
        readPref();
    }

    // OnStop, close the service connection
    @Override
    protected void onStop() {
        super.onStop();
        serialPort.stopSelf();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public  void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        Button bt = (Button) v;
        if (v.getId() == R.id.connect) {
            // the device can send data to
            if (bt.getText().equals(getResources().getString(R.string.send))) {
                sendView(v);
            }
            // if the device is not connectted
            if (bt.getText().equals(getResources().getString(R.string.connect))) {
                Intent intent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(intent, REQUEST_DEVICE);
            }
        }
        if (v.getId() == R.id.btnSettings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // serial port Callback handlers.
    @Override
    public void onConnected(Context context) {
        // when serial port device is connected
        writeLine("Connected!");
        // Enable the send button
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                connect = (Button) findViewById(R.id.connect);
//                connect.setText(R.string.send);
                String msg = getText(R.string.connected) + " " + connectedDeviceName;
                connect.setText(msg);
            }
        });
    }

    @Override
    public void onConnectFailed(Context context) {
        // when some error occured which prevented serial port connection from completing.
        writeLine("Error connecting to device!");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                connect = (Button) findViewById(R.id.connect);
                connect.setText(R.string.connect);
            }
        });
    }

    @Override
    public void onDisconnected(Context context) {
        //when device disconnected.
        writeLine("Disconnected!");
        // update the send button text to connect
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                connect = (Button)findViewById(R.id.connect);
                connect.setText(R.string.connect);
            }
        });
    }

    @Override
    public void onCommunicationError(int status, String msg) {
        // get the send value bytes
        if (status > 0) {
        }// when the send process found error, for example the send thread  time out
        else {
            writeLine("send error status = " + status);
        }
//        connect = (Button)findViewById(R.id.connect);
//        connect.setText(R.string.connect);
    }

    @Override
    public void onReceive(Context context, BluetoothGattCharacteristic rx) {
        String msg = rx.getStringValue(0);
        rindex = rindex + msg.length();
        writeLine("> " + rindex + ":" + msg);

    }

    @Override
    public void onDeviceFound(BluetoothDevice device) {
        // Called when a UART device is discovered (after calling startScan).
        writeLine("Found device : " + device.getAddress());
        writeLine("Waiting for a connection ...");

    }

    @Override
    public void onDeviceInfoAvailable() {
        writeLine(serialPort.getDeviceInfo());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case REQUEST_DEVICE:
            //When the DeviceListActivity return, with the selected device address
            if (resultCode == Activity.RESULT_OK && data != null) {
                String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
                serialPort.connect(device);
//                showMessage("dahai is here");
                writeLine("Try to connect...");
                connectedDeviceName = (device.getName() != null)?device.getName():"";
                connect = (Button) findViewById(R.id.connect);
                connect.setText(R.string.pleasewait);
//                if (device.getName() != null) showMessage(device.getName());

            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();
            } else {
                // User did not enable Bluetooth or an error occurred
                Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
            }
            break;
        default:
            break;
        }
    }

    private void showMessage(String msg) {
        Log.e(MainActivity.class.getSimpleName(), msg);
    }

}
