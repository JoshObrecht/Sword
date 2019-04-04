import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ghost extends SwordObject
	{
		protected String type;
		
		public Ghost(Vector v, String t)
		{
			super(v, t);
			loadInformation();
		}
		
	}
