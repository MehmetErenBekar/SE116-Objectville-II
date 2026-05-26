package com.objectville.model;

import com.objectville.interfaces.Serviceable;
import com.objectville.interfaces.Upgradable;
import com.objectville.interfaces.UtilityConsumer;

public abstract class Zone extends Cell implements Serviceable, Upgradable, UtilityConsumer {
    protected int level = 0;
    protected int demand = 1;
    protected int output = 0;
    protected int receivedElectricity = 0;
    protected int receivedWater = 0;
    protected int receivedInternet = 0;
    protected boolean hasSecurity = false;
    protected boolean hasHealth = false;
    protected boolean hasEducation = false;

    public Zone(int row, int col) {
        super(row, col);
    }

    public int getLevel() {
        return level;
    }

    public int getDemand() {
        return demand;
    }

    public int getOutput() {
        return output;
    }

    public int getReceivedElectricity() {
        return receivedElectricity;
    }

    public int getReceivedWater() {
        return receivedWater;
    }

    public int getReceivedInternet() {
        return receivedInternet;
    }

    public boolean isHasSecurity() {
        return hasSecurity;
    }

    public boolean isHasHealth() {
        return hasHealth;
    }

    public boolean isHasEducation() {
        return hasEducation;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public void setReceivedElectricity(int receivedElectricity) {
        this.receivedElectricity = receivedElectricity;
    }

    public void setReceivedWater(int receivedWater) {
        this.receivedWater = receivedWater;
    }

    public void setReceivedInternet(int receivedInternet) {
        this.receivedInternet = receivedInternet;
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public abstract int calculateProduction();

    public abstract void updateLevel();

    public void reset() {
        receivedElectricity = 0;
        receivedWater = 0;
        receivedInternet = 0;
        hasSecurity = false;
        hasHealth = false;
        hasEducation = false;
    }

    public void receiveSecurity(boolean value) {
        hasSecurity = value;
    }

    public void receiveHealth(boolean value) {
        hasHealth = value;
    }

    public void receiveEducation(boolean value) {
        hasEducation = value;
    }

    public void adjustDemand() {
        if (output < 1) {
            demand = 1;
        } else {
            demand = output;
        }
    }
}
