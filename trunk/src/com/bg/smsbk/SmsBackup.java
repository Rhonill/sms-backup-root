package com.bg.smsbk;

import java.io.DataOutputStream;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder; 
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SmsBackup extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button backup = (Button) findViewById(R.id.backup);
        Button restore = (Button) findViewById(R.id.restore);
        final Builder builder = new AlertDialog.Builder(SmsBackup.this);
        
        builder.setTitle("Info");
        builder.setPositiveButton("Ok", null);
        
        backup.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
            	
            	
                    Process process = null;
                    DataOutputStream os = null;
            		try {
            			process = Runtime.getRuntime().exec("su");
            	        os = new DataOutputStream(process.getOutputStream());
            	        os.writeBytes("cp /data/data/com.android.providers.telephony/databases/mmssms.db /sdcard \n");
            	        os.writeBytes("exit\n");
            	        os.flush();
            	        process.waitFor();
            	        
            	        builder.setMessage("Backup done.. Found it in /sdcard/mmssms.db");
                        builder.show();
                        
            		} catch (Exception e) {
            			return;
            		}
            		finally {
            			try {
            				if (os != null) {
            					os.close();
            				}
            				process.destroy();
            			} catch (Exception e) {
            				// Be Happy :)
            			}
            		}
            		return;
                }  });
        
        restore.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
            	
            	
                    Process process = null;
                    DataOutputStream os = null;
            		try {
            			process = Runtime.getRuntime().exec("su");
            	        os = new DataOutputStream(process.getOutputStream());
            	        os.writeBytes("cp /sdcard/mmssms.db /data/data/com.android.providers.telephony/databases \n");
            	        os.writeBytes("exit\n");
            	        os.flush();
            	        process.waitFor();

            	        builder.setMessage("Backup restored..");
                        builder.show();
            		} catch (Exception e) {
            			return;
            		}
            		finally {
            			try {
            				if (os != null) {
            					os.close();
            				}
            				process.destroy();
            			} catch (Exception e) {
            				// Be Happy :)
            				
            			}
            		}
            		return;
                }  });
        
        
}}