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
}
