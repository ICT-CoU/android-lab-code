package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsEmailPhone extends AppCompatActivity {
    Button sms, email, phone;
    EditText smsNo, smsBody, emailTo, emailSubject, emailBody, phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_email_phone);

        sms = findViewById(R.id.sms);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        smsNo = findViewById(R.id.smsNo);
        smsBody = findViewById(R.id.smsBody);
        emailTo = findViewById(R.id.emailTo);
        emailSubject = findViewById(R.id.emailSubject);
        emailBody = findViewById(R.id.emailBody);
        phoneNo = findViewById(R.id.phoneNo);


        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        try {
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(smsNo.getText().toString(), null, smsBody.getText().toString(), null,null);
                        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                                Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Message sending failed!",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }
//                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//                sendIntent.putExtra("address", "01689740070");
//                sendIntent.putExtra("sms_body", "default content");
//                sendIntent.setType("vnd.android-dir/mms-sms");
//                startActivity(sendIntent);

//                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to=emailTo.getText().toString();
                String subject=emailSubject.getText().toString();
                String message=emailBody.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=phoneNo.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + number));
                startActivity(callIntent);
            }
        });
    }
}