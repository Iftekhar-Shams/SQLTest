package sqltest2.sh4dow.com.sqltestv2;

import android.content.Context;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertData {

    private Context context;

    InsertData(Context context) {
        this.context = context;
    }

    public void insert(int id, String name, String password){
        try{
            Connection connect = new ConnectionHelper().connectionclass();

            if(connect!=null){
                String query = "INSERT INTO usersonly (id, name, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,password);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show();
                }
                connect.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
