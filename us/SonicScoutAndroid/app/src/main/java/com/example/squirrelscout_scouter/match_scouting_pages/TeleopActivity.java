package com.example.squirrelscout_scouter.match_scouting_pages;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.squirrelscout_scouter.MainApplication;
import com.example.squirrelscout_scouter.R;
import com.example.squirrelscout_scouter.ui.viewmodels.ScoutingSessionViewModel;
import com.example.squirrelscout_scouter.util.ScoutSingleton;
import com.example.squirrelscout_scouter.util.SharedImageSingleton;

public class TeleopActivity extends ComponentActivity implements View.OnClickListener {

    //Speaker Scoring
    TextView speakerTitle;
    //AMP Scoring
    TextView ampTitle, ampMissLabel, ampScoreLabel, ampScoreCounter, ampMissCounter;

    TextView teleOpCoralL4Score, teleOpCoralL4Miss;

    TextView teleOpCoralL4ScoreDecrement, teleOpCoralL4MissDecrement;

    TextView teleOpCoralL4ScoreIncrement, teleOpCoralL4MissIncrement;

    TextView ampMissIncrement, ampMissDecrement;

    TextView teleOpCoralL3Score, teleOpCoralL3Miss;

    TextView teleOpCoralL3ScoreDecrement, teleOpCoralL3MissDecrement;

    TextView teleOpCoralL3ScoreIncrement, teleOpCoralL3MissIncrement;

    TextView teleOpCoralL2Score, teleOpCoralL2Miss;

    TextView teleOpCoralL2ScoreDecrement, teleOpCoralL2MissDecrement;

    TextView teleOpCoralL2ScoreIncrement, teleOpCoralL2MissIncrement;

    TextView teleOpCoralL1Score, teleOpCoralL1Miss;

    TextView teleOpCoralL1ScoreDecrement, teleOpCoralL1MissDecrement;

    TextView teleOpCoralL1ScoreIncrement, teleOpCoralL1MissIncrement;

    TextView teleOpProcessorScore, teleOpProcessorMiss;

    TextView teleOpProcessorScoreDecrement, teleOpProcessorMissDecrement;

    TextView teleOpProcessorScoreIncrement, teleOpProcessorMissIncrement;

    TextView teleOpNetScore, teleOpNetMiss;

    TextView teleOpNetScoreIncrement, teleOpNetMissIncrement;
    TextView teleOpNetScoreDecrement, teleOpNetMissDecrement;





    //Breakdown
    AutoCompleteTextView dropdown, dropdown2;
    //Endgame
    Button trapYes, trapNo;
    boolean trapBool;
    CheckBox checkBox1, checkBox2, checkBox3;
    private boolean popUpWindowLaunched = false;
    private static final int REQUEST_POPUP = 1;
    float a, b;
    private MotionEvent savedEvent;
    ImageView imageView;
    Bitmap markerBitmap;
    SharedImageSingleton sharedImageSingleton = SharedImageSingleton.getInstance();

    //...
    Button nextButton;



//    ScoutInfo scoutInfo;

