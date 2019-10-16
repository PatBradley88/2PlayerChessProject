import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/
 
public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	boolean takeTurn;

 
    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);
 
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }
 
        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){			
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
	    takeTurn = true;
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}

	private Boolean checkBlackOponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			opponent = true;
		}
		else{
			opponent = false;
		}
		return opponent;
	}

	private void Turn(Boolean turn) {
		if (takeTurn) {
			takeTurn = !turn;
			JOptionPane.showMessageDialog(null, "Blacks turn");
		} else {
			takeTurn = turn;
			JOptionPane.showMessageDialog(null, "Whites turn");
		}

	}

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have 
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        String name = getPieceName(e.getX(), e.getY());
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

	private String getPieceName(int x, int y) {
		Component c1 = chessBoard.findComponentAt(x, y);
		if (c1 instanceof JPanel) {
			return "empty";
		} else if (c1 instanceof JLabel) {
			JLabel awaitingPiece = (JLabel) c1;
			String tmp1 = awaitingPiece.getIcon().toString();
			return tmp1;
		} else {
			return "empty";
		}
	}

	public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before 
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;


		int landingX = (e.getX()/75);
		int landingY = (e.getY()/75);
		int xMovement = Math.abs((e.getX()/75)-startX);
		int yMovement = Math.abs((e.getY()/75)-startY);

		System.out.println("----------------------------");
		System.out.println("The piece that is being moved is : "+pieceName);
		System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
		System.out.println("The xMovement is : "+xMovement);
		System.out.println("The yMovement is : "+yMovement);
		System.out.println("The landing coordinates are : "+"( "+landingX+","+landingY+")");
		System.out.println("----------------------------");



		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/
		if (takeTurn != true) {

			if (pieceName.equals("BlackPawn")) {
				if (startY == 6) { //pawn making first move

					/*
					 * if the pawn is making its first movement
					 * the pawn can either move one square or two squares in the Y direction
					 * as long as we are moving up the board!! and also there is no movement in the X direction
					 * */

					// startY > landingY ensures that movement can only be progressive. Doesn't work for any other piece.

					if (((yMovement == 1) || (yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
						if (yMovement == 2) {
							if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
								validMove = true;
							}
						} else {
							if (!piecePresent(e.getX(), e.getY())) {
								validMove = true;
							}
						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						}
					}

				} else if (startY == 1 && startY > landingY) {
					// moving from second last row to last row
					success = true;
					validMove = true;
				}
				//taking the king to win the game.
				if (getPieceName(e.getX(), e.getY()).contains("WhiteKing")) {
					System.out.println("Black has won the game");
					JOptionPane.showMessageDialog(null, "Black has won the game");
					System.exit(0);

				} else { //pawns all other moves
					if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
						if (!piecePresent(e.getX(), e.getY())) {

							validMove = true;

						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						}
					}
				}
			}
			else if (pieceName.contains("BlackBishop")) {
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
			/*
			Bishop makes diagonally up and down the board.
			 */

				if (((xMovement == 2) && (yMovement == 2)) || ((xMovement == 1) && (yMovement == 1)) ||
						((xMovement == 3) && (yMovement == 3)) || ((xMovement == 4) && (yMovement == 4)) ||
						((xMovement == 5) && (yMovement == 5)) || ((xMovement == 6) && (yMovement == 6)) ||
						((xMovement == 7) && (yMovement == 7))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("White")) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("WhiteKing")) {
								System.out.println("Black has won the game");
								JOptionPane.showMessageDialog(null, "Black has won the game");
								System.exit(0);
							}
						}
					}
				}
			}
			else if (pieceName.contains("BlackRook")) {
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
			/*
			The Rook moves up, down and across in either direction on the board.
			 */

				if (((xMovement == 1) && (yMovement == 0)) || ((xMovement == 2) && (yMovement == 0)) ||
						((xMovement == 3) && (yMovement == 0)) || ((xMovement == 4) && (yMovement == 0)) ||
						((xMovement == 5) && (yMovement == 0)) || ((xMovement == 6) && (yMovement == 0)) ||
						((xMovement == 7) && (yMovement == 0)) || ((xMovement == 0) && (yMovement == 1)) ||
						((xMovement == 0) && (yMovement == 2)) || ((xMovement == 0) && (yMovement == 3)) ||
						((xMovement == 0) && (yMovement == 4)) || ((xMovement == 0) && (yMovement == 5)) ||
						((xMovement == 0) && (yMovement == 6)) || ((xMovement == 0) && (yMovement == 7))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("White")) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("WhiteKing")) {
								System.out.println("Black has won the game");
								JOptionPane.showMessageDialog(null, "Black has won the game");
								System.exit(0);
							}
						}
					}
				}
			}
			else if (pieceName.contains("BlackKing")) {
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
				/*
				The King can move in any direction but only one space at a time.
				 */

				if (((xMovement == 1) || (yMovement == 0)) || ((xMovement == 0) || (yMovement == 1)) ||
						((xMovement == 1) && (yMovement == 1))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("White")) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("WhiteKing")) {
								System.out.println("Black has won the game");
								JOptionPane.showMessageDialog(null, "Black has won the game");
								System.exit(0);
							}
						}
					}
				}
			}
			else if (pieceName.contains("BlackQueen")) {
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
			/*
				Queen can move vertically, horizontally and diagonally as long as the path is clear.
			 */

				if (((xMovement == 2) && (yMovement == 2)) || ((xMovement == 1) && (yMovement == 1)) ||
						((xMovement == 3) && (yMovement == 3)) || ((xMovement == 4) && (yMovement == 4)) ||
						((xMovement == 5) && (yMovement == 5)) || ((xMovement == 6) && (yMovement == 6)) ||
						((xMovement == 7) && (yMovement == 7)) || ((xMovement == 1) && (yMovement == 0)) ||
						((xMovement == 2) && (yMovement == 0)) || ((xMovement == 3) && (yMovement == 0)) ||
						((xMovement == 4) && (yMovement == 0)) || ((xMovement == 5) && (yMovement == 0)) ||
						((xMovement == 6) && (yMovement == 0)) || ((xMovement == 7) && (yMovement == 0)) ||
						((xMovement == 0) && (yMovement == 1)) || ((xMovement == 0) && (yMovement == 2)) ||
						((xMovement == 0) && (yMovement == 3)) || ((xMovement == 0) && (yMovement == 4)) ||
						((xMovement == 0) && (yMovement == 5)) || ((xMovement == 0) && (yMovement == 6)) ||
						((xMovement == 0) && (yMovement == 7))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("White")) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("WhiteKing")) {
								System.out.println("Black has won the game");
								JOptionPane.showMessageDialog(null, "Black has won the game");
								System.exit(0);
							}
						}
					}
				}
			}

			else if (pieceName.contains("BlackKnight")) {
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
			/*
				The Knight can move in an 'L' movement. It means that if xMovement ==1 then the yMovement
				must equal 2 and vice versa.

				We need to check the square being moved to and make sure if there is a piece there that
				it's not our own.

			 */

				if (((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("White")) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("WhiteKing")) {
								System.out.println("Black has won the game");
								JOptionPane.showMessageDialog(null, "Black has won the game");
								System.exit(0);
							}
						}
					}
				}
			}
		}

		else if (pieceName.contains("WhiteBishop")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}

			/*
			Bishop makes diagonally up and down the board.
			 */

				if (((xMovement == 2) && (yMovement == 2)) || ((xMovement == 1) && (yMovement == 1)) ||
						((xMovement == 3) && (yMovement == 3)) || ((xMovement == 4) && (yMovement == 4)) ||
						((xMovement == 5) && (yMovement == 5)) || ((xMovement == 6) && (yMovement == 6)) ||
						((xMovement == 7) && (yMovement == 7))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("Black")) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true; // allows to take a black piece.
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("BlackKing")) {
								System.out.println("White has won the game");
								JOptionPane.showMessageDialog(null, "White has won the game");
								System.exit(0);
							}
						}
					}
			}
		}

		else if (pieceName.contains("WhiteRook")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
			/*
			The Rook moves up, down and across in either direction on the board.
			 */

				if (((xMovement == 1) && (yMovement == 0)) ||
						((xMovement == 2) && (yMovement == 0)) || ((xMovement == 3) && (yMovement == 0)) ||
						((xMovement == 4) && (yMovement == 0)) || ((xMovement == 5) && (yMovement == 0)) ||
						((xMovement == 6) && (yMovement == 0)) || ((xMovement == 7) && (yMovement == 0)) ||
						((xMovement == 0) && (yMovement == 1)) || ((xMovement == 0) && (yMovement == 2)) ||
						((xMovement == 0) && (yMovement == 3)) || ((xMovement == 0) && (yMovement == 4)) ||
						((xMovement == 0) && (yMovement == 5)) || ((xMovement == 0) && (yMovement == 6)) ||
						((xMovement == 0) && (yMovement == 7))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("Black")) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("BlackKing")) {
								System.out.println("White has won the game");
								JOptionPane.showMessageDialog(null, "White has won the game");
								System.exit(0);
							}
						}
					}
			}
		}

		else if (pieceName.contains("WhiteKing")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
				/*
				The King can move in any direction but only one space at a time.
				 */

				if (((xMovement == 1) || (yMovement == 0)) || ((xMovement == 0) || (yMovement == 1)) ||
						((xMovement == 1) && (yMovement == 1))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("Black")) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("BlackKing")) {
								System.out.println("White has won the game");
								JOptionPane.showMessageDialog(null, "White has won the game");
								System.exit(0);
							}
						}
					}
			}
		}

		else if (pieceName.contains("WhiteQueen")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}

			/*
				Queen can move vertically, horizontally and diagonally as long as the path is clear.
			 */

					// diagonal moves

				if (((xMovement == 2) && (yMovement == 2)) || ((xMovement == 1) && (yMovement == 1)) ||
						((xMovement == 3) && (yMovement == 3)) || ((xMovement == 4) && (yMovement == 4)) ||
						((xMovement == 5) && (yMovement == 5)) || ((xMovement == 6) && (yMovement == 6)) ||
						((xMovement == 7) && (yMovement == 7)) ||
						// horizontal moves
						((xMovement == 1) && (yMovement == 0)) || ((xMovement == 2) && (yMovement == 0)) ||
						((xMovement == 3) && (yMovement == 0)) || ((xMovement == 4) && (yMovement == 0)) ||
						((xMovement == 5) && (yMovement == 0)) || ((xMovement == 6) && (yMovement == 0)) ||
						((xMovement == 7) && (yMovement == 0)) ||
						// vertical moves
						((xMovement == 0) && (yMovement == 1)) || ((xMovement == 0) && (yMovement == 2)) ||
						((xMovement == 0) && (yMovement == 3)) || ((xMovement == 0) && (yMovement == 4)) ||
						((xMovement == 0) && (yMovement == 5)) || ((xMovement == 0) && (yMovement == 6)) ||
						((xMovement == 0) && (yMovement == 7))) {

					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
							if (pieceName.contains("Black")) {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								}
							} else {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									validMove = true;
								}
								//taking the king to win the game.
								if (getPieceName(e.getX(), e.getY()).contains("BlackKing")) {
									System.out.println("White has won the game");
									JOptionPane.showMessageDialog(null, "White has won the game");
									System.exit(0);
								}
							}
						}
			}
		}

		else if (pieceName.contains("WhiteKnight")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
			/*
				The Knight can move in an 'L' movement. It means that if xMovement ==1 then the yMovement
				must equal 2 and vice versa.

				We need to check the square being moved to and make sure if there is a piece there that
				it's not our own.

			 */

			if (((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1))) {

				if (!piecePresent(e.getX(), e.getY())) {
					validMove = true;
				} else {
						if (pieceName.contains("Black")) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
							//taking the king to win the game.
							if (getPieceName(e.getX(), e.getY()).contains("BlackKing")) {
								System.out.println("White has won the game");
								JOptionPane.showMessageDialog(null, "White has won the game");
								System.exit(0);
							}
						}
					}
			}
		}


		else if(pieceName.equals("WhitePawn")){
			if(startY == 1)
			{
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2)) {
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
			}
			else{
				int newY = e.getY()/75;
				int newX = e.getX()/75;
				if((startX - 1 >= 0) || (startX + 1 <= 7))
				{
					if((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX+1) && (startX+1<=7))) || ((newX == (startX-1)) && (startX-1 >=0)))))
					{
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;
							if(startY == 6){
								success = true;
							}
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
				//taking the king to win the game.
				if (getPieceName(e.getX(), e.getY()).contains("BlackKing")) {
					System.out.println("White has won the game");
					JOptionPane.showMessageDialog(null, "White has won the game");
					System.exit(0);
				}
			}
		}
		if(!validMove){
			int location = 0;
			if(startY == 0){
				location = startX;
			}
			else{
				location  = (startY * 8) + startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
			if(success){
				if (pieceName.equalsIgnoreCase("BlackPawn")){
					int location = (e.getX() / 75);
					if (c instanceof JLabel) {
						Container parent = c.getParent();
						parent.remove(0);

						pieces = new JLabel(new ImageIcon("BlackQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					} else {
						Container parent = (Container) c;
						pieces = new JLabel(new ImageIcon("BlackQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					}
					Turn(true);


				} else if (pieceName.equalsIgnoreCase("WhitePawn")) {
					int location = 56 + (e.getX() / 75);
					if (c instanceof JLabel) {
						Container parent = c.getParent();
						parent.remove(0);

						pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					} else {
						Container parent = (Container) c;
						pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					}
					Turn(true);
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);
	        	Turn(true);
			}
		}
    }
 
    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 	
	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}


