package com.jarvishub.emandemo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.jarvishub.emandemo.utils.AppConstants;

/**
 * Created by mrrobot on 8/24/17.
 */

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (Object aPdusObj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                    String senderAddress = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.d(TAG, "Received SMS: .................................. " + message + ", Sender: " + senderAddress);

                    // if the SMS is not from our gateway, ignore the message
                    if (!senderAddress.toLowerCase().contains(AppConstants.SMS_ORIGIN.toLowerCase())) {
                        return;
                    }

                    // verification code from sms
                    String verificationCode = getVerificationCode(message);
                    Log.d(TAG, "OTP received: ........................ " + verificationCode);

                    Intent verIntent = new Intent(context, HttpService.class);
                    verIntent.putExtra("otp", verificationCode);
                    verIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(verIntent);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }


    private String getVerificationCode(String message) {
        String code = null;
        int index = message.indexOf(AppConstants.OTP_DELIMITER);
        code = message.substring(message.length() - AppConstants.NUM_OF_VERIFICATION_CODE_DIGIT);

        return code;
    }
}
