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
		public ArrayList<Enemy> goombas = new ArrayList<Enemy>();
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
									if(level.get(r).get(c).getColor()==Color.GREEN||level.get(r).get(c).getColor()==Color.RED)
										{
											g.drawImage(level.get(r).get(c).getImage(), level.get(r).get(c).getPos().getX(), level.get(r).get(c).getPos().getY(), null);	
										}
									else
										{	
											g.setColor(level.get(r).get(c).getColor());
											g.fillRect(level.get(r).get(c).getPos().getX(), level.get(r).get(c).getPos().getY(), size, size);
										}	
//									g.setColor(level.get(r).get(c).getColor());
//									g.fillRect(level.get(r).get(c).getPos().getX(), level.get(r).get(c).getPos().getY(), size, size);
								}
						}
				}
			for(Enemy e: goombas)
				{
					g.setColor(Color.MAGENTA);
					g.fillRect(e.getPos().getX(), e.getPos().getY(), size, size);
				}
			g.setColor(Color.BLACK);
			g.fillRect(guy.getPos().getX(), guy.getPos().getY(), size, size);
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
			int x = 0;
			int y = 0;
			while(levelReader.hasNextLine())
				{
					ArrayList<Block> newLine = new ArrayList<Block>();
					String levelLine = levelReader.nextLine();
					char[] levelArray = levelLine.toCharArray();
					for(char c: levelArray)
						{
							Block b;
							switch(c)
							{
								case 'g':
									b = new Block(new Vector(x,y), Color.GREEN, null);
									newLine.add(b);
									b.loadInformation();
									break;
								case 'r':
									b = new Block(new Vector(x,y), Color.RED, null);
									newLine.add(b);
									b.loadInformation();
									break;
								case 'c':
									newLine.add(new Block(new Vector(x,y), Color.CYAN, null));
									break;
								case 'p':
									guy = new Entity(new Vector(x, y));
									break;
								case 'e':
									goombas.add(new Enemy(new Vector(x,y)));
									break;
								case ' ':
									newLine.add(null);
									break;
							}
							x += size;
						}
					level.add(newLine);
					x = 0;
					y += size;
				}
//			x = 0;
//			y = 812;
//			for(int r = level.size() - 1; r >= 0; r--)
//				{
//					for(int c = 0; c < level.get(r).size(); c++)
//						{
//							if(level.get(r).get(c) != null)
//								level.get(r).get(c).setPos(new Vector(x, y));
//							x += size;
//						}
//					x = 0;
//					y -= size;
//				}
			
		}

		public void tick(int incX, int incY)
		{
			for(ArrayList<Block> line: level)
				{
					for(Block b: line)
						{
							if(b != null)
								{
									b.getPos().setX(b.getPos().getX() - incX);
									b.getPos().setY(b.getPos().getY() + incY);
								}
						}
				}
			for(Enemy e: goombas)
				{
					e.getPos().setX(e.getPos().getX() - incX);
					e.getPos().setY(e.getPos().getY() + incY);
				}
		}
		public void playerTick()
		{
			for(int i = 0; i < Math.abs(guy.getVel().getX()); i++)
				{
					if(!checkWall())
						{
							int increment = guy.getVel().getX() / Math.abs(guy.getVel().getX());
							if(checkLevelMove())
								tick(increment, 0);
							else
								guy.getPos().setX(guy.getPos().getX() + increment);
						}
					guy.getLeftB().setLocation(guy.getPos().getX() - 1, guy.getPos().getY());
					guy.getRightB().setLocation(guy.getPos().getX() + size, guy.getPos().getY());
				}
			for(int i = 0; i < Math.abs(guy.getVel().getY()); i++)
				{
					checkStanding();
					if(!guy.isStanding() || guy.getVel().getY() < 0)
						{
							if(guy.getVel().getY() != 0)
								{
									int increment = guy.getVel().getY() / Math.abs(guy.getVel().getY());
									guy.getPos().setY(guy.getPos().getY() + increment);
								}
						}
					else if(guy.isStanding())
						{
							guy.getVel().setY(0);
							break;
						}
					guy.getUpB().setLocation(guy.getPos().getX(), guy.getPos().getY() - 1);
					guy.getDownB().setLocation(guy.getPos().getX(), guy.getPos().getY() + size);
				}
			guy.getLeftB().setLocation(guy.getPos().getX() - 1, guy.getPos().getY());
			guy.getRightB().setLocation(guy.getPos().getX() + size, guy.getPos().getY());
			guy.getUpB().setLocation(guy.getPos().getX(), guy.getPos().getY() - 1);
			guy.getDownB().setLocation(guy.getPos().getX(), guy.getPos().getY() + size);
			checkStanding();
			if(!guy.isStanding() && guy.getVel().getY() < 15)
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
									if(b.getBounds().intersects(guy.getDownB()) || b.getBounds().intersects(guy.getUpB()))
										{
											guy.setStanding(true);
										}
//									else if(b is a double-jumpy block)
//										{
//											check upB as well;
//										}
//									if(b.getBounds().intersects(guy.getUpB())) makes you collide with bottoms of blocks
//										{
//											guy.getVel().setY(0);
//										}
								}								
						}
				}
		}
		public boolean checkLevelMove()
		{
			if((guy.getPos().getX() == 40 && guy.getVel().getX() < 0) || (guy.getPos().getX() == 460 && guy.getVel().getX() > 0))
				{
					return true;
				}
			return false;
		}
		public boolean checkWall()
		{
			boolean inWall = false;
			for(ArrayList<Block> line: level)
				{
					for(Block b: line)
						{
							if(b != null)
								{
									b.tick();
									if(b.getBounds().intersects(guy.getLeftB()) && guy.getVel().getX() < 0)
										{
											inWall = true;;
										}
									else if(b.getBounds().intersects(guy.getRightB()) && guy.getVel().getX() > 0)
										{
											inWall = true;;
										}
								}								
						}
				}
			return inWall;
		}
	}