    private ScoutingSessionViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teleop_scouting);

        //...
        ScoutSingleton scoutSingleton = ScoutSingleton.getInstance();
        TextView label = (TextView) findViewById(R.id.textView3);
        label.setText("Match #" + scoutSingleton.getMatchNum() + "\n" + scoutSingleton.getRobotNum());
        sharedImageSingleton.reset();

        // view model
        ViewModelStoreOwner scoutingSessionViewModelStoreOwner = ((MainApplication) getApplication()).getScoutingSessionViewModelStoreOwner();
        model = new ViewModelProvider(scoutingSessionViewModelStoreOwner).get(ScoutingSessionViewModel.class);

        //...
        teleOpCoralL4Score = (TextView) findViewById(R.id.teleOpCoralL4Score);
        teleOpCoralL4Miss = (TextView) findViewById(R.id.teleOpCoralL4Miss);
        teleOpCoralL3Score = (TextView) findViewById(R.id.teleOpCoralL3Score);
        teleOpCoralL3Miss = (TextView) findViewById(R.id.teleOpCoralL3Miss);
        teleOpCoralL2Score = (TextView) findViewById(R.id.teleOpCoralL2Score);
        teleOpCoralL2Miss = (TextView) findViewById(R.id.teleOpCoralL2Miss);
        teleOpCoralL1Score = (TextView) findViewById(R.id.teleOpCoralL1Score);
        teleOpCoralL1Miss = (TextView) findViewById(R.id.teleOpCoralL1Miss);
        teleOpProcessorScore = (TextView) findViewById(R.id.teleOpProcessorScore);
        teleOpProcessorMiss = (TextView) findViewById(R.id.teleOpProcessorMiss);

        teleOpNetScore = (TextView) findViewById(R.id.teleOpNetScore);
        teleOpNetMiss = (TextView) findViewById(R.id.teleOpNetMiss);

        //ampMissCounter = (TextView) findViewById(R.id.AmpMissedCounter);

        //...
        //speakerTitle = (TextView) findViewById(R.id.SpeakerTitle);
        //speakerTitle.setOnClickListener(this);
        //ampTitle = (TextView) findViewById(R.id.AmpTitle);
        //ampTitle.setOnClickListener(this);
        teleOpCoralL4ScoreIncrement = (Button) findViewById(R.id.teleOpCoralL4ScoreIncrement);
        teleOpCoralL4ScoreIncrement.setOnClickListener(this);
        teleOpCoralL4ScoreDecrement = (Button) findViewById(R.id.teleOpCoralL4ScoreDecrement);
        teleOpCoralL4ScoreDecrement.setOnClickListener(this);

        teleOpCoralL4MissIncrement = (Button) findViewById(R.id.teleOpCoralL4MissIncrement);
        teleOpCoralL4MissIncrement.setOnClickListener(this);
        teleOpCoralL4MissDecrement = (Button) findViewById(R.id.teleOpCoralL4MissDecrement);
        teleOpCoralL4MissDecrement.setOnClickListener(this);

        teleOpCoralL3ScoreIncrement = (Button) findViewById(R.id.teleOpCoralL3ScoreIncrement);
        teleOpCoralL3ScoreIncrement.setOnClickListener(this);
        teleOpCoralL3ScoreDecrement = (Button) findViewById(R.id.teleOpCoralL3ScoreDecrement);
        teleOpCoralL3ScoreDecrement.setOnClickListener(this);

        teleOpCoralL3MissIncrement = (Button) findViewById(R.id.teleOpCoralL3MissIncrement);
        teleOpCoralL3MissIncrement.setOnClickListener(this);
        teleOpCoralL3MissDecrement = (Button) findViewById(R.id.teleOpCoralL3MissDecrement);
        teleOpCoralL3MissDecrement.setOnClickListener(this);


        teleOpCoralL2MissIncrement = (Button) findViewById(R.id.teleOpCoralL2MissIncrement);
        teleOpCoralL2MissIncrement.setOnClickListener(this);
        teleOpCoralL2MissDecrement = (Button) findViewById(R.id.teleOpCoralL2MissDecrement);
        teleOpCoralL2MissDecrement.setOnClickListener(this);

        teleOpCoralL2ScoreIncrement = (Button) findViewById(R.id.teleOpCoralL2ScoreIncrement);
        teleOpCoralL2ScoreIncrement.setOnClickListener(this);
        teleOpCoralL2ScoreDecrement = (Button) findViewById(R.id.teleOpCoralL2ScoreDecrement);
        teleOpCoralL2ScoreDecrement.setOnClickListener(this);



        teleOpCoralL1MissIncrement = (Button) findViewById(R.id.teleOpCoralL1MissIncrement);
        teleOpCoralL1MissIncrement.setOnClickListener(this);
        teleOpCoralL1MissDecrement = (Button) findViewById(R.id.teleOpCoralL1MissDecrement);
        teleOpCoralL1MissDecrement.setOnClickListener(this);

        teleOpCoralL1ScoreIncrement = (Button) findViewById(R.id.teleOpCoralL1ScoreIncrement);
        teleOpCoralL1ScoreIncrement.setOnClickListener(this);
        teleOpCoralL1ScoreDecrement = (Button) findViewById(R.id.teleOpCoralL1ScoreDecrement);
        teleOpCoralL1ScoreDecrement.setOnClickListener(this);



        teleOpProcessorMissIncrement = (Button) findViewById(R.id.teleOpProcessorMissIncrement);
        teleOpProcessorMissIncrement.setOnClickListener(this);
        teleOpProcessorMissDecrement = (Button) findViewById(R.id.teleOpProcessorMissDecrement);
        teleOpProcessorMissDecrement.setOnClickListener(this);

        teleOpProcessorScoreIncrement = (Button) findViewById(R.id.teleOpProcessorScoreIncrement);
        teleOpProcessorScoreIncrement.setOnClickListener(this);
        teleOpProcessorScoreDecrement = (Button) findViewById(R.id.teleOpProcessorScoreDecrement);
        teleOpProcessorScoreDecrement.setOnClickListener(this);



        teleOpNetMissIncrement = (Button) findViewById(R.id.teleOpNetMissIncrement);
        teleOpNetMissIncrement.setOnClickListener(this);
        teleOpNetMissDecrement = (Button) findViewById(R.id.teleOpNetMissDecrement);
        teleOpNetMissDecrement.setOnClickListener(this);

        teleOpNetScoreIncrement = (Button) findViewById(R.id.teleOpNetScoreIncrement);
        teleOpNetScoreIncrement.setOnClickListener(this);
        teleOpNetScoreDecrement = (Button) findViewById(R.id.teleOpNetScoreDecrement);
        teleOpNetScoreDecrement.setOnClickListener(this);



        ampMissIncrement = (Button) findViewById(R.id.Amp_Missed_Increment);
        ampMissIncrement.setOnClickListener(this);
        ampMissDecrement = (Button) findViewById(R.id.Amp_Missed_Decrement);
        ampMissDecrement.setOnClickListener(this);
        ampMissLabel = (TextView) findViewById(R.id.L1Label);
        ampMissLabel.setOnClickListener(this);
        ampScoreLabel = (TextView) findViewById(R.id.L4Label);
        ampScoreLabel.setOnClickListener(this);
        trapYes = (Button) findViewById(R.id.TRAP_YES);
        trapYes.setOnClickListener(this);
        trapNo = (Button) findViewById(R.id.TRAP_NO);
        trapNo.setOnClickListener(this);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox2);

        //Buttons
        nextButton = (Button) findViewById(R.id.NEXT);
        nextButton.setOnClickListener(this);

        //Breakdown dropdown
        dropdown = findViewById(R.id.dropdown);
        String[] items = new String[]{"Tipped", "Mechanical Failure", "Incapacitated",};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_text, items);
        dropdown.setAdapter(adapter);
        dropdown.setKeyListener(null);
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdown.showDropDown();
            }
        });
        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nextPageCheck();
            }
        });

        //Climb dropdown
        dropdown2 = findViewById(R.id.dropdown2);
        String[] items2 = new String[]{"Did Not Attempt", "Parked", "Failed", "Success", "Harmony"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.dropdown_text, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setKeyListener(null);
        dropdown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdown2.showDropDown();
            }
        });
        dropdown2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nextPageCheck();
            }
        });

        //Image Heatmap logics
        //image capture
        imageView = findViewById(R.id.fieldImageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                savedEvent = event;

                if (!popUpWindowLaunched) {
                    // Save the touch event details

                    Intent popUpWindow = new Intent(TeleopActivity.this, PopUpWindow.class);
                    startActivityForResult(popUpWindow, REQUEST_POPUP);
                    popUpWindowLaunched = true;
                }

                if (action == MotionEvent.ACTION_DOWN) {
                    // Check if the touch is within the bounds of the ImageView
                    if (isTouchInsideView(event.getRawX(), event.getRawY(), imageView)) {
                        // Add a marker or perform any action you want
                        a = savedEvent.getX();
                        b = savedEvent.getY();
                        addMarker(savedEvent.getX(), savedEvent.getY(), imageView, 3); //no
                        sharedImageSingleton.setMarkedImage(markerBitmap);

                        return true;
                    }
                }

                return true;
            }
        });

        /*
        //start animation
        animationStart();
         */
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_POPUP) {
            // Reset the flag when the PopUpWindow is closed
            popUpWindowLaunched = false;

            if (requestCode == REQUEST_POPUP) {
                // Reset the flag and savedEvent to allow launching the PopUpWindow again
                System.out.println(sharedImageSingleton.getScored());
                addMarker(a, b, imageView, sharedImageSingleton.getScored());
                sharedImageSingleton.setMarkedImage(markerBitmap);
                popUpWindowLaunched = false;
                savedEvent = null;
            }
        }
    }

    public void onClick(View view){
        int clickedId = view.getId();

        if(clickedId == R.id.teleOpCoralL4ScoreIncrement){
            counterIncrementLogic(teleOpCoralL4Score);
        }
        else if(clickedId == R.id.teleOpCoralL4ScoreDecrement){
            counterDecrementLogic(teleOpCoralL4Score);
        }

        else if(clickedId == R.id.teleOpCoralL4MissIncrement){
            counterIncrementLogic(teleOpCoralL4Miss);
        }

        else if(clickedId == R.id.teleOpCoralL4MissDecrement){
            counterDecrementLogic(teleOpCoralL4Miss);
        }

        else if(clickedId == R.id.teleOpCoralL3ScoreIncrement){
            counterIncrementLogic(teleOpCoralL3Score);
        }

        else if(clickedId == R.id.teleOpCoralL3ScoreDecrement){
            counterDecrementLogic(teleOpCoralL3Score);
        }

        else if(clickedId == R.id.teleOpCoralL3MissIncrement){
            counterIncrementLogic(teleOpCoralL3Miss);
        }

        else if(clickedId == R.id.teleOpCoralL3MissDecrement){
            counterDecrementLogic(teleOpCoralL3Miss);
        }


        else if(clickedId == R.id.teleOpCoralL2ScoreIncrement){
            counterIncrementLogic(teleOpCoralL2Score);
        }

        else if(clickedId == R.id.teleOpCoralL2ScoreDecrement){
            counterDecrementLogic(teleOpCoralL2Score);
        }

        else if(clickedId == R.id.teleOpCoralL2MissIncrement){
            counterIncrementLogic(teleOpCoralL2Miss);
        }

        else if(clickedId == R.id.teleOpCoralL2MissDecrement){
            counterDecrementLogic(teleOpCoralL2Miss);
        }


        else if(clickedId == R.id.teleOpCoralL1ScoreIncrement){
            counterIncrementLogic(teleOpCoralL1Score);
        }

        else if(clickedId == R.id.teleOpCoralL1ScoreDecrement){
            counterDecrementLogic(teleOpCoralL1Score);
        }

        else if(clickedId == R.id.teleOpCoralL1MissIncrement){
            counterIncrementLogic(teleOpCoralL1Miss);
        }

        else if(clickedId == R.id.teleOpCoralL1MissDecrement){
            counterDecrementLogic(teleOpCoralL1Miss);
        }


        else if(clickedId == R.id.teleOpProcessorScoreIncrement){
            counterIncrementLogic(teleOpProcessorScore);
        }

        else if(clickedId == R.id.teleOpProcessorScoreDecrement){
            counterDecrementLogic(teleOpProcessorScore);
        }

        else if(clickedId == R.id.teleOpProcessorMissIncrement){
            counterIncrementLogic(teleOpProcessorMiss);
        }

        else if(clickedId == R.id.teleOpProcessorMissDecrement){
            counterDecrementLogic(teleOpProcessorMiss);
        }

        else if(clickedId == R.id.teleOpNetScoreIncrement){
            counterIncrementLogic(teleOpNetScore);
        }

        else if(clickedId == R.id.teleOpNetScoreDecrement){
            counterDecrementLogic(teleOpNetScore);
        }

        else if(clickedId == R.id.teleOpNetMissIncrement){
            counterIncrementLogic(teleOpNetMiss);
        }

        else if(clickedId == R.id.teleOpNetMissDecrement){
            counterDecrementLogic(teleOpNetMiss);
        }





        else if(clickedId == R.id.Amp_Missed_Increment){
            counterIncrementLogic(ampMissCounter);
        }
        else if(clickedId == R.id.Amp_Missed_Decrement){
            counterDecrementLogic(ampMissCounter);
        }
        else if(clickedId == R.id.TRAP_YES){
            trapYesLogic();
        }
        else if(clickedId == R.id.TRAP_NO){
            trapNoLogic();
        }
        else if(clickedId == R.id.NEXT){
            animateButton((Button) view);
            nextPageLogic();
        }
    }

    //trap logic
    private void trapYesLogic(){
        //if not selected
        if(trapYes.getTextColors() != ContextCompat.getColorStateList(this, R.color.white)){
            trapYes.setTextColor(ContextCompat.getColor(this, R.color.white));
            trapYes.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));
            trapNo.setTextColor(ContextCompat.getColor(this, R.color.black));
            trapNo.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGrey));
            trapBool = true;
            nextPageCheck();
        }
    }
    private void trapNoLogic(){
        //if not selected
        if(trapNo.getTextColors() != ContextCompat.getColorStateList(this, R.color.white)){
            trapYes.setTextColor(ContextCompat.getColor(this, R.color.black));
            trapYes.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGrey));
            trapNo.setTextColor(ContextCompat.getColor(this, R.color.white));
            trapNo.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.error));
            trapBool = false;
            nextPageCheck();
        }
    }

    //next page logic
    private void nextPageCheck(){
        if(!(dropdown2.getText().toString().isEmpty()) && (trapYes.getTextColors() != ContextCompat.getColorStateList(this, R.color.green))){
            nextButton.setTextColor(ContextCompat.getColor(this, R.color.black));
            nextButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.accent));
            nextButton.setText("NEXT PAGE");
            Toast.makeText(TeleopActivity.this, dropdown.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void nextPageLogic(){
        if(nextButton.getText().toString().equals("NEXT PAGE")){
            Toast.makeText(TeleopActivity.this, "Going to Next Page", Toast.LENGTH_SHORT).show();
            //saveScoutInfo();
            // Create an Intent to launch the target activity
            Intent intent = new Intent(TeleopActivity.this, QRCodeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Start the target activity with the Intent
            addMarker(0,0,imageView,4);
            saveImageToGallery(getMarkedImage(imageView), "Trial");
            saveScoutInfo();
            startActivity(intent);
        }
    }

    //Counter increment and decrement logic
    private void counterIncrementLogic(TextView counter) {
        String matchString = counter.getText().toString();
        try {
            int num = Integer.parseInt(matchString);
            counter.setText(String.valueOf(num + 1));
        } catch (NumberFormatException e) {
            // Handle the case where the input string is not a valid integer
            // Display an error message or perform appropriate error handling
            e.printStackTrace();
        }
    }
    private void counterDecrementLogic(TextView counter) {
        String matchString = counter.getText().toString();
        try {
            int num = Integer.parseInt(matchString);
            if(num - 1 < 0){
                counter.setText("0");
            }else{
                counter.setText(String.valueOf(num - 1));
            }
        } catch (NumberFormatException e) {
            // Handle the case where the input string is not a valid integer
            // Display an error message or perform appropriate error handling
            e.printStackTrace();
        }
    }

    //pick location logic
    private void pickUpLocationLogic(Button button){
        if(button.getBackgroundTintList() == ContextCompat.getColorStateList(this, R.color.green)){
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
        }
        else {
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green));

        }
    }

    //heatmap logic functions
    private boolean isTouchInsideView(float x, float y, View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int viewX = location[0];
        int viewY = location[1];
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        return (x > viewX && x < (viewX + viewWidth) && y > viewY && y < (viewY + viewHeight));
    }

    private void addMarker(float x, float y, ImageView imageView, int num) {
        // Create a marker (in this example, a red circle)
        Bitmap originalBitmap = getMarkedImage(imageView);
        markerBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(markerBitmap);
        canvas.drawBitmap(originalBitmap, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        Paint paint2 = new Paint();
        paint2.setColor(Color.LTGRAY);
        paint2.setStyle(Paint.Style.STROKE);
        if(num == 1){
            paint.setColor(Color.GREEN);
            canvas.drawCircle(x, y, 10, paint); // Adjust the radius as needed
        }
        else if(num == 0){
            paint.setColor(Color.RED);
            canvas.drawCircle(x, y, 10, paint); // Adjust the radius as needed
        }
        else if(num == 3){
            canvas.drawPoint(1,1,paint2);
        }
        else if(num == 4){
            paint2.setStrokeWidth(1.5f);
            paint2.setTextSize(20f);
            String s = ("Team: " + ScoutSingleton.getInstance().getRobotNum() + "; Match: " + ScoutSingleton.getInstance().getMatchNum());
            canvas.drawText(s, 250, 40, paint2); // Adjust the text position as needed
        }

        // Set the marked image with the added marker to the ImageView
        imageView.setImageBitmap(markerBitmap);
    }

    private Bitmap getMarkedImage(ImageView imageView) {
        // Create a bitmap from the ImageView
        Bitmap originalBitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(originalBitmap);
        imageView.draw(canvas);

        return originalBitmap;
    }

    private void saveImageToGallery(Bitmap bitmap, String displayName) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, displayName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);

        // Save the image to the Photos (or Gallery) using MediaStore
        try {
            // Use insertImage method to add image to the gallery
            String imageUrl = MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    bitmap,
                    displayName,
                    "Image with marker"
            );

            // If the insertion was successful, notify the media scanner
            if (imageUrl != null) {
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(imageUrl)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //user feedback when clicking button
    private void animateButton(Button button){
        button.animate().scaleXBy(0.025f).scaleYBy(0.025f).setDuration(250).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(() -> {
            button.animate().scaleXBy(-0.025f).scaleYBy(-0.025f).setDuration(250);
        }).start();
    }

    private String getDistance(){
        String dist = "";
        if(checkBox1.isChecked()){
            dist = "S";
        }
        if(checkBox2.isChecked()){
            if(!dist.equals("")){
                dist += "-W";
            }
            else{
                dist = "W";
            }
        }
        if(checkBox3.isChecked()){
            if(!dist.equals("")){
                dist += "-C";
            }
            else{
                dist = "C";
            }
        }
        return dist;
    }

    /*
    private void animationStart(){
        //animations
        titleCard.setTranslationY(-500);
        titleCard.setAlpha(0f);
        firstCard.setTranslationY(250);
        firstCard.setAlpha(0f);
        secondCard.setTranslationY(250);
        secondCard.setAlpha(0f);
        title.setAlpha(0f);
        title.setTranslationX(-100);
        info.setAlpha(0f);
        info.setTranslationX(-100);
        cone1.setTranslationX(50);
        cone1.setAlpha(0);
        cone2.setTranslationX(50);
        cone2.setAlpha(0);
        cone3.setTranslationX(50);
        cone3.setAlpha(0);
        cube1.setTranslationX(-50);
        cube1.setAlpha(0);
        cube2.setTranslationX(-50);
        cube2.setAlpha(0);
        cube3.setTranslationX(-50);
        cube3.setAlpha(0);
        defenseLayout.setAlpha(0f);
        defenseLayout.setTranslationY(50);
        incapLayout.setAlpha(0f);
        incapLayout.setTranslationY(50);
        climbLayout.setAlpha(0f);
        climbLayout.setTranslationY(50);
        menuLayout.setAlpha(0f);
        menuLayout.setTranslationY(50);
        nextButton.setAlpha(0f);
        nextButton.setTranslationY(50);
        firstCard.animate().alpha(1f).translationYBy(-250).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(() -> {
            secondCard.animate().alpha(1f).translationYBy(-250).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(() ->{
                titleCard.animate().alpha(1f).translationYBy(500).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(() ->{
                    title.animate().alpha(1f).translationXBy(100).setDuration(300);
                    info.animate().alpha(1f).translationXBy(100).setDuration(300);
                    cone1.animate().alpha(1f).translationXBy(-50).setDuration(750);
                    cone2.animate().alpha(1f).translationXBy(-50).setDuration(750);
                    cone3.animate().alpha(1f).translationXBy(-50).setDuration(750);
                    cube1.animate().alpha(1f).translationXBy(50).setDuration(750);
                    cube2.animate().alpha(1f).translationXBy(50).setDuration(750);
                    cube3.animate().alpha(1f).translationXBy(50).setDuration(750);
                    incapLayout.animate().alpha(1f).translationYBy(-50).setDuration(750);
                    defenseLayout.animate().alpha(1f).translationYBy(-50).setDuration(750);
                    climbLayout.animate().alpha(1f).translationYBy(-50).setDuration(750);
                    menuLayout.animate().alpha(1f).translationYBy(-50).setDuration(750);
                    nextButton.animate().alpha(1f).translationYBy(-50).setDuration(750);
                }).start();
            }).start();
        }).start();

    }
*/
    public void saveScoutInfo(){
        // TODO: Keyush/Archit: For Saturday. Do the UI -> Model as a model.captureTeleop()
        ScoutSingleton scoutSingleton = ScoutSingleton.getInstance();

        model.captureTeleData(
                sharedImageSingleton.getSuccess(),
                sharedImageSingleton.getMiss(),
                0,//Integer.parseInt(ampScoreCounter.getText().toString()),
                0,//Integer.parseInt(ampMissCounter.getText().toString()),
                getDistance(),
                dropdown.getText().toString(),
                dropdown2.getText().toString(),
                trapBool,
                scoutSingleton.getFirstPickup(),
                Integer.parseInt(teleOpCoralL4Score.getText().toString()),
                Integer.parseInt(teleOpCoralL4Miss.getText().toString()),
                Integer.parseInt(teleOpCoralL3Score.getText().toString()),
                Integer.parseInt(teleOpCoralL3Miss.getText().toString()),
                Integer.parseInt(teleOpCoralL2Score.getText().toString()),
                Integer.parseInt(teleOpCoralL2Miss.getText().toString()),
                Integer.parseInt(teleOpCoralL1Score.getText().toString()),
                Integer.parseInt(teleOpCoralL1Miss.getText().toString()),
                Integer.parseInt(teleOpProcessorScore.getText().toString()),
                Integer.parseInt(teleOpProcessorMiss.getText().toString()),
                Integer.parseInt(teleOpNetScore.getText().toString()),
                Integer.parseInt(teleOpNetMiss.getText().toString())


        );
    }

}
