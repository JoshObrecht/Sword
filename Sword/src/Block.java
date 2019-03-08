import java.awt.*;
public class Block extends SwordObject
	{
		Color color;
		Rectangle bounds;
		public Block(Vector pos, Color c)
		{
			super(pos);
			this.color = c;
			bounds = new Rectangle(pos.getX(), pos.getY(), 40, 40);
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

		public void tick()
		{
			bounds = new Rectangle(pos.getX(), pos.getY(), 48, 48);
		}
	}
