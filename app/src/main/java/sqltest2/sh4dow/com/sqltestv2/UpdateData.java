package sqltest2.sh4dow.com.sqltestv2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateData {

    private Context context;

    UpdateData(Context context) {
        this.context = context;
    }

    public void update(String username, String newName, String newPass){
        try{
            Connection connect = new ConnectionHelper().connectionclass();

            if(connect!=null){
                String query = "UPDATE usersonly SET name=?, password=? WHERE name=?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1,newName);
                preparedStatement.setString(2,newPass);
                preparedStatement.setString(3,username);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0)
                    Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();

                connect.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
