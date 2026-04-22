package ru.mirea.nosenkovtm.mireaproject;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

import ru.mirea.nosenkovtm.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CameraFragment.OnPhotoTakenListener {

    private DrawerLayout drawerLayout;
    private ActivityMainBinding binding;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    private ImageView getHeaderImageView() {
        View headerView = navigationView.getHeaderView(0);
        if (headerView != null) {
            Log.d("headerView", "found");
            return headerView.findViewById(R.id.imageView);
        }
        Log.d("headerView", "not found");
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (item.getItemId() == R.id.nav_browser) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BrowserFragment()).commit();
        } else if (item.getItemId() == R.id.nav_music) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MusicFragment()).commit();
        } else if (item.getItemId() == R.id.nav_compass) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompassFragment()).commit();
        } else if (item.getItemId() == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CameraFragment()).commit();
        } else if (item.getItemId() == R.id.nav_microphone) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MicrophoneFragment()).commit();
        } else if (item.getItemId() == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        } else if (item.getItemId() == R.id.nav_file) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FileFragment()).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPhotoTaken(String photoPath) {
        ImageView headerImageView = getHeaderImageView();
        File imgFile = new File(photoPath);
        if (headerImageView != null && imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath()    );
            headerImageView.setImageBitmap(myBitmap);
        }
    }
}