import java.awt.Rectangle;
import java.util.ArrayList;

public class Entity extends SwordObject
	{
		protected Vector vel;
		protected final int gravity = 1;
		protected Rectangle[] hitBoxes; //hitboxes stored clockwise starting with left
		protected int counter;
		protected int lives;
		
		public Entity(Vector v)
			{
				super(v);
				vel = new Vector(0,0);
				counter = 0;
				hitBoxes = new Rectangle[5];
				hitBoxes[0] = new Rectangle(pos.getX() - 1, pos.getY(), 1, 40);
				hitBoxes[2] = new Rectangle(pos.getX() + 40, pos.getY(), 1, 40);
				hitBoxes[1] = new Rectangle(pos.getX(), pos.getY() - 1, 40, 1);
				hitBoxes[3] = new Rectangle(pos.getX(), pos.getY() + 40, 40, 1);
				hitBoxes[4] = new Rectangle(pos.getX(), pos.getY(), 40, 40);
				lives = 1;
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
				return hitBoxes[0];
			}
		public void setLeftB(Rectangle leftB)
			{
				hitBoxes[0] = leftB;
			}
		public Rectangle getRightB()
			{
				return hitBoxes[2];
			}
		public void setRightB(Rectangle rightB)
			{
				hitBoxes[2] = rightB;
			}
		public Rectangle getUpB()
			{
				return hitBoxes[1];
			}
		public void setUpB(Rectangle upB)
			{
				hitBoxes[1] = upB;
			}
		public Rectangle getDownB()
			{
				return hitBoxes[3];
			}
		public void setDownB(Rectangle downB)
			{
				hitBoxes[3] = downB;
			}
		public Rectangle[] getHitBoxes()
			{
				return hitBoxes;
			}
		public void setHitBoxes(Rectangle[] hitBoxes)
			{
				this.hitBoxes = hitBoxes;
			}
		public int getLives()
			{
				return lives;
			}
		public void setLives(int lives)
			{
				this.lives = lives;
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
									if(b.getBounds().intersects(hitBoxes[3]))
										checks[0] = true;
									if(!b.getType().equals("cloud"))
										{
											if(b.getBounds().intersects(hitBoxes[0]) && vel.getX() < 0)
												checks[1] = true;
											else if(b.getBounds().intersects(hitBoxes[2]) && vel.getX() > 0)
												checks[1] = true;	
											if(b.getBounds().intersects(hitBoxes[1]) && vel.getY() < 0)
												checks[2] = true;
										}
									
								}
						}
				}
			return checks;
		}
	}
