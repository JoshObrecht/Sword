import java.awt.Rectangle;
import java.util.ArrayList;

public class Entity extends SwordObject
	{
		protected Vector vel;
		protected final int gravity = 1;
		protected Rectangle leftB;
		protected Rectangle rightB;
		protected Rectangle upB;
		protected Rectangle downB;
		protected int counter;
		
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
				counter = 0;
				leftB = new Rectangle(pos.getX() - 1, pos.getY(), 1, 40);
				rightB = new Rectangle(pos.getX() + 40, pos.getY(), 1, 40);
				upB = new Rectangle(pos.getX(), pos.getY() - 1, 40, 1);
				downB = new Rectangle(pos.getX(), pos.getY() + 40, 40, 1);
			}
		
		public int getCounter()
			{
				return counter;
			}
		public void setCounter(int counter)
			{
				this.counter = counter;
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

		public void tick()
		{
			
		}
		public boolean[] checkEverything()
		{
			/*
			 * For the checks, checks[0] is Standing, 1 is Wall, 2 is Ceiling, 3 is End, and 4 is LevelMove
			*/
			boolean[] checks = new boolean[5];
			for(ArrayList<Block> line: SwordRunner.level)
				{
					for(Block b: line)
						{
							if(b != null)
								{
									b.tick();
									if(b.getBounds().intersects(downB))
										checks[0] = true;
									if(!b.getType().equals("cloud"))
										{
											if(b.getBounds().intersects(leftB) && vel.getX() < 0)
												checks[1] = true;
											else if(b.getBounds().intersects(rightB) && vel.getX() > 0)
												checks[1] = true;	
											if(b.getBounds().intersects(upB) && vel.getY() < 0)
												checks[2] = true;
										}
									
								}
						}
				}
			return checks;
		}
	}
