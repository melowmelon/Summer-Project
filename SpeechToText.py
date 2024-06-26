import speech_recognition as sr
import pyttsx3
import os


r = sr.Recognizer()

def record_text():
    while(1):
        try:
            with sr.Microphone() as source2:
                r.adjust_for_ambient_noise(source2, duration=0.2)
                
                audio2 = r.listen(source2)
                
                MyText = r.recognize_google(audio2)
                
                return MyText
            
        except sr.RequestError as e:
            print("could not request results; {}".format(e))
        except sr.UnknownValueError:
            print("unknown error occured")
        
    return




def output_text(text):
    f = open("output.txt", "a")
    f.write(text)
    f.write("\n")
    if text == "stop recording":
        f.write("stopping the speech to text program...\n")
        f.write("program ended.\n")
        os.exit(0)
        
    #response = get_ai_response(text)
    #f.write("AI Response:", response)
    #f.write("\n")
    
    f.close()
    
    return


while(1):
    text = record_text()
    output_text(text)
    
    print("wrote text")




