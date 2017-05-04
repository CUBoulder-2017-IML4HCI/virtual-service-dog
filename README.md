# Virtual Service Dog
The Virtual Service Dog is an Android app aimed at users with debilitating mental illnesses, but may also be useful for anyone who experiences high levels of stress. It relies on data from the built-in camera and accelerometer to determine the user's heart rate and shakiness, then runs it through Java-ML to guess whether or not they may be stressed. It learns from the user's data over time and incorporates that into its predictions.

# First Time Setup
Virtual Service Dog is built using Android Studio, so it is recommended that you build and make modifications to the project using Android Studio as well. If you do not have Android Studio installed, you can get it from here: https://developer.android.com/studio/index.html

After you have Android studio up and running, clone this repository by clicking the "Clone or Download" button or open a terminal and type:
"$ git clone https://github.com/CUBoulder-2017-IML4HCI/virtual-service-dog.git"

After getting the source code for the project, open Android Studio and select "open an existing Android Studio project". You should be all set for building and testing now!(*Note:*Since this app requires the usage of camera lens, you cannot run it on the emulator.)

# Usage
To start monitoring heart rate and accelerometer data, press the “Monitor” button on the home screen. Lightly press your index finger on the LED and camera, wait for about one half to one minute for the app to detect your biometric status(heart rate and shaking).

You can see your heart rate and degree of shaking of your hand when the monitoring is done. You can then click the ‘Stop Recording’ button below to end the monitoring process. If you’re doing okay, you can view your heart rate records as a chart overtime, or you can confirm on the popup telling the service dog you’re doing well to help improve the model.
Otherwise, the app will lead you through a guidance to help you calm down and learn to overcome and control pessimistic emotions in your life.

Users can view their customized data history by clicking “Insight” button on the home screen, as well as activate the guidance on their own terms if they know that they’re stressed by pressing the “Help” button.

# Credit
We used the [Java-ML](http://java-ml.sourceforge.net) library for our machine learning algorithms.

Logo font used is [OpenDyslexic](http://opendyslexic.org/).

# License
This program is released under **GPL V3**. More information is available in the /legal folder.
