import java.awt.*;
import java.util.Objects;

/**
 * Created by DibaVosta on 06/12/17.
 */
public abstract class ChessPiece {
    String color;
    String name;

    //int positionX;
    //int positionY;

    ChessPiece(String inColor, String inName){
        color = inColor;
        name = inName;
    }

    abstract boolean checkMove(Square current, Square move);
}

class Pawn extends ChessPiece{
    String color;
    String name;
    boolean hasMoved;

    Pawn(String c, String n){
        super(c, n);
        color = c;
        name = n;
        hasMoved = false;
    }

    boolean checkMove(Square current, Square move){

        int xdistance = move.x - current.x;
        int ydistance = move.y - current.y;

        if ((xdistance > 0 && Objects.equals(color, "white")) || (xdistance < 0 && Objects.equals(color, "black"))){
            if (Math.abs(ydistance) == 0 && Math.abs(xdistance) == 1) {
                hasMoved = true;
                return true;
            }
            else if (Math.abs(ydistance) == 0 && Math.abs(xdistance) == 2 && !hasMoved){
                System.out.println("twotwo");
                hasMoved = true;
                return true;
            }
            else if ((Math.abs(ydistance) == 1) && (Math.abs(xdistance) == 1) && (move.value != null)
                    && !(move.value.color.equals(color)))
            {
                System.out.println("kill");
                hasMoved = true;
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;

        }
    }
    public String toString(){
        return name;
    }
}

class Bishop extends ChessPiece{
    String color;
    String name;

    Bishop(String c, String n){
        super(c, n);
        color = c;
        name = n;

    }

    @Override
    boolean checkMove(Square current, Square move){

        int xdistance = move.x - current.x;
        int ydistance = move.y - current.y;

        if (Math.abs(xdistance) == Math.abs(ydistance) && Math.abs(xdistance) > 0){
            return true;
        }
        else {
            return false;
        }
    }


    public String toString(){
        return name;
    }
}

class Knight extends ChessPiece{
    String color;
    String name;

    Knight(String c, String n){
        super(c, n);
        color = c;
        name = n;

    }

    @Override
    boolean checkMove(Square current, Square move){
        System.out.println(color);
        int xdistance = move.x - current.x;
        int ydistance = move.y - current.y;

        if (Math.sqrt(Math.pow(xdistance, 2)+Math.pow(ydistance, 2)) == Math.sqrt(5)){
            return true;
        }
        else {
            return false;
        }
    }

    public String toString(){
        return name;
    }
}

class Rook extends ChessPiece{
    String color;
    String name;

    Rook(String c, String n){
        super(c, n);
        color = c;
        name = n;

    }

    @Override
    boolean checkMove(Square current, Square move){

        int xdistance = move.x - current.x;
        int ydistance = move.y - current.y;

        if (Math.abs(xdistance) > 0 && Math.abs(ydistance) == 0 || Math.abs(xdistance) == 0 && Math.abs(ydistance) > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString(){
        return name;
    }
}

class Queen extends ChessPiece{
    String color;
    String name;

    Queen(String c, String n){
        super(c, n);
        color = c;
        name = n;

    }

    @Override
    boolean checkMove(Square current, Square move){

        int xdistance = move.x - current.x;
        int ydistance = move.y - current.y;

        if ((Math.abs(xdistance) > 0 && Math.abs(ydistance) == 0 || Math.abs(xdistance) == 0 && Math.abs(ydistance) > 0)
                || (Math.abs(xdistance) == Math.abs(ydistance) && Math.abs(xdistance) > 0)){
            return true;
        }
        else {
            return false;
        }
    }

    public String toString(){
        return name;
    }
}

class King extends ChessPiece{
    String color;
    String name;

    King(String c, String n){
        super(c, n);
        color = c;
        name = n;

    }

    @Override
    boolean checkMove(Square current, Square move){

        int xdistance = move.x - current.x;
        int ydistance = move.y - current.y;

        if (Math.sqrt(Math.pow(xdistance, 2) + Math.pow(ydistance, 2)) <= 1 &&
                Math.sqrt(Math.pow(xdistance, 2) + Math.pow(ydistance, 2)) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return name;
    }
}

class Empty extends ChessPiece{
    String color;
    String name;

    Empty(String c, String n){
        super(c, n);
        color = c;
        name = n;

    }

    @Override
    boolean checkMove(Square current, Square move){ return false; }

    public String toString(){
        return name;
    }
}