import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
public class Block extends SwordObject
	{
		Color color;
		BufferedImage image;
		public Block(Vector pos, Color c, BufferedImage i)
		{
			super(pos);
			this.color = c;
			this.image = i;
		}
		
		public Image getImage()
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
				  image = ImageIO.read(new File("src/Images/block.png.png"));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}
	}
