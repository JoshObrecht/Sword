import java.awt.Rectangle;

public class Boss extends Entity
	{
		public Boss(Vector v)
			{
				super(v, "boss");
				vel = new Vector(0,0);
				counter = 0;
				hitBoxes = new Rectangle[5];
				hitBoxes[0] = new Rectangle(pos.getX() - 1, pos.getY(), 1, 80);
				hitBoxes[2] = new Rectangle(pos.getX() + 80, pos.getY(), 1, 80);
				hitBoxes[1] = new Rectangle(pos.getX(), pos.getY() - 1, 80, 1);
				hitBoxes[3] = new Rectangle(pos.getX(), pos.getY() + 80, 80, 1);
				hitBoxes[4] = new Rectangle(pos.getX(), pos.getY(), 80, 80);
				lives = 3;
			}
	}
