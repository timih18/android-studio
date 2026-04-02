package ru.mirea.nosenkovtm.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.security.InvalidParameterException;

import javax.crypto.SecretKey;

public class MainActivity<D> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public final String TAG = this.getClass().getSimpleName();
    private final int LoaderID = 1234;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void onClickButton(View v) {
        String name = editText.getText().toString();
        SecretKey key = Encryptor.generateKey();
        Bundle bundle = new Bundle();
        bundle.putByteArray(MyLoader.ARG_WORD, Encryptor.encryptMsg(name, key));
        bundle.putByteArray("key", key.getEncoded());
        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LoaderID) {
            Toast.makeText(this, "onCreateLoader " + id, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, args);
        }
        throw new InvalidParameterException("Invalid loader id");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished " + data);
            Toast.makeText(this, "onLoadFinished " + data, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }
}