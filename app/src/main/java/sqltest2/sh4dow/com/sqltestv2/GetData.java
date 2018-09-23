package sqltest2.sh4dow.com.sqltestv2;

import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {
    Connection connect;
    String ConnectionResult;
    Boolean isSuccess = false;

    public List<Map<String,String>> getData(){
        List<Map<String,String>> data = new ArrayList<>();

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();

            if(connect==null){
                ConnectionResult="Check internet Access";
            }else{
                String query = "select * from usersonly";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    Map<String,String> datanum = new HashMap<>();
                    datanum.put("id",resultSet.getString("id"));
                    datanum.put("name",resultSet.getString("name"));
                    datanum.put("password",resultSet.getString("password"));
                    data.add(datanum);ConnectionResult = "Success";
                }
                isSuccess = true;
                connect.close();
            }
        }catch (Exception e){
            isSuccess = false;
            ConnectionResult = e.getMessage();
        }
        return data;
    }
}
