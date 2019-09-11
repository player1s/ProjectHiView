package Model;

import java.util.ArrayList;

public class Board {
    private int size;
    private ArrayList<Field> allFields = new ArrayList();
    private ArrayList<Queen> queenList = new ArrayList();
    private ArrayList<Rook> rookList = new ArrayList();


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
                Field field = new Field(i,j,false, false, "");
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

            if(allFields.get(i).isThreatened())
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

    public boolean placeFigure(Field field, String figureType)
    {

        switch (figureType)
        {
            case("queen"): {
                field.setOccupiedBy(figureType);
                Queen queen = new Queen(field);
                queenList.add(queen);
                queen.getField().setOccupied(true);

                //mark threatened fields
                threatFieldsOn1(field);
                threatFieldsOn2(field);
                threatDiagonalsOf12(field);
                return true;
            }

            case("rook"): {
                field.setOccupiedBy(figureType);
                Rook rook = new Rook(field);
                rookList.add(rook);
                rook.getField().setOccupied(true);

                //mark threatened fields
                threatFieldsOn1(field);
                threatFieldsOn2(field);
                return true;
            }
    }
    return false;
    }

    public void removeLastQueen()
    {
        if(queenList.size() != 0) {
            restoreFields1(queenList.get(queenList.size() - 1).getField());
            restoreFieldsDiagonal12(queenList.get(queenList.size() - 1).getField());
            queenList.remove(queenList.size() - 1);

            //redestroy fields that would be destroyed by non removed queens
            for (int i = 0; i < queenList.size(); i++) {
                threatFieldsOn1(queenList.get(i).getField());
                threatDiagonalsOf12(queenList.get(i).getField());
            }
        }
    }

    //dimensions are noted as 1,2,3, so on. 1: line, 2: square, 3: volume

    public void threatFieldsOn1(Field field)
    {
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getPosX() == field.getPosX()) {
                allFields.get(i).setThreatened(true);
            }
        }
    }

    public void threatFieldsOn2(Field field)
    {
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getPosY() == field.getPosY()) {
                allFields.get(i).setThreatened(true);
            }
        }
    }

    public  ArrayList<Field> threatDiagonalsOf12(Field field)
    {
        ArrayList<Field> diagonals = new ArrayList<>();

        for (int i = 1; i < size; i++) {

            if(field.getPosX() + i <= size && field.getPosY() + i <= size )
            {
                diagonals.add(getField(field.getPosX() + i , field.getPosY() + i ));
                getField(field.getPosX() + i , field.getPosY() + i ).setThreatened(true);
            }

            if(field.getPosX() - i > 0 && field.getPosY() - i > 0)
            {
                diagonals.add(getField(field.getPosX() - i , field.getPosY() - i ));
                getField(field.getPosX() - i , field.getPosY() - i ).setThreatened(true);
            }

            if(field.getPosY() - i > 0 && field.getPosX() + i <= size)
            {
                diagonals.add(getField(field.getPosX() + i , field.getPosY() - i ));
                getField(field.getPosX() + i , field.getPosY() - i ).setThreatened(true);
            }

            if(field.getPosX() - i > 0 && field.getPosY() + i <= size)
            {
                diagonals.add(getField(field.getPosX() - i , field.getPosY() + i ));
                getField(field.getPosX() - i , field.getPosY() + i ).setThreatened(true);
               // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }
        }

        return diagonals;
    }

    public void restoreFields1(Field field)
    {
        field.setOccupied(false);
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getPosX() == field.getPosX()) {
                allFields.get(i).setThreatened(false);
            }
        }
    }

    public void restoreFields2(Field field)
    {
        field.setOccupied(false);
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getPosY() == field.getPosY()) {
                allFields.get(i).setThreatened(false);
            }
        }
    }

    public void restoreFieldsDiagonal12(Field field)
    {
        for (int i = 1; i < size; i++) {

            if(field.getPosX() + i <= size && field.getPosY() + i <= size )
            {
                getField(field.getPosX() + i , field.getPosY() + i ).setThreatened(false);
                //System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }

            if(field.getPosX() - i > 0 && field.getPosY() - i > 0)
            {
                getField(field.getPosX() - i , field.getPosY() - i ).setThreatened(false);
                // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }

            if(field.getPosY() - i > 0 && field.getPosX() + i <= size)
            {
                getField(field.getPosX() + i , field.getPosY() - i ).setThreatened(false);
                // System.out.println(diagonals.get(diagonals.size()-1).getPosX() + " " + diagonals.get(diagonals.size()-1).getPosY() + " is added as a diagonal");
            }

            if(field.getPosX() - i > 0 && field.getPosY() + i <= size)
            {
                getField(field.getPosX() - i , field.getPosY() + i ).setThreatened(false);
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
                if(getField(j,i).isOccupied()) {
                    if(getField(j,i).getOccupiedBy().equals("queen"))
                    System.out.print("q");

                    if(getField(j,i).getOccupiedBy().equals("rook"))
                        System.out.print("r");
                }

                else if(getField(j,i).isThreatened())
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
