package ru.mirea.nosenkovtm.mireaproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

public class MicrophoneFragment extends Fragment {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private TextView tvResult;
    private Button btnStart;
    private Button btnStop;

    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;
    private Handler handler;
    private Runnable updateRunnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_microphone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvResult = view.findViewById(R.id.tvResult);
        btnStart = view.findViewById(R.id.btnStart);
        btnStop = view.findViewById(R.id.btnStop);

        handler = new Handler(Looper.getMainLooper());

        btnStart.setOnClickListener(v -> startRecording());
        btnStop.setOnClickListener(v -> stopRecording());
        btnStop.setEnabled(false);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }

    private void startRecording() {
        if (isRecording) return;

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "Нет разрешения на запись звука", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            File tempDir = requireContext().getExternalFilesDir(null);
            if (tempDir == null) {
                tempDir = requireContext().getCacheDir();
            }
            mediaRecorder.setOutputFile(tempDir.getAbsolutePath() + "/temp_audio.3gp");

            mediaRecorder.prepare();
            mediaRecorder.start();

            isRecording = true;
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

            startUpdatingAmplitude();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Ошибка запуска микрофона", Toast.LENGTH_SHORT).show();
            releaseRecorder();
        }
    }

    private void stopRecording() {
        if (!isRecording) return;

        if (updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }

        releaseRecorder();

        isRecording = false;
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        tvResult.setText("Запись остановлена");
    }

    private void releaseRecorder() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaRecorder = null;
        }
    }

    private void startUpdatingAmplitude() {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                if (isRecording && mediaRecorder != null) {
                    int maxAmplitude = mediaRecorder.getMaxAmplitude();
                    int normalized = (int) (maxAmplitude / 327.67);
                    normalized = Math.min(100, Math.max(0, normalized));

                    tvResult.setText("Громкость: " + normalized + "%\n");

                    handler.postDelayed(this, 200);
                }
            }
        };
        handler.post(updateRunnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRecording) {
            stopRecording();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseRecorder();
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }
    }
}