from tkinter import *
import time
#reminder to pip install beautifulsoup4
#pip install lxml
#pip install requests
from bs4 import BeautifulSoup
import requests
import threading

weatherDict = {
    "Clear":"sunny.jpg","Sunny":"sunny.jpg","Partly Cloudy":"partlyCloudy.jpg","Mostly Sunny":"partlyCloudy.jpg"
}

def update_time():
    time_string = time.strftime("%H:%M:%S")
    hr = int(time_string[0]+time_string[1])
    if hr in range(13,25):
        time_string = str(hr-12)+time_string[2:]
    if time_string[0] == "0":
        time_string = time_string[1:]
    clock.config(text = time_string)
    clock.after(200, update_time)

def update_date():
    date_string = time.strftime("%m/%d %Y")
    date.config(text = date_string)
    date.after(1000, update_date)

def update_temp():
    # source = requests.get("https://weather.com/weather/today/l/5c51b83496c9dacc59b73678fcb814dcf384e3499ea75ae248dc64265d377482").text
    # soup = BeautifulSoup(source,"lxml")
    # temp_string = soup.find("div", class_="today_nowcard-temp").text
    # temp_string = "Temperature: "+temp_string+"F"
    temp_string = "Temperature: 84°F"
    temp.config(text=temp_string)
    tempTimer = threading.Timer(30*60,update_temp)
    tempTimer.start()

def update_forcast():
    # source2 = requests.get(
    #     "https://weather.com/weather/tenday/l/Chandler+AZ?canonicalCityId=ec223dad7a987c276ed3c85eda6bcc97b9a8a4e47d15045bd2033217ed64783c").text
    # soup2 = BeautifulSoup(source2, "lxml")
    # forcast_string = soup2.find("td", class_="description").text
    # forcast_string = "Todays forcast: "+forcast_string
    #weather_symbol("Sunny")
    forcast_string = "Todays forcast: sunny"
    forcast.config(text=forcast_string)
    forcastTimer = threading.Timer(30*60,update_forcast)
    forcastTimer.start()

# def weather_symbol(forcast):
#     # weatherImg = PhotoImage(file=weatherDict[forcast])
#     weatherImg = "rain.jpg"
#     weatherCan.config(20,20,image=weatherImg)


def destroy():
    root.destroy()

root = Tk()
root.attributes("-fullscreen", True)
root.configure(background='black')

clock = Label(root,fg="white", bg="black", font=("Helvetica", 128))
clock.grid(row=0, column=0)
update_time()

date = Label(root,fg="white",bg="black", font=("Helvetica",44))
date.grid(row=1,column=0)
update_date()

temp = Label(root,fg="white",bg="black", font=("Helvetica",44))
temp.place(x=25,y=800)
update_temp()

forcast = Label(root, fg="white", bg="black", font=("Helvetica",44))
forcast.place(x=25,y=700)
update_forcast()

weatherImg = PhotoImage(file="rain.jpg")
weatherSym = Label(root,image=weatherImg)
weatherSym.pack()

endProgram = Button(root,text="     ",bg="white",command=destroy)
endProgram.place(x=500,y=30)

root.mainloop()


# ##test for number of data pulls###
# global i
# temp_string = "temperature"+str(i)
# i+=1
# temp.config(text=temp_string)
# tempTimer = threading.Timer(2,update_temp)
# tempTimer.start()