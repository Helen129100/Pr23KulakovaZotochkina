package com.example.pr23;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mCatSound, mChickenSound, mCowSound, mDogSound, mDuckSound, mSheepSound;
    private int mStreamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSoundPool();

        mAssetManager = getAssets();

        mCatSound = loadSound("cat.ogg");
        mChickenSound = loadSound("chicken.ogg");
        mCowSound = loadSound("cow.ogg");
        mDogSound = loadSound("dog.ogg");
        mDuckSound = loadSound("duck.ogg");
        mSheepSound = loadSound("sheep.ogg");

        ImageView cowImageButton = findViewById(R.id.image_cow);
        cowImageButton.setOnClickListener(onClickListener);

        ImageView chickenImageButton = findViewById(R.id.image_chicken);
        chickenImageButton.setOnClickListener(onClickListener);

        ImageView catImageButton = findViewById(R.id.image_cat);
        catImageButton.setOnClickListener(onClickListener);

        View duckImageButton = findViewById(R.id.image_duck);
        duckImageButton.setOnClickListener(onClickListener);

        ImageView sheepImageButton = findViewById(R.id.image_sheep);
        sheepImageButton.setOnClickListener(onClickListener);

        ImageView dogImageButton = findViewById(R.id.image_dog);
        dogImageButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.image_cow){
                playSound(mCowSound);
            }
            else{
                if(v.getId() == R.id.image_chicken){
                    playSound(mChickenSound);
                }
                else{
                    if(v.getId() == R.id.image_cat){
                        playSound(mCatSound);
                    }
                    else{
                        if(v.getId() == R.id.image_duck){
                            playSound(mDuckSound);
                        }
                        else{
                            if(v.getId() == R.id.image_sheep){
                                playSound(mSheepSound);
                            }
                            else{
                                playSound(mDogSound);
                            }
                        }
                    }
                }
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        createSoundPool();

        mAssetManager = getAssets();

        mCatSound = loadSound("cat.ogg");
        mChickenSound = loadSound("chicken.ogg");
        mCowSound = loadSound("cow.ogg");
        mDogSound = loadSound("dog.ogg");
        mDuckSound = loadSound("duck.ogg");
        mSheepSound = loadSound("sheep.ogg");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }
}