const int buttonPin = 2;
int pressed = 0;

int i = 0;
const unsigned long delta = 1000;
unsigned long nextTime = 0;

unsigned long lastDebounceTime = 0;
const unsigned long debounceDelay = 100;

int buttonState;

void buttonPressed() {

  if((millis()-lastDebounceTime) > debounceDelay) {
        pressed++;
        Serial.print(pressed);
        Serial.print("Interrupt");
        lastDebounceTime = millis();
  }
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
  Serial.begin(9600);
}

void loop() {
  int now = millis();
  
  if(now>=nextTime){
    Serial.print(i);
    i++;
    nextTime+=delta;
  }
}
