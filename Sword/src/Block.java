import java.awt.*;
public class Block extends SwordObject
	{
		Color color;
		public Block(Vector pos, Color c)
		{
			super(pos);
			this.color = c;
		}
		
		public Color getColor()
			{
				return color;
			}
		public void setColor(Color c)
			{
				this.color = c;
			}
	}
