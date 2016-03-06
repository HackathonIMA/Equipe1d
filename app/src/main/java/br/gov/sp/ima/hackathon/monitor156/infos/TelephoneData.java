package br.gov.sp.ima.hackathon.monitor156.infos;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephoneData {

    public static String getPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }
}
