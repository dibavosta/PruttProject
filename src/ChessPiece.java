import java.util.Objects;

abstract class ChessPiece {
    String color;
    String name;

    ChessPiece(String inColor, String inName){
        color = inColor;
        name = inName;
    }

    abstract boolean checkMove(int x, int y, int i, int j, ChessPiece move);
}

class Pawn extends ChessPiece{
    private boolean hasMoved;

    Pawn(String c, String n){
        super(c, n);
        color = c;
        name = n;
        hasMoved = false;
    }

    boolean checkMove(int x, int y, int i, int j, ChessPiece move){

        int xdistance = i-x;
        int ydistance = j-y;

        if ((xdistance > 0 && Objects.equals(color, "white")) || (xdistance < 0 && Objects.equals(color, "black"))){
            if (Math.abs(ydistance) == 0 && Math.abs(xdistance) == 1 && move.color.equals("gray")) {
                hasMoved = true;
                return true;
            }
            else if (Math.abs(ydistance) == 0 && Math.abs(xdistance) == 2 && !hasMoved && move.color.equals("gray")){
                hasMoved = true;
                return true;
            }
            else if ((Math.abs(ydistance) == 1) && (Math.abs(xdistance) == 1)
                    && !(move.color.equals(color)) && !(move.color.equals("gray")))
            {
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

    Bishop(String c, String n){
        super(c, n);
        color = c;
        name = n;
    }

    @Override
    boolean checkMove(int x, int y, int i, int j, ChessPiece move){

        int xdistance = i-x;
        int ydistance = j-y;

        return Math.abs(xdistance) == Math.abs(ydistance) && Math.abs(xdistance) > 0;
    }


    public String toString(){
        return name;
    }
}

class Knight extends ChessPiece{

    Knight(String c, String n){
        super(c, n);
        color = c;
        name = n;
    }

    @Override
    boolean checkMove(int x, int y, int i, int j, ChessPiece move){
        int xdistance = i-x;
        int ydistance = j-y;

        return Math.sqrt(Math.pow(xdistance, 2) + Math.pow(ydistance, 2)) == Math.sqrt(5);
    }

    public String toString(){
        return name;
    }
}

class Rook extends ChessPiece{

    Rook(String c, String n){
        super(c, n);
        color = c;
        name = n;
    }

    @Override
    boolean checkMove(int x, int y, int i, int j, ChessPiece move){

        int xdistance = i-x;
        int ydistance = j-y;

        return Math.abs(xdistance) > 0 && Math.abs(ydistance) == 0 || Math.abs(xdistance) == 0 && Math.abs(ydistance) > 0;
    }

    public String toString(){
        return name;
    }
}

class Queen extends ChessPiece{

    Queen(String c, String n){
        super(c, n);
        color = c;
        name = n;
    }

    @Override
    boolean checkMove(int x, int y, int i, int j, ChessPiece move){

        int xdistance = i-x;
        int ydistance = j-y;

        return (Math.abs(xdistance) > 0 && Math.abs(ydistance) == 0 || Math.abs(xdistance) == 0 && Math.abs(ydistance) > 0)
                || (Math.abs(xdistance) == Math.abs(ydistance) && Math.abs(xdistance) > 0);
    }

    public String toString(){
        return name;
    }
}

class King extends ChessPiece{

    King(String c, String n){
        super(c, n);
        color = c;
        name = n;
    }

    @Override
    boolean checkMove(int x, int y, int i, int j, ChessPiece move){

        int xdistance = i-x;
        int ydistance = j-y;

        return Math.abs(xdistance) == 1 && Math.abs(ydistance) == 0 || Math.abs(xdistance) == 0 && Math.abs(ydistance) == 1
                || Math.abs(xdistance) == 1 && Math.abs(ydistance) == 1;
    }

    public String toString(){
        return name;
    }
}

class Empty extends ChessPiece{

    Empty(String c, String n){
        super(c, n);

    }

    @Override
    boolean checkMove(int x, int y, int i, int j, ChessPiece move){ return false; }

    public String toString(){
        return name;
    }
}