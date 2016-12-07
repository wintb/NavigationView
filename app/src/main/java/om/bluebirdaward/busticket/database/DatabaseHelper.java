package om.bluebirdaward.busticket.database;

import java.util.ArrayList;

import om.bluebirdaward.busticket.activity.MyApplication;
import om.bluebirdaward.busticket.dao.database.TicketDto;

/**
 * Created by VuVanThang on 12/6/2016.
 */
public class DatabaseHelper {

    public static DatabaseHelper instance = new DatabaseHelper();
    public static DatabaseHandler databaseHandler;

    public static DatabaseHelper getInstance(){
        if (databaseHandler == null){
            synchronized (DatabaseHelper.class){
                if (databaseHandler == null){
                    databaseHandler = new DatabaseHandler(MyApplication.getContext());
                }
            }
        }
        return instance;
    }

    /**
     *  add ticket infomation to database
     */
    public void addTicketDtoToDatabase(TicketDto ticketDto){
        TicketTable ticketTable = new TicketTable(databaseHandler);
        ticketTable.insertTicket(ticketDto);
    }

    /**
     * get ticket by date
     */
    public TicketDto getTicketByDateFromDatabase(String date){
        TicketTable ticketTable = new TicketTable(databaseHandler);
        return ticketTable.getTicketByDate(date);
    }

    /**
     * get ticket by date and trip
     */
    public TicketDto getTicketByDateAndTrip(String date, String trip){
        TicketTable ticketTable = new TicketTable(databaseHandler);
        return ticketTable.getTicketByDateAndTrip(date, trip);
    }

    /**
     *  get list ticket from database
     */
    public ArrayList<TicketDto> getListTicketFromDatabase(){
        TicketTable ticketTable = new TicketTable(databaseHandler);
        return ticketTable.getListTicket();
    }


}
