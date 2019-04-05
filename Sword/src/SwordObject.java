import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class SwordObject
	{
		protected Vector pos;
		protected BufferedImage image;
		protected ArrayList<BufferedImage> anim;
		protected String type;
		
		public SwordObject(Vector v, String t)
		{
			pos = v;
			type = t;
			image = null;
			loadInformation();
			if(type.equals("enemy"))
				anim = fillAnim(3);
		}
		
		public Vector getPos()
		{
			return pos;
		}
		public void setPos(Vector v)
		{
			pos = v;
		}
		public BufferedImage getImage()
		{
			return image;
		}
		public void setImage(BufferedImage image)
		{
			this.image = image;
		}
		public ArrayList<BufferedImage> getAnim()
		{
			return anim;
		}
		public void setAnim(ArrayList<BufferedImage> anim)
		{
			this.anim = anim;
		}
		public ArrayList<BufferedImage> fillAnim(int frames)
		{
			ArrayList<BufferedImage> newAnim = new ArrayList<BufferedImage>();
			int frameNum = 0;
			for(int j = 0; j < image.getHeight(); j+=40)
				{
					for(int k = 0; k < image.getWidth(); k+=40)
						{
							if(frameNum <= frames)
								{
									newAnim.add(image.getSubimage(k, j, 40, 40));
									frameNum++;
								}
						}
				}
			return newAnim;
		}
		public void loadInformation()
			{
				try
					{
						switch(type)
						{
							case "life":
								image = ImageIO.read(new File("src/Images/heart.png"));
								break;
							case "grass":
								image = ImageIO.read(new File("src/Images/grassblock2.png"));
								break;
							case "dirt":
								image = ImageIO.read(new File("src/Images/dirtblock.png"));
								break;
							case "cloud":
								image = ImageIO.read(new File("src/Images/cloud.png"));
								break;
							case "end":
								image = ImageIO.read(new File("src/Images/curtains.png"));
								break;
							case "enemy":
								image = ImageIO.read(new File("src/Images/slime.png"));
								break;
						}
					} catch (IOException e)
					{
						e.printStackTrace();
					}
			}
		
	}
