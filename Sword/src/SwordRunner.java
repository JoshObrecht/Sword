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
		public ArrayList<ArrayList<Color>> level = new ArrayList<ArrayList<Color>>();
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
			readLevel();
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
			int x = 0;
			int y = 835;
			for(int r = level.size() - 1; r >= 0; r--)
				{
					for(int c = 0; c < level.get(r).size(); c++)
						{
							g.setColor(level.get(r).get(c));
							g.fillRect(x, y, 48, 48);
							x += 48;
						}
					x = 0;
					y -= 48;
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
					ArrayList<Color> newLine = new ArrayList<Color>();
					String levelLine = levelReader.nextLine();
					char[] levelArray = levelLine.toCharArray();
					for(char c: levelArray)
						{
							switch(c)
							{
								case 'g':
									newLine.add(Color.GREEN);
									break;
								case 'r':
									newLine.add(Color.RED);
									break;
								case 'c':
									newLine.add(Color.CYAN);
									break;
							}
						}
					level.add(newLine);
				}
			
		}
	}
