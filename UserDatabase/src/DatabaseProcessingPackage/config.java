package DatabaseProcessingPackage;

public class config {
	public static final String DATABASE_NAME = "Userdatabase.db";
	public static final String KEY_DATABASE = "Userkey.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:/home/developer/Desktop/SqlDatabase/" + DATABASE_NAME;
	public static final String KEY_CONNECTION_STRING = "jdbc:sqlite:/home/developer/Desktop/SqlDatabase/" + KEY_DATABASE;
	public static final String TABLE_USERLOGIN = "userLogin";
	public static final String TABLE_USERINFO = "userInfo";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_USERNAME = "userName";
	public static final String COLUMN_PASSWORD = "passWord";
	public static final String COLUMN_STATUS = "userStatus";
	public static final String INISTATUS = "false";
	public static final String FINSTATUS = "true";
}
