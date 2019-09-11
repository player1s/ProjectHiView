package Model;

public class Field {
    private int posX;
    private int posY;
    private boolean isThreatened;
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

    public boolean isThreatened() {
        return isThreatened;
    }

    public void setThreatened(boolean threatened) {
        isThreatened = threatened;
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

    public Field(int posX, int posY, boolean isThreatened, boolean isOccupied, String occupiedBy) {
        this.posX = posX;
        this.posY = posY;
        this.isThreatened = isThreatened;
        this.isOccupied = isOccupied;
        this.occupiedBy = occupiedBy;
    }
}
