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
		static JFrame frame = new JFrame("Sword");
		public double height;
		public double width;
		public ArrayList<ArrayList<Block>> level = new ArrayList<ArrayList<Block>>();
		public static void main(String[] args)
			{
				frame = new JFrame("Sword");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-1013)/2, ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-913)/2, 1013, 913);
				SwordRunner game = new SwordRunner();
				frame.add(game);
				frame.setVisible(true);
				frame.setResizable(true);
				game.setFocusable(true);
				
			}
		public SwordRunner()
		{
			setBackground(Color.LIGHT_GRAY);
			readLevel();
			getFrameDimensions();
			Timer timer = new Timer(10, new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					repaint();
				}
			});
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			getFrameDimensions();
			int blockX = (int)((width*.0473840079));
			int blockY = (int)((height*.0525739321));
			int x = 0;
			int y = (int) (height-blockY);
			for(int r = level.size() - 1; r >= 0; r--)
				{
					for(int c = 0; c < level.get(r).size(); c++)
						{
							g.setColor(level.get(r).get(c).getColor());
							g.fillRect(x, y, blockX, blockY);
							level.get(r).get(c).setPos(new Vector(x, y));
							x += blockX;
						}
					x = 0;
					y -= blockY;
				}
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
							}
						}
					level.add(newLine);
				}
			
		}
		
		public void getFrameDimensions()
		{
			height = frame.getContentPane().getHeight();
			width = frame.getContentPane().getWidth();
		}
	}
