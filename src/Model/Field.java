package Model;

public class Field {
    private int posX;
    private int posY;
    private int threatCount;
    private boolean isOccupied;
    private String occupiedBy;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    public Field(int posX, int posY, int threatCount, boolean isOccupied, String occupiedBy) {
        this.posX = posX;
        this.posY = posY;
        this.threatCount = threatCount;
        this.isOccupied = isOccupied;
        this.occupiedBy = occupiedBy;
    }
}
