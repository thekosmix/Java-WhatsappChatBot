###Requirement
 - This project will work in linux.
 - Import this project in eclipse (or run from command line with mvn).
 - Install [Chrome webdriver] (https://sites.google.com/a/chromium.org/chromedriver/downloads)
 - You'll have to have whatsapp installed in a phone connected to same wi-fi network as your computer.
 
###Initialization
 - Once the project is imported in eclipse, run WebWhatsapp.java
 - It will open a chrome window with web.whatsapp.com address and will display a QR code.
 - Open whatsapp -> menu -> Whatsapp Web -> + -> Scan QR Code
 - Once your contacts has been loaded, enter 'start' in console and hit enter
 - It will automatically detect the new incoming message, will select them and reply them, appending the message they have sent (you can obviously modify it to process the incoming message and reply accorrdingly).
