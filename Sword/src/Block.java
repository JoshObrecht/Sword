import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
public class Block extends SwordObject
	{
		Color color;
		Rectangle bounds;
	
		public Block(Vector pos, Color c)
		{
			super(pos);
			this.color = c;
			this.image = i;
			bounds = new Rectangle(pos.getX(), pos.getY(), 40, 40);

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

		public void tick()
		{
			bounds = new Rectangle(pos.getX(), pos.getY(), 40, 40);
		}
	}
