package org.gmele.ellak.ellak2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity
{
    TextView TvTitle;
    EditText EtUserID;
    EditText EtPassword;
    Button BtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_login);
        TvTitle = (TextView) findViewById (R.id.TvTitle);
        EtUserID = (EditText) findViewById(R.id.EtUserID);
        EtPassword = (EditText) findViewById(R.id.EtPassword);
        BtLogin = (Button) findViewById (R.id.BtLogin);
        BtLogin.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Κωδικός = EtUserID.getText().toString().trim();
                String Συνθημ = EtPassword.getText().toString().trim();
                Intent Ret = new Intent ();
                if (Κωδικός.equals ("123") && Συνθημ.equals ("Ellak"))
                    Ret.putExtra ("Result", "Επιτυχής Αυθεντικοποίηση");
                else
                    Ret.putExtra ("Result", "Αποτυχημένη Αυθεντικοποίηση");
                setResult(RESULT_OK, Ret);
                finish ();
            }
        });
        Bundle bu;
        bu = getIntent().getExtras();
        TvTitle.setText ("Αυθεντικοποίηση χρήστη: " + bu.getString("Name"));
    }

}
