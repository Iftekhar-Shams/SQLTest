package sqltest2.sh4dow.com.sqltestv2;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateData {
    Connection connect;
    String ConnectionResult;
    Boolean isSuccess = false;


    public void update(String username, String newName, String newPass){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();

            if(connect==null){
                ConnectionResult="Check internet Access";
            }else{
                String query = "UPDATE usersonly SET name=?, password=? WHERE name=?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1,newName);
                preparedStatement.setString(2,newPass);
                preparedStatement.setString(3,username);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    Log.d("crud","Updated 1 data");
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
