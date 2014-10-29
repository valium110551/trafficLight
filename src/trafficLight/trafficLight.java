
package trafficLight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class trafficLight extends JFrame implements ActionListener{ 
 
	int gwidth = 450, gheight = 450, gx, gy; //gwidth = width of canvas, gheight = hight of canvas, 
	                                        //gx = x position of canvas
	double slope; //velocity of the vehicle
	double firstInterSectionFi1;
	double verticalDistance;
	int count=0;
	int numT;
	int x0;
	int y0;
	
	Dimension ScreenSize, FrameSize; 
	JLabel jl1, jl2, 
	       jlInt1, jlInt2, jlInt3,
	       jlInt1G, jlInt2G, jlInt3G, jlInt1R, jlInt2R, jlInt3R, 
	       jlInt1Off, jlInt2Off, jlInt3Off, jlInt1P, jlInt2P, jlInt3P; 
	       //Ex: jlInt2G = jlabel of 2nd intersection (Green light)
	       //Off = offset, P = position
	JTextField jtf,
	           jtfInt1G, jtfInt2G, jtfInt3G, jtfInt1R, jtfInt2R, jtfInt3R,
	           jtfInt1Off, jtfInt2Off, jtfInt3Off, jtfInt1P, jtfInt2P, jtfInt3P;
	
	JButton jb1,jb2,jb3; 
	JPanel jp1,jp2; //jp1 is in the north of JFrame, including jl1, jtf, jb1, jb2, jb3;
	                //jp2 is in the south of JFrame, including jl2
	Graphics g; //g = canvas
	
	ArrayList<Intersection> intersections;
	Intersection initialIntersec; 
	ArrayList<PassLine> passLines;	
 
	public trafficLight()
	{ 	
		jl1 = new JLabel("請輸入行車速度："); 
		jl2 = new JLabel(" ");
		jlInt1 = new JLabel("路口1.");
		jlInt2 = new JLabel("路口2.");
		jlInt3 = new JLabel("路口3.");
		jlInt1G = new JLabel("綠燈");
		jlInt2G = new JLabel("綠燈");
		jlInt3G = new JLabel("綠燈");
		jlInt1R = new JLabel("紅燈");
		jlInt2R = new JLabel("紅燈");
		jlInt3R = new JLabel("紅燈");
		jlInt1Off = new JLabel("offset");
		jlInt2Off = new JLabel("offset");
		jlInt3Off = new JLabel("offset");
		jlInt1P = new JLabel("位置");
		jlInt2P = new JLabel("位置");
		jlInt3P = new JLabel("位置");
		jtf = new JTextField(10); 
		jtfInt1G = new JTextField(8);
		jtfInt1R = new JTextField(8);
		jtfInt1Off = new JTextField(8);
		jtfInt1P = new JTextField(8);
		jtfInt2G = new JTextField(8); 
		jtfInt2R = new JTextField(8);
		jtfInt2Off = new JTextField(8);
		jtfInt2P = new JTextField(8);
		jtfInt3G = new JTextField(8);
		jtfInt3R = new JTextField(8);
		jtfInt3Off = new JTextField(8);
		jtfInt3P = new JTextField(8);
		jb1 = new JButton("開始畫！"); 
		jb2 = new JButton("清除"); 
		jb3 = new JButton("關閉"); 
		jp1 = new JPanel(); 
		jp2 = new JPanel(); 
		jp1.add(jlInt1);
		jp1.add(jlInt1G);
		jp1.add(jtfInt1G);
		jp1.add(jlInt1R);
		jp1.add(jtfInt1R);
		jp1.add(jlInt1Off);
		jp1.add(jtfInt1Off);
		jp1.add(jlInt1P);
		jp1.add(jtfInt1P);
		jp1.add(jlInt2);
		jp1.add(jlInt2G);
		jp1.add(jtfInt2G);
		jp1.add(jlInt2R);
		jp1.add(jtfInt2R);
		jp1.add(jlInt2Off);
		jp1.add(jtfInt2Off);
		jp1.add(jlInt2P);
		jp1.add(jtfInt2P);
		jp1.add(jlInt3);
		jp1.add(jlInt3G);
		jp1.add(jtfInt3G);
		jp1.add(jlInt3R);
		jp1.add(jtfInt3R);
		jp1.add(jlInt3Off);
		jp1.add(jtfInt3Off);
		jp1.add(jlInt3P);
		jp1.add(jtfInt3P);
		jp1.add(jl1); 
		jp1.add(jtf); 
		jp1.add(jb1); 
		jp1.add(jb2); 
		jp1.add(jb3); 
		add(jp1, BorderLayout.CENTER); //add jp1 into JFrame, and set to be on the north side of BorderLayout
		jp2.add(jl2); 
		add(jp2, BorderLayout.SOUTH); 
		jb1.addActionListener(this); 
		jb2.addActionListener(this); 
		jb3.addActionListener(this); 
 
		//-----implementation of jtf's KeyListener-----
		jtf.addKeyListener( 
			new KeyListener()
			{
				public void keyPressed(KeyEvent e) 
				{ 
					if(e.getKeyCode()==10) //if click "Enter"
					{
						JButton2_Action(); //execute JButton2_Action
					}
				}
				public void keyTyped(KeyEvent e) {} 
				public void keyReleased(KeyEvent e) {} 
			});
		//---------------------End-----------------------
 
		//-----Arrange the layout----- 
		setSize(600, 600); //set JFrame's screen size
		setResizable(false);
		ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the position of JFrame screen 
		FrameSize = getSize(); 
		setLocation((ScreenSize.width-FrameSize.width)/2,(ScreenSize.height-FrameSize.height)/2); 
		// set JFrame in the middle of screen
		setTitle("交通號誌續進規劃"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true); 
		//-----------End-------------- 
 
		//-----------Create canvas-----------
		gx=(FrameSize.width-gwidth)/2; 
		gy=(FrameSize.height-gheight)/2; 
		g = getGraphics(); 
		g.setClip(gx-40,gy+80, gwidth+20, gheight); //set the position of the canvas's center in the middle 
		g.setColor(Color.BLACK); 
		x0=gx+20;//x0 = x position of the origin of the axis
		y0=gy+gwidth-20;
		//----------------END----------------
	}
 
	public static void main(String[] args) 
	{ 
		new trafficLight(); 
	}
 
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getSource()==jb1) 
		{
			JButton2_Action(); 
		}
		else if(e.getSource()==jb2) 
		{
			update(g); //delete the content of canvas
			jl2.setText(" "); 
			jtf.setText(""); 
			count=0;	
			g.setColor(Color.BLACK);
		}
		else if(e.getSource()==jb3) 
		{
			System.exit(0); //close the window
		}
	}
	
	public void JButton2_Action()
	{ 
		update(g);
		slope=Integer.parseInt(jtf.getText())/10; //get the slope from jtf
 		double int1G = Double.parseDouble(jtfInt1G.getText());		
 		double int1R = Double.parseDouble(jtfInt1R.getText());	
 		double int1P = Double.parseDouble(jtfInt1P.getText());	
 		double int1Off = Double.parseDouble(jtfInt1Off.getText());	
 		
 		double int2G = Double.parseDouble(jtfInt2G.getText());		
 		double int2R = Double.parseDouble(jtfInt2R.getText());	
 		double int2P = Double.parseDouble(jtfInt2P.getText());	
 		double int2Off = Double.parseDouble(jtfInt2Off.getText());
 		
 		double int3G = Double.parseDouble(jtfInt3G.getText());		
 		double int3R = Double.parseDouble(jtfInt3R.getText());	
 		double int3P = Double.parseDouble(jtfInt3P.getText());	
 		double int3Off = Double.parseDouble(jtfInt3Off.getText());
 				
		Graphics2D g2=(Graphics2D)g;
		
		//draw X axis
		g.drawLine(x0, y0, gx+gwidth, y0);
		//draw Y axis
		g.drawLine(x0, y0, x0, gy);
			
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.BOLD, 14));
		//Label of x axis
		g.drawString("Time(sec.)", gx+gwidth/2, gy+gwidth);
		//Label of y axis
		g.drawString("Position", gx-40, gy+gwidth/2-130);
		g.drawString("(m)", gx-18, gy+gwidth/2-110);
				
		//Inptut the parameters of each intersection
		intersections=new ArrayList<Intersection>(0);
		intersections.add(new Intersection(int1G,int1R,int1P,int1Off,g2));
		intersections.add(new Intersection(int2G,int2R,int2P,int2Off,g2));
		intersections.add(new Intersection(int3G,int3R,int3P,int3Off,g2));		
			
