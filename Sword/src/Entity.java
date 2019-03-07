import java.awt.Rectangle;

public class Entity extends SwordObject
	{
		protected Vector vel;
		protected final int gravity = 1;
		protected Rectangle yBounds;
		protected Rectangle xBounds;
		protected boolean isStanding;
		
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
				isStanding = true;
				yBounds = new Rectangle(pos.getX(), pos.getY() - 1, 40, 42);
			}
		
		public Vector getVel()
			{
				return vel;
			}
		public void setVel(Vector vel)
			{
				this.vel = vel;
			}
		public Rectangle getyBounds()
			{
				return yBounds;
			}
		public void setyBounds(Rectangle bounds)
			{
				this.yBounds = bounds;
			}
		public Rectangle getxBounds()
			{
				return xBounds;
			}
		public void setxBounds(Rectangle bounds)
			{
				this.xBounds = bounds;
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
			yBounds = new Rectangle(pos.getX(), pos.getY() + 40);
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
