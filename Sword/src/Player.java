import java.util.ArrayList;

public class Player extends Entity
	{
		public Player(Vector v)
		{
			super(v);
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
									if(b.getBounds().intersects(upB) || b.getBounds().intersects(leftB) || b.getBounds().intersects(rightB) || b.getBounds().intersects(downB))
										{
											checks[3] = true;
										}
								}
						}
				}
			if(SwordRunner.level.get(0).get(0).getPos().getX() == -40 && vel.getX() < 0)
				checks[4] = false;
			else if((pos.getX() == 40 && vel.getX() < 0) || (pos.getX() == 460 && vel.getX() > 0))
				checks[4] = true;
			return checks;
		}
	}
