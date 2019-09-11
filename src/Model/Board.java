package Model;

import java.util.ArrayList;

public class Board {
    private int size;
    private ArrayList<Field> allFields = new ArrayList();
    private ArrayList<Queen> queenList = new ArrayList();


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Board(int size) {
        this.size = size;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                Field field = new Field(i,j,false, false);
                allFields.add(field);
            }
        }
    }

    public Field getField(int posX, int posY)
    {
        for (int i = 0; i < allFields.size(); i++) {

            if(allFields.get(i).getPosX() == posX && allFields.get(i).getPosY() == posY)
                return allFields.get(i);

        }
        return null;
    }

    public int countDestroyed()
    {

        int destroyedCount = 0;

        for (int i = 0; i < allFields.size(); i++) {

            if(allFields.get(i).isDestroyed())
                destroyedCount++;

        }

        return destroyedCount;
    }

    public int countOccupied()
    {

        int occupiedCount = 0;

        for (int i = 0; i < allFields.size(); i++) {

            if(allFields.get(i).isOccupied())
                occupiedCount++;

        }

        return occupiedCount;
    }

    public boolean placeQueen(Field field)
    {
        if(field.isDestroyed()){
            System.out.println("field is destroyed already, cant put queen to: " + field.getPosX() + " " + field.getPosY());
            return false;
        }
        Queen queen = new Queen(field);
        queenList.add(queen);

        queen.getField().setOccupied(true);

        collectDestroyedFields(field);
        collectDiagonals(field);
        return true;
    }

    public void removeLastQueen()
    {
        if(queenList.size() != 0) {
            restoreFields(queenList.get(queenList.size() - 1).getField());
            restoreFieldsDiagonal(queenList.get(queenList.size() - 1).getField());
            queenList.remove(queenList.size() - 1);

            //redestroy fields that would be destroyed by non removed queens
            for (int i = 0; i < queenList.size(); i++) {
                collectDestroyedFields(queenList.get(i).getField());
                collectDiagonals(queenList.get(i).getField());
            }
        }
    }

    public void collectDestroyedFields(Field field)
    {
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getPosX() == field.getPosX() || allFields.get(i).getPosY() == field.getPosY()) {
                allFields.get(i).setDestroyed(true);
            }
        }
    }

    public  ArrayList<Field> collectDiagonals(Field field)
    {
        ArrayList<Field> diagonals = new ArrayList<>();

        for (int i = 1; i < size; i++) {

            if(field.getPosX() + i <= size && field.getPosY() + i <= size )
            {
                diagonals.add(getField(field.getPosX() + i , field.getPosY() + i ));
                getField(field.getPosX() + i , field.getPosY() + i ).setDestroyed(true);
            }

            if(field.getPosX() - i > 0 && field.getPosY() - i > 0)
            {
                diagonals.add(getField(field.getPosX() - i , field.getPosY() - i ));
                getField(field.getPosX() - i , field.getPosY() - i ).setDestroyed(true);
            }

            if(field.getPosY() - i > 0 && field.getPosX() + i <= size)
            {
                diagonals.add(getField(field.getPosX() + i , field.getPosY() - i ));
                getField(field.getPosX() + i , field.getPosY() - i ).setDestroyed(true);
            }

            if(field.getPosX() - i > 0 && field.getPosY() + i <= size)
            {
                diagonals.add(getField(field.getPosX() - i , field.getPosY() + i ));
                getField(field.getPosX() - i , field.getPosY() + i ).setDestroyed(true);
               // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }
        }

        return diagonals;
    }

    public void restoreFields(Field field)
    {
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getPosX() == field.getPosX() || allFields.get(i).getPosY() == field.getPosY()) {
                allFields.get(i).setDestroyed(false);
                allFields.get(i).setOccupied(false);
                //System.out.println("field on " + allFields.get(i).getPosX() + " " + allFields.get(i).getPosY() + " is destroyed");
            }
        }
    }

    public void restoreFieldsDiagonal(Field field)
    {
        for (int i = 1; i < size; i++) {

            if(field.getPosX() + i <= size && field.getPosY() + i <= size )
            {
                getField(field.getPosX() + i , field.getPosY() + i ).setDestroyed(false);
                //System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }

            if(field.getPosX() - i > 0 && field.getPosY() - i > 0)
            {
                getField(field.getPosX() - i , field.getPosY() - i ).setDestroyed(false);
                // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }

            if(field.getPosY() - i > 0 && field.getPosX() + i <= size)
            {
                getField(field.getPosX() + i , field.getPosY() - i ).setDestroyed(false);
                // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }

            if(field.getPosX() - i > 0 && field.getPosY() + i <= size)
            {
                getField(field.getPosX() - i , field.getPosY() + i ).setDestroyed(false);
                // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }
        }
    }

    public Queen getLastQueen()
    {
        return queenList.get(queenList.size()-1);
    }

    public void drawCurrentBoard(Board board)
    {
        System.out.println("-");
        for (int i = 1; i <= board.getSize(); i++) {
            for (int j = 1; j <= board.getSize(); j++) {
                System.out.print(" ");
                if(getField(j,i).isOccupied())
                    System.out.print("q");

                else if(getField(j,i).isDestroyed())
                    System.out.print("|");

                else
                {
                    System.out.print(".");
                }
            }

            System.out.print(" ");
            System.out.println("");
        }
        System.out.println("-");
    }

    public void clearBoard()
    {
        while(queenList.size() != 0)
        {
            removeLastQueen();
        }
    }

}
