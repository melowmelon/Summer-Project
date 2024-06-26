import speech_recognition as sr
import pyttsx3
import os

OPENAI_KEY = 'sk-proj-TPmiA6Wz37M2ZG4YqB5LT3BlbkFJDQXjZM5Do5HbJFTUxJD7'

import openai
from openai import AzureOpenAI
openai.api_key = OPENAI_KEY

def SpeakText(command):
    engine = pytts3.init()
    engine.say(command)
    engine.runAndWait()

r = sr.Recognizer()

def record_text():
    while(1):
        try:
            with sr.Microphone() as source2:
                r.adjust_for_ambient_noise(source2, duration=0.2)
                print("I'm Listening...")
                
                audio2 = r.listen(source2)
                
                MyText = r.recognize_google(audio2)
                
                return MyText
            
        except sr.RequestError as e:
            print("could not request results; {}".format(e))
        except sr.UnknownValueError:
            print("unknown error occured")
        
    return

def send_to_chatGPT(messages, model="gpt-3.5-turbo"):
    response = openai.ChatCompletion.create(
        model = model,
        messages = messages,
        max_tokens = 100,
        n = 1,
        stop = None,
        temperature = 0.5,
    )
    
    message = response.choices[0].message.content
    messages.append(response.choices[0].message)
    return message


messages = []
while(1):
    text = record_text()
    messages.append({"role": "user", "content": text})
    response = send_to_chatGPT(messages)
    SpeakText(response)
    
    print(response)