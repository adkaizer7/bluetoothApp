package edu.berkeley.sampleapps.bluetoothsampleapp;

import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.berkeley.monitoring.util.bluetooth.PairedBTDevices;

public class ListPairedDevices extends Activity {

	private ArrayAdapter<PairedBTDevices> mPairedDevicesArrayAdapter1;
	private ArrayAdapter<String> mPairedDevicesArrayAdapter;
	private BluetoothAdapter mbluetoothAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_paired_devices);
		// Set result CANCELED incase the user backs out
        setResult(Activity.RESULT_CANCELED);
        

        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices
        //mPairedDevicesArrayAdapter = new ArrayAdapter<PairedBTDevices>(this, R.layout.activity_list_paired_devices);
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
        ListView pairedListView = (ListView) findViewById(R.id.listViewPairedBTdevices);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        mbluetoothAdapter =  BluetoothAdapter.getDefaultAdapter();
        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mbluetoothAdapter.getBondedDevices();
        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            //findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.noPairedDevices).toString();
            mPairedDevicesArrayAdapter.add(noDevices);
        }
        
	}
	
	/**@Override
	public void onStart(){		
	}*/
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_paired_devices, menu);
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
