package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.Couleur;
import model.observable.ChessGame;
import tools.ChessImageProvider;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

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
	String message;

	public ChessGameGUI(String message, ChessGameControlers chessGameControler2, Dimension boardSize) {
		this.chessGameControler = chessGameControler2;
		this.boardSize = new Dimension(600, 600);
		this.message = message;

		// Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Add a chess board to the Layered Pane

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.white : Color.black);
			else
				square.setBackground(i % 2 == 0 ? Color.black : Color.white);
		}
		// Add a few pieces to the board


		int i = 2;
		int j = 1;
		int k = 0;
		JLabel piece = null;
		JPanel panel = null;
		Couleur color = Couleur.NOIR;
		while (i > 0) {
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Tour", color)));
			panel = (JPanel) chessBoard.getComponent(j == 1 ? 0 : 56);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Cavalier", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Fou", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Roi", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Reine", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Fou", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Cavalier", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);
			piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Tour", color)));
			panel = (JPanel) chessBoard.getComponent(j++);
			panel.add(piece);

			color = Couleur.BLANC;
			i = i - 1;
			j = 57;
		}
		i = 2;
		k = 48;
		while (i > 0) {

			for (j = k; j < k + 8; j++) {
				piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Pion", color)));
				panel = (JPanel) chessBoard.getComponent(j);
				panel.add(piece);
			}
			i--;
			k = 8;
			color = Couleur.NOIR;

		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (chessPiece == null)
			return;
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
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null)
			return;

		chessPiece.setVisible(false);
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JLabel) {
			Container parent = c.getParent();
			parent.remove(0);
			parent.add(chessPiece);
		} else {
			Container parent = (Container) c;
			parent.add(chessPiece);
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
