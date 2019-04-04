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
	
		public Block(Vector pos, Color c, String t)
		{
			super(pos, t);
			this.color = c;
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
