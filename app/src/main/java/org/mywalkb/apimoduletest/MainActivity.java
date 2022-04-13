package org.mywalkb.apimoduletest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Switch;

import java.util.ArrayList;

import io.github.xposed.xposedservice.IXposedService;
import io.github.xposed.xposedservice.models.Application;
import io.github.xposed.xposedservice.utils.ParceledListSlice;

public class MainActivity extends Activity
{
    private static final String TAG = "APIModuleTest";
    private IXposedService ser;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ser = IXposedService.Stub.asInterface((IBinder)getSystemService("LSPosed"));
    }

    public void click(android.view.View x)
    {
        try {
            Log.i(TAG, "getXposedVersionName " + ser.getXposedVersionName());
            Log.i(TAG, "getXposedApiVersion " + ser.getXposedApiVersion());
            Log.i(TAG, "getXposedVersionCode " + ser.getXposedVersionCode());
            Log.i(TAG, "getApi " + ser.getApi());
            var lst = ser.getModuleScope();
            for (var app: lst.getList()) {
                Log.i(TAG, "getModulScope " + app.packageName + " " + app.userId);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getSystemService RemoteException", e);
            return;
        }
        ArrayList<Application> listapp = new ArrayList<>();
        if(((Switch)findViewById(R.id.switch1)).isChecked())
        {
            var app = new Application();
            app.packageName = "org.mywalkb.apimoduletestapp";
            app.userId = 0;
            listapp.add(app);
        }
        try {
            ser.setModuleScope(new ParceledListSlice<>(listapp));
        } catch (RemoteException e) {
            Log.e(TAG, "setModuleScope RemoteException", e);
        }
    }
}
