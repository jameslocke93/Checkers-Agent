import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserGraphics extends JFrame {
	
	public UserGraphics(boolean[][] board) throws HeadlessException {
		setSize(450, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new DrawArea(board));
		setVisible(true);
		
	}
	
	
	class DrawArea extends JPanel{
		
		private boolean[][] board;
		
		Point a = null;
		public DrawArea(boolean[][] board){
			a = new Point(50, 50);
			
			this.board = board;
		}
		
		@Override
		protected void paintComponent(Graphics g){

			for(int j = 0; j < 8; j++){
				if((j & 1) == 0){
					for(int i = 0; i < 8; i++){
						if((i & 1) == 0){
							g.setColor(Color.WHITE);
							g.fillRect(i*50, j * 50,50,50);
						} else {
							g.setColor(Color.BLACK);
							g.fillRect(i*50, j * 50,50,50);
						}
					}
				} else {
					for(int i = 0; i < 8; i++){
						if((i & 1) == 0){
							g.setColor(Color.BLACK);
							g.fillRect(i*50, j * 50,50,50);
						} else {
							g.setColor(Color.WHITE);
							g.fillRect(i*50, j * 50,50,50);
						}
					}
				}
			}
			
			int y = -50;
			int x = 0;
			boolean even = false;
			
			for(int i = 0; i < board[0].length; i++){
				
				if(i % 4 == 0){
					if(even){
						even = false;
					} else {
						even = true;
					}
					y += 50;
					x = 0;
				}
				
				
				if(board[0][i]){
					if(board[1][i]){
						g.setColor(Color.RED);
						if(even){
							g.fillOval(x + 50, y, 30, 30);
						} else {
							g.fillOval(x, y, 30, 30);
						}
					} else {
						g.setColor(Color.WHITE);
						if(even){
							g.fillOval(x + 50, y, 30, 30);
						} else {
							g.fillOval(x, y, 30, 30);
						}
					}
				}
				
				if(even){
					if(board[2][i]){
						g.setColor(Color.BLACK);
						g.drawString("K", x+60, y+15);
					}
				} else {
					if(board[2][i]){
						g.setColor(Color.BLACK);
						g.drawString("K", x+10, y+15);
					}
				}
				
				x += 100;
			}
		}
	}
	
}
