package ru.mirea.nosenkovtm.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    private byte[] name;
    private SecretKey key;
    public static final String ARG_WORD = "word";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null) {
            name = args.getByteArray(ARG_WORD);
            byte[] k = args.getByteArray("key");
            key = new SecretKeySpec(k, 0, k.length, "AES");
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);
        return Encryptor.decryptMsg(name, key);
    }
}
