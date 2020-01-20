from bs4 import BeautifulSoup
import requests

source = requests.get("https://weather.com/weather/tenday/l/Chandler+AZ?canonicalCityId=ec223dad7a987c276ed3c85eda6bcc97b9a8a4e47d15045bd2033217ed64783c").text
soup = BeautifulSoup(source,"lxml")
forcast = soup.find("td", class_="description").text
print(forcast)
