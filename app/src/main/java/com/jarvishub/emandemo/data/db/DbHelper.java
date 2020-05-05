package com.jarvishub.emandemo.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jarvishub.emandemo.di.custom_annotation.ApplicationContext;
import com.jarvishub.emandemo.model.Order;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.model.service_packages.Packages;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mrrobot on 9/13/17.
 */
@Singleton
public class DbHelper extends SQLiteOpenHelper implements IDbHelper {

    //DB Info
    public static final String DB_NAME = "emandb";
    public static final int DB_VERSION = 1;

    //TABLE
    public static final String TABLE_USER = "users";
    public static final String TABLE_ORDER = "order";
    public static final String TABLE_SERVICE_PACKAGES = "servicePackages";

    //COLUMNS
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "user_name";
    public static final String USER_COLUMN_USER_ADDRESS = "user_address";
    public static final String USER_COLUMN_USER_MOBILE = "mobile";
    public static final String USER_COLUMN_USER_PASSWORD = "password";

    public static final String ORDER_COLUMN_ORDER_ID = "id";
    public static final String ORDER_COLUMN_SENDER_NAME = "sender_name";
    public static final String ORDER_COLUMN_SENDER_MOBILE = "sender_mobile";
    public static final String ORDER_COLUMN_RECEIVER_NAME = "receiver_name";
    public static final String ORDER_COLUMN_RECEIVER_MOBILE = "receiver_mob";
    public static final String ORDER_COLUMN_PICKUP_LOCATION = "pickup_loc";
    public static final String ORDER_COLUMN_DELIVERY_LOCATION = "delivery_loc";
    public static final String ORDER_COLUMN_PACKAGE_NAME = "package_name";
    public static final String ORDER_COLUMN_PACKAGE_SIZE = "package_size";
    public static final String ORDER_COLUMN_MAX_WEIGHT = "max_weight";
    public static final String ORDER_COLUMN_LEAD_TIME = "lead_time";
    public static final String ORDER_COLUMN_AMOUNT_PAYABLE = "amount_payable";
    public static final String ORDER_COLUMN_PICKUP_TIME = "pickup_time";
    public static final String ORDER_COLUMN_DELIVERY_TIME = "delivery_time";
    public static final String ORDER_COLUMN_PAYMENT_POLICY = "delivery_time";


    public static final String SERVICE_PACKAGE_ID = "id";
    public static final String SERVICE_PACKAGE_NAME = "package_name";
    public static final String SERVICE_PACKAGE_PRICE = "package_price";
    public static final String SERVICE_PACKAGE_LEAD_TIME = "lead_time";
    public static final String SERVICE_PACKAGE_DESCRIPTION = "description";
    public static final String SERVICE_PACKAGE_MAX_SIZE = "max_size";
    public static final String SERVICE_PACKAGE_MAX_WEIGHT = "max_weight";

    public static final String TAG = "DbHelper";


    @Inject
    public DbHelper(@ApplicationContext Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createUserTable(sqLiteDatabase);
        createServicePackages(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*
    *
    * Create Tables
    *
    * */


    private void createUserTable(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + TABLE_USER + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY,"
                            + USER_COLUMN_USER_NAME + " TEXT,"
                            + USER_COLUMN_USER_ADDRESS + " TEXT,"
                            + USER_COLUMN_USER_MOBILE + " TEXT,"
                            + USER_COLUMN_USER_PASSWORD + " TEXT" + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createOrderTable(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + TABLE_ORDER + "("
                            + ORDER_COLUMN_ORDER_ID + " INTEGER PRIMARY KEY,"
                            + ORDER_COLUMN_SENDER_NAME + " TEXT,"
                            + ORDER_COLUMN_SENDER_MOBILE + " TEXT,"
                            + ORDER_COLUMN_RECEIVER_NAME + " TEXT,"
                            + ORDER_COLUMN_RECEIVER_MOBILE + " TEXT,"
                            + ORDER_COLUMN_PICKUP_LOCATION + " TEXT,"
                            + ORDER_COLUMN_DELIVERY_LOCATION + " TEXT,"
                            + ORDER_COLUMN_LEAD_TIME + " TEXT,"
                            + ORDER_COLUMN_PACKAGE_NAME + " TEXT,"
                            + ORDER_COLUMN_PACKAGE_SIZE + " TEXT,"
                            + ORDER_COLUMN_MAX_WEIGHT + " TEXT,"
                            + ORDER_COLUMN_AMOUNT_PAYABLE + " TEXT,"
                            + ORDER_COLUMN_PICKUP_TIME + " TEXT,"
                            + ORDER_COLUMN_DELIVERY_TIME + " TEXT,"
                            + ORDER_COLUMN_PAYMENT_POLICY + " TEXT" + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createServicePackages(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + TABLE_SERVICE_PACKAGES + "("
                            + SERVICE_PACKAGE_ID + " INTEGER PRIMARY KEY,"
                            + SERVICE_PACKAGE_NAME + " TEXT,"
                            + SERVICE_PACKAGE_PRICE + " TEXT,"
                            + SERVICE_PACKAGE_LEAD_TIME + " TEXT,"
                            + SERVICE_PACKAGE_DESCRIPTION + " TEXT,"
                            + SERVICE_PACKAGE_MAX_SIZE + " TEXT,"
                            + SERVICE_PACKAGE_MAX_WEIGHT + " TEXT," + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /*
    *
    * Insert Data In DB
    *
    * */

    @Override
    public Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getFirstName());
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
            contentValues.put(USER_COLUMN_USER_MOBILE, user.getMobile());
            contentValues.put(USER_COLUMN_USER_PASSWORD, user.getPassword());
            return db.insert(TABLE_USER, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Long draftOrder(Order order) throws Exception {

        return Long.valueOf(1);
    }

/*    @Override
    public Long insertServicePackages(Packages packages) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SERVICE_PACKAGE_NAME, packages.getName());
            contentValues.put(SERVICE_PACKAGE_PRICE, packages.getPrice());
            contentValues.put(SERVICE_PACKAGE_LEAD_TIME, packages.getLead_time());
            contentValues.put(SERVICE_PACKAGE_DESCRIPTION, packages.getDescription());
            contentValues.put(SERVICE_PACKAGE_MAX_SIZE, packages.getMax_size());
            contentValues.put(SERVICE_PACKAGE_MAX_WEIGHT, packages.getMax_weight());
            return db.insert(TABLE_USER, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }*/



    /*
    *
    * Retrieve Data From DB
    *
    * */

    @Override
    public User getUser(String mobile) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + TABLE_USER
                            + " WHERE "
                            + USER_COLUMN_USER_MOBILE
                            + " = ? ",
                    new String[]{mobile + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                //  user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_MOBILE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_PASSWORD)));
                return user;
            } else {
                throw new Resources.NotFoundException("User with mobile " + mobile + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }


  /*  @Override
    public Packages getServicePacksFromLoc() throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        int id = 0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + TABLE_SERVICE_PACKAGES
                            + " WHERE "
                            + SERVICE_PACKAGE_ID
                            + " = ? ",
                    new String[]{id + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Packages packages = new Packages();
               // packages.setName();

              *//*  //  user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_MOBILE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_PASSWORD)));*//*
                return packages;
            } else {
                throw new Resources.NotFoundException("User with mobile " + id + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }*/


}
