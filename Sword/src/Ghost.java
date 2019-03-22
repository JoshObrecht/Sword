import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ghost extends SwordObject
	{
		protected String type;
		
		public Ghost(Vector v)
		{
			super(v);
			type = "life";
			loadInformation();
		}
		
		public void loadInformation()
		{
			switch(type)
			{
				case "life":
				try
					{
						image = ImageIO.read(new File("src/Images/heart.png"));
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					break;
			}
		}
	}
