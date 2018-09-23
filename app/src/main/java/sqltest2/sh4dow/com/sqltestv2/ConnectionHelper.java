package sqltest2.sh4dow.com.sqltestv2;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    public static final String USERNAME = "sa";
    public static final String PASSWORD = "testpassword";
    public static final String DATABASE = "TestDB";
    public static final String IP = ""; // your server ip

    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + IP + ";"
                    + "databaseName=" + DATABASE + ";user=" + USERNAME
                    + ";password=" + PASSWORD + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se) { Log.e("error here 1 : ", se.getMessage()); }
        catch (ClassNotFoundException e) { Log.e("error here 2 : ", e.getMessage()); }
        catch (Exception e) { Log.e("error here 3 : ", e.getMessage()); }
        return connection;
    }
}
