import java.awt.Rectangle;
import java.util.ArrayList;

public class Player extends Entity
	{
		public Player(Vector v, String t)
		{
			super(v, t);
			lives = 3;
		}
		
		
		public boolean[] checkEverything()
		{
			ArrayList<Block> gc = new ArrayList<Block>();
			boolean[] checks = super.checkEverything();
			for(ArrayList<Block> line: SwordRunner.level)
				{
					for(Block b: line)
						{
							if(b != null)
								{
									switch(b.getType())
									{
										case "end":
											for(Rectangle r: hitBoxes)
												{
													if(b.getBounds().intersects(r))
														{
															checks[3] = true;
														}
												}
											break;
										case "spike":
											for(Rectangle r: hitBoxes)
												{
													if(b.getBounds().intersects(r))
														{
															checks[5] = true;
														}
												}
											break;
										case "coin":
											for(Rectangle r: hitBoxes)
												{
													if(b.getBounds().intersects(r))
														{
															checks[6] = true;
															gc.add(b);
														}
												}
											break;
									}
								}
						}
				}
				
			for(int r =0; r<SwordRunner.level.size(); r++)
				{
					SwordRunner.level.get(r).removeAll(gc);
				}
			gc.clear();
			
			if(SwordRunner.level.get(0).get(0).getPos().getX() == -40 && vel.getX() < 0)
				checks[4] = false;
			else if((pos.getX() == 40 && vel.getX() < 0) || (pos.getX() == 460 && vel.getX() > 0))
				checks[4] = true;
			return checks;
		}
	}
