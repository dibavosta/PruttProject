
interface BoardGame{
    boolean move(int x, int y, int i, int j);
    ChessPiece getStatus(int i, int j);
    String getMessage();
    boolean checkCurrentPlayer(int x, int y);
    String getCurrentPlayer();
}

public class ChessBoard implements BoardGame{
    private ChessPiece[][] board = new ChessPiece[8][8];
    private String currentMessage = "";
    private String currentPlayer;

    ChessBoard(){

        currentPlayer = "white";

        for(int i = 2; i<board.length-2; i++){
            for(int j = 0; j <board[i].length; j++){
                board[i][j] = new Empty( "gray", "empty");
            }
        }
        for(int j = 0; j <board[1].length; j++){
            board[1][j] = new Pawn("white", "Pawn");
        }
        for(int j = 0; j <board[6].length; j++){
            board[6][j] = new Pawn("black", "Pawn");
        }

        board[0][7] = new Rook("white", "Rook");
        board[0][0] = new Rook("white", "Rook");

        board[7][7] = new Rook("black", "Rook");
        board[7][0] = new Rook("black", "Rook");

        board[0][6] = new Knight("white", "Knight");
        board[0][1] = new Knight("white", "Knight");

        board[7][6] = new Knight("black", "Knight");
        board[7][1] = new Knight("black", "Knight");

        board[0][5] = new Bishop("white", "Bishop");
        board[0][2] = new Bishop("white", "Bishop");

        board[7][5] = new Bishop("black", "Bishop");
        board[7][2] = new Bishop("black", "Bishop");

        board[0][4] = new Queen("white", "Queen");
        board[0][3] = new King("white", "King");

        board[7][4] = new Queen("black", "Queen");
        board[7][3] = new King("black", "King");
    }

    @Override
    public boolean move(int x, int y, int i, int j) {

        ChessPiece current = board[x][y];
        ChessPiece move = board[i][j];
        int xdistance = Math.abs(x-i);
        int ydistance = Math.abs(y-j);


        if (current.checkMove(x, y, i, j, move) && (board[i][j].name.equals("empty")
                || !(board[i][j].color.equals(currentPlayer)))) {
            if (xdistance == 1 && ydistance == 1 || xdistance == 1 && ydistance == 0 || xdistance == 0 && ydistance == 1){
                board[i][j]=current;
                board[x][y]= new Empty("gray", "empty");
                changePlayer();
                currentMessage = "OK";
                return true;
            }
            else if (blocked(x, y, i ,j) && (!current.name.equals("Knight"))){
                currentMessage = "Your path is blocked";
                return false;
            } else {

                board[i][j] = current;
                board[x][y] = new Empty("gray", "empty");
                changePlayer();
                currentMessage = "OK";
                return true;
            }
        } else {
            currentMessage = "NOT OK";
            return false;
        }

    }

    private boolean blocked(int x, int y, int i, int j) {


        ChessPiece s = board[x][y];
        switch (s.name) {
            case "Pawn":
            case "Rook":
                return checkStraight(x, y, i, j);
            case "Bishop":
                return checkDiagonal(x, y, i, j);
            case "Queen":
                if (Math.abs(x - i) == Math.abs(y - j)) {
                    return (checkDiagonal(x, y, i, j));
                } else {
                    return (checkStraight(x, y, i, j));
                }
        }

        return true;
    }

    private boolean checkDiagonal(int x, int y, int i, int j) {
        if (x < i && y < j) {
            while ((x < i-1) && (y < j-1)) {
                if (!(board[x + 1][y + 1].name.equals("empty"))) {
                    return true;
                }
                x++;
                y++;
            }
        }
        else if (x > i && y > j) {
            while ((x > i+1) && (y > j+1)) {
                if (!(board[x - 1][y - 1].name.equals("empty"))) {
                    return true;
                }
                x--;
                y--;
            }
        }
        else if (x > i && y < j) {
            while ((x > i+1) && (y < j-1)) {
                if (!(board[x - 1][y + 1].name.equals("empty"))) {
                    return true;
                }
                x--;
                y++;
            }
        }
        else if (x < i && y > j) {
            while ((x < i-1) && (y > j+1)) {
                if (!(board[x + 1][y - 1].name.equals("empty"))) {
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
                    if (!(board[x][y+1].name.equals("empty"))) {
                        return true;
                    }
                    y++;
                }
            }
            else {
                while(y - j > 1){
                    if (!(board[x][y-1].name.equals("empty"))) {
                        return true;
                    }
                    y--;
                }
            }
        }
        else if (x != i){
            if (x - i < 0) {
                while (x - i < -1) {
                    if (!(board[x+1][y].name.equals("empty"))) {
                        return true;
                    }
                    x++;
                }
            }
            else {
                while(x - i > 1){
                    if (!(board[x-1][y].name.equals("empty"))) {
                        return true;
                    }
                    x--;
                }
            }
        }
        return false;
    }

    private void changePlayer(){
        if (currentPlayer.equals("white")){
            currentPlayer = "black";
        }
        else {
            currentPlayer = "white";
        }
    }

    public boolean checkCurrentPlayer(int x, int y) {
        if (currentPlayer.equals(board[x][y].color)) {
            currentMessage = "Make your move";
            return true;
        }
        else {
            currentMessage = "That's not your piece";
            return false;
        }
    }

    public String getCurrentPlayer(){
        return currentPlayer;
    }

    public ChessPiece getStatus(int i, int j) {
        return board[i][j];
    }

    public String getMessage() {
        return currentMessage;
    }
}
