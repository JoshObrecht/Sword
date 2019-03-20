import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
@SuppressWarnings("unused")
public class Block extends SwordObject
	{
		protected Color color;
		protected Rectangle bounds;
		protected String type;
	
		public Block(Vector pos, Color c, String t)
		{
			super(pos);
			this.color = c;
			bounds = new Rectangle(pos.getX(), pos.getY(), 40, 40);
			type = t;
		}
		
		public BufferedImage getImage()
			{
				return image;
			}

		public void setImage(BufferedImage image)
			{
				this.image = image;
			}

		public Color getColor()
			{
				return color;
			}
		public void setColor(Color c)
			{
				this.color = c;
			}
		public void loadInformation()
		{
			try
				{
				  if(color==Color.GREEN)
					  image = ImageIO.read(new File("src/Images/grassblock2.png"));
				  else if(color==Color.RED)
					  image = ImageIO.read(new File("src/Images/dirtblock.png"));
				  else if(type.equals("cloud"))
					  image = ImageIO.read(new File("src/Images/cloud.png"));
				  else if(type.equals("end"))
					  image = ImageIO.read(new File("src/Images/curtains.png"));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		public Rectangle getBounds()
			{
				return bounds;
			}
		public void setBounds(Rectangle bounds)
			{
				this.bounds = bounds;
			}
		public String getType()
			{
				return type;
			}
		public void setType(String type)
			{
				this.type = type;
			}

		public void tick()
		{
			bounds = new Rectangle(pos.getX(), pos.getY(), 40, 40);
		}
	}
