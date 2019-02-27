
public class Entity extends SwordObject
	{
		protected Vector vel;
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
			}
		
		public Vector getVel()
			{
				return vel;
			}
		public void setVel(Vector vel)
			{
				this.vel = vel;
			}
		
		public void tick()
		{
			pos.setX(pos.getX() + vel.getX());
			pos.setY(pos.getY() + vel.getY());
			if(pos.getY() < 769)
				{
					vel.setY(2);
				}
			else
				{
					vel.setY(0);
				}
		}
	}
