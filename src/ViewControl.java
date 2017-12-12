import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ViewControl extends JFrame implements ActionListener {

    private BoardGame game;
    private Square[][] board;        // Square är subklass till JButton
    private JLabel mess = new JLabel();
    private JLabel playerMess = new JLabel();
    private boolean clicked = false;
    private int x;
    private int y;


    private ViewControl (BoardGame gm) {  // OK med fler parametrar om ni vill!
        super("Chess");
        int size = 8;
        game = gm;
        board = new Square[size][size];

        BorderLayout border = new BorderLayout();
        GridLayout grid = new GridLayout(size ,size);
        JPanel panel = new JPanel();
        panel.setLayout(grid);
        setLayout(border);
        int count = 0;

        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (int i = 0 ; i < size ; i++) {
            count++;
            for (int j = 0 ; j < size ; j++) {

                Square newButton = new Square(game.getStatus(i, j));
                newButton.setOpaque(true);
                newButton.setBorderPainted(false);
                board[i][j] = newButton;

                newButton.addActionListener(this);
                newButton.setActionCommand(""+i+" "+j);

                if ((j+count)%2 == 0) {
                    newButton.setBackground(Color.gray);

                }
                else {
                    newButton.setBackground(Color.white);
                }
                panel.add(newButton);

            }
        }

        add(panel,BorderLayout.CENTER);
        JPanel messagePanel = new JPanel();
        add(messagePanel, BorderLayout.SOUTH);

        messagePanel.add(mess);

        JPanel playerPanel = new JPanel();
        add(playerPanel, BorderLayout.NORTH);
        playerPanel.add(playerMess);
        playerMess.setText("Current player: " + game.getCurrentPlayer());
        setVisible(true);


    }
    public void actionPerformed(ActionEvent e) {
        String p = e.getActionCommand();
        String[] s = p.split(" ");

        int i = Integer.parseInt(s[0]);
        int j = Integer.parseInt(s[1]);


        if (clicked) {

            if (game.move(x, y, i, j)) {
                board[i][j].changeImage(game.getStatus(i,j));
                board[x][y].changeImage(game.getStatus(x,y));
            }
            String newMessage = game.getMessage();
            mess.setText(newMessage);
            clicked = false;
            playerMess.setText("Current player: " + game.getCurrentPlayer());
    }
    else {
            if (game.checkCurrentPlayer(i, j))
            {
                x = i;
                y = j;
                clicked = true;
                String newMessage = game.getMessage();
                mess.setText(newMessage);
            }
            else {
                String newMessage = game.getMessage();
                mess.setText(newMessage);
            }
        }
    }
    public static void main(String[] args) {
        BoardGame gm = new ChessBoard();
        new ViewControl(gm);
    }
}

class Square extends JButton{

    Square(ChessPiece c){
        setIcon(new ImageIcon(getClass().getResource("/"+c.color+"/" + c.name + ".png")));
    }

    void changeImage(ChessPiece c){
        setIcon(new ImageIcon(getClass().getResource("/"+c.color+"/" + c.name + ".png")));
    }
}


