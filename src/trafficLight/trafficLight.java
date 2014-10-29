
//目前只能吃int 要用graphics2D去改
//新增視窗可輸入trafficLight的資訊

package trafficLight;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; //載入java.awt的所有物件和方法。包含用於創建使用者介面和繪製圖形圖像的全部類別。
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //載入java.awt.event的所有物件和方法。提供處理由 AWT 元件所觸發的各類別事件的介面和類別。
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame; //載入javax.swing的所有物件和方法。提供一組「輕量級」的JAVA元件，盡量讓這些元件在所有平臺上的工作方式都相同。
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class trafficLight extends JFrame implements ActionListener{ //宣告LineDraw類別，並將其繼承JFrame物件。實作ActionListener(可用","隔開不同實作)，接收操作JFrame的事件。
 
	int gwidth=450,gheigh=450,gx,gy; //gwidth為畫布之寬；gheigh為畫布之高；gx為畫布之x位置；gy為畫布之y位置。
	Dimension ScreenSize,FrameSize; //ScreenSize為螢幕之大小；FrameSize為表單之大小。
	JLabel jl1,jl2,jlInt1,jlInt2,jlInt3,jlInt1G,jlInt2G,jlInt3G,jlInt1R,jlInt2R,jlInt3R,
	jlInt1Off,jlInt2Off,jlInt3Off,jlInt1P,jlInt2P,jlInt3P; //jl1為提示使用者輸入東西的標籤；jl2為提示使用者輸入數值或格式有誤的標籤。
	JTextField jtf,jtfInt1G,jtfInt1R,jtfInt1Off,jtfInt1P,jtfInt2G,jtfInt2R,jtfInt2Off,jtfInt2P,
	jtfInt3G,jtfInt3R,jtfInt3Off,jtfInt3P; //jtf提供使用者輸入文字的方塊區。
	JButton jb1,jb2,jb3; //jb1為確認jtf輸入之按鈕，並開始繪圖；jb2能讓使用者清除JFrame視窗上可以不有的東西，即回到剛開始的畫面；jb3提供使用者關閉程式的功能。
	JPanel jp1,jp2; //jp1位於JFrame之北，內含物件jl1、jtf、jb1、jb2、jb3；jp2位於JFrame之南，內含物件jl2。
	Graphics g; //g為繪製多邊形的畫布。
	double slope;
	ArrayList<Intersection> intersections;
	Intersection initialIntersec; 
	double firstInterSectionFi1;
	ArrayList<PassLine> passLines;
	int count=0;
	int numT;
	int x0;
	int y0;
	double verticalDistance;
 
	public trafficLight(){ 
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
		jl2.setForeground(Color.red); //設定jl2標籤物件之文字顏色為紅色。
		jtf = new JTextField(10); //建立jtf為JTextField文字方塊物件，並設定長度為10。
		jtfInt1G = new JTextField(8);//Fi1 
		jtfInt1R = new JTextField(8);
		jtfInt1Off = new JTextField(8);
		jtfInt1P = new JTextField(8);
		jtfInt2G = new JTextField(8);//Fi1 
		jtfInt2R = new JTextField(8);
		jtfInt2Off = new JTextField(8);
		jtfInt2P = new JTextField(8);
		jtfInt3G = new JTextField(8);//Fi1 
		jtfInt3R = new JTextField(8);
		jtfInt3Off = new JTextField(8);
		jtfInt3P = new JTextField(8);
		jb1 = new JButton("開始畫！"); //建立jb1為JButton按鈕物件，並設定文字為「開始畫！」。
		jb2 = new JButton("清除"); //建立jb2為JButton按鈕物件，並設定文字為「清除」。
		jb3 = new JButton("關閉"); //建立jb3為JButton按鈕物件，並設定文字為「關閉」。
		jp1 = new JPanel(); //建立jp1為JPanel容器物件。
		jp2 = new JPanel(); //建立jp2為JPanel容器物件。
		jp1.add(jlInt1);
		jp1.add(jlInt1G);
		jp1.add(jtfInt1G);
		jp1.add(jlInt1R);
		jp1.add(jtfInt1R);
		jp1.add(jlInt1Off);
		jp1.add(jtfInt1Off);
		jp1.add(jlInt1P);
		jp1.add(jtfInt1P);
		jp1.add(jlInt2);//
		jp1.add(jlInt2G);
		jp1.add(jtfInt2G);
		jp1.add(jlInt2R);
		jp1.add(jtfInt2R);
		jp1.add(jlInt2Off);
		jp1.add(jtfInt2Off);
		jp1.add(jlInt2P);
		jp1.add(jtfInt2P);
		jp1.add(jlInt3);//
		jp1.add(jlInt3G);
		jp1.add(jtfInt3G);
		jp1.add(jlInt3R);
		jp1.add(jtfInt3R);
		jp1.add(jlInt3Off);
		jp1.add(jtfInt3Off);
		jp1.add(jlInt3P);
		jp1.add(jtfInt3P);
		jp1.add(jl1); //將jl1標籤加入jp1容器。
		jp1.add(jtf); //將jtf文字方塊加入jp1容器。
		jp1.add(jb1); //將jb1按鈕加入jp1容器。
		jp1.add(jb2); //將jb2按鈕加入jp1容器。
		jp1.add(jb3); //將jb3按鈕加入jp1容器。
		//jp1.add(jtf2,BorderLayout.SOUTH);
		add(jp1,BorderLayout.CENTER); //將jp1加入JFrame，並設定為BorderLayout排版之NORTH(北方)位置。
		jp2.add(jl2); //將jl2標籤加入jp2容器。
		add(jp2,BorderLayout.SOUTH); //將jp2加入JFrame，並設定為BorderLayout排版之SOUTH(南方)位置。
		jb1.addActionListener(this); //建立jb1在JFrame(LineDraw類別)的ActionListener實作介面。
		jb2.addActionListener(this); //建立jb2在JFrame(LineDraw類別)的ActionListener實作介面。
		jb3.addActionListener(this); //建立jb3在JFrame(LineDraw類別)的ActionListener實作介面。
 
		//---jtf的KeyListener實作介面---
			jtf.addKeyListener( //建立jtf的KeyListener實作介面。
				new KeyListener(){ //建立KeyListener
					public void keyPressed(KeyEvent e) { //當鍵盤按下某個鍵時使用此方法。
						if(e.getKeyCode()==10) //如果按下Enter。
						{
							JButton2_Action(); //執行JButton2_Action方法。
						}
					}
 
 
					public void keyTyped(KeyEvent e) {} //當鍵盤輸入某個鍵時使用此方法，這裡沒有用到。
 
					public void keyReleased(KeyEvent e) {} //當鍵盤放開某個鍵時使用此方法，這裡沒有用到。
 
				}
			);
		//-------------END-------------
 
		setSize(600,600); //設定JFrame的視窗大小。
		setResizable(false); //關閉JFrame能更變視窗大小的功能。
		ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); //取得JFrame視窗位於螢幕的位置點。
		FrameSize = getSize(); //取得JFrame視窗大小。
		setLocation((ScreenSize.width-FrameSize.width)/2,(ScreenSize.height-FrameSize.height)/2); //將JFrame視窗預設位置擺在螢幕中央。
		setTitle("交通號誌續進規劃"); //設定JFrame視窗標題。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //設定JFrame按下叉叉關閉鈕可以關閉程式。
		setVisible(true); //顯示JFrame。
 
		//-----------建立畫布-----------
			gx=(FrameSize.width-gwidth)/2; //設定畫布x起始點位於JFrame表單寬度減掉畫布寬度後再除二的位置。
			gy=(FrameSize.height-gheigh)/2; //設定畫布y起始點位於JFrame表單高度減掉畫布高度後再除二的位置。
			g = getGraphics(); //建立g畫布方法。
			g.setClip(gx-40,gy+80, gwidth+20, gheigh); //設定g畫布的位置為表單的正中間。
			g.setColor(Color.BLACK); //設置g畫布的繪圖顏色為黑色。
			x0=gx+20;//x0為座標原點的x位置
			y0=gy+gwidth-20;//y0為座標原點的y位置
		//-------------END-------------
	}
 
	public static void main(String[] args) { //主程式
		new trafficLight(); //建立trafficLight物件。
	}
 
	public void actionPerformed(ActionEvent e) { //ActionListener的actionPerformed方法。
		if(e.getSource()==jb1) //如果按下jb1。(開始畫)
		{
			JButton2_Action(); //執行JButton2_Action方法。
		}
		else if(e.getSource()==jb2) //如果按下jb2。(清除)
		{
			update(g); //消除g畫布內容。
			jl2.setText(" "); //清空jl2。
			jtf.setText(""); //清空jtf。
			count=0;	
			g.setColor(Color.BLACK);
		}
		else if(e.getSource()==jb3) //如果按下jb3。(關閉)
		{
			System.exit(0); //關閉本程式。
		}
	}
	
	public void JButton2_Action(){ //按下"畫圖"後或是在jtf上按Enter後之動作
		update(g); //消除g畫布內容。
		slope=Integer.parseInt(jtf.getText())/10; //擷取jtf輸入進來的斜率
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
 				
		Graphics2D g2=(Graphics2D)g;//將g downcast成Graphics2D物件(因為Graphics2D物件能做更多繪圖功能)
		
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
				
			//輸入各路口的參數
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
			
			//計算要畫多少週期
			ArrayList<Double> periods=new ArrayList<Double>();		
			for(Intersection item:intersections)
				periods.add(item.getPeriod());			
			double shortestPeriod=Collections.min(periods);						
			int totalTime=gwidth;
			numT=totalTime/(int)(shortestPeriod);//畫布寬度除以最小周期=最少要畫多少週期				
								
		    //順向passLine的計算
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
		
			//逆向passLine的計算
			passLines.clear();
			count=0;
			initialIntersec = intersections.get(2); 
			firstInterSectionFi1 = intersections.get(2).getFi1();
			
			PassLine passLine2 = new PassLine(initialIntersec.getGreenIntervalList().get(0).getBegin(), initialIntersec.getPosition());	
			passLine2.setSlope(-slope);
			crossRed(passLine2);
			
			//畫出所有passLine(順向跟逆向)
			g2.setColor(Color.orange);
			for(PassLine item:passLines)
			{
				item.drawLine(g2);
			}				
	}
	
	//passLine交到紅燈區時執行。功能:將passLine向右移出紅燈區
	public void crossRed(PassLine passLine)
	{
		count++;
		int count2=0;
		double movement=-1;//movement即passLine交到各路口(Intersection)紅燈段時該移動的距離
		while(!(movement==0) && count2<numT*4)
		{
			count2++;
			//抓出對應所有路口，passLine該移動的距離
			ArrayList<Double> movements = new ArrayList<Double>();	
			for(Intersection item:intersections)
			{
				double movementForEachIntersection=item.passInRed(passLine);				
				movements.add(movementForEachIntersection); 		
			}
			movement=Collections.max(movements);//找紅燈區最大截斷長度
			passLine.addPosiStartX(movement);//將passLine向右移出紅燈區
		}
	
		if(movement==0)//如果順利移出紅燈區，執行crossGreen()
		{
			movement=-1;
			PassLine passLineCopy =new PassLine(passLine.getStartX(),passLine.getStartY());						
			passLines.add(passLineCopy);//加入此順利移出紅燈區的passLine
			crossGreen(passLine);	
		}
	}
	
	//passLine交到綠燈區時執行。功能:將passLine向右移出綠燈區
	public void crossGreen(PassLine passLineR)
	{
		count++;
		double movement=0;//movement即passLine交到各路口(Intersection)綠燈段時該移動的距離
		
		//抓出對應所有路口，passLine該移動的距離
		ArrayList<Double> movements = new ArrayList<Double>();	
		for(Intersection item:intersections)
		{
			double movementForEachIntersection=item.passInGreen(passLineR);				
			movements.add(movementForEachIntersection); 		
		}
		
		movement=Collections.min(movements);//找綠燈區最短截斷長度
		passLineR.addPosiStartX(movement);//將passLine向右移出綠燈區	
	
		PassLine passLineCopy =new PassLine(passLineR.getStartX(),passLineR.getStartY());						
		passLines.add(passLineCopy);
		if(count<=numT*4)
		crossRed(passLineR);
	}
	
	public void drawLine(Graphics2D g2,double a,double b,double c,double d)
	{
		Line2D line = new Line2D.Double();
		line.setLine(a,b,c,d);
		g2.draw(line);
	}
	//路口
	public class Intersection
	{
		double fi1;//綠燈時間長度
		double fi2;//紅燈時間長度
		double period;//週期=綠燈+紅燈時間長度
		double position;//路口位置(y軸位置)
		double offset;//(相差)
		int numberT;
		ArrayList<GreenInterval> greenIntervals = new ArrayList<GreenInterval>();
		
		public Intersection(double fi1In,double fi2In,double positionIn,double offsetIn,Graphics2D g2)
		{
			fi1=fi1In;
			fi2=fi2In;
			period=fi1+fi2;
			numberT=2*gwidth/(int)period;
			position=y0-positionIn;
			offset=offsetIn;
			
			if(offset>=period)//確保offset小於週期
			offset=offset % period;		
			
			//計算(並畫出)各路口的紅、綠燈區間，並儲存在各路口物件的ArrayLIst:greenInterval內
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
		
		public void addGreenInterval(GreenInterval greenIntervalIn) //加入綠燈區段
		{
			greenIntervals.add(greenIntervalIn);
		}	
		public ArrayList<GreenInterval> getGreenIntervalList() //獲取此路口的所有綠燈區段資訊
		{
			return greenIntervals;
		}			
		public double passInRed(PassLine pl)//回傳passLine在紅燈區間時該移動的"距離"
		{
			double distanceY=pl.getStartY()-position;
			double distanceX=(double)distanceY/pl.getSlope()+pl.getStartX();
			double moveLS=0;			
			
			for( GreenInterval item:greenIntervals)
			{
				//綠燈區段begin一"大"於passLine在該路口的x即停止此for loop
				if(item.getBegin() >= distanceX) 
				{
					moveLS=item.getBegin()-distanceX;
					break;
				}
				
				//綠燈區段begin一"介"於passLine在該路口的x即停止此for loop
				else if(item.getBegin() <= distanceX && distanceX <item.getEnd())
				{
					moveLS=0;
					break;
				}				
			}
			
			return moveLS; 
		}
		
		public double passInGreen(PassLine pl)//回傳passLine在綠燈區間時該移動的"距離"
		{
			double distanceY=pl.getStartY()-position;
			double distanceX=(double)distanceY/pl.getSlope()+pl.getStartX();
			double moveRS=0;			
			
			//綠燈區段begin一"介"於passLine在該路口的x即停止此for loop
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
			begin=beginIn;
			end=endIn;
		}
		public double getBegin() {return begin;}
		public double getEnd() {return end;}
	}
	
	//PassLine
	public class PassLine
	{
		double startX;
		double startY;
		double endX;
		double endY;
		
		public PassLine(double x,double y)
		{
			startX=x;
			startY=y;
			if(slope>=0)
			{
				endX=x+verticalDistance/slope;
				endY=y-verticalDistance;
			}
			else if(slope<0)
			{
				endX=x-verticalDistance/slope;
				endY=y+verticalDistance;
			}
			
			//endX=x+gwidth*0.6*Math.cos(Math.atan(slope));
			//endY=y-gwidth*0.6*Math.sin(Math.atan(slope));
		}	
		
		public void addPosiStartX(double xIn) //移動passLine的位置(x方向)
		{
			startX+=xIn ;
			endX+=xIn;
		}		
		public void drawLine(Graphics2D g) ////畫passLine
		{
			Line2D line = new Line2D.Double();
			if(slope>=0)
				line.setLine(startX-20/slope, startY+20, endX+20/slope, endY-20);
			else
				line.setLine(startX+20/slope, startY-20, endX-20/slope, endY+20);
			g.draw(line);
		} 	
		public double getStartX(){return startX;}
		public double getStartY(){return startY;}
		public double getSlope(){return slope;}
		public void setSlope(double slopeIn){ slope=slopeIn; }//設定passLine斜率		
	}
}


 
