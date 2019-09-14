package Model;

import java.util.ArrayList;

public class Board {
    private int size;
    private ArrayList<Field> allFields = new ArrayList<Field>();
    private ArrayList<Queen> queenList = new ArrayList<Queen>();
    private ArrayList<Rook> rookList = new ArrayList<Rook>();
    private ArrayList<Integer> coords = new ArrayList<Integer>();
    private Field targetField;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Board(int size) {
        this.size = size;
        coords.add(null);
        coords.add(null);

        for (int i = 1; i <= size; i++) {
            coords.set(0,i);
            for (int j = 1; j <= size; j++) {
                coords.set(1,j);
                Field field = new Field(coords,0, false, "");
               // System.out.println("generated field on: " + coords.get(0) + " " + + coords.get(1) + field);
                allFields.add(field);
            }
        }
        coords.clear();
    }

    public Field getField(ArrayList receivedCoordinates)
    {
        for (int i = 0; i < allFields.size(); i++) {

            if(allFields.get(i).getCoords().get(0) == receivedCoordinates.get(0) && allFields.get(i).getCoords().get(1) == receivedCoordinates.get(1))
                return allFields.get(i);

        }
        return null;
    }

    public int countThreatened()
    {

        int threatenedCount = 0;

        for (int i = 0; i < allFields.size(); i++) {

            if(allFields.get(i).getThreatCount() > 0)
                threatenedCount++;

        }

        return threatenedCount;
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
                threatFieldsOn0(field);
                threatFieldsOn1(field);
                threatDiagonalsOf01(field);
                return true;
            }

            case("rook"): {
                field.setOccupiedBy(figureType);
                Rook rook = new Rook(field);
                rookList.add(rook);
                rook.getField().setOccupied(true);

                //mark threatened fields
                threatFieldsOn0(field);
                threatFieldsOn1(field);
                return true;
            }
    }
    return false;
    }

    public void removeLastQueen()
    {
        if(queenList.size() != 0) {
            restoreFields0(queenList.get(queenList.size() - 1).getField());
            restoreFieldsDiagonal01(queenList.get(queenList.size() - 1).getField());
            queenList.remove(queenList.size() - 1);

            //redestroy fields that would be destroyed by non removed queens
            for (int i = 0; i < queenList.size(); i++) {
                threatFieldsOn0(queenList.get(i).getField());
                threatDiagonalsOf01(queenList.get(i).getField());
            }
        }
    }

    //dimensions are noted as 0,1,2, so on. 0: line, 1: square, 2: volume

    public void threatFieldsOn0(Field field)
    {
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getCoords().get(0) == field.getCoords().get(0)) {
                allFields.get(i).setThreatCount(allFields.get(i).getThreatCount()+1);
            }
        }
    }

    public void threatFieldsOn1(Field field)
    {
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getCoords().get(1) == field.getCoords().get(1)) {
                allFields.get(i).setThreatCount(allFields.get(i).getThreatCount()+1);
            }
        }
    }

    public  ArrayList<Field> threatDiagonalsOf01(Field field)
    {
        ArrayList<Field> diagonals = new ArrayList<>();

        targetField = field;

        //diagonal values towards the bottom left corner
        while(targetField.getCoords().get(0) != 0 && targetField.getCoords().get(1) != 0 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) - 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) - 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() + 1);
        }

        //diagonal values towards the upper left corner
        while(targetField.getCoords().get(0) != 0 && targetField.getCoords().get(1) != getSize() + 1 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) - 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) + 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() + 1);
        }

        //diagonal values towards the upper right corner
        while(targetField.getCoords().get(0) != getSize() + 1 && targetField.getCoords().get(1) != getSize() + 1 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) + 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) + 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() + 1);
        }

        //diagonal values towards the bottom right corner
        while(targetField.getCoords().get(0) != getSize() + 1 && targetField.getCoords().get(1) != 0 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) + 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) - 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() + 1);
        }


        return diagonals;
    }

    public void restoreFields0(Field field)
    {
        field.setOccupied(false);
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getCoords().get(0) == field.getCoords().get(0)) {
                allFields.get(i).setThreatCount(allFields.get(i).getThreatCount()-1);
            }
        }
    }

    public void restoreFields1(Field field)
    {
        field.setOccupied(false);
        for (int i = 0; i < allFields.size(); i++) {
            if(allFields.get(i).getCoords().get(1) == field.getCoords().get(1)) {
                allFields.get(i).setThreatCount(allFields.get(i).getThreatCount()-1);
            }
        }
    }

    public ArrayList<Field> restoreFieldsDiagonal01(Field field)
    {
        ArrayList<Field> diagonals = new ArrayList<>();
        field.setOccupied(false);
        targetField = field;

        //diagonal values towards the bottom left corner
        while(targetField.getCoords().get(0) != 0 && targetField.getCoords().get(1) != 0 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) - 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) - 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() - 1);
        }

        //diagonal values towards the upper left corner
        while(targetField.getCoords().get(0) != 0 && targetField.getCoords().get(1) != getSize() + 1 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) - 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) + 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() - 1);
        }

        //diagonal values towards the upper right corner
        while(targetField.getCoords().get(0) != getSize() + 1 && targetField.getCoords().get(1) != getSize() + 1 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) + 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) + 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() - 1);
        }

        //diagonal values towards the bottom right corner
        while(targetField.getCoords().get(0) != getSize() + 1 && targetField.getCoords().get(1) != 0 )
        {
            targetField.getCoords().set(0,targetField.getCoords().get(0) + 1);
            targetField.getCoords().set(1,targetField.getCoords().get(1) - 1);
            diagonals.add(targetField);
            targetField.setThreatCount(targetField.getThreatCount() - 1);
        }
        return  diagonals;
    }

    public Queen getLastQueen()
    {
        return queenList.get(queenList.size()-1);
    }

    public void drawCurrentBoard(Board board)
    {
        coords.add(null);
        coords.add(null);

        System.out.println("-");
        for (int i = 1; i <= board.getSize(); i++) {
            coords.set(0,i);
            for (int j = 1; j <= board.getSize(); j++) {
                coords.set(1,j);
                System.out.print(" ");
                if(getField(coords).isOccupied()) {
                    if(getField(coords).getOccupiedBy().equals("queen"))
                    System.out.print("q");

                    if(getField(coords).getOccupiedBy().equals("rook"))
                        System.out.print("r");
                }

                 if(getField(coords).getThreatCount() == 1 && !getField(coords).isOccupied()) {
                     System.out.print("|");
                 }

                else if(getField(coords).getThreatCount() == 2 && !getField(coords).isOccupied()) {
                     System.out.print("/");
                 }

                 else if(getField(coords).getThreatCount() == 0 && !getField(coords).isOccupied()) {
                     System.out.print(".");
                 }
            }

            System.out.print(" ");
            System.out.println(" ");

        }
        System.out.println("-");
        System.out.println(countThreatened());
    }

    public void clearBoard()
    {
        while(queenList.size() != 0)
        {
            removeLastQueen();
        }
    }

}
