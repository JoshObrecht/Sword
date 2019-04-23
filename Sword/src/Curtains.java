import java.awt.Color;

public class Curtains extends Block
	{
		private int destination;
		public Curtains(Vector pos, int d)
			{
				super(pos, Color.RED, "end");
				destination = d;
			}
	}
