package Model;

import java.util.ArrayList;

public class Field {
    private ArrayList<Integer> coords = new ArrayList<>();
    private int threatCount;
    private boolean isOccupied;
    private String occupiedBy;

    public Field(ArrayList<Integer> coords, int threatCount, boolean isOccupied, String occupiedBy) {
        this.coords = coords;
        this.threatCount = threatCount;
        this.isOccupied = isOccupied;
        this.occupiedBy = occupiedBy;
    }

    public ArrayList<Integer> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<Integer> coords) {
        this.coords = coords;
    }

    public int getThreatCount() {
        return threatCount;
    }

    public void setThreatCount(int threatCount) {
        this.threatCount = threatCount;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
