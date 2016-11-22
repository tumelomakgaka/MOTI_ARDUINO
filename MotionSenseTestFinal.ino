//Complete version of code with all features present. Not used in testing session since it would take too long for students to complete

#include <toneAC.h>
#include <IRremote.h>
#include <LiquidCrystal.h>
bool armed=false;
int RECV_PIN = 6;
int pirPin = 11;  
int sensorValue=0;
int backlight=7;
String prevResult="";
String pin="1993";
String translatedResult="";
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
  //while(10000); // Stop (so it doesn't repeat forever driving you crazy--you're welcome).
Serial.begin(9600);
  irrecv.enableIRIn(); // Start the receiver
   pinMode(pirPin, INPUT);
  digitalWrite(pirPin, LOW);
Serial.println("******************MOTI ALARM******************");
  Serial.println("Press Mode for more options"); 
  
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

void Arm(int time_to_arm)
{
  Serial.println(String(time_to_arm)+" seconds to arm...");
  delay(time_to_arm*1000);
  Serial.println("Armed");
  armed=true;
}

void Disarm(String pin)
{
      sensorValue=0;
      buzzerOff();
      Serial.println("Disarmed");
}


void showOptions()
{
  Serial.println("1- Arm");
  Serial.println("2- Change Pin");
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("1- Arm");
  lcd.setCursor(0,1);
  lcd.print("2- Change Pin");
  //alarmStatus="showOptions";
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
    else if (codeValue.equals("16724175"))
    {
      output="1";
    }
    else if (codeValue.equals("16718055"))
    {
      output="2";
    }
    else if (codeValue.equals("16743045"))
    {
      output="3";
    }
    else if (codeValue.equals("16716015"))
    {
      output="4";
    }
    else if (codeValue.equals("16726215"))
    {
      output="5";
    }
    else if (codeValue.equals("16734885"))
    {
      output="6";
    }
    else if (codeValue.equals("16728765"))
    {
      output="7";
    }
    else if (codeValue.equals("16730805"))
    {
      output="8";
    }
    else if (codeValue.equals("16732845"))
    {
      output="9";
    }
    return output;
}

String inputloop()
{
  translatedResult="";
  if (irrecv.decode(&results)) //results from remote
  {   
     String res=String(results.value);
     translatedResult=translateCode(res);    
     irrecv.resume(); // Receive the next value
  }
  return translatedResult;
}

void loop()
{
  translatedResult=inputloop();
  if (translatedResult.equals("mode"))
  {
    showOptions();
    prevResult="mode";
  }
  if((prevResult.equals("mode"))&&(translatedResult.equals("1"))) //Motion sensed
  {
     Serial.println("Armed");
     lcd.clear();
     lcd.setCursor(0,0);
     lcd.print("Armed");
     String checkPin="";
     do
     {
       sensorValue=digitalRead(pirPin);
       armed=true;      
       translatedResult=inputloop();
       if(sensorValue==1)
       {
           ringBuzzer();
           String input="";
           do
           {
              input=inputloop();
              if(!(input.equals("play")))
              {
                checkPin=checkPin+input;
                lcd.setCursor(0,1);
                lcd.print(checkPin);
              }
           }
           while(!(input.equals("play"))); 
           if(!((checkPin.equals(pin))))
           {
              do{  
                  lcd.clear();
                  lcd.setCursor(0,0);
                  lcd.print("Incorrect! Try again"); 
                  checkPin="";
                  do
                  {
                     input=inputloop();
                     if(!(input.equals("play")))
                     {
                       checkPin=checkPin+input;
                       lcd.setCursor(0,1);
                       lcd.print(checkPin);
                     }
                  }
                  while(!(input.equals("play")));
              }while(!((checkPin.equals(pin)))); 
           }
       }        
     }
     while(!((checkPin.equals(pin)))); //Motion sensed     
     lcd.clear();
     lcd.setCursor(0,0);
     lcd.print("Disarmed!"); 
     buzzerOff();
  }
  
  if((prevResult.equals("mode"))&&(translatedResult.equals("2"))) //Motion sensed
  {
     Serial.println("Enter new pin:");
     lcd.clear();
     lcd.setCursor(0,0);
     lcd.print("Enter new pin:");
     pin="";
     String input="";
     do
     {
        input=inputloop();
        if(!(input.equals("play")))
        {
          pin=pin+input;
          lcd.setCursor(0,1);
          lcd.print(pin);
        }
     }
     while(!(input.equals("play"))); 
     lcd.clear();
     lcd.setCursor(0,0);
     lcd.print("Done!");
     translatedResult="";
  }
  translatedResult="";
}
