package sqltest2.sh4dow.com.sqltestv2;

import android.content.Context;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteData {

    private Context context;

    DeleteData(Context context) {
        this.context = context;
    }

    public void delete(String username){
        try{
            Connection connect = new ConnectionHelper().connectionclass();

            if(connect!=null){
                String query = "DELETE FROM usersonly WHERE username=?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1,username);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
                connect.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
