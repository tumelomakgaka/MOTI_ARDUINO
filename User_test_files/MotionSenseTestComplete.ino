// Completed version of code given to students during testing session

#include <toneAC.h>
#include <IRremote.h>
#include <LiquidCrystal.h>
bool armed=false;
int RECV_PIN = 6;
int pirPin = 11;  
int sensorValue=0;
int backlight=7;
String Clicked="";
IRrecv irrecv(RECV_PIN);
decode_results results;
LiquidCrystal lcd(12,9,8,5,4,3,2);


void setup()
{
  pinMode(backlight,OUTPUT);
  analogWrite(backlight,130);
  lcd.begin(16,2);
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("MOTI ALARM");
  lcd.setCursor(0,1);
  lcd.print("Press Mode for more options");
  
  Serial.begin(9600);
  irrecv.enableIRIn(); // Start the receiver
   pinMode(pirPin, INPUT);
  digitalWrite(pirPin, LOW);  
}

void ringBuzzer()
{
    for (unsigned long freq = 64; freq <= 1000; freq += 10) 
    {  
        toneAC(freq); // Play the frequency (125 Hz to 15 kHz in 10 Hz steps).
    }
}

void buzzerOff()
{
  toneAC();
}



String translateCode(String codeValue)
{
  String output="";
    if (codeValue.equals("16753245"))
    {
      output="on";
    }
    else if (codeValue.equals("16736925"))
    {
      output="mode";
    }
    else if (codeValue.equals("16720605"))
    {
      output="play";
    }
    return output;
}

String inputloop()
{
  {   
     String res=String(results.value);
     Clicked=translateCode(res);    
     irrecv.resume(); // Receive the next value
  }
  return Clicked;
}


void loop()
{
  Clicked=inputloop();
  if (Clicked.equals("on"))
  {    
    bool play = false;
    lcd.clear();
    lcd.setCursor(0,0);
    lcd.print("Armed");
  
    do 
    {
      sensorValue=digitalRead(pirPin);
      if (sensorValue== digitalRead(pirPin))
      {
        ringBuzzer();
      }
      Clicked = inputloop();
      if (Clicked.equals("play"))
      {
        play = true;
      }
    }while (play == false);
  }
  
  
       
}
