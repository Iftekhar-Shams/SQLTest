package sqltest2.sh4dow.com.sqltestv2;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteData {

    Connection connect;
    String ConnectionResult;
    Boolean isSuccess = false;

    public void delete(String username){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();

            if(connect==null){
                ConnectionResult="Check internet Access";
            }else{
                String query = "DELETE FROM usersonly WHERE username=?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1,username);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    Log.d("crud","Deleted 1 data");
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
