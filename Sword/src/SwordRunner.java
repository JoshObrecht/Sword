import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SwordRunner extends JPanel
	{
		public Entity guy = new Entity(new Vector(-48, -48));
		public ArrayList<ArrayList<Block>> level = new ArrayList<ArrayList<Block>>();
		public Vector levelVel = new Vector(0,0);
		public String xDir = "";
		public boolean isJumping = false;
		public final int size = 40;
		
		public static void main(String[] args)
			{
				JFrame frame = new JFrame("Sword");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(1000, 880);
				SwordRunner game = new SwordRunner();
				frame.add(game);
				frame.setVisible(true);
				frame.setResizable(false);
				game.setFocusable(true);
				
			}
		public SwordRunner()
		{
			setBackground(Color.CYAN);
			addKeyListener(new KeyAdapter()
					{
						@Override
						public void keyPressed(KeyEvent e)
						{
							switch(e.getKeyCode())
							{
								case KeyEvent.VK_RIGHT:
									xDir = "r";
									break;
								case KeyEvent.VK_LEFT:
									xDir = "l";
									break;
								case KeyEvent.VK_UP:
									isJumping = true;
									break;
							}
						}
						public void keyReleased(KeyEvent e)
						{
							switch(e.getKeyCode())
							{
								case KeyEvent.VK_RIGHT:
									xDir = "";
									break;
								case KeyEvent.VK_LEFT:
									xDir = "";
									break;
								case KeyEvent.VK_UP:
									isJumping = false;
									break;
							}
						}
					});
			readLevel();
			Timer timer = new Timer(10, new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					if(xDir.equals("r"))
						{
							guy.getVel().setX(5);
						}
					else if(xDir.equals("l"))
						{
							guy.getVel().setX(-5);
						}
					else
						{
							guy.getVel().setX(0);
						}
					if(isJumping)
						{
							checkStanding();
							if(guy.isStanding())
								guy.getVel().setY(-20);
						}
					playerTick();
					tick();
					repaint();
				}
			});
			timer.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(int r = level.size() - 1; r >= 0; r--)
				{
					for(int c = 0; c < level.get(r).size(); c++)
						{
							if(level.get(r).get(c) != null)
								{
									g.setColor(level.get(r).get(c).getColor());
									g.fillRect(level.get(r).get(c).getPos().getX(), level.get(r).get(c).getPos().getY(), size, size);
								}
						}
				}
			g.setColor(Color.MAGENTA);
			g.fillRect((int)guy.getBounds().getX(), (int)guy.getBounds().getY(), 42, 42);
			g.setColor(Color.black);
			g.fillRect(guy.getPos().getX(), guy.getPos().getY(), size, size);
			g.setColor(Color.RED);
			g.drawLine(40, 0, 40, 830);
			g.drawLine(500, 0, 500, 830);
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
					for(char c: levelArray)
						{
							switch(c)
							{
								case 'g':
									newLine.add(new Block(new Vector(0,0), Color.GREEN));
									break;
								case 'r':
									newLine.add(new Block(new Vector(0,0), Color.RED));
									break;
								case 'c':
									newLine.add(new Block(new Vector(0,0), Color.CYAN));
									break;
								case 'p':
									guy = new Entity(new Vector(40, 721));
									break;
								case ' ':
									newLine.add(null);
									break;
							}
						}
					level.add(newLine);
				}
			int x = 0;
			int y = 812;
			for(int r = level.size() - 1; r >= 0; r--)
				{
					for(int c = 0; c < level.get(r).size(); c++)
						{
							if(level.get(r).get(c) != null)
								level.get(r).get(c).setPos(new Vector(x, y));
							x += size;
						}
					x = 0;
					y -= size;
				}
			
		}

		public void tick()
		{
			for(ArrayList<Block> line: level)
				{
					for(Block b: line)
						{
							if(b != null)
								{
									b.getPos().setX(b.getPos().getX() - levelVel.getX());
									b.getPos().setY(b.getPos().getY() - levelVel.getY());
								}
						}
				}
		}
		public void playerTick()
		{
			for(int i = 0; i < Math.abs(guy.getVel().getX()); i++)
				{
					int increment = guy.getVel().getX() / Math.abs(guy.getVel().getX());
					guy.getPos().setX(guy.getPos().getX() + increment);
				}
			for(int i = 0; i < Math.abs(guy.getVel().getY()); i++)
				{
					checkStanding();
					if(!guy.isStanding() || guy.getVel().getY() < 0)
						{
							int increment = guy.getVel().getY() / Math.abs(guy.getVel().getY());
							guy.getPos().setY(guy.getPos().getY() + increment);
						}
					else if(guy.isStanding())
						{
							guy.getVel().setY(0);
							break;
						}
					guy.getBounds().setLocation(guy.getPos().getX() - 1, guy.getPos().getY() - 1);
				}
			guy.getBounds().x = guy.getPos().getX() - 1;
			checkStanding();
			if(!guy.isStanding())
				{
					guy.getVel().setY(guy.getVel().getY() + 1);
				}
		}
		public void checkStanding()
		{
			guy.setStanding(false);
			for(ArrayList<Block> line: level)
				{
					for(Block b: line)
						{
							if(b != null)
								{
									b.tick();
									if(b.getBounds().intersects(guy.getBounds()))
										{
											guy.setStanding(true);
										}
								}								
						}
				}
		}
	}
