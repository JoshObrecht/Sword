import java.awt.Rectangle;

public class Entity extends SwordObject
	{
		protected Vector vel;
		protected final int gravity = 1;
		protected Rectangle leftB;
		protected Rectangle rightB;
		protected Rectangle upB;
		protected Rectangle downB;
		protected boolean isStanding;
		
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
				isStanding = true;
				leftB = new Rectangle(pos.getX() - 1, pos.getY(), 1, 40);
				rightB = new Rectangle(pos.getX() + 40, pos.getY(), 1, 40);
				upB = new Rectangle(pos.getX(), pos.getY() - 1, 40, 1);
				downB = new Rectangle(pos.getX(), pos.getY() + 40, 40, 1);
			}
		
		public Vector getVel()
			{
				return vel;
			}
		public void setVel(Vector vel)
			{
				this.vel = vel;
			}
		public Rectangle getLeftB()
			{
				return leftB;
			}
		public void setLeftB(Rectangle leftB)
			{
				this.leftB = leftB;
			}
		public Rectangle getRightB()
			{
				return rightB;
			}
		public void setRightB(Rectangle rightB)
			{
				this.rightB = rightB;
			}
		public Rectangle getUpB()
			{
				return upB;
			}
		public void setUpB(Rectangle upB)
			{
				this.upB = upB;
			}
		public Rectangle getDownB()
			{
				return downB;
			}
		public void setDownB(Rectangle downB)
			{
				this.downB = downB;
			}
		public boolean isStanding()
			{
				return isStanding;
			}
		public void setStanding(boolean isStanding)
			{
				this.isStanding = isStanding;
			}

		public void tick()
		{
			/*for(int i = 0; i < Math.abs(vel.getX()); i++)
				{
					int increment = vel.getX() / Math.abs(vel.getX());
					pos.setX(pos.getX() + increment);
				}
			for(int i = 0; i < Math.abs(vel.getY()); i++)
				{
					int increment = vel.getY() / Math.abs(vel.getY());
					pos.setY(pos.getY() + increment);
				}
			yBounds = new Rectangle(pos.getX(), pos.getY() + 40);
			if(!isStanding)
				{
					vel.setY(vel.getY() + gravity);
				}
			else
				{
					vel.setY(0);
				}*/
		}
	}
