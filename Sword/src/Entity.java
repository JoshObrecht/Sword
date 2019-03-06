import java.awt.Rectangle;

public class Entity extends SwordObject
	{
		protected Vector vel;
		protected final int gravity = 1;
		protected Rectangle bounds;
		protected boolean isStanding;
		
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
				isStanding = true;
				bounds = new Rectangle(pos.getX() - 1, pos.getY() - 1, 42, 42);
			}
		
		public Vector getVel()
			{
				return vel;
			}
		public void setVel(Vector vel)
			{
				this.vel = vel;
			}
		public Rectangle getBounds()
			{
				return bounds;
			}
		public void setBounds(Rectangle bounds)
			{
				this.bounds = bounds;
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
			bounds = new Rectangle(pos.getX() - 1, pos.getY() + 40);
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
