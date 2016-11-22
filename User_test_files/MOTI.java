//Moti alarm test version, armed by pressing on button and disarmed by pressing off button
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.text.*;

public class MOTI extends JFrame implements ActionListener  
{
	JPanel buttons = new JPanel();
	JTextPane lcd = new JTextPane();
	JScrollPane pane = new JScrollPane(lcd);
   String Clicked="";
   int sensorValue=0;
   public String Dialed="";
	boolean buzzer=false;	 
   
   public void Arm()
   {
      lcd.setText("Armed");
      buzzer=true;
   }
   
   public void Disarm()
   {
      sensorValue=0;
      Clicked="";
      lcd.setText("Disarmed");
   }
   
   
   
   
   
   public void mainLoop()
   {
      if (Clicked.equals("on"))
      {   
         lcd.setText("Armed");
         Thread t1 = new Thread(new Runnable() {
         public void run()
         {
             do
             {
                if(sensorValue==1)
                {
                   lcd.setText("MOTION");
                   
                }
             }
             while(!(Clicked.equals("Play")));
             Disarm(); 
         } 
        });
        t1.start();
      }
      
   } 
   
   public String getClicked()
   {
      return Clicked;
   }
   
   public void setClicked(String c)
   {
      Clicked=c;
   }
   
   
   public MOTI()
	{
		super("MOTI Alarm");
		setSize(500,400);  
		lcd.setText("");
      lcd.setText("");
      lcd.setText("");
      lcd.setText("MOTI ALARM");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		    Container Celli = getContentPane();
		
		buttons.setLayout(new GridLayout(5,3));
		buttons.setPreferredSize(new Dimension(200, 50));
      Celli.setLayout(new BorderLayout());
		StyledDocument doc = lcd.getStyledDocument();

      //BUTTONS AND TEXT PANE ARE CREATED
		JButton on=new JButton("on");
		on.addActionListener(this);
		on.setPreferredSize(new Dimension(10, 10));
		try 
      {
         Image img = ImageIO.read(getClass().getResource("on.png"));
         on.setIcon(new ImageIcon(img));
      } 
      catch (IOException ex) {
      }
      buttons.add(on);
		
		JButton Mode=new JButton("Mode");
		Mode.addActionListener(this);
		Mode.setPreferredSize(new Dimension(10, 10));
		Mode.setBackground(Color.gray);
		Mode.setForeground(Color.WHITE);
      buttons.add(Mode);
		JButton Play=new JButton("Play");
      try 
      {
         Image img = ImageIO.read(getClass().getResource("play.jpg"));
         Play.setIcon(new ImageIcon(img));
      } 
      catch (IOException ex) {
      }
      Play.addActionListener(this);
		Play.setPreferredSize(new Dimension(10, 10));
		buttons.add(Play);
		
      JButton PIR=new JButton("");
      PIR.addMouseListener(new MouseAdapter() 
      {
            public void mouseEntered(MouseEvent me) {
                if (Clicked.equals("on"))
                {
                  sensorValue=1;
                }
            }
      }
      );
      try 
      {
         Image img = ImageIO.read(getClass().getResource("pir.jpg"));
         PIR.setIcon(new ImageIcon(img));
      } 
      catch (IOException ex) {
      }
		JButton One=new JButton("1");
		One.addActionListener(this);
      One.setBackground(Color.gray);
		One.setForeground(Color.WHITE);
      buttons.add(One);
		    
		JButton Two=new JButton("2");
		Two.addActionListener(this);
      Two.setBackground(Color.gray);
		Two.setForeground(Color.WHITE);
      buttons.add(Two);
		
		JButton Three=new JButton("3");
		Three.addActionListener(this);
      Three.setBackground(Color.gray);
		Three.setForeground(Color.WHITE);
      buttons.add(Three);
		
		JButton Four=new JButton("4");
		Four.addActionListener(this);
		Four.setBackground(Color.gray);
      Four.setForeground(Color.WHITE);
      buttons.add(Four);
		
		JButton Five=new JButton("5");
		Five.addActionListener(this);
		Five.setBackground(Color.gray);
		Five.setForeground(Color.WHITE);
      buttons.add(Five);
		
		JButton Six=new JButton("6");
		Six.addActionListener(this);
		Six.setBackground(Color.gray);
      Six.setForeground(Color.WHITE);
      buttons.add(Six);
		
		JButton Seven=new JButton("7");
		Seven.addActionListener(this);
		Seven.setBackground(Color.gray);
      Seven.setForeground(Color.WHITE);
      buttons.add(Seven);
		
		JButton Eight=new JButton("8");
		Eight.addActionListener(this);
		Eight.setBackground(Color.gray);
      Eight.setForeground(Color.WHITE);
      buttons.add(Eight);
		
		JButton Nine=new JButton("9");
		Nine.addActionListener(this);
		Nine.setBackground(Color.gray);
      Nine.setForeground(Color.WHITE);
      buttons.add(Nine);
		
		
		lcd.setText("");
		lcd.setEditable(false);
		
		//ELEMENTS ARE ADDED TO BORDER LAYOUT IN THEIR RESPECTIVE POSITIONS
		add(buttons, BorderLayout.WEST);
		add(pane,BorderLayout.NORTH);
		add(PIR,BorderLayout.EAST);
	}
	//EVENTS OF THE USER PRESSING BUTTONS ON THE GRID ARE HANDLED
	public void actionPerformed(ActionEvent e)
 		{	
			String buttonString = e.getActionCommand();
			String txt=lcd.getText();
			lcd.setText("");
         // NUMBERS ARE ENTERED
			if(buttonString.equals("1"))
			{
				if( txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"1";
            setClicked("1");
				lcd.setText(Dialed);
				StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("2"))
			{
				if (txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"2";
				lcd.setText(Dialed);
				setClicked("2");
            StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("3"))
			{
				if( txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"3";
				lcd.setText(Dialed);
            setClicked("3");
				StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("4"))
			{
				if( txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"4";
				lcd.setText(Dialed);
            setClicked("4");
				StyledDocument doc = lcd.getStyledDocument();
			}					
			if(buttonString.equals("5"))
			{
				if( txt=="null")
					lcd.setText("");                        
				Dialed=Dialed+"5";
				lcd.setText(Dialed);
            setClicked("5");
				StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("6"))
			{
				if( txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"6";
				lcd.setText(Dialed);
            setClicked("6");
				StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("7"))
			{
				if( txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"7";
				lcd.setText(Dialed);
            setClicked("7");
				StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("8"))
			{
				if (txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"8";
				lcd.setText(Dialed);
            setClicked("8");
				StyledDocument doc = lcd.getStyledDocument();
			}				
			if(buttonString.equals("9"))
			{
				if(txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"9";
				lcd.setText(Dialed);
            setClicked("9");
				StyledDocument doc = lcd.getStyledDocument();
			}	
			if(buttonString.equals("*"))
			{
				if (txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"*";
				lcd.setText(Dialed);
				StyledDocument doc = lcd.getStyledDocument();
			}	
			if(buttonString.equals("#"))
			{
				if( txt=="null")
					lcd.setText("");
				
				Dialed=Dialed+"#";
				lcd.setText(Dialed);
				StyledDocument doc = lcd.getStyledDocument();
			}
			//CALLS ARE DIALLED ,RECIEVED AND IGNORED
			if(buttonString.equals("on"))
			{
				lcd.setText("");
				//lcd.setText("on");
            setClicked("on");
				StyledDocument doc = lcd.getStyledDocument();
			}
			if(buttonString.equals("Mode"))
			{
				lcd.setText("");
				lcd.setText("Mode");
            setClicked("Mode");
				StyledDocument doc = lcd.getStyledDocument();
			}
			
         if(buttonString.equals("Play"))
			{
				lcd.setText("");
				setClicked("Play");
				StyledDocument doc = lcd.getStyledDocument();
			}
         
			if(buttonString.equals("Ignore"))
			{
				lcd.setText("");
				lcd.setText("*****Call Ignored******");
				StyledDocument doc = lcd.getStyledDocument();
			}
			 mainLoop();	
		}
	// MAIN METHOD EXECUTES ALARM OBJECT AND WAITS FOR EVENTS TO TAKE PLACE 
	public static void main(String[]args)
	{
		MOTI alarm= new MOTI();
		alarm.setVisible(true);
		
	}
}
