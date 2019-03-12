import java.awt.image.BufferedImage;

public abstract class SwordObject
	{
		protected Vector pos;
		protected BufferedImage image;
		
		public SwordObject(Vector v)
		{
			pos = v;
			image = null;
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
		
	}
