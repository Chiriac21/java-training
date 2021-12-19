package clean.code.chess.requirements;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameGUI {

    private boolean isFirstMoveWhite = true;
    private boolean isFirstMoveBlack = true;
    private ChessBoard chessTable = new ChessBoard();
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private int[][] auxMatrix = new int[8][8];
    private boolean[][] firstMoveFlags = new boolean[8][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private JToolBar toolBar;
    private JButton btnNewGame;
    private JButton btnRestart;
    private boolean moveFlag = true;
    private boolean startNewGame = true;
    ImageIcon blackPawn = new ImageIcon(new ImageIcon("./pawns/BP.png").getImage().getScaledInstance(49, 49, Image.SCALE_DEFAULT));
    ImageIcon whitePawn = new ImageIcon(new ImageIcon("./pawns/WP.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

    GameGUI() {
        initializeGui();
    }


    private boolean isWhitePawn(JButton btn) {
        if (!(btn.getIcon() == null)) {
            return btn.getIcon().getIconHeight() == 50;
        }
        return false;
    }

    private boolean isBlackPawn(JButton btn) {
        if (!(btn.getIcon() == null)) {
            return btn.getIcon().getIconHeight() == 49;
        }
        return false;
    }

    private void createAuxMatrix(ActionEvent e) {
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                if (chessBoardSquares[jj][ii] == e.getSource() && isWhitePawn(chessBoardSquares[jj][ii]) && moveFlag) {
                    auxMatrix[jj][ii] = 1;
                }
                if (chessBoardSquares[jj][ii] == e.getSource() && isBlackPawn(chessBoardSquares[jj][ii]) && !moveFlag) {
                    auxMatrix[jj][ii] = -1;
                }
            }
        }
    }

    private void createFirstMoveFlagsMatrix() {
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                firstMoveFlags[jj][6] = true;
                firstMoveFlags[jj][1] = true;
            }
        }
    }

    private int getOptionsOnFirstMove(JButton button) {
        Object[] options = {"Forward 2 tiles",
                "Forward 1 tile",
                "Cancel"};
        int n = JOptionPane.showOptionDialog(button,
                "How many tiles forward?",
                "Action",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        return n;
    }

    private int getOptionsOnMovement(JButton button) {
        Object[] options = {"Forward 1 tile",
                "Cancel"};
        int n = JOptionPane.showOptionDialog(button,
                "How many tiles forward?",
                "Action",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        return n;
    }

    private void deletePawns() {
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                chessBoardSquares[jj][ii].setIcon(null);
            }
        }
    }

    private void spawnPawns() {
        for (int j = 0; j < chessBoardSquares.length; j++) {
            chessBoardSquares[j][1].setIcon(blackPawn);
            chessBoardSquares[j][6].setIcon(whitePawn);
        }
    }


    public final void initializeGui() {

        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);


        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);

                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(new Color(255, 206, 158, 255));
                } else {
                    b.setBackground(new Color(209, 139, 71, 255));
                }
                chessBoardSquares[jj][ii] = b;
            }
        }


        chessBoard.add(new JLabel(""));

        toolBar = new JToolBar();
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar.setFloatable(false);
        toolBar.setToolTipText("");
        gui.add(toolBar, BorderLayout.EAST);


        btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (startNewGame) {
                    createFirstMoveFlagsMatrix();
                    spawnPawns();
                    for (int j = 0; j < chessBoardSquares.length; j++) {
                        chessTable.addPawn(new Pawn(PieceColor.BLACK), j, 1, PieceColor.BLACK);
                        chessTable.addPawn(new Pawn(PieceColor.WHITE), j, 6, PieceColor.WHITE);
                    }
                    startNewGame = false;
                }
            }
        });


        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                chessBoardSquares[jj][ii].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createAuxMatrix(e);
                        for (int ii1 = 0; ii1 < auxMatrix.length; ii1++) {
                            for (int jj1 = 0; jj1 < auxMatrix[ii1].length; jj1++) {
                                if (auxMatrix[jj1][ii1] == 1 && firstMoveFlags[jj1][ii1] && moveFlag) {
                                    int choice = getOptionsOnFirstMove((JButton) e.getSource());

                                    if (choice == 0 && !isBlackPawn(chessBoardSquares[jj1][ii1 - 2]) && !isBlackPawn(chessBoardSquares[jj1][ii1 - 1])) {
                                        chessBoardSquares[jj1][ii1].setIcon(null);
                                        chessBoardSquares[jj1][ii1 - 2].setIcon(whitePawn);
                                        auxMatrix[jj1][ii1 - 2] = 0;
                                        moveFlag = false;
                                        firstMoveFlags[jj1][ii1] = false;
                                    } else if (choice == 0 && (isBlackPawn(chessBoardSquares[jj1][ii1 - 2]) || isBlackPawn(chessBoardSquares[jj1][ii1 - 1]))) {
                                        JLabel error = new JLabel();
                                        JOptionPane.showMessageDialog(error, "Can't move further!");
                                    } else if (choice == 1 && !isBlackPawn(chessBoardSquares[jj1][ii1 - 1])) {
                                        chessBoardSquares[jj1][ii1].setIcon(null);
                                        chessBoardSquares[jj1][ii1 - 1].setIcon(whitePawn);
                                        auxMatrix[jj1][ii1 - 1] = 0;
                                        moveFlag = false;
                                        firstMoveFlags[jj1][ii1] = false;
                                    } else if (choice == 1 && isBlackPawn(chessBoardSquares[jj1][ii1 - 1])) {
                                        JLabel error = new JLabel();
                                        JOptionPane.showMessageDialog(error, "Can't move further!");
                                    }


                                    auxMatrix[jj1][ii1] = 0;
                                } else if (auxMatrix[jj1][ii1] == -1 && firstMoveFlags[jj1][ii1] && !moveFlag) {
                                    int choice = getOptionsOnFirstMove((JButton) e.getSource());

                                    if (choice == 0 && !isWhitePawn(chessBoardSquares[jj1][ii1 + 2]) && !isWhitePawn(chessBoardSquares[jj1][ii1 + 1])) {
                                        chessBoardSquares[jj1][ii1].setIcon(null);
                                        chessBoardSquares[jj1][ii1 + 2].setIcon(blackPawn);
                                        auxMatrix[jj1][ii1 + 2] = 0;
                                        moveFlag = true;
                                        firstMoveFlags[jj1][ii1] = false;
                                    } else if (choice == 0 && (isWhitePawn(chessBoardSquares[jj1][ii1 + 2]) || isWhitePawn(chessBoardSquares[jj1][ii1 + 1]))) {
                                        JLabel error = new JLabel();
                                        JOptionPane.showMessageDialog(error, "Can't move further!");
                                    } else if (choice == 1 && !isWhitePawn(chessBoardSquares[jj1][ii1 + 1])) {
                                        chessBoardSquares[jj1][ii1].setIcon(null);
                                        chessBoardSquares[jj1][ii1 + 1].setIcon(blackPawn);
                                        auxMatrix[jj1][ii1 + 1] = 0;
                                        moveFlag = true;
                                        firstMoveFlags[jj1][ii1] = false;
                                    } else if (choice == 0 && isWhitePawn(chessBoardSquares[jj1][ii1 + 1])) {
                                        JLabel error = new JLabel();
                                        JOptionPane.showMessageDialog(error, "Can't move further!");
                                    }

                                    auxMatrix[jj1][ii1] = 0;
                                } else if (auxMatrix[jj1][ii1] == 1 && !firstMoveFlags[jj1][ii1] && moveFlag) {
                                    int choice = getOptionsOnMovement((JButton) e.getSource());

                                    if (choice == 0 && !isBlackPawn(chessBoardSquares[jj1][ii1 - 1])) {
                                        chessBoardSquares[jj1][ii1].setIcon(null);
                                        chessBoardSquares[jj1][ii1 - 1].setIcon(whitePawn);
                                        auxMatrix[jj1][ii1 - 1] = 0;
                                        moveFlag = false;
                                    } else {
                                        JLabel error = new JLabel();
                                        JOptionPane.showMessageDialog(error, "Can't move further!");
                                    }

                                    auxMatrix[jj1][ii1] = 0;
                                } else if (auxMatrix[jj1][ii1] == -1 && !firstMoveFlags[jj1][ii1] && !moveFlag) {
                                    int choice = getOptionsOnMovement((JButton) e.getSource());

                                    if (choice == 0 && !isWhitePawn(chessBoardSquares[jj1][ii1 + 1])) {
                                        chessBoardSquares[jj1][ii1].setIcon(null);
                                        chessBoardSquares[jj1][ii1 + 1].setIcon(blackPawn);
                                        auxMatrix[jj1][ii1 + 1] = 0;
                                        moveFlag = true;
                                    } else {
                                        JLabel error = new JLabel();
                                        JOptionPane.showMessageDialog(error, "Can't move further!");
                                    }
                                    auxMatrix[jj1][ii1] = 0;
                                }
                            }
                        }

                    }
                });
            }
        }


        toolBar.add(btnNewGame);

        btnRestart = new JButton("Restart");
        btnRestart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!startNewGame) {
                    deletePawns();
                    moveFlag = true;
                    createFirstMoveFlagsMatrix();
                    spawnPawns();
                }
            }
        });

        toolBar.add(btnRestart);

        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }

        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void InitializeGUI() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                GameGUI cb = new GameGUI();

                JFrame f = new JFrame("ChessChamp");
                f.getContentPane().add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);


                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}