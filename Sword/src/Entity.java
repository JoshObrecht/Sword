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
				feet = new Point(pos.getX() + 20, pos.getY() + 40);
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
			for(int i = 0; i < Math.abs(vel.getX()); i++)
				{
					int increment = vel.getX() / Math.abs(vel.getX());
					pos.setX(pos.getX() + increment);
				}
			for(int i = 0; i < Math.abs(vel.getY()); i++)
				{
					int increment = vel.getY() / Math.abs(vel.getY());
					pos.setY(pos.getY() + increment);
				}
			feet = new Point(pos.getX() + 20, pos.getY() + 40);
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
