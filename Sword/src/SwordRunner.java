import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;

import sun.invoke.empty.Empty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@SuppressWarnings({ "serial", "unused" })
public class SwordRunner extends JPanel
	{
		public Player guy;
		public Ghost guyLives = new Ghost(new Vector(5,10));
		public static ArrayList<ArrayList<Block>> level;
		public ArrayList<Enemy> goombas = new ArrayList<Enemy>();
		public Vector levelVel = new Vector(0,0);
		public String xDir = "";
		public boolean isJumping = false;
		public final int size = 40;
		public Entity skybox1;
		public Entity skybox2;
		public int levelNum = 1;
		
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
							if(guy.checkEverything()[0])
								guy.getVel().setY(-20);
						}
					playerTick();
					enemyTick();
					repaint();
				}
			});
			timer.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(skybox1.getImage(), skybox1.getPos().getX(), skybox1.getPos().getY(), null);
			g.drawImage(skybox2.getImage(), skybox2.getPos().getX(), skybox2.getPos().getY(), null);
			for(int r = level.size() - 1; r >= 0; r--)
				{
					for(int c = 0; c < level.get(r).size(); c++)
						{
							if(level.get(r).get(c) != null)
								{
									if(!level.get(r).get(c).getType().equals(""))
										{
											g.drawImage(level.get(r).get(c).getImage(), level.get(r).get(c).getPos().getX(), level.get(r).get(c).getPos().getY(), null);	
										}
									else
										{	
//											g.setColor(level.get(r).get(c).getColor());
//											g.fillRect(level.get(r).get(c).getPos().getX(), level.get(r).get(c).getPos().getY(), size, size);
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
			for(int i = 0; i < guy.getLives(); i++)
				{
					g.drawImage(guyLives.getImage(), guyLives.getPos().getX() + (45 * i), guyLives.getPos().getY(), null);
				}
		}
		public void readLevel()
		{
			level = new ArrayList<ArrayList<Block>>();
			Scanner levelReader = null;
			int position = 0;
			try
				{
					levelReader = new Scanner(new File("level" + levelNum +".txt"));
				} catch (FileNotFoundException e)
				{
//					System.out.println("Level not found");
					System.out.println("BET");
				}
			int x = size * -1;
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
									b = new Block(new Vector(x,y), Color.GREEN, "grass");
									newLine.add(b);
									b.loadInformation();
									break;
								case 'r':
									b = new Block(new Vector(x,y), Color.RED, "dirt");
									newLine.add(b);
									b.loadInformation();
									break;
								case 'w':
									newLine.add(new Block(new Vector(x,y), Color.CYAN, ""));
									break;
								case 'p':
									guy = new Player(new Vector(x, y));
									break;
								case 'e':
									goombas.add(new Enemy(new Vector(x,y)));
									break;
								case 'c':
									b = new Block(new Vector(x,y), Color.CYAN, "cloud");
									newLine.add(b);
									b.loadInformation();
									break;
								case 'x':
									b = new Block(new Vector(x,y), Color.DARK_GRAY, "end");
									newLine.add(b);
									b.loadInformation();
									break;
								case ' ':
									newLine.add(null);
									break;
							}
							x += size;
						}
					level.add(newLine);
					x = size * -1;
					y += size;
				}
			try
				{
					
			skybox1 = new Entity(new Vector(0,0));
			skybox1.setImage(ImageIO.read(new File("src/Images/grasslandskybox1.png")));
			
			skybox2 = new Entity(new Vector(1000,0));
			skybox2.setImage(ImageIO.read(new File("src/Images/grasslandskybox2.png")));
			
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
			for(Enemy e: goombas)
				{
					e.getPos().setX(e.getPos().getX() - incX);
					e.getPos().setY(e.getPos().getY() + incY);
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
			boolean[] checks;
			for(int i = 0; i < Math.abs(guy.getVel().getX()); i++)
				{
					checks = guy.checkEverything();
					if(!checks[1])
						{
							int increment = guy.getVel().getX() / Math.abs(guy.getVel().getX());
							if(checks[4])
								tick(increment, 0);
							else
								guy.getPos().setX(guy.getPos().getX() + increment);
						}
					guy.getLeftB().setLocation(guy.getPos().getX() - 1, guy.getPos().getY());
					guy.getRightB().setLocation(guy.getPos().getX() + size, guy.getPos().getY());
				}
			for(int i = 0; i < Math.abs(guy.getVel().getY()); i++)
				{
					checks = guy.checkEverything();
					if(!checks[0] || guy.getVel().getY() < 0)
						{
							if(checks[2])
								{
									guy.getVel().setY(0);
								}
							if(guy.getVel().getY() != 0)
								{
									int increment = guy.getVel().getY() / Math.abs(guy.getVel().getY());
									guy.getPos().setY(guy.getPos().getY() + increment);
								}
						}
					else if(checks[0])
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
			
			checks = guy.checkEverything();
			if(!checks[0] && guy.getVel().getY() < 15)
				{
					guy.getVel().setY(guy.getVel().getY() + 1);
				}
			if(checks[3])
				{
					levelNum++;
					readLevel();
				}
		}
		public void enemyTick()
		{
			for(Enemy e: goombas)
				{
					for(int i = 0; i < Math.abs(e.getVel().getX()); i++)
						{
							boolean[] checks = e.checkEverything();
							if(!checks[1])
								{
									int increment = e.getVel().getX() / Math.abs(e.getVel().getX());
									e.getPos().setX(e.getPos().getX() + increment);
								}
							else
								{
									e.getVel().setX(e.getVel().getX() * -1);
								}
							e.getLeftB().setLocation(e.getPos().getX() - 1, e.getPos().getY());
							e.getRightB().setLocation(e.getPos().getX() + size, e.getPos().getY());
						}
				}
		}
	}