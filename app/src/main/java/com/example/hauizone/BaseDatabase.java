package com.example.hauizone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

import com.example.hauizone.domesticDeclaration.DomesticDeclaration;
import com.example.hauizone.entryDeclaration.EntryDeclaration;

import java.util.ArrayList;

public class BaseDatabase extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabase";
    private static final String DATABASE_NAME = "MY_DB";
    private static final int DATABASE_VERSION = 1;


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


    // table domestic

    private static final String TABLE_DOMESTIC= "DOMESTIC_TABLE";
    private static final String ID_DOMESTIC_COLUMN = "id_domestic";
    private static final String CHECK_COLUMN="check_cl";
    private static final String VEHICLE_COLUMN="vehicle";
    private static final String ADDRESS_DEPARTURE_COLUMN = "departure";
    private static final String ADDRESS_DESTINATION_COLUMN = "destination";
    private static final String NUMBER_PASSPORT_COLUMN = "number_passport";



    private static final String SYMPTON_COLUMN="sympton";
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

    //
    //table entry
    private static final String TABLE_ENTRY= "ENTRY_TABLE";
    private static final String ID_ENTRY_COLUMN = "id_entry";
    private static final String GATE_COLUMN="gate";
    private static final String DATE_ENTRY_COLUMN = "date_entry";
    private static final String NATIONALITY="nationality";

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
    //

    private static BaseDatabase sInstance;

    public static BaseDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BaseDatabase(context.getApplicationContext());
        }
        return sInstance;
    }
    public BaseDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG,"on create");
        try {
            db.execSQL(CREATE_TABLE_DOMESTIC_SQL);
            db.execSQL(CREATE_TABLE_ENTRY_SQL);
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

        values.put(CHECK_COLUMN,domesticDeclaration.getCkKH());
        values.put(VEHICLE_COLUMN,domesticDeclaration.getVehicle());
        values.put(ADDRESS_DEPARTURE_COLUMN,domesticDeclaration.getDeparture());
        values.put(ADDRESS_DESTINATION_COLUMN,domesticDeclaration.getDestination());
        values.put(NAME_COLUMN,domesticDeclaration.getName());
        values.put(DATE_OF_BIRTH_COLUMN,domesticDeclaration.getDateOfBirth());
        values.put(NUMBER_PASSPORT_COLUMN,domesticDeclaration.getNumberPassport());
        values.put(SEX_COLUMN,domesticDeclaration.getSex());
        values.put(CITY_CONTACT_COLUMN,domesticDeclaration.getContactCity());
        values.put(DISTRICT_CONTACT_COLUMN,domesticDeclaration.getContactDistrict());
        values.put(TOWN_CONTACT_COLUMN,domesticDeclaration.getContactTown());
        values.put(ADDRESS_CONTACT_COLUMN,domesticDeclaration.getContactAddress());
        values.put(NUMBERPHONE_CONTACT_COLUMN,domesticDeclaration.getContactAddress());
        values.put(SYMPTON_COLUMN,domesticDeclaration.getSympton());
        values.put(COVID_CONTACT_COLUMN,domesticDeclaration.getCovidContact());
        values.put(ID_USERNAME_COLUMN,domesticDeclaration.getIdUsername());

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

        values.put(GATE_COLUMN,entryDeclaration.getGate());
        values.put(NAME_COLUMN,entryDeclaration.getName());
        values.put(DATE_OF_BIRTH_COLUMN,entryDeclaration.getDateOfBirth());
        values.put(SEX_COLUMN,entryDeclaration.getSex());
        values.put(DATE_ENTRY_COLUMN,entryDeclaration.getDate());
        values.put(NATIONALITY,entryDeclaration.getNationality());
        values.put(CITY_CONTACT_COLUMN,entryDeclaration.getContactCity());
        values.put(DISTRICT_CONTACT_COLUMN,entryDeclaration.getContactDistrict());
        values.put(TOWN_CONTACT_COLUMN,entryDeclaration.getContactTown());
        values.put(ADDRESS_CONTACT_COLUMN,entryDeclaration.getContactAddress());
        values.put(NUMBERPHONE_CONTACT_COLUMN,entryDeclaration.getPhoneNumber());
        values.put(ID_USERNAME_COLUMN,entryDeclaration.getIdUser());
        long rowId = db.insert(TABLE_ENTRY, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }

    public ArrayList<EntryDeclaration> getAllEntry() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<EntryDeclaration> entryDeclarations = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_ENTRY;
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
}
