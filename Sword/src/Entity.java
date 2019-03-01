import java.awt.Point;

public class Entity extends SwordObject
	{
		protected Vector vel;
		protected final int gravity = 1;
		protected Point feet;
		protected boolean isStanding;
		
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
				isStanding = true;
			}
		
		public Vector getVel()
			{
				return vel;
			}
		public void setVel(Vector vel)
			{
				this.vel = vel;
			}
		public Point getFeet()
			{
				return feet;
			}
		public void setFeet(Point feet)
			{
				this.feet = feet;
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
			pos.setX(pos.getX() + vel.getX());
			pos.setY(pos.getY() + vel.getY());
			feet = new Point(pos.getX() + 24, pos.getY() + 48);
			if(!isStanding)
				{
					vel.setY(vel.getY() + gravity);
				}
			else
				{
					vel.setY(0);
				}
		}
	}
