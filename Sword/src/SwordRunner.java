import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;

import sun.invoke.empty.Empty;

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
		public Entity skybox1;
		public Entity skybox2;
		
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
			g.drawImage(skybox1.getImage(), skybox1.getPos().getX(), skybox1.getPos().getY(), null);
			g.drawImage(skybox1.getImage(), skybox2.getPos().getX(), skybox2.getPos().getY(), null);
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
			g.setColor(Color.black);
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
									b = new Block(new Vector(0,0), Color.GREEN);
									newLine.add(b);
									b.loadInformation();
									break;
								case 'r':
									b = new Block(new Vector(0,0), Color.RED);
									newLine.add(b);
									b.loadInformation();
									break;
								case 'c':
									newLine.add(new Block(new Vector(0,0), Color.CYAN));
									break;
								case 'p':
									guy = new Entity(new Vector(44, 721));
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
			try
				{
					
			skybox1 = new Entity(new Vector(0,0));
			skybox1.setImage(ImageIO.read(new File("src/Images/skybox.png")));
			
			skybox2 = new Entity(new Vector(1000,0));
			skybox2.setImage(ImageIO.read(new File("src/Images/skybox.png")));
			
				}
			catch(Exception e){}
			
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
			skybox1.setCounter(skybox1.getCounter()+1);
			if(skybox1.getCounter()==3)
				{
					if(skybox1.getPos().getX()<-1000)
						skybox1.getPos().setX(999);
					if(skybox2.getPos().getX()<-1000)
						skybox2.getPos().setX(999);
					
					if(skybox1.getPos().getX()>1000)
						skybox1.getPos().setX(-999);
					if(skybox2.getPos().getX()>1000)
						skybox2.getPos().setX(-999);
					
					skybox1.getPos().setX(skybox1.getPos().getX() - incX);
					skybox2.getPos().setX(skybox2.getPos().getX() -incX);
					skybox1.setCounter(0);
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