package org.gmele.ellak.ellak2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class UserActivity extends Activity implements View.OnClickListener {
    EditText EtUser;
    Button BtAuth;
    TextView TvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_user);
        EtUser = (EditText) findViewById (R.id.EtUser);
        BtAuth = (Button) findViewById (R.id.BtAuth);
        BtAuth.setOnClickListener (this);
        TvResult = (TextView) findViewById (R.id.TvResult);
    }


    @Override
    public void onClick(View v)
    {
        if (v == BtAuth)
        {
            String UserName = EtUser.getText().toString().trim();
            EtUser.setText(UserName);
            if (UserName.length() < 3)
            {
                EtUser.requestFocus();
                return;
            }
            Intent MyIntent = new Intent(this, LoginActivity.class);
            MyIntent.putExtra ("Name", UserName);
            startActivityForResult (MyIntent, 12);
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 12)
            if (resultCode == RESULT_OK)
            {
                String Res = data.getExtras().getString ("Result");
                TvResult.setText (Res);
            }
    }
}
