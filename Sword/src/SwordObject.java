
public abstract class SwordObject
	{
		protected Vector pos;
		
		public SwordObject(Vector v)
		{
			pos = v;
		}
		
		public Vector getPos()
		{
			return pos;
		}
		public void setPos(Vector v)
		{
			pos = v;
		}
	}
