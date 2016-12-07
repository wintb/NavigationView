package om.bluebirdaward.busticket.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import om.bluebirdaward.busticket.dao.database.TicketDto;

/**
 * Created by VuVanThang on 12/6/2016.
 */
public class TicketTable implements DbDefines {

    private static final String TABLE_NAME = "ticket_table";

    private static final String ID = "id";
    private static final String TRIP = "trip";
    private static final String DATE = "date";
    private static final String CREATED_AT = "created_at";

    private static final String[] ALL_COLUMN_TICKET = {
            ID,
            TRIP,
            DATE,
            CREATED_AT
    };

    private static final String CREATE_TABLE =
            DbDefines.CREATE_TABLE + TABLE_NAME + " ("
            + ID + T_PRIMARY_KEY_AUTOINCREMENT + ","
            + TRIP + T_TEXT + ","
            + DATE + T_TEXT + ","
            + CREATED_AT + T_TEXT
            + " );";

    private DatabaseHandler databaseHandler;

    public TicketTable(DatabaseHandler databaseHandler){
        this.databaseHandler = databaseHandler;
    }

    public static final String CREATE_INDEX =
            DbDefines.CREATE_INDEX + " index_tiket" + " ON" + TABLE_NAME + " ( " + ID + " , " + TRIP + "," + DATE + " );";

    public String getCreateSQL(){
        return CREATE_TABLE + CREATE_INDEX;
    }

    public String getUpgradeSQL(){
        return DROP_TABLE_IF_EXISTS + TABLE_NAME;
    }

    public String getTableName(){
        return TABLE_NAME;
    }

    private TicketDto CursorToTicketDto(Cursor cursor){
        TicketDto ticket = new TicketDto();
        ticket.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        ticket.setTrip(cursor.getString(cursor.getColumnIndex(TRIP)));
        ticket.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
        ticket.setCreated_at(cursor.getString(cursor.getColumnIndex(CREATED_AT)));

        return ticket;
    }

    private ContentValues getValuesTicketDto(TicketDto ticket){
        ContentValues values = new ContentValues();
        putStringValue(TRIP,ticket.getTrip(),values);
        putStringValue(DATE, ticket.getDate(), values);
        putStringValue(CREATED_AT, ticket.getCreated_at(), values);

        return values;
    }

    private void putStringValue(String key, String content, ContentValues contentValues) {
        if (content != null && !content.isEmpty()) {
            contentValues.put(key, content);
        }
    }

    /**
     * Insert Ticket to Database
     */
    public void insertTicket(TicketDto ticket){
        synchronized (this){
            SQLiteDatabase database = databaseHandler.getWritableDatabase();
            try {
                ContentValues contentValues = getValuesTicketDto(ticket);
                database.insertWithOnConflict(TABLE_NAME,null,contentValues,SQLiteDatabase.CONFLICT_REPLACE);
                Log.d("TicketTable"," insert ticket table succes");

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                database.close();
            }
        }
    }

    /**
     * get Ticket from database
     */

    public TicketDto getTicketByDate(String date){
        synchronized (this){
            SQLiteDatabase database = databaseHandler.getReadableDatabase();
            try {
                TicketDto ticket = new TicketDto();
                Cursor cursor = database.query(false,
                        TABLE_NAME,
                        ALL_COLUMN_TICKET,
                        DATE + " = ?",
                        new String[]{date},
                        null,
                        null,
                        null,
                        null);

                if (cursor!= null){
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()){
                        ticket.setId(CursorToTicketDto(cursor).getId());
                        ticket.setTrip(CursorToTicketDto(cursor).getTrip());
                        ticket.setDate(CursorToTicketDto(cursor).getDate());
                        ticket.setCreated_at(CursorToTicketDto(cursor).getCreated_at());

                        cursor.moveToNext();
                    }
                    if (cursor != null && !cursor.isClosed()){
                        cursor.close();
                    }
                }
                return ticket;
            }catch (Exception e){
                e.printStackTrace();
                return new TicketDto();
            }finally {
                database.close();
            }
        }
    }

    /**
     *  get ticket by date and trip name
     */
    public TicketDto getTicketByDateAndTrip(String date, String trip){
        synchronized (this){
            SQLiteDatabase database = databaseHandler.getReadableDatabase();
            try {
                TicketDto ticket = new TicketDto();
                Cursor cursor = database.query(false,
                        TABLE_NAME,
                        ALL_COLUMN_TICKET,
                        DATE + " = ? AND " + TRIP + " = ?",
                        new String[]{date, trip},
                        null,
                        null,
                        null,
                        null);

                if (cursor != null){
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()){
                        ticket.setId(CursorToTicketDto(cursor).getId());
                        ticket.setDate(CursorToTicketDto(cursor).getDate());
                        ticket.setTrip(CursorToTicketDto(cursor).getTrip());
                        ticket.setCreated_at(CursorToTicketDto(cursor).getCreated_at());

                        cursor.moveToNext();
                    }
                    if (cursor != null && !cursor.isClosed()){
                        cursor.close();
                    }
                }
                return ticket;
            }catch (Exception e){
                e.printStackTrace();
                return new TicketDto();
            }finally {
                database.close();
            }
        }
    }

    /**
     *  get name trip from datebase
     */
    public ArrayList<String> getNameTrip(){
        synchronized (this){
            SQLiteDatabase database = databaseHandler.getReadableDatabase();
            try {
                ArrayList<String> listNameTrip = new ArrayList<>();
                Cursor cursor = database.query(true,
                        TABLE_NAME,
                        new String[]{TRIP},
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

                if (cursor != null){
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()){
                        listNameTrip.add(CursorToTicketDto(cursor).getTrip());
                        cursor.moveToNext();
                    }
                    if (cursor != null && !cursor.isClosed()){
                        cursor.close();
                    }
                }

                return listNameTrip;
            }catch (Exception e){
                e.printStackTrace();
                return new ArrayList<>();
            }finally {
                database.close();
            }
        }
    }

    /**
     * Get ListSongItem from Database
     */

    public ArrayList<TicketDto> getListTicket(){
        synchronized (this){
            SQLiteDatabase database = databaseHandler.getReadableDatabase();
            try {
                ArrayList<TicketDto> listTicket = new ArrayList<>();

                Cursor cursor = database.query(false,
                        TABLE_NAME,
                        ALL_COLUMN_TICKET,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

                if (cursor != null){
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()){
                        listTicket.add(CursorToTicketDto(cursor));

                        cursor.moveToNext();
                    }
                    if (cursor != null && !cursor.isClosed()){
                        cursor.close();
                    }
                }
                return listTicket;
            }catch (Exception e){
                e.printStackTrace();
                return new ArrayList<>();
            } finally {
              database.close();
            }
        }
    }

    /**
     *
     */
    public void deleteAllTicket(){
        synchronized (this){
            SQLiteDatabase database = databaseHandler.getWritableDatabase();
            try {
                database.delete(TABLE_NAME, "1", null);
                Log.d("TicketTable","Delete all ticket from database successfull");
            }catch (Exception e){
                e.printStackTrace();

            }finally {
                database.close();
            }
        }
    }

    /**
     *  delete ticket by date
     */
    public void deleteTicketByDate(String date){
       synchronized (this){
           SQLiteDatabase database = databaseHandler.getWritableDatabase();
           try {
               database.delete(TABLE_NAME, DATE + " = ?", new String[]{date});
               Log.d("TicketTable","Delete all ticket are day " + date + "successfull");
           }catch (Exception e){
               e.printStackTrace();
           }finally {
               database.close();
           }
       }
    }
}
