package Model;

public class Field {
    private int posX;
    private int posY;
    private boolean isDestroyed;
    private boolean isOccupied;

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

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public Field(int posX, int posY, boolean isDestroyed, boolean isOccupied) {
        this.posX = posX;
        this.posY = posY;
        this.isDestroyed = isDestroyed;
        this.isOccupied = isOccupied;
    }
}
