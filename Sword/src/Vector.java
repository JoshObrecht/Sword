
public class Vector
	{
		int x;
		int y;
		double r;
		double o;
		double xDub;
		double yDub;
		
		public Vector(int x, int y)
		{
			this.x = x;
			this.y = y;
			xDub = (double) x;
			yDub = (double) y;
			updateTypes(true);
		}
		public Vector(double r, double o)
		{
			this.r = r;
			this.o = o;
			updateTypes(false);
		}
		
		
		
		public int getX()
			{
				return x;
			}
		public void setX(int x)
			{
				this.x = x;
				updateTypes(true);
			}
		public void setXSafe(int x)
		{
			this.x = x;
		}
		public int getY()
			{
				return y;
			}
		public void setY(int y)
			{
				this.y = y;
				updateTypes(true);
			}
		public void setYSafe(int y)
		{
			this.y = y;
		}
		public double getR()
			{
				return r;
			}
		public void setR(double r)
			{
				this.r = r;
				updateTypes(false);
			}
		public void setRSafe(double r)
		{
			this.r = r;
		}
		public double getO()
			{
				return o;
			}
		public void setO(double o)
			{
				this.o = o;
				updateTypes(false);
			}
		public void setOSafe(double o)
		{
			this.o = o;
		}
		public double getxDub()
			{
				return xDub;
			}
		public void setxDub(double xDub)
			{
				this.xDub = xDub;
			}
		public double getyDub()
			{
				return yDub;
			}
		public void setyDub(double yDub)
			{
				this.yDub = yDub;
			}
		
		private void updateTypes(boolean isXY)
		{
			if(isXY)
				{
					double theta = 0.00;
					if(x == 0 && y == 0)
					{
						theta = 0.00;
					}
					else if(x == 0)
					{
						theta = (Math.PI / 2);
					}
					else
					{
						theta = Math.atan((double)(y/x));
					}
					double magnitude = Math.sqrt((Math.pow((double) x, 2)) + (Math.pow((double) y, 2)));
					xDub = (double) x;
					yDub = (double) y;
					setRSafe(magnitude);
					setOSafe(theta);
				}
			else
				{
					double newX = (Math.cos(o) * r);
					double newY = (Math.sin(o) * r);
					xDub = newX;
					yDub = newY;
					setXSafe((int) Math.round(newX));
					setYSafe((int) Math.round(newY));
				}
//			System.out.println(xDub + " " + yDub);
		}
		public void addVects(Vector v1)
		{
			setR(Math.sqrt(Math.pow(r, 2) + Math.pow(v1.getR(), 2) + (2*r*v1.getR() * Math.cos(o - v1.getO()))));
			setO(o + Math.atan2(v1.getR() * Math.sin(v1.getO() - o), r + v1.getR() * Math.cos(v1.getO() - o)));
		}
		
	}
