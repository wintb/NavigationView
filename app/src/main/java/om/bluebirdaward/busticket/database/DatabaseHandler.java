package om.bluebirdaward.busticket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by VuVanThang on 12/6/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ticket_database";
    private TicketTable ticketTable;
    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        ticketTable = new TicketTable(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ticketTable.getCreateSQL());
            Log.d("TicketTable", "Create table ticket success");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(ticketTable.getUpgradeSQL());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
