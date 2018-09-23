package sqltest2.sh4dow.com.sqltestv2;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    Connection con;
    String un,pass,db,ip,url;

    Button getDataBtn, insert, update;
    ListView listView;
    SimpleAdapter adapter;
    EditText currentUsername, newUsername, newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
        getDataBtn = findViewById(R.id.btnget);
        listView = findViewById(R.id.listview);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        currentUsername = findViewById(R.id.currentusername);
        newUsername = findViewById(R.id.newusername);
        newPassword = findViewById(R.id.newpassword);

        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData insertData = new InsertData();
                String newName = newUsername.getText().toString().trim();
                String newPass = newPassword.getText().toString().trim();
                String currentName = currentUsername.getText().toString().trim();
                insertData.insert(Integer.parseInt(currentName),newName,newPass);
                adapter.notifyDataSetChanged();
                update();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData updateData = new UpdateData();
                String newName = newUsername.getText().toString().trim();
                String newPass = newPassword.getText().toString().trim();
                String currentName = currentUsername.getText().toString().trim();
                updateData.update(currentName,newName,newPass);
                newUsername.setText("");
                newPassword.setText("");
                currentUsername.setText("");
                update();
            }
        });
//        login = findViewById(R.id.button);
//        username = findViewById(R.id.editText);
//        password = findViewById(R.id.editText2);
//
//        ip = "192.168.0.100";
//        db = "TestDB";
//        un = "sa";
//        pass = "testpassword";
//
//        login.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
//                checkLogin.execute("");
//            }
//        });
    }

    public void update(){
        List<Map<String,String>> mylist = null;
        GetData getData = new GetData();
        mylist = getData.getData();

        String[] fromhere = {"id","name","password"};

        int[] viewwhere = {R.id.theid,R.id.name,R.id.password};

        adapter = new SimpleAdapter(getApplicationContext(),mylist,R.layout.listlayout,fromhere,viewwhere);
        listView.setAdapter(adapter);
    }

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected void onPostExecute(String r)
        {
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(MainActivity.this , "Login Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {
            String usernam = username.getText().toString();
            String passwordd = password.getText().toString();
            if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Username and Password";
            else
            {
                try
                {

                    con = connectionclass(un,pass,db,ip);        // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select * from usersonly where name= '" + usernam.toString() + "' and password = '"+ passwordd.toString() +"'  ";
//                        String query = "select * from usersonly";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            z = "Login successful";
                            isSuccess=true;
                            con.close();
                        }
                        else
                        {
                            z = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String ip)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + database + ";user=" + user
                    + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
