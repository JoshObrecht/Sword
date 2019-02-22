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
			level.add(new ArrayList<Color>());
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
			for(Color c: level.get(0))
				{
					x += 48;
					g.setColor(c);
					g.fillRect(x, 48, 48, 48);
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
					String levelLine = levelReader.nextLine();
					char[] levelArray = levelLine.toCharArray();
					for(char c: levelArray)
						{
							switch(c)
							{
								case 'g':
									level.get(0).add(Color.GREEN);
									break;
								case 'r':
									level.get(0).add(Color.RED);
									break;
							}
						}
				}
			
		}
	}
