import java.awt.Rectangle;
import java.util.ArrayList;

public class Player extends Entity
	{
		private Vector pushVel;
		
		public Player(Vector v, String t)
		{
			super(v, t);
			lives = 3;
			pushVel = new Vector(0,0);
		}
		
		public Vector getPushVel()
			{
				return pushVel;
			}
		public void setPushVel(Vector pushVel)
			{
				this.pushVel = pushVel;
			}

		public boolean[] checkEverything()
		{
			boolean[] checks = super.checkEverything();
			for(ArrayList<Block> line: SwordRunner.level)
				{
					for(Block b: line)
						{
							if(b != null && b.getType().equals("end"))
								{
									for(Rectangle r: hitBoxes)
										{
											if(b.getBounds().intersects(r))
												{
													checks[3] = true;
												}
										}
								}
						}
				}
			if(SwordRunner.level.get(0).get(0).getPos().getX() == -40 && (vel.getX() < 0 || pushVel.getX() < 0))
				checks[4] = false;
			else if((pos.getX() == 40 && (vel.getX() < 0 || pushVel.getX() < 0)) || (pos.getX() == 460 && (vel.getX() > 0 || pushVel.getX() > 0)))
				checks[4] = true;
			return checks;
		}
	}
