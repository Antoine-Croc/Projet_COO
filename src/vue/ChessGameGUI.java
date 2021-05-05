package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.observable.ChessGame;

public class ChessGameGUI extends JFrame implements MouseListener ,MouseMotionListener, Observer   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	ChessGameControlers chessGameControler;
	Dimension boardSize;
	String title;
	public   ChessGameGUI(String title,ChessGameControlers chessGameControler2 ,Dimension boardSize) {
		this.chessGameControler = chessGameControler2 ;
		this.boardSize = new Dimension(600, 600);
		this.title = title;
		 
		  //  Use a Layered Pane for this this application
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
		  square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
		  else
		  square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
		  } 
		//Add a few pieces to the board
		  
		  JLabel piece = new JLabel( new ImageIcon("./images/tourNoireS.png") );
		  JPanel panel = (JPanel)chessBoard.getComponent(0);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/cavalierNoirS.png"));
		  panel = (JPanel)chessBoard.getComponent(1);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/fouNoirS.png"));
		  panel = (JPanel)chessBoard.getComponent(2);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/roiNoirS.png"));
		  panel = (JPanel)chessBoard.getComponent(4);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/reineNoireS.png"));
		  panel = (JPanel)chessBoard.getComponent(3);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/fouNoirS.png"));
		  panel = (JPanel)chessBoard.getComponent(5);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/cavalierNoirS.png"));
		  panel = (JPanel)chessBoard.getComponent(6);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/tourNoireS.png") );
		  panel = (JPanel)chessBoard.getComponent(7);
		  panel.add(piece);
		  
		  for (int i = 8; i < 16; i++) {
			  piece = new JLabel( new ImageIcon("./images/pionNoirS.png") );
			  panel = (JPanel)chessBoard.getComponent(i);
			  panel.add(piece);
		  }

		 
		  
		  piece = new JLabel( new ImageIcon("./images/tourBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(63);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/cavalierBlancS.png"));
		  panel = (JPanel)chessBoard.getComponent(62);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/fouBlancS.png"));
		  panel = (JPanel)chessBoard.getComponent(61);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/roiBlancS.png"));
		  panel = (JPanel)chessBoard.getComponent(60);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/reineBlancS.png"));
		  panel = (JPanel)chessBoard.getComponent(59);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/fouBlancS.png"));
		  panel = (JPanel)chessBoard.getComponent(58);
		  panel.add(piece);
		  piece = new JLabel(new ImageIcon("./images/cavalierBlancS.png"));
		  panel = (JPanel)chessBoard.getComponent(57);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/tourBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(56);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(48);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(49);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(50);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(51);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(52);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(53);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(54);
		  panel.add(piece);
		  piece = new JLabel( new ImageIcon("./images/pionBlancS.png") );
		  panel = (JPanel)chessBoard.getComponent(55);
		  panel.add(piece);
		  
		  }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		  if (chessPiece == null) return;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 chessPiece = null;
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JPanel) 
		  return;
		 
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		  if(chessPiece == null) return;
		  
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
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
		  

	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
