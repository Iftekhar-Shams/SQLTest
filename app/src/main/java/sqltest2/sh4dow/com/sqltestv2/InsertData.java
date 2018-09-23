package sqltest2.sh4dow.com.sqltestv2;

import android.util.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertData {

    Connection connect;
    String ConnectionResult;
    Boolean isSuccess = false;

    public void insert(int id, String name, String password){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();

            if(connect==null){
                ConnectionResult="Check internet Access";
            }else{
                String query = "INSERT INTO usersonly (id, name, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,password);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    Log.d("crud","Inserted 1 data");
                }
                isSuccess = true;
                connect.close();
            }
        }catch (Exception e){
            isSuccess = false;
            ConnectionResult = e.getMessage();
        }
    }
}
