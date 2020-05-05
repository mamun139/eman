package com.jarvishub.emandemo.utils;

public class AppConstants {
    public static final long NULL_INDEX = -1L;

    public static final int SMS_PERMISSION_REQUESR_CODE = 4001;
    //sms provider identification, it should match sms gateway origin
    public static final String SMS_ORIGIN = "01737336616";
    // special character to prefix the otp. Make sure this character appears only once in the sms
    public static final String OTP_DELIMITER = ":";
    public static final int NUM_OF_VERIFICATION_CODE_DIGIT = 6;

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
}
