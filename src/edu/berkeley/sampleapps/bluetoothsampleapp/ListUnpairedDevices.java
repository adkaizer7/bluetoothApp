package edu.berkeley.sampleapps.bluetoothsampleapp;

import edu.berkeley.monitoring.util.bluetooth.PairedBTDevices;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListUnpairedDevices extends Activity {

	private ArrayAdapter<String> mUnpairedDevicesArrayAdapter;
	
    private static final boolean D = true;
    private static final String TAG = "ListPairedDevices";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (D)
			Log.e(TAG,"++OnCreate++");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_unpaired_devices);
		
        setResult(Activity.RESULT_CANCELED);
        
        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices
        mUnpairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
        ListView pairedListView = (ListView) findViewById(R.id.listViewPairedBTdevices);
        pairedListView.setAdapter(mUnpairedDevicesArrayAdapter);
        // Get a set of currently paired devices
        // If there are paired devices, add each one to the ArrayAdapter
        if (MainActivityBluetoothSampleApp.listPairedDevices.size() > 0) {
            //findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (PairedBTDevices device : MainActivityBluetoothSampleApp.listPairedDevices) {
                mUnpairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.noPairedDevices).toString();
            mUnpairedDevicesArrayAdapter.add(noDevices);
        }
        

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_unpaired_devices, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
