package com.jo.testappfingerprintauth;

import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "JO";
    private Cipher cipher;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager)getSystemService(FINGERPRINT_SERVICE);

        if (!fingerprintManager.isHardwareDetected())
            Toast.makeText(this, "@string/finger_print_authentication_not_enabled_message",Toast.LENGTH_SHORT).show();
        else {
            if (!fingerprintManager.hasEnrolledFingerprints())
                Toast.makeText(this, "@string/register_request_message",Toast.LENGTH_SHORT).show();
            else {
                if (!keyguardManager.isKeyguardSecure())
                    Toast.makeText(this, "@string/lock_screen_security_not_enabled_message",Toast.LENGTH_SHORT).show();
                else
                    genKey();
                if (cipherInit()){
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager().CryptoObject(cipher);
                    FingerprintHandler helper = new FingerprintHandler(this)
                }

        }
    }
    private void genKey(){

        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        keyStore.load(null);
        keyGenerator




    }

    private boolean cipherInit(){
        try {
            cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/"+KeyProperties.BLOCK_MODE_CBC+"/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();

            try {
                keyStore.load(null);
                SecretKey key = (SecretKey)keyStore.getKey(KEY_NAME,null);
                cipher.init(Cipher.ENCRYPT_MODE,key);
                return true;
        } catch (IOException e1) {
                return false;
                e1.printStackTrace();
            } catch (CertificateException e1) {
                return false;
                e1.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
                return false;
                e1.printStackTrace();
            } catch (UnrecoverableKeyException e1) {
                return false;
                e1.printStackTrace();
            } catch (KeyStoreException e1) {
                return false;
                e1.printStackTrace();
            } catch (InvalidKeyException e1) {
                return false;
                e1.printStackTrace();
            }

        }
    }
}
}
