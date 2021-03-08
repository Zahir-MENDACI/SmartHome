#include <SoftwareSerial.h>
#include <Servo.h>
Servo monServomoteur;
char command;
String string;
boolean Led_On1 = false;
boolean Led_On2 = false;
boolean Led_On3 = false;
boolean Vent_On = false;
boolean Volet_On = false;
boolean Door_On = false;

#define Led1 12
#define Led2 13
#define Led3 30

SoftwareSerial Bluetooth (14, 15);

void setup()
  {
    Serial.begin(9600);
    pinMode(Led1, OUTPUT);
    pinMode(Led2, OUTPUT);
    pinMode(Led3, OUTPUT);
    monServomoteur.attach(9);
  }

  void loop()
  {    
    if (Serial.available() > 0) 
    {string = "";}
    
    while(Serial.available() > 0)
    {
      command = ((byte)Serial.read());
      
      if(command == ':')
      {
        break;
      }
      
      else
      {
        string += command;
      }
      
      delay(1);
    }

    sendData("azertyu");

    sendData2("adfkdfj");
    
    if(string == "TO1")
    {
        LedOn1();
        Led_On1 = true;
    }
    
    if(string =="TF1")
    {
        LedOff1();
        Led_On1 = false;
        Serial.println(string);
    }
    if(string == "TO2")
    {
        LedOn2();
        Led_On2 = true;
    }
    
    if(string =="TF2")
    {
        LedOff2();
        Led_On2 = false;
        Serial.println(string);
    }

        if(string == "TO3")
    {
        LedOn3();
        Led_On3 = true;
    }
    
    if(string =="TF3")
    {
        LedOff3();
        Led_On3 = false;
        Serial.println(string);
    }

     if(string == "VentOn")
    {
        VentOn();
        Vent_On = true;
    }
    
    if(string =="VentOff")
    {
        VentOff();
        Vent_On = false;
        Serial.println(string);
    }

    
    if(string == "VoletOn")
    {
        VoletOn();
        Volet_On = true;
    }
    
    if(string =="VoletOff")
    {
        VoletOff();
        Volet_On = false;
        Serial.println(string);
    }

     if(string == "DoorOn")
    {
        DoorOn();
        Door_On = true;
    }
    
    if(string =="DoorOff")
    {
        DoorOff();
        Door_On = false;
        Serial.println(string);
    }
    
    if ((string.toInt()>=0)&&(string.toInt()<=255))
    {
      if (Led_On1==true)
      {
        analogWrite(Led1, string.toInt());
        Serial.println(string);
        delay(10);
      }
      if (Led_On2==true)
      {
        analogWrite(Led2, string.toInt());
        Serial.println(string);
        delay(10);
      }
      if (Led_On3==true)
      {
        analogWrite(Led3, string.toInt());
        Serial.println(string);
        delay(10);
      }
    }
 }
 
void LedOn1()
   {
      analogWrite(Led1, 255);
      delay(10);
    }
 
 void LedOff1()
 {
      analogWrite(Led1, 0);
      delay(10);
 }
 void LedOn2()
   {
      analogWrite(Led2, 255);
      delay(10);
    }
 
 void LedOff2()
 {
      analogWrite(Led2, 0);
      delay(10);
 }
  void LedOn3()
   {
      analogWrite(Led3, 255);
      delay(10);
    }
 
 void LedOff3()
 {
      analogWrite(Led3, 0);
      delay(10);
 }

void VentOn()
   {
      monServomoteur.write(180);
      delay(15);
    }

void VentOff()
   {
      monServomoteur.write(0);
      delay(15);
    }
//A modifier
void VoletOn()
   {
      monServomoteur.write(180);
      delay(15);
    }
//A modifier
void VoletOff()
   {
      monServomoteur.write(0);
      delay(15);
    }
//A modifier
void DoorOn()
   {
      monServomoteur.write(180);
      delay(15);
   }
//A modifier
void DoorOff()
   {
      monServomoteur.write(0);
      delay(15);
   }


   
 
 void sendData(String value) {
  String string = String(value); // Convert to string and send
  Serial.print(' ' + string + '#'); // With the character "#" the app understands the sending is finished
}

 void sendData2(String value) {
  String string = String(value); // Convert to string and send
  Serial.print(' ' + string + '$'); // With the character "#" the app understands the sending is finished
}
 

    
