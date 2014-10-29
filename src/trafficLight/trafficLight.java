
//�ثe�u��Yint �n��graphics2D�h��
//�s�W�����i��JtrafficLight����T

package trafficLight;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; //���Jjava.awt���Ҧ�����M��k�C�]�t�Ω�ЫبϥΪ̤����Mø�s�ϧιϹ����������O�C
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //���Jjava.awt.event���Ҧ�����M��k�C���ѳB�z�� AWT �����Ĳ�o���U���O�ƥ󪺤����M���O�C
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame; //���Jjavax.swing���Ҧ�����M��k�C���Ѥ@�աu���q�šv��JAVA����A�ɶq���o�Ǥ���b�Ҧ����O�W���u�@�覡���ۦP�C
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class trafficLight extends JFrame implements ActionListener{ //�ŧiLineDraw���O�A�ñN���~��JFrame����C��@ActionListener(�i��","�j�}���P��@)�A�����ާ@JFrame���ƥ�C
 
	int gwidth=450,gheigh=450,gx,gy; //gwidth���e�����e�Fgheigh���e�������Fgx���e����x��m�Fgy���e����y��m�C
	Dimension ScreenSize,FrameSize; //ScreenSize���ù����j�p�FFrameSize����椧�j�p�C
	JLabel jl1,jl2,jlInt1,jlInt2,jlInt3,jlInt1G,jlInt2G,jlInt3G,jlInt1R,jlInt2R,jlInt3R,
	jlInt1Off,jlInt2Off,jlInt3Off,jlInt1P,jlInt2P,jlInt3P; //jl1�����ܨϥΪ̿�J�F�誺���ҡFjl2�����ܨϥΪ̿�J�ƭȩή榡���~�����ҡC
	JTextField jtf,jtfInt1G,jtfInt1R,jtfInt1Off,jtfInt1P,jtfInt2G,jtfInt2R,jtfInt2Off,jtfInt2P,
	jtfInt3G,jtfInt3R,jtfInt3Off,jtfInt3P; //jtf���ѨϥΪ̿�J��r������ϡC
	JButton jb1,jb2,jb3; //jb1���T�{jtf��J�����s�A�ö}�lø�ϡFjb2�����ϥΪ̲M��JFrame�����W�i�H�������F��A�Y�^���}�l���e���Fjb3���ѨϥΪ������{�����\��C
	JPanel jp1,jp2; //jp1���JFrame���_�A���t����jl1�Bjtf�Bjb1�Bjb2�Bjb3�Fjp2���JFrame���n�A���t����jl2�C
	Graphics g; //g��ø�s�h��Ϊ��e���C
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
		jl1 = new JLabel("�п�J�樮�t�סG"); 
		jl2 = new JLabel(" ");
		jlInt1 = new JLabel("���f1.");
		jlInt2 = new JLabel("���f2.");
		jlInt3 = new JLabel("���f3.");
		jlInt1G = new JLabel("��O");
		jlInt2G = new JLabel("��O");
		jlInt3G = new JLabel("��O");
		jlInt1R = new JLabel("���O");
		jlInt2R = new JLabel("���O");
		jlInt3R = new JLabel("���O");
		jlInt1Off = new JLabel("offset");
		jlInt2Off = new JLabel("offset");
		jlInt3Off = new JLabel("offset");
		jlInt1P = new JLabel("��m");
		jlInt2P = new JLabel("��m");
		jlInt3P = new JLabel("��m");
		jl2.setForeground(Color.red); //�]�wjl2���Ҫ��󤧤�r�C�⬰����C
		jtf = new JTextField(10); //�إ�jtf��JTextField��r�������A�ó]�w���׬�10�C
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
		jb1 = new JButton("�}�l�e�I"); //�إ�jb1��JButton���s����A�ó]�w��r���u�}�l�e�I�v�C
		jb2 = new JButton("�M��"); //�إ�jb2��JButton���s����A�ó]�w��r���u�M���v�C
		jb3 = new JButton("����"); //�إ�jb3��JButton���s����A�ó]�w��r���u�����v�C
		jp1 = new JPanel(); //�إ�jp1��JPanel�e������C
		jp2 = new JPanel(); //�إ�jp2��JPanel�e������C
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
		jp1.add(jl1); //�Njl1���ҥ[�Jjp1�e���C
		jp1.add(jtf); //�Njtf��r����[�Jjp1�e���C
		jp1.add(jb1); //�Njb1���s�[�Jjp1�e���C
		jp1.add(jb2); //�Njb2���s�[�Jjp1�e���C
		jp1.add(jb3); //�Njb3���s�[�Jjp1�e���C
		//jp1.add(jtf2,BorderLayout.SOUTH);
		add(jp1,BorderLayout.CENTER); //�Njp1�[�JJFrame�A�ó]�w��BorderLayout�ƪ���NORTH(�_��)��m�C
		jp2.add(jl2); //�Njl2���ҥ[�Jjp2�e���C
		add(jp2,BorderLayout.SOUTH); //�Njp2�[�JJFrame�A�ó]�w��BorderLayout�ƪ���SOUTH(�n��)��m�C
		jb1.addActionListener(this); //�إ�jb1�bJFrame(LineDraw���O)��ActionListener��@�����C
		jb2.addActionListener(this); //�إ�jb2�bJFrame(LineDraw���O)��ActionListener��@�����C
		jb3.addActionListener(this); //�إ�jb3�bJFrame(LineDraw���O)��ActionListener��@�����C
 
		//---jtf��KeyListener��@����---
			jtf.addKeyListener( //�إ�jtf��KeyListener��@�����C
				new KeyListener(){ //�إ�KeyListener
					public void keyPressed(KeyEvent e) { //����L���U�Y����ɨϥΦ���k�C
						if(e.getKeyCode()==10) //�p�G���UEnter�C
						{
							JButton2_Action(); //����JButton2_Action��k�C
						}
					}
 
 
					public void keyTyped(KeyEvent e) {} //����L��J�Y����ɨϥΦ���k�A�o�̨S���Ψ�C
 
					public void keyReleased(KeyEvent e) {} //����L��}�Y����ɨϥΦ���k�A�o�̨S���Ψ�C
 
				}
			);
		//-------------END-------------
 
		setSize(600,600); //�]�wJFrame�������j�p�C
		setResizable(false); //����JFrame����ܵ����j�p���\��C
		ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); //���oJFrame�������ù�����m�I�C
		FrameSize = getSize(); //���oJFrame�����j�p�C
		setLocation((ScreenSize.width-FrameSize.width)/2,(ScreenSize.height-FrameSize.height)/2); //�NJFrame�����w�]��m�\�b�ù������C
		setTitle("��q���x��i�W��"); //�]�wJFrame�������D�C
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�]�wJFrame���U�e�e�����s�i�H�����{���C
		setVisible(true); //���JFrame�C
 
		//-----------�إߵe��-----------
			gx=(FrameSize.width-gwidth)/2; //�]�w�e��x�_�l�I���JFrame���e�״�e���e�׫�A���G����m�C
			gy=(FrameSize.height-gheigh)/2; //�]�w�e��y�_�l�I���JFrame��氪�״�e�����׫�A���G����m�C
			g = getGraphics(); //�إ�g�e����k�C
			g.setClip(gx-40,gy+80, gwidth+20, gheigh); //�]�wg�e������m����檺�������C
			g.setColor(Color.BLACK); //�]�mg�e����ø���C�⬰�¦�C
			x0=gx+20;//x0���y�Э��I��x��m
			y0=gy+gwidth-20;//y0���y�Э��I��y��m
		//-------------END-------------
	}
 
	public static void main(String[] args) { //�D�{��
		new trafficLight(); //�إ�trafficLight����C
	}
 
	public void actionPerformed(ActionEvent e) { //ActionListener��actionPerformed��k�C
		if(e.getSource()==jb1) //�p�G���Ujb1�C(�}�l�e)
		{
			JButton2_Action(); //����JButton2_Action��k�C
		}
		else if(e.getSource()==jb2) //�p�G���Ujb2�C(�M��)
		{
			update(g); //����g�e�����e�C
			jl2.setText(" "); //�M��jl2�C
			jtf.setText(""); //�M��jtf�C
			count=0;	
			g.setColor(Color.BLACK);
		}
		else if(e.getSource()==jb3) //�p�G���Ujb3�C(����)
		{
			System.exit(0); //�������{���C
		}
	}
	
	public void JButton2_Action(){ //���U"�e��"��άO�bjtf�W��Enter�ᤧ�ʧ@
		update(g); //����g�e�����e�C
		slope=Integer.parseInt(jtf.getText())/10; //�^��jtf��J�i�Ӫ��ײv
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
 				
		Graphics2D g2=(Graphics2D)g;//�Ng downcast��Graphics2D����(�]��Graphics2D����వ��hø�ϥ\��)
		
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
				
			//��J�U���f���Ѽ�
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
			
			//�p��n�e�h�ֶg��
			ArrayList<Double> periods=new ArrayList<Double>();		
			for(Intersection item:intersections)
				periods.add(item.getPeriod());			
			double shortestPeriod=Collections.min(periods);						
			int totalTime=gwidth;
			numT=totalTime/(int)(shortestPeriod);//�e���e�װ��H�̤p�P��=�̤֭n�e�h�ֶg��				
								
		    //���VpassLine���p��
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
		
			//�f�VpassLine���p��
			passLines.clear();
			count=0;
			initialIntersec = intersections.get(2); 
			firstInterSectionFi1 = intersections.get(2).getFi1();
			
			PassLine passLine2 = new PassLine(initialIntersec.getGreenIntervalList().get(0).getBegin(), initialIntersec.getPosition());	
			passLine2.setSlope(-slope);
			crossRed(passLine2);
			
			//�e�X�Ҧ�passLine(���V��f�V)
			g2.setColor(Color.orange);
			for(PassLine item:passLines)
			{
				item.drawLine(g2);
			}				
	}
	
	//passLine�����O�Ϯɰ���C�\��:�NpassLine�V�k���X���O��
	public void crossRed(PassLine passLine)
	{
		count++;
		int count2=0;
		double movement=-1;//movement�YpassLine���U���f(Intersection)���O�q�ɸӲ��ʪ��Z��
		while(!(movement==0) && count2<numT*4)
		{
			count2++;
			//��X�����Ҧ����f�ApassLine�Ӳ��ʪ��Z��
			ArrayList<Double> movements = new ArrayList<Double>();	
			for(Intersection item:intersections)
			{
				double movementForEachIntersection=item.passInRed(passLine);				
				movements.add(movementForEachIntersection); 		
			}
			movement=Collections.max(movements);//����O�ϳ̤j�I�_����
			passLine.addPosiStartX(movement);//�NpassLine�V�k���X���O��
		}
	
		if(movement==0)//�p�G���Q���X���O�ϡA����crossGreen()
		{
			movement=-1;
			PassLine passLineCopy =new PassLine(passLine.getStartX(),passLine.getStartY());						
			passLines.add(passLineCopy);//�[�J�����Q���X���O�Ϫ�passLine
			crossGreen(passLine);	
		}
	}
	
	//passLine����O�Ϯɰ���C�\��:�NpassLine�V�k���X��O��
	public void crossGreen(PassLine passLineR)
	{
		count++;
		double movement=0;//movement�YpassLine���U���f(Intersection)��O�q�ɸӲ��ʪ��Z��
		
		//��X�����Ҧ����f�ApassLine�Ӳ��ʪ��Z��
		ArrayList<Double> movements = new ArrayList<Double>();	
		for(Intersection item:intersections)
		{
			double movementForEachIntersection=item.passInGreen(passLineR);				
			movements.add(movementForEachIntersection); 		
		}
		
		movement=Collections.min(movements);//���O�ϳ̵u�I�_����
		passLineR.addPosiStartX(movement);//�NpassLine�V�k���X��O��	
	
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
	//���f
	public class Intersection
	{
		double fi1;//��O�ɶ�����
		double fi2;//���O�ɶ�����
		double period;//�g��=��O+���O�ɶ�����
		double position;//���f��m(y�b��m)
		double offset;//(�ۮt)
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
			
			if(offset>=period)//�T�Ooffset�p��g��
			offset=offset % period;		
			
			//�p��(�õe�X)�U���f�����B��O�϶��A���x�s�b�U���f����ArrayLIst:greenInterval��
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
		
		public void addGreenInterval(GreenInterval greenIntervalIn) //�[�J��O�Ϭq
		{
			greenIntervals.add(greenIntervalIn);
		}	
		public ArrayList<GreenInterval> getGreenIntervalList() //��������f���Ҧ���O�Ϭq��T
		{
			return greenIntervals;
		}			
		public double passInRed(PassLine pl)//�^��passLine�b���O�϶��ɸӲ��ʪ�"�Z��"
		{
			double distanceY=pl.getStartY()-position;
			double distanceX=(double)distanceY/pl.getSlope()+pl.getStartX();
			double moveLS=0;			
			
			for( GreenInterval item:greenIntervals)
			{
				//��O�Ϭqbegin�@"�j"��passLine�b�Ӹ��f��x�Y���for loop
				if(item.getBegin() >= distanceX) 
				{
					moveLS=item.getBegin()-distanceX;
					break;
				}
				
				//��O�Ϭqbegin�@"��"��passLine�b�Ӹ��f��x�Y���for loop
				else if(item.getBegin() <= distanceX && distanceX <item.getEnd())
				{
					moveLS=0;
					break;
				}				
			}
			
			return moveLS; 
		}
		
		public double passInGreen(PassLine pl)//�^��passLine�b��O�϶��ɸӲ��ʪ�"�Z��"
		{
			double distanceY=pl.getStartY()-position;
			double distanceX=(double)distanceY/pl.getSlope()+pl.getStartX();
			double moveRS=0;			
			
			//��O�Ϭqbegin�@"��"��passLine�b�Ӹ��f��x�Y���for loop
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
		
		public void addPosiStartX(double xIn) //����passLine����m(x��V)
		{
			startX+=xIn ;
			endX+=xIn;
		}		
		public void drawLine(Graphics2D g) ////�epassLine
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
		public void setSlope(double slopeIn){ slope=slopeIn; }//�]�wpassLine�ײv		
	}
}


 
