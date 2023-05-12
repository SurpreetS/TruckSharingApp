package sqllitehelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyOrderDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "delivery.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "deliveries";

    // Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PICKUP_TIME = "pickup_time";
    private static final String COLUMN_PICKUP_DATE = "pickup_date";
    private static final String COLUMN_RECEIVER_NAME = "receiver_name";
    private static final String COLUMN_PICKUP_LOCATION = "pickup_location";
    private static final String COLUMN_VEHICLE_TYPE = "vehicle_type";
    private static final String COLUMN_GOOD_TYPE = "good_type";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_LENGTH = "length";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TRUCK_NAME = "truck_name";
    public static final String COLUMN_DESCRIPTION = "description";

    public MyOrderDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PICKUP_TIME + " TEXT,"
                + COLUMN_PICKUP_DATE + " TEXT,"
                + COLUMN_RECEIVER_NAME + " TEXT,"
                + COLUMN_PICKUP_LOCATION + " TEXT,"
                + COLUMN_VEHICLE_TYPE + " TEXT,"
                + COLUMN_GOOD_TYPE + " TEXT,"
                + COLUMN_WEIGHT + " REAL,"
                + COLUMN_LENGTH + " REAL,"
                + COLUMN_HEIGHT + " REAL,"
                + COLUMN_DESCRIPTION+ " TEXT, "
                + COLUMN_TRUCK_NAME + " TEXT, "
                + COLUMN_IMAGE + " TEXT,"
                + COLUMN_WIDTH + " REAL"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        // Recreate the table
        onCreate(db);
    }


    public long insertDelivery(MyOrderData delivery) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = DatabaseUtility.deliveryToContentValues(delivery);
        return db.insert(TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public List<MyOrderData> getAllDeliveries() {
        List<MyOrderData> deliveries = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                MyOrderData delivery = new MyOrderData();
                delivery.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                delivery.setPickupTime(cursor.getString(cursor.getColumnIndex(COLUMN_PICKUP_TIME)));
                delivery.setPickupDate(cursor.getString(cursor.getColumnIndex(COLUMN_PICKUP_DATE)));
                delivery.setReceiverName(cursor.getString(cursor.getColumnIndex(COLUMN_RECEIVER_NAME)));
                delivery.setPickupLocation(cursor.getString(cursor.getColumnIndex(COLUMN_PICKUP_LOCATION)));
                delivery.setVehicleType(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_TYPE)));
                delivery.setGoodType(cursor.getString(cursor.getColumnIndex(COLUMN_GOOD_TYPE)));
                delivery.setWeight(cursor.getDouble(cursor.getColumnIndex(COLUMN_WEIGHT)));
                delivery.setLength(cursor.getDouble(cursor.getColumnIndex(COLUMN_LENGTH)));
                delivery.setHeight(cursor.getDouble(cursor.getColumnIndex(COLUMN_HEIGHT)));
                delivery.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                delivery.setImage(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)));
                delivery.setTruckName(cursor.getString(cursor.getColumnIndex(COLUMN_TRUCK_NAME)));
                delivery.setWidth(cursor.getDouble(cursor.getColumnIndex(COLUMN_WIDTH)));

                deliveries.add(delivery);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return deliveries;
    }

//    private MyOrderData getDeliveryFromDatabase(int position) {
//        MyOrderDatabaseHelper databaseHelper = new MyOrderDatabaseHelper();
//        List<MyOrderData> deliveries = databaseHelper.getAllDeliveries();
//
//        // Check if the position is within the valid range
//        if (position >= 0 && position < deliveries.size()) {
//            return deliveries.get(position);
//        }
//
//        return null;
//    }



}
