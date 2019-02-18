package com.example.idlegamepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Resource ironOre = new Resource("Iron Ore");
    Resource ironBar = new Resource("Iron Bar");
    private TextView textMineClicks;
    private TextView textIronOre;
    private TextView textIronBar;
    private TextView textUpgradeIron;
    private TextView textUpgradeSpeed;
    private int upgradeIronNumber;
    private int upgradeSpeedNumber;
    private Button buttonUpgradeIron;
    private Button buttonUpgradeSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMineClicks = findViewById(R.id.textViewMineClicks);
        textIronOre = findViewById(R.id.textViewIronOre);
        textIronBar = findViewById(R.id.textViewIronBar);
        textUpgradeIron = findViewById(R.id.textViewAmountToUpgradeIron);
        textUpgradeSpeed = findViewById(R.id.textViewAmountToUpgradeSpeed);
        buttonUpgradeIron = findViewById(R.id.buttonUpgradeAmount);
        buttonUpgradeSpeed = findViewById(R.id.buttonUpgradeSpeed);

        ironOre.setAcquireNumber(10);
        ironBar.setAcquireNumber(2);
        upgradeIronNumber = 2;
        upgradeSpeedNumber = 2;
        setTextViews();

    }

    public void clickMine(View view) {
        if (ironOre.getAcquireCount() < ironOre.getAcquireNumber()) {
            ironOre.setAcquireCount(ironOre.getAcquireCount() + 1);
        } else {
            ironOre.setAcquireCount(1);
            ironOre.setResourceNumber(ironOre.getResourceNumber() + ironOre.getIncrementNumber());
        }
        setTextViews();
    }

    public void clickSmelt(View view) {
        int i = (int)Math.floor(ironOre.getResourceNumber() / ironBar.getAcquireNumber());
        ironBar.setResourceNumber(ironBar.getResourceNumber() + i);
        ironOre.setResourceNumber(ironOre.getResourceNumber() - (i*2));
        setTextViews();
    }

    private void setTextViews() {
        updateButtons();
        textIronOre.setText(ironOre.getResourceName() + ": " + String.valueOf(ironOre.getResourceNumber()));
        textIronBar.setText(ironBar.getResourceName() + ": " + String.valueOf(ironBar.getResourceNumber()));
        textMineClicks.setText(String.valueOf(ironOre.getAcquireCount() - 1) + " / " + String.valueOf(ironOre.getAcquireNumber()));
        textUpgradeIron.setText(String.valueOf(upgradeIronNumber));
        textUpgradeSpeed.setText(String.valueOf(upgradeSpeedNumber));
    }

    private void updateButtons() {
        if (upgradeIronNumber > ironBar.getResourceNumber()) {
            buttonUpgradeIron.setAlpha(.3f);
            buttonUpgradeIron.setClickable(false);
        } else {
            buttonUpgradeIron.setAlpha(1f);
            buttonUpgradeIron.setClickable(true);
        }

        if (ironOre.getAcquireNumber() < 2) {
            buttonUpgradeSpeed.setAlpha(0f);
            buttonUpgradeSpeed.setClickable(false);
            textUpgradeSpeed.setAlpha(0f);
            textMineClicks.setAlpha(0f);
        } else if (upgradeSpeedNumber > ironBar.getResourceNumber()) {
            buttonUpgradeSpeed.setAlpha(.3f);
            buttonUpgradeSpeed.setClickable(false);
        } else {
            buttonUpgradeSpeed.setAlpha(1f);
            buttonUpgradeSpeed.setClickable(true);
        }
    }

    public void clickUpgradeIron(View view) {
        ironBar.setResourceNumber(ironBar.getResourceNumber() - upgradeIronNumber);
        ironOre.setIncrementNumber(ironOre.getIncrementNumber() * 2);
        upgradeIronNumber = (int) Math.ceil(upgradeIronNumber * 2.5);
        setTextViews();
    }

    public void clickUpgradeSpeed(View view) {
        ironBar.setResourceNumber(ironBar.getResourceNumber() - upgradeSpeedNumber);
        ironOre.setAcquireNumber(ironOre.getAcquireNumber() - 1);
        upgradeSpeedNumber = (int) Math.ceil(upgradeSpeedNumber * 1.7);
        setTextViews();
    }

}
