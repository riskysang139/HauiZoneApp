package com.example.hauizone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.hauizone.ScanQR.DataYourRoute;
import com.example.hauizone.Account.User;
import com.example.hauizone.DomesticDeclaration.DomesticDeclaration;
import com.example.hauizone.EntryDeclaration.EntryDeclaration;
import com.example.hauizone.Notification.Notification;
import com.example.hauizone.Report.Report;


import java.util.ArrayList;
import java.util.List;

public class BaseDatabase extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabase";
    private static final String DATABASE_NAME = "MY_DB";
    private static final int DATABASE_VERSION = 3;


    // same column in table domestic and entry
    private static final String NAME_COLUMN = "name";
    private static final String SEX_COLUMN = "sex";
    private static final String DATE_OF_BIRTH_COLUMN = "date_of_birth";
    private static final String CITY_CONTACT_COLUMN = "contact_city";
    private static final String DISTRICT_CONTACT_COLUMN = "contact_district";
    private static final String TOWN_CONTACT_COLUMN = "contact_town";
    private static final String ADDRESS_CONTACT_COLUMN = "contact_address";
    private static final String NUMBERPHONE_CONTACT_COLUMN = "numberphone";
    private static final String ID_USERNAME_COLUMN = "id_username";

    // table user

    private static final String TABLE_USER = "USER_TABLE";
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "userName";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";
    public static final String USER_DATEOFBIRTH = "date_of_birth";
    public static final String USER_SEX = "sex";
    public static final String USER_PROVINCE = "province";
    public static final String USER_DISTRICT = "district";
    public static final String USER_WARD = "ward";
    public static final String USER_STREET = "street";
    public static final String USER_PHONE_NUMBER = "phoneNumber";
    public static final String USER_EMAIL = "email";
    public static final String USER_EPIDEMIC = "epidemic";
    public static final String USER_FLAG = "flag";


    public static final String CREATE_TABLE_USER_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                    USER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    USER_USERNAME + " TEXT NOT NULL," +
                    USER_PASSWORD + " TEXT NOT NULL," +
                    USER_NAME + " TEXT NOT NULL," +
                    USER_DATEOFBIRTH + " TEXT NOT NULL," +
                    USER_SEX + " TEXT NOT NULL," +
                    USER_PROVINCE + " TEXT NOT NULL," +
                    USER_DISTRICT + " TEXT NOT NULL," +
                    USER_WARD + " TEXT NOT NULL," +
                    USER_STREET + " TEXT NOT NULL," +
                    USER_PHONE_NUMBER + " TEXT NOT NULL," +
                    USER_EMAIL + " TEXT," +
                    USER_EPIDEMIC + " TEXT NOT NULL," +
                    USER_FLAG + " INTEGER NOT NULL" +
                    ")";
    // table domestic

    private static final String TABLE_DOMESTIC = "DOMESTIC_TABLE";
    private static final String ID_DOMESTIC_COLUMN = "id_domestic";
    private static final String CHECK_COLUMN = "check_cl";
    private static final String VEHICLE_COLUMN = "vehicle";
    private static final String ADDRESS_DEPARTURE_COLUMN = "departure";
    private static final String ADDRESS_DESTINATION_COLUMN = "destination";
    private static final String NUMBER_PASSPORT_COLUMN = "number_passport";


    private static final String SYMPTON_COLUMN = "sympton";
    private static final String COVID_CONTACT_COLUMN = "covid_contact";

    private static final String CREATE_TABLE_DOMESTIC_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_DOMESTIC + " (" +
                    ID_DOMESTIC_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    CHECK_COLUMN + " TEXT NOT NULL," +
                    VEHICLE_COLUMN + " TEXT NOT NULL," +
                    ADDRESS_DEPARTURE_COLUMN + " TEXT NOT NULL," +
                    ADDRESS_DESTINATION_COLUMN + " TEXT NOT NULL," +
                    NAME_COLUMN + " TEXT NOT NULL," +
                    DATE_OF_BIRTH_COLUMN + " TEXT NOT NULL," +
                    NUMBER_PASSPORT_COLUMN + " TEXT NOT NULL," +
                    SEX_COLUMN + " TEXT NOT NULL," +
                    CITY_CONTACT_COLUMN + " TEXT NOT NULL," +
                    DISTRICT_CONTACT_COLUMN + " TEXT NOT NULL," +
                    TOWN_CONTACT_COLUMN + " TEXT NOT NULL," +
                    ADDRESS_CONTACT_COLUMN + " TEXT NOT NULL," +
                    NUMBERPHONE_CONTACT_COLUMN + " TEXT NOT NULL," +
                    SYMPTON_COLUMN + " TEXT NOT NULL," +
                    COVID_CONTACT_COLUMN + " TEXT NOT NULL," +
                    ID_USERNAME_COLUMN + " INTEGER NOT NULL" +
                    ")";

    //table notification
    private static final String TABLE_NOTIFI= "NOTIFICATION_TABLE";
    private static final String ID_NOTIFI_COLUMN = "ID";
    private static final String TYPE_NOTIFI_COLUMN = "Type";
    private static final String DATE_NOTIFI_COLUMN = "Date";
    private static final String TIME_NOTIFI_COLUMN = "Time";
    private static final String CONTENT_NOTIFI_COLUMN = "Content";
    private static final String URL_NOTIFI_COLUMN = "Image";
    private static final String CREATE_TABLE_NOTIFI_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFI + " (" +
                    ID_NOTIFI_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    TYPE_NOTIFI_COLUMN + " TEXT NOT NULL," +
                    DATE_NOTIFI_COLUMN + " TEXT NOT NULL," +
                    TIME_NOTIFI_COLUMN + " TEXT NOT NULL," +
                    CONTENT_NOTIFI_COLUMN + " TEXT NOT NULL," +
                    URL_NOTIFI_COLUMN + " TEXT NOT NULL" +
                    ")";
    public ArrayList<Notification> getAllNotifi() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        String sql = "SELECT * FROM " + TABLE_NOTIFI;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                notifications.add(new
                        Notification(cursor.getInt(cursor.getColumnIndex(ID_NOTIFI_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TYPE_NOTIFI_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DATE_NOTIFI_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TIME_NOTIFI_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(CONTENT_NOTIFI_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(URL_NOTIFI_COLUMN))
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return notifications;
    }



    public Notification getNotifications( Notification notifications) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTIFI, new String[]{ID_NOTIFI_COLUMN,
                        TYPE_NOTIFI_COLUMN, DATE_NOTIFI_COLUMN, TIME_NOTIFI_COLUMN, CONTENT_NOTIFI_COLUMN, URL_NOTIFI_COLUMN},
                ID_NOTIFI_COLUMN + " = ?",
                new String[]{String.valueOf(notifications.getId())}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            notifications = new Notification(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),cursor.getString(4), cursor.getString(5));
            cursor.close();
        }
        db.close();
        return notifications;
    }

    public boolean insertNotification(Notification notifications) {
        Log.e(TAG, "onInsert: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TYPE_NOTIFI_COLUMN, notifications.getType());
        values.put(DATE_NOTIFI_COLUMN, notifications.getDate());
        values.put(TIME_NOTIFI_COLUMN, notifications.getTime());
        values.put(CONTENT_NOTIFI_COLUMN, notifications.getContent());
        values.put(URL_NOTIFI_COLUMN, notifications.getImageNotification());
        long rowId = db.insert(TABLE_NOTIFI, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public int updateNotificaton(Notification notification) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TYPE_NOTIFI_COLUMN, notification.getType());
        values.put(DATE_NOTIFI_COLUMN, notification.getDate());
        values.put(TIME_NOTIFI_COLUMN, notification.getTime());
        values.put(CONTENT_NOTIFI_COLUMN, notification.getContent());
        values.put(URL_NOTIFI_COLUMN, notification.getImageNotification());
        int rowEffect = db.update(TABLE_NOTIFI, values, ID_NOTIFI_COLUMN + " = ?",
                new String[]{String.valueOf(notification.getId())});
        db.close();
        return rowEffect;

//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(TYPE_COLUMN, notifications.getLoaiTin());
//        values.put(DATE_COLUMN, notifications.getNgayThem());
//        values.put(TIME_COLUMN, notifications.getGioThem());
//        values.put(CONTENT_COLUMN, notifications.getNoiDung());
//        int rowEffect = db.update(TABLE, values, ID_COLUMN + " = ?",
//                new String[]{String.valueOf(notifications.getId())});
//        db.close();
//        return rowEffect;
    }

    public int deleteNotifi(Notification notifications) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_NOTIFI, ID_NOTIFI_COLUMN + " = ?", new
                String[]{String.valueOf(notifications.getId())});
        db.close();
        return rowEffect;
    }


    //table entry
    private static final String TABLE_ENTRY = "ENTRY_TABLE";
    private static final String ID_ENTRY_COLUMN = "id_entry";
    private static final String GATE_COLUMN = "gate";
    private static final String DATE_ENTRY_COLUMN = "date_entry";
    private static final String NATIONALITY = "nationality";

    private static final String CREATE_TABLE_ENTRY_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ENTRY + " (" +
                    ID_ENTRY_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    GATE_COLUMN + " TEXT NOT NULL," +
                    NAME_COLUMN + " TEXT NOT NULL," +
                    DATE_OF_BIRTH_COLUMN + " TEXT NOT NULL," +
                    SEX_COLUMN + " TEXT NOT NULL," +
                    NATIONALITY + " TEXT NOT NULL," +
                    DATE_ENTRY_COLUMN + " TEXT NOT NULL," +
                    CITY_CONTACT_COLUMN + " TEXT NOT NULL," +
                    DISTRICT_CONTACT_COLUMN + " TEXT NOT NULL," +
                    TOWN_CONTACT_COLUMN + " TEXT NOT NULL," +
                    ADDRESS_CONTACT_COLUMN + " TEXT NOT NULL," +
                    NUMBERPHONE_CONTACT_COLUMN + " TEXT NOT NULL," +
                    ID_USERNAME_COLUMN + " INTEGER NOT NULL" +
                    ")";

    // table scan qr
    private static final String TABLE_YOURROUTE = "YOURROUTE_TABLE";
    private static final String ID_YOURROUTE_COLUMN = "id";
    private static final String NAME_YOURROUTE_COLUMN = "name";
    private static final String ADDRESS_YOURROUTE_COLUMN = "address";
    private static final String ADDRESS_DES_YOURROUTE_COLUMN = "address_des";
    private static final String ADDRESS_GO_YOURROUTE_COLUMN = "address_go";
    private static final String DAY_DES_YOURROUTE_COLUMN = "day_des";
    private static final String DAY_GO_YOURROUTE_COLUMN = "day_go";

    private static final String CREATE_TABLE_YOURROUTE =
            " CREATE TABLE IF NOT EXISTS " + TABLE_YOURROUTE + " ( " +
                    ID_YOURROUTE_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                    NAME_YOURROUTE_COLUMN + " TEXT NOT NULL , " +
                    ADDRESS_YOURROUTE_COLUMN + " TEXT NOT NULL , " +
                    ADDRESS_DES_YOURROUTE_COLUMN + " TEXT NOT NULL , " +
                    ADDRESS_GO_YOURROUTE_COLUMN + " TEXT NOT NULL , " +
                    DAY_DES_YOURROUTE_COLUMN + " TEXT NOT NULL , " +
                    DAY_GO_YOURROUTE_COLUMN + " TEXT NOT NULL " +
                    " ) ";

    private static BaseDatabase sInstance;

    public static BaseDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BaseDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    public BaseDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "on create");
        try {
            db.execSQL(CREATE_TABLE_DOMESTIC_SQL);
            db.execSQL(CREATE_TABLE_ENTRY_SQL);
            db.execSQL(CREATE_TABLE_USER_SQL);
            db.execSQL(CREATE_TABLE_NOTIFI_SQL);
            db.execSQL(CREATE_TABLE_REPORT_SQL);
            db.execSQL(CREATE_TABLE_YOURROUTE);
        }
        catch (Exception e)
        {
            Log.e(TAG,e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOMESTIC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_YOURROUTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
        onCreate(db);
    }
    //Đếm tổng số dòng trong database
//    public int getTotal() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE;
//        Cursor cursor = db.rawQuery(sql, null);
//        int totalRows = cursor.getCount();
//        cursor.close();
//        return totalRows;
//    }
//
//    public int deleteAll() {
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE, null,null);
//        db.close();
//        return rowEffect;
//    }
//    public int delete(DomesticDeclaration domesticDeclaration) {
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE, ID_COLUMN + " = ?", new
//                String[]{String.valueOf(domesticDeclaration.getId())});
//        db.close();
//        return rowEffect;
//    }


    public int getIndex(){

        List<User> list = new ArrayList<>();
        list = getAllUser();
        for(User u : list){
            if(u.getFlag() == 1){
                return u.getUserId();
            }
        }
        return -1;
    }
    public void setFlagOut(){

        List<User> list = new ArrayList<>();
        list = getAllUser();
        for(User u : list){
            if(u.getFlag() == 1){
                u.setFlag(0);
                updateUser(u);
            }
        }
    }

    //check trung ten tk
    public int checkUsername(String username){
        List<User> list = new ArrayList<>();
        list = getAllUser();
        for(User u : list){
            if(u.getUserName().equals(username.trim())){
                return 0;
            }
        }
        return 1;
    }


    public long insertUser(User user) {

        Log.e(TAG, "onInsertUser: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_USERNAME, user.getUserName());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_NAME, user.getName());
        values.put(USER_DATEOFBIRTH, user.getDateOfBirth());
        values.put(USER_SEX, user.getGender());
        values.put(USER_PROVINCE, user.getUserProvince());
        values.put(USER_DISTRICT, user.getUserDistrict());
        values.put(USER_WARD, user.getUserWard());
        values.put(USER_STREET, user.getUserStreet());
        values.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_EPIDEMIC, user.getEpidemic());
        values.put(USER_FLAG, user.getFlag());

        long rowId = db.insert(TABLE_USER, null, values);
        db.close();
        return rowId;
    }

    public int getCountUser(){
        List<User> list = new ArrayList<>();
        list = getAllUser();
        return list.size();
    }

    public User getUserById(int id){
        SQLiteDatabase db = getReadableDatabase();
        User user = new User();

        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + " = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getInt(13)
            );
            cursor.close();
        }
        db.close();

        return user;
    }

    public User getUserByUsernamePassword(String name, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        User user = new User();

        String sql = "SELECT * FROM " +
                TABLE_USER + " WHERE " + USER_USERNAME + " = ? AND " + USER_PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{name, pass});

        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getInt(13)
            );
            cursor.close();
        }
        db.close();
        return user;
    }

    public List<User> getAllUser() {
        SQLiteDatabase db = getReadableDatabase();
        List<User> lists = new ArrayList<User>();
        String sql = " SELECT * FROM " + TABLE_USER ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lists.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getInt(13)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, user.getUserName());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_NAME, user.getName());
        values.put(USER_DATEOFBIRTH, user.getDateOfBirth());
        values.put(USER_SEX, user.getGender());
        values.put(USER_PROVINCE, user.getUserProvince());
        values.put(USER_DISTRICT, user.getUserDistrict());
        values.put(USER_WARD, user.getUserWard());
        values.put(USER_STREET, user.getUserStreet());
        values.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_EPIDEMIC, user.getEpidemic());
        values.put(USER_FLAG, user.getFlag());

        int rowEffect = db.update(TABLE_USER,
                values,
                USER_ID + " = ? ",
                new String[]{String.valueOf(user.getUserId())});
        db.close();
        return rowEffect;
    }

    public int deleteUserByID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_USER, USER_ID + " = ? ", new String[]{String.valueOf(id)});
        db.close();
        return rowEffect;
    }

    public ArrayList<DomesticDeclaration> getAllDomesTic() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DomesticDeclaration> domesticDeclarations = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_DOMESTIC;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                domesticDeclarations.add(new
                        DomesticDeclaration(cursor.getInt(cursor.getColumnIndex(ID_DOMESTIC_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(CHECK_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(VEHICLE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ADDRESS_DEPARTURE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ADDRESS_DESTINATION_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NUMBER_PASSPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(SEX_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(CITY_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DISTRICT_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TOWN_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ADDRESS_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NUMBERPHONE_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(SYMPTON_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(COVID_CONTACT_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(ID_USERNAME_COLUMN))

                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return domesticDeclarations;
    }


    public boolean insertDomestic(DomesticDeclaration domesticDeclaration) {
        Log.e(TAG, "onInsert: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CHECK_COLUMN, domesticDeclaration.getCkKH());
        values.put(VEHICLE_COLUMN, domesticDeclaration.getVehicle());
        values.put(ADDRESS_DEPARTURE_COLUMN, domesticDeclaration.getDeparture());
        values.put(ADDRESS_DESTINATION_COLUMN, domesticDeclaration.getDestination());
        values.put(NAME_COLUMN, domesticDeclaration.getName());
        values.put(DATE_OF_BIRTH_COLUMN, domesticDeclaration.getDateOfBirth());
        values.put(NUMBER_PASSPORT_COLUMN, domesticDeclaration.getNumberPassport());
        values.put(SEX_COLUMN, domesticDeclaration.getSex());
        values.put(CITY_CONTACT_COLUMN, domesticDeclaration.getContactCity());
        values.put(DISTRICT_CONTACT_COLUMN, domesticDeclaration.getContactDistrict());
        values.put(TOWN_CONTACT_COLUMN, domesticDeclaration.getContactTown());
        values.put(ADDRESS_CONTACT_COLUMN, domesticDeclaration.getContactAddress());
        values.put(NUMBERPHONE_CONTACT_COLUMN, domesticDeclaration.getContactAddress());
        values.put(SYMPTON_COLUMN, domesticDeclaration.getSympton());
        values.put(COVID_CONTACT_COLUMN, domesticDeclaration.getCovidContact());
        values.put(ID_USERNAME_COLUMN, domesticDeclaration.getIdUsername());

        long rowId = db.insert(TABLE_DOMESTIC, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }


//    public int updateData(KhaiBao khaiBao) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(NAME_COLUMN, khaiBao.getName());
//        values.put(SEX_COLUMN, khaiBao.getSex());
//        values.put(ADDRESS_COLUMN, khaiBao.getAddress());
//        values.put(DATE_OF_BIRTH_COLUMN, khaiBao.getDateOfBirth());
//        int rowEffect = db.update(TABLE, values, ID_COLUMN + " = ?",
//                new String[]{String.valueOf(khaiBao.getId())});
//        db.close();
//        return rowEffect;
//    }

    public boolean insertEntry(EntryDeclaration entryDeclaration) {
        Log.e(TAG, "onInsert: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GATE_COLUMN, entryDeclaration.getGate());
        values.put(NAME_COLUMN, entryDeclaration.getName());
        values.put(DATE_OF_BIRTH_COLUMN, entryDeclaration.getDateOfBirth());
        values.put(SEX_COLUMN, entryDeclaration.getSex());
        values.put(DATE_ENTRY_COLUMN, entryDeclaration.getDate());
        values.put(NATIONALITY, entryDeclaration.getNationality());
        values.put(CITY_CONTACT_COLUMN, entryDeclaration.getContactCity());
        values.put(DISTRICT_CONTACT_COLUMN, entryDeclaration.getContactDistrict());
        values.put(TOWN_CONTACT_COLUMN, entryDeclaration.getContactTown());
        values.put(ADDRESS_CONTACT_COLUMN, entryDeclaration.getContactAddress());
        values.put(NUMBERPHONE_CONTACT_COLUMN, entryDeclaration.getPhoneNumber());
        values.put(ID_USERNAME_COLUMN, entryDeclaration.getIdUser());
        long rowId = db.insert(TABLE_ENTRY, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }

    public ArrayList<EntryDeclaration> getAllEntry() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<EntryDeclaration> entryDeclarations = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_ENTRY;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                entryDeclarations.add(new
                        EntryDeclaration(cursor.getInt(cursor.getColumnIndex(ID_ENTRY_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(GATE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(SEX_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NATIONALITY)),
                        cursor.getString(cursor.getColumnIndex(DATE_ENTRY_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(CITY_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DISTRICT_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TOWN_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ADDRESS_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NUMBERPHONE_CONTACT_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(ID_USERNAME_COLUMN))

                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return entryDeclarations;
    }


    // query your route
    public boolean insertYourRoute(DataYourRoute dataYourRoute){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME_YOURROUTE_COLUMN,dataYourRoute.getName());
        contentValues.put(ADDRESS_YOURROUTE_COLUMN,dataYourRoute.getAddress());
        contentValues.put(ADDRESS_DES_YOURROUTE_COLUMN,dataYourRoute.getAddress_des());
        contentValues.put(ADDRESS_GO_YOURROUTE_COLUMN,dataYourRoute.getAddress_go());
        contentValues.put(DAY_DES_YOURROUTE_COLUMN,dataYourRoute.getDay_des());
        contentValues.put(DAY_GO_YOURROUTE_COLUMN,dataYourRoute.getDay_go());

        long rowId = db.insert(TABLE_YOURROUTE, null, contentValues);
        Log.e(TAG, "onInsert: YourRoute = " + rowId);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    // updateData
    public boolean updateYourRoute(DataYourRoute dataYourRoute){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME_YOURROUTE_COLUMN,dataYourRoute.getName());
        contentValues.put(ADDRESS_YOURROUTE_COLUMN,dataYourRoute.getAddress());
        contentValues.put(ADDRESS_DES_YOURROUTE_COLUMN,dataYourRoute.getAddress_des());
        contentValues.put(ADDRESS_GO_YOURROUTE_COLUMN,dataYourRoute.getAddress_go());
        contentValues.put(DAY_DES_YOURROUTE_COLUMN,dataYourRoute.getDay_des());
        contentValues.put(DAY_GO_YOURROUTE_COLUMN,dataYourRoute.getDay_go());

        long rowId = db.update(TABLE_YOURROUTE, contentValues, " id = ? ",new String[]{String.valueOf(dataYourRoute.getId())});
        Log.e(TAG, "onUpdate: YourRoute = " + rowId);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public void deleteYourRoute(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_YOURROUTE,"id = ? ",new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<DataYourRoute> getAllYourRoute(){

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DataYourRoute> dataYourRoutes = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_YOURROUTE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                    int id = cursor.getInt(cursor.getColumnIndex(ID_YOURROUTE_COLUMN));
                    String name = cursor.getString(cursor.getColumnIndex(NAME_YOURROUTE_COLUMN));
                    String address = cursor.getString(cursor.getColumnIndex(ADDRESS_YOURROUTE_COLUMN));
                    String address_des = cursor.getString(cursor.getColumnIndex(ADDRESS_DES_YOURROUTE_COLUMN));
                    String address_go = cursor.getString(cursor.getColumnIndex(ADDRESS_GO_YOURROUTE_COLUMN));
                    String day_des = cursor.getString(cursor.getColumnIndex(DAY_DES_YOURROUTE_COLUMN));
                    String day_go = cursor.getString(cursor.getColumnIndex(DAY_GO_YOURROUTE_COLUMN));

                    dataYourRoutes.add(new DataYourRoute(id, name, address, address_des, address_go, day_des, day_go));
            }while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return dataYourRoutes;
    }

    public ArrayList<EntryDeclaration> getAllEntryWithUser(int idUser) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<EntryDeclaration> entryDeclarations = new ArrayList<>();

        String sql = "SELECT * FROM " +
                TABLE_ENTRY + " WHERE " + ID_USERNAME_COLUMN + " = ? ";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(idUser)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                entryDeclarations.add(new
                        EntryDeclaration(cursor.getInt(cursor.getColumnIndex(ID_ENTRY_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(GATE_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DATE_OF_BIRTH_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(SEX_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NATIONALITY)),
                        cursor.getString(cursor.getColumnIndex(DATE_ENTRY_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(CITY_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DISTRICT_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TOWN_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(ADDRESS_CONTACT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NUMBERPHONE_CONTACT_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(ID_USERNAME_COLUMN))

                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return entryDeclarations;
    }

    public int updateEntry(EntryDeclaration entryDeclaration) {
        Log.e(TAG, "onUpdate: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GATE_COLUMN, entryDeclaration.getGate());
        values.put(NAME_COLUMN, entryDeclaration.getName());
        values.put(DATE_OF_BIRTH_COLUMN, entryDeclaration.getDateOfBirth());
        values.put(SEX_COLUMN, entryDeclaration.getSex());
        values.put(DATE_ENTRY_COLUMN, entryDeclaration.getDate());
        values.put(NATIONALITY, entryDeclaration.getNationality());
        values.put(CITY_CONTACT_COLUMN, entryDeclaration.getContactCity());
        values.put(DISTRICT_CONTACT_COLUMN, entryDeclaration.getContactDistrict());
        values.put(TOWN_CONTACT_COLUMN, entryDeclaration.getContactTown());
        values.put(ADDRESS_CONTACT_COLUMN, entryDeclaration.getContactAddress());
        values.put(NUMBERPHONE_CONTACT_COLUMN, entryDeclaration.getPhoneNumber());
        values.put(ID_USERNAME_COLUMN, entryDeclaration.getIdUser());

        int rowEffect = db.update(TABLE_ENTRY, values, ID_ENTRY_COLUMN + " = ? ",
                new String[]{String.valueOf(entryDeclaration.getId())});
        db.close();
        return rowEffect;
    }

    public int deleteEntryByID(EntryDeclaration entryDeclaration) {

        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_ENTRY, ID_ENTRY_COLUMN + " = ? ", new String[]{String.valueOf(entryDeclaration.getId())});
        db.close();
        return rowEffect;
    }
    //table report
    private static final String TABLE_REPORT= "REPORT_TABLE";
    private static final String ID_REPORT_COLUMN = "id_report";
    private static final String NAME_REPORT_COLUMN= "name_report";
    private static final String SDT_REPORT_COLUMN = "sdt_report";
    private static final String DATE_REPORT_COLUMN = "date_report";
    private static final String PROVINCE_REPORT_COLUMN= "province_report";
    private static final String DISTRICT_REPORT_COLUMN= "district_report";
    private static final String WARD_REPORT_COLUMN = "ward_report";
    private static final String STREET_REPORT_COLUMN = "street_report";
    private static final String TYPE_REPORT_COLUMN = "type_report";
    private static final String CONTENT_REPORT_COLUMN = "content_report";

    private static final String CREATE_TABLE_REPORT_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_REPORT + "(" +
                    ID_REPORT_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    NAME_REPORT_COLUMN + " TEXT NOT NULL," +
                    SDT_REPORT_COLUMN + " TEXT NOT NULL," +
                    PROVINCE_REPORT_COLUMN + " TEXT NOT NULL," +
                    DISTRICT_REPORT_COLUMN + " TEXT NOT NULL," +
                    WARD_REPORT_COLUMN + " TEXT NOT NULL," +
                    STREET_REPORT_COLUMN + " TEXT NOT NULL," +
                    TYPE_REPORT_COLUMN + " TEXT NOT NULL," +
                    CONTENT_REPORT_COLUMN + " TEXT NOT NULL," +
                    DATE_REPORT_COLUMN + " TEXT NOT NULL" +
                    ")";
    //
    public boolean insertReport(Report report) {
        Log.e(TAG, "onInsert: ");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_REPORT_COLUMN,report.getNameReport());
        values.put(SDT_REPORT_COLUMN,report.getSdtReport());
        values.put(PROVINCE_REPORT_COLUMN,report.getProvince());
        values.put(DISTRICT_REPORT_COLUMN,report.getDistrict());
        values.put(WARD_REPORT_COLUMN,report.getWard());
        values.put(STREET_REPORT_COLUMN,report.getStreet());
        values.put(TYPE_REPORT_COLUMN,report.getTypeReport());
        values.put(CONTENT_REPORT_COLUMN,report.getContentReport());
        values.put(DATE_REPORT_COLUMN,report.getTimeDetectReport());
        long rowId = db.insert(TABLE_REPORT, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public ArrayList<Report> getAllReport(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_REPORT;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                reports.add(new
                        Report(cursor.getInt(cursor.getColumnIndex(ID_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DATE_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(NAME_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(SDT_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(PROVINCE_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(DISTRICT_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(WARD_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(STREET_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(TYPE_REPORT_COLUMN)),
                        cursor.getString(cursor.getColumnIndex(CONTENT_REPORT_COLUMN))
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return reports;
    }
    public int updateReport(Report report) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_REPORT_COLUMN, report.getNameReport());
        values.put(DATE_REPORT_COLUMN,report.getTimeDetectReport());
        values.put(SDT_REPORT_COLUMN,report.getSdtReport());
        values.put(PROVINCE_REPORT_COLUMN,report.getProvince());
        values.put(DISTRICT_REPORT_COLUMN,report.getDistrict());
        values.put(WARD_REPORT_COLUMN,report.getWard());
        values.put(STREET_REPORT_COLUMN,report.getStreet());
        values.put(TYPE_REPORT_COLUMN,report.getTypeReport());
        values.put(CONTENT_REPORT_COLUMN,report.getContentReport());
        int rowEffect = db.update(TABLE_REPORT,
                values,
                ID_REPORT_COLUMN + " = ? " ,
                new String[]{String.valueOf(report.getIdReport())});
        db.close();
        return rowEffect;
    }
    public int deleteReport(Report report) {
        SQLiteDatabase db = getWritableDatabase();
        int rowEffect = db.delete(TABLE_REPORT, ID_REPORT_COLUMN + " = ?", new
                String[]{String.valueOf(report.getIdReport())});
        db.close();
        return rowEffect;
    }
}