//			intersections.add(new Intersection(40,30,50,0,g2));
//			intersections.add(new Intersection(50,20,130,10,g2));
//			intersections.add(new Intersection(20,30,200,20,g2));		
			
		verticalDistance=intersections.get(0).getPosition()-intersections.get(2).getPosition();
			
		g.drawString("A", gx-5, (int)intersections.get(0).getPosition()+5);
		g.drawString("B", gx-5, (int)intersections.get(1).getPosition()+5);
		g.drawString("C", gx-5, (int)intersections.get(2).getPosition()+5);
			
		//Roughly cal the number of T to plot
		ArrayList<Double> periods=new ArrayList<Double>();	
		
		for(Intersection item:intersections)
			periods.add(item.getPeriod());	
		
		double shortestPeriod=Collections.min(periods);						
		int totalTime=gwidth;
		numT=totalTime/(int)(shortestPeriod);			
								
		    //-----passLine cal (slope > 0)-----
			initialIntersec = intersections.get(0); 
			firstInterSectionFi1=intersections.get(0).getFi1();
			
			passLines = new ArrayList<PassLine>();	
			PassLine passLine = new PassLine(initialIntersec.getGreenIntervalList().get(0).getBegin(), initialIntersec.getPosition());	
			passLine.setSlope(slope);
			crossRed(passLine);
			
			g2.setColor(Color.blue);
			for(PassLine item:passLines)
			{
				item.drawLine(g2);
			}
		
			//-----passLine cal (slope < 0)-----
			passLines.clear();
			count = 0;
			initialIntersec = intersections.get(2); 
			firstInterSectionFi1 = intersections.get(2).getFi1();
			
			PassLine passLine2 = new PassLine(initialIntersec.getGreenIntervalList().get(0).getBegin(), initialIntersec.getPosition());	
			passLine2.setSlope(-slope);
			crossRed(passLine2);
			
			//plot all the passLines
			g2.setColor(Color.orange);
			for(PassLine item:passLines)
			{
				item.drawLine(g2);
			}				
	}
	
	/*
	 * executed when passLine touch the red T(move the passline out of red T to the right side)
	*/
	public void crossRed(PassLine passLine)
	{
		count++;
		int count2 = 0;
		double movement = -1;//movement = distance to be moved in order to move out of red T
		while(!(movement == 0) && count2 < numT*4)
		{
			count2++;
			//Get the distances needed to be moved corresponding to each intersection
			ArrayList<Double> movements = new ArrayList<Double>();	
			for(Intersection item:intersections)
			{
				double movementForEachIntersection=item.passInRed(passLine);				
				movements.add(movementForEachIntersection); 		
			}
			movement=Collections.max(movements);//find the longest distance cutted in the red T
			passLine.addPosiStartX(movement);//move the passline out of red T to the right side
		}
	
		if(movement == 0) //if moved out of red T successively ,execute crossGreen()
		{
			movement = -1;
			PassLine passLineCopy =new PassLine(passLine.getStartX(), passLine.getStartY());						
			passLines.add(passLineCopy);
			crossGreen(passLine);	
		}
	}
	
	/*
	 * executed when passLine touch the green T(move the passline out of green T to the right side)
	 */
	
	public void crossGreen(PassLine passLineR)
	{
		count++;
		double movement = 0;
		
		ArrayList<Double> movements = new ArrayList<Double>();	
		for(Intersection item:intersections)
		{
			double movementForEachIntersection=item.passInGreen(passLineR);				
			movements.add(movementForEachIntersection); 		
		}
		
		movement = Collections.min(movements);
		passLineR.addPosiStartX(movement);
	
		PassLine passLineCopy = new PassLine(passLineR.getStartX(), passLineR.getStartY());						
		passLines.add(passLineCopy);
		if(count <= numT*4)
		crossRed(passLineR);
	}
	
	public void drawLine(Graphics2D g2,double a,double b,double c,double d)
	{
		Line2D line = new Line2D.Double();
		line.setLine(a,b,c,d);
		g2.draw(line);
	}
	
	public class Intersection
	{
		double fi1;//green light period T
		double fi2;
		double period;//red + green light T
		double position;//y position of intersection
		double offset;
		int numberT;
		ArrayList<GreenInterval> greenIntervals = new ArrayList<GreenInterval>();
		
		public Intersection(double fi1In, double fi2In, double positionIn, double offsetIn, Graphics2D g2)
		{
			fi1 = fi1In;
			fi2 = fi2In;
			period = fi1+fi2;
			numberT = 2*gwidth/(int)period;
			position = y0-positionIn;
			offset = offsetIn;
			
			if(offset>=period)//make sure offset is less than period
			offset=offset % period;		
			
			//cal. and plot the green and red periods corresponding to each intersection 
			//and store into ArrayLIst:greenInterval
			double yPosition = getPosition();		
			if(getOffset() <= getFi2())
			{
				double redBegin=x0;
				double redEnd=x0+getOffset();					
				drawLine(g2,redBegin,yPosition,redEnd,yPosition);		            
  			}
			else if(getOffset()>getFi2())
			{
				double redBegin=x0+getFi1()+getOffset()-getPeriod();
				double redEnd=x0+getFi1()+getOffset()-getFi1();
				drawLine(g2,redBegin,yPosition,redEnd,yPosition);
				
				GreenInterval greenInterval = new GreenInterval(x0,redBegin);
				addGreenInterval(greenInterval);
			}
			
			for(int j=0;j!=numberT;j++)
			{				
				double redBegin=x0+getFi1()+getPeriod()*j+getOffset();
				double redEnd=x0+getFi1()+getFi2()+getPeriod()*j+getOffset();
				drawLine(g2,redBegin,yPosition,redEnd,yPosition);
				
				GreenInterval greenInterval = new GreenInterval(redBegin-getFi1(),redBegin);
				addGreenInterval(greenInterval);
			}		
		}
		
		public double getFi1(){return fi1;}
		public double getFi2(){return fi2;}
		public double getPeriod(){return period;}
		public double getPosition(){return position;}
		public double getOffset(){return offset;}
		
		public void addGreenInterval(GreenInterval greenIntervalIn) //add green period 
		{
			greenIntervals.add(greenIntervalIn);
		}	
		public ArrayList<GreenInterval> getGreenIntervalList() //Get the info. of green light for this intersection 
		{
			return greenIntervals;
		}			
		public double passInRed(PassLine pl)//get the distance needed to be passed when the passLine is in red T
		{
			double distanceY=pl.getStartY()-position;
			double distanceX=(double)distanceY/pl.getSlope()+pl.getStartX();
			double moveLS=0;			
			
			for( GreenInterval item:greenIntervals)
			{
				//whenever the begin point of green T is bigger than the x position of intersection 
				//in which passLine is in, stop the for loop 
				if(item.getBegin() >= distanceX) 
				{
					moveLS=item.getBegin()-distanceX;
					break;
				}
				
				//whenever the begin point of green T is in the intersection in which passline is in,
				//stop the for loop
				else if(item.getBegin() <= distanceX && distanceX <item.getEnd())
				{
					moveLS=0;
					break;
				}				
			}
			
			return moveLS; 
		}
		
		public double passInGreen(PassLine pl)
		{
			double distanceY=pl.getStartY()-position;
			double distanceX=(double)distanceY/pl.getSlope()+pl.getStartX();
			double moveRS=0;			

			for( GreenInterval item:greenIntervals)
			{
				if(item.getBegin() <= distanceX && distanceX <=item.getEnd())
				{
					moveRS=item.getEnd()-distanceX;
					break;
				}				
			}			
			return moveRS; 
		}		
	}
	
	public class GreenInterval
	{
		double begin;
		double end;
		public GreenInterval(double beginIn,double endIn)
		{
			begin = beginIn;
			end = endIn;
		}
		public double getBegin() {return begin;}
		public double getEnd() {return end;}
	}
	
	public class PassLine
	{
		double startX;
		double startY;
		double endX;
		double endY;
		
		public PassLine(double x,double y)
		{
			startX = x;
			startY = y;
			if(slope >= 0)
			{
				endX = x+verticalDistance/slope;
				endY = y-verticalDistance;
			}
			else if(slope<0)
			{
				endX = x-verticalDistance/slope;
				endY = y+verticalDistance;
			}
		}	
		
		public void addPosiStartX(double xIn) //move passline
		{
			startX += xIn ;
			endX += xIn;
		}		
		public void drawLine(Graphics2D g) 
		{
			Line2D line = new Line2D.Double();
			if(slope >= 0)
				line.setLine(startX-20/slope, startY+20, endX+20/slope, endY-20);
			else
				line.setLine(startX+20/slope, startY-20, endX-20/slope, endY+20);
			g.draw(line);
		} 	
		public double getStartX(){return startX;}
		public double getStartY(){return startY;}
		public double getSlope(){return slope;}
		public void setSlope(double slopeIn){ slope = slopeIn; }	
	}
}


 
