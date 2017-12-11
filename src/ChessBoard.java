import java.awt.*;
import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeMath.abs;
import static jdk.nashorn.internal.runtime.ScriptObject.setGlobalObjectProto;

/**
 * Created by DibaVosta on 06/12/17.
 */

interface BoardGame{
    public boolean move(int i, int j);
    public String getStatus(int i, int j);
    public String getMessage();
}

public class ChessBoard {
    Square[][] board = new Square[8][8];
    String currentMessage = "";
    private String currentPlayer;

    ChessBoard(){

        currentPlayer = "white";

        for(int i = 2; i<board.length-2; i++){
            for(int j = 0; j <board[i].length; j++){
                board[i][j] = new Square(i, j);
            }
        }
        for(int j = 0; j <board[1].length; j++){
            board[1][j] = new Square(1, j, new Pawn("white", "Pawn"));
        }
        for(int j = 0; j <board[6].length; j++){
            board[6][j] = new Square(6, j, new Pawn("black", "Pawn"));
        }

        board[0][7] = new Square(0, 7, new Rook("white", "Rook"));
        board[0][0] = new Square(0, 0, new Rook("white", "Rook"));

        board[7][7] = new Square(7, 7, new Rook("black", "Rook"));
        board[7][0] = new Square(7, 0, new Rook("black", "Rook"));

        board[0][6] = new Square(0, 6, new Knight("white", "Knight"));
        board[0][1] = new Square(0, 1, new Knight("white", "Knight"));

        board[7][6] = new Square(7, 6, new Knight("black", "Knight"));
        board[7][1] = new Square(7, 1, new Knight("black", "Knight"));

        board[0][5] = new Square(0, 5, new Bishop("white", "Bishop"));
        board[0][2] = new Square(0, 2, new Bishop("white", "Bishop"));

        board[7][5] = new Square(7, 5, new Bishop("black", "Bishop"));
        board[7][2] = new Square(7, 2, new Bishop("black", "Bishop"));

        board[0][4] = new Square(0, 4, new Queen("white", "Queen"));
        board[0][3] = new Square(0, 3, new King("white", "King"));

        board[7][4] = new Square(7, 4, new Queen("black", "Queen"));
        board[7][3] = new Square(7, 3, new King("black", "King"));
    }


    public void print(){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j <board[i].length; j++){
                System.out.println(board[i][j]);
            }
        }
    }

    //@Override
    public boolean move(int x, int y, int i, int j) {

        Square current = board[x][y];
        Square move = board[i][j];
        int xdistance = x-i;
        int ydistance = y-j;

        if (currentPlayer.equals(current.value.color)) {

            if (current.value.checkMove(current, move) && (board[i][j].value == null
                    || !(board[i][j].value.color.equals(currentPlayer)))) {
                if (xdistance == 1 && ydistance == 1 || xdistance == 1 && ydistance == 0 || xdistance == 0 && ydistance == 1){
                    board[i][j].setValue(board[x][y].value);
                    board[x][y].setValue(null);
                    changePlayer();
                    currentMessage = "OK";
                    return true;
                }
                else if (blocked(x, y, i ,j) && (!current.value.name.equals("Knight"))){
                    currentMessage = "Your path is blocked";
                    return false;
                } else {

                    board[i][j].setValue(board[x][y].value);
                    board[x][y].setValue(null);
                    changePlayer();
                    currentMessage = "OK";
                    return true;
                }
            } else {
                currentMessage = "NOT OK";
                return false;
            }
        }
        else {
            currentMessage = "That's not your piece";
            return false;
        }
    }

    public boolean blocked(int x, int y, int i, int j) {


        ChessPiece s = board[x][y].value;
        if (s.name.equals("Pawn") || s.name.equals("Rook")){
            return checkStraight(x, y, i, j);
        }
        else if (s.name.equals("Bishop")){
            return checkDiagonal(x, y, i, j);
        }
        else if (s.name.equals("Queen"))
            if (checkStraight(x, y, i ,j) || checkDiagonal(x, y, i ,j)){
                return false;
            }
            else {
                return true;
            }
        /*
        for (int m = 0; m < board.length; m++){
            for (int n = 0; n<board[m].length; n++){
                if ((m > x && m < i || m < x && m > i || m == x) && (n > y && n < j || n < y && n > j || n == y)) {
                    if (board[m][n].value != null) {
                        return false;
                    }
                }
            }
        }
        return true;*/
        return true;
    }

    private boolean checkDiagonal(int x, int y, int i, int j) {
        if (x < i && y < j) {
            while ((x != i) && (y != j)) {
                if (board[x + 1][y + 1].value != null) {
                    return true;
                }
                x++;
                y++;
            }
        }
        else if (x > i && y > j) {
            while ((x != i) && (y != j)) {
                if (board[x - 1][y - 1].value != null) {
                    return true;
                }
                x--;
                y--;
            }
        }
        else if (x > i && y < j) {
            while ((x != i) && (y != j)) {
                if (board[x - 1][y + 1].value != null) {
                    return true;
                }
                x--;
                y++;
            }
        }
        else if (x < i && y > j) {
            while ((x != i) && (y != j)) {
                if (board[x + 1][y - 1].value != null) {
                    return true;
                }
                x++;
                y--;
            }
        }
        return false;
    }

    private boolean checkStraight(int x, int y, int i, int j) {
        if (y != j){
            if (y - j < 0) {
                while (y - j < -1) {
                    if (board[x][y+1].value != null) {
                        return true;
                    }
                    y++;
                }
            }
            else {
                while(y - j > 1){
                    if (board[x][y-1].value != null) {
                        return true;
                    }
                    y--;
                }
            }
        }
        else if (x != i){
            if (x - i < 0) {
                while (x - i < -1) {
                    if (board[x+1][y].value != null) {
                        return true;
                    }
                    x++;
                }
            }
            else {
                while(x - i > 1){
                    if (board[x-1][y].value != null) {
                        return true;
                    }
                    x--;
                }
            }
        }
        return false;
    }

    public void changePlayer(){
        if (currentPlayer.equals("white")){
            currentPlayer = "black";
        }
        else {
            currentPlayer = "white";
        }
    }


    public Square getStatus(int i, int j) {
        return board[i][j];
    }

    public String getMessage() {
        return currentMessage;
    }

    public static void main(String[] args){
        ChessBoard b = new ChessBoard();
        b.print();
    }
}

class Square{
    int x;
    int y;
    ChessPiece value;
    String n;

    Square(int inX, int inY){
        x = inX;
        y = inY;
        n = "";
    }

    Square(int inX, int inY, ChessPiece c){
        x = inX;
        y = inY;
        value = c;
        n = value.name;
    }

    public void setValue(ChessPiece c) {
        value = c;
        if (value == null) {
            n = "";
        }
        else {n = value.name;}
    }

    public String toString(){
        return n;
    }
}


