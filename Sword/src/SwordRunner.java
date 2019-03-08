import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SwordRunner extends JPanel
	{
		public Entity guy = new Entity(new Vector(-40, -40));
		public ArrayList<ArrayList<Block>> level = new ArrayList<ArrayList<Block>>();
		public static void main(String[] args)
			{
				JFrame frame = new JFrame("Sword");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(1013, 913);
				SwordRunner game = new SwordRunner();
				frame.add(game);
				frame.setVisible(true);
				frame.setResizable(false);
				game.setFocusable(true);
				
			}
		public SwordRunner()
		{
			setBackground(Color.LIGHT_GRAY);
			addKeyListener(new KeyAdapter()
					{
						@Override
						public void keyPressed(KeyEvent e)
						{
							switch(e.getKeyCode())
							{
								case KeyEvent.VK_RIGHT:
									guy.getVel().setX(4);
									break;
								case KeyEvent.VK_LEFT:
									guy.getVel().setX(-4);
									break;
								case KeyEvent.VK_UP:
									guy.getVel().setY(-4);
									break;
							}
						}
						public void keyReleased(KeyEvent e)
						{
							switch(e.getKeyCode())
							{
								case KeyEvent.VK_RIGHT:
									guy.getVel().setX(0);
									break;
								case KeyEvent.VK_LEFT:
									guy.getVel().setX(0);
									break;
							}
						}
					});
			readLevel();
			Timer timer = new Timer(10, new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					guy.tick();
					repaint();
				}
			});
			timer.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			int x = 0;
			int y = 865;
			for(int r = level.size() - 1; r >= 0; r--)
				{
					for(int c = 0; c < level.get(r).size(); c++)
						{
							if(level.get(r).get(c).getColor()==Color.GREEN)
								{
								level.get(r).get(c).loadInformation();
							    g.drawImage(level.get(r).get(c).getImage(), x, y, null);
								}
							else
								{
							g.setColor(level.get(r).get(c).getColor());
							g.fillRect(x, y, 40, 40);
								}
							
							level.get(r).get(c).setPos(new Vector(x, y));
							x += 40;
						}
					x = 0;
					y -= 40;
				}
			g.setColor(Color.black);
			g.fillRect(guy.getPos().getX(), guy.getPos().getY(), 40, 40);
		}
		
		public void readLevel()
		{
			Scanner levelReader = null;
			int position = 0;
			try
				{
					levelReader = new Scanner(new File("level.txt"));
				} catch (FileNotFoundException e)
				{
//					System.out.println("Level not found");
					System.out.println("BET");
				}
			while(levelReader.hasNextLine())
				{
					ArrayList<Block> newLine = new ArrayList<Block>();
					String levelLine = levelReader.nextLine();
					char[] levelArray = levelLine.toCharArray();
					try
						{
					for(char c: levelArray)
						{
							switch(c)
							{
								case 'g':
									newLine.add(new Block(new Vector(0,0), Color.GREEN, null));
									break;
								case 'r':
									newLine.add(new Block(new Vector(0,0), Color.RED, null));
									break;
								case 'c':
									newLine.add(new Block(new Vector(0,0), Color.CYAN, null));
									break;
								case 'p':
									guy = new Entity(new Vector(48, 769));
									newLine.add(new Block(new Vector(0,0), Color.CYAN, null));
									break;
							}
						}
						}
					catch(Exception e){};
					level.add(newLine);
				}
			
		}
	}
