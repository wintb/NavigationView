package om.bluebirdaward.busticket.dao.database;

/**
 * Created by VuVanThang on 12/6/2016.
 */
public class TicketDto {

    private int id;
    private String trip;
    private String date;
    private String created_at;

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
