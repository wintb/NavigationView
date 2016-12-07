package om.bluebirdaward.busticket.database;

/**
 * Created by VuVanThang on 12/6/2016.
 */
public interface DbDefines {

    public final static String CREATE_TABLE = "CREATE TABLE ";
    public final static String ALTER_TABLE = "ALTER TABLE ";
    public final static String ADD_COLUMN = " ADD COLUMN ";
    public final static String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";

    public final static String T_REAL = " REAL ";
    public final static String T_INTEGER = " INTEGER ";
    public final static String T_TEXT = " TEXT ";
    public final static String T_VARCHAR = " VARCHAR[20]";
    public final static String T_DATE = " DATE";

    public final static String T_UNIQUE = " UNIQUE ";
    public final static String T_PRIMARY_KEY_AUTOINCREMENT = " PRIMARY KEY AUTOINCREMENT ";
    public final static String T_PRIMARY_KEY = " PRIMARY KEY ";
    public final static String T_NOCASE = " COLLATE NOCASE ";

    public final static String O_ASC = " ASC";
    public final static String O_DESC = " DESC";

    public final static String CREATE_INDEX = "CREATE INDEX ";
}
