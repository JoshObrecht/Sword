import java.awt.Rectangle;
import java.util.ArrayList;

public class Boss extends Enemy
	{
		private boolean jumpingRight;
		private ArrayList<Ghost> hearts = new ArrayList<Ghost>();
		private int hurtTicks;
		
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
				hurtTicks = 0;
			}
				
		public boolean isJumpingRight()
			{
				return jumpingRight;
			}
		public void setJumpingRight(boolean jumpingRight)
			{
				this.jumpingRight = jumpingRight;
			}
		public ArrayList<Ghost> getHearts()
			{
				return hearts;
			}
		public void setHearts(ArrayList<Ghost> hearts)
			{
				this.hearts = hearts;
			}
		public int getHurtTicks()
			{
				return hurtTicks;
			}
		public void setHurtTicks(int hurtTicks)
			{
				this.hurtTicks = hurtTicks;
			}

		public void updateHitBoxes()
		{
			hitBoxes[0] = new Rectangle(pos.getX() - 1, pos.getY(), 1, 80);
			hitBoxes[2] = new Rectangle(pos.getX() + 80, pos.getY(), 1, 80);
			hitBoxes[1] = new Rectangle(pos.getX(), pos.getY() - 1, 80, 1);
			hitBoxes[3] = new Rectangle(pos.getX(), pos.getY() + 80, 80, 1);
			hitBoxes[4] = new Rectangle(pos.getX(), pos.getY(), 80, 80);
		}
	}
