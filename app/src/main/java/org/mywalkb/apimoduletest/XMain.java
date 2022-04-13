package org.mywalkb.apimoduletest;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XMain implements IXposedHookLoadPackage
{
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam objXLP)
    {
        /* do something */
    }
}
