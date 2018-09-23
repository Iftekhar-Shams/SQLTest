package sqltest2.sh4dow.com.sqltestv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button insert, update;
    ListView listView;
    SimpleAdapter adapter;
    EditText currentUsername, newUsername, newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        currentUsername = findViewById(R.id.currentusername);
        newUsername = findViewById(R.id.newusername);
        newPassword = findViewById(R.id.newpassword);

        update();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData insertData = new InsertData(getApplicationContext());
                String newName = newUsername.getText().toString().trim();
                String newPass = newPassword.getText().toString().trim();
                String currentName = currentUsername.getText().toString().trim();
                insertData.insert(Integer.parseInt(currentName),newName,newPass);
                adapter.notifyDataSetChanged();
                clearInputs();
                update();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData updateData = new UpdateData(getApplicationContext());
                String newName = newUsername.getText().toString().trim();
                String newPass = newPassword.getText().toString().trim();
                String currentName = currentUsername.getText().toString().trim();
                updateData.update(currentName,newName,newPass);
                clearInputs();
                update();
            }
        });
    }

    private void update(){
        List<Map<String,String>> mylist = new GetData().getData();

        String[] fromhere = {"id","name","password"};
        int[] viewwhere = {R.id.theid,R.id.name,R.id.password};

        adapter = new SimpleAdapter(getApplicationContext(),mylist,R.layout.listlayout,fromhere,viewwhere);
        listView.setAdapter(adapter);
    }

    private void clearInputs(){
        newUsername.setText("");
        newPassword.setText("");
        currentUsername.setText("");
    }

}
