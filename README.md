# IM-HNGRY
 Get to food, faster!

 ### Overview & Project Goal
 For this project, the idea is to solve the age-old college student problem of finding something to eat after a party, study session, or just when you're HNGRY. The aim is to have an application (for now, mostly backend, possibly using Ktor or similar) to fetch the restaurants closest to you which are currently open, in order of least time to eat. That is, combining factors like average wait times, type of restaurant, and distance to you, the app will try to determine which is the fastest food you can get when you are really just HNGRY.

### Current Status
Right now, the app fetches the closest restaurants from the Google Places API and outputs key information about each one to the console.

### Functionality
When the app runs, it asks the user for a latitude and longitude to search near using console input. If the user doesn't enter anything, the app uses the location of the Illini Union by default. 

### Next Steps
I would like to take this from a basic console application and make it into either a website or cross-platform webapp. I also want to add DoorDash/GrubHub/etc. integration to allow food to be ordered.

---

#### Challenges
- Wait times are not available on the Google Places API yet, so I only sorted by distance.
- Not all restaurants returned have all the necessary fields for sorting them, so I will need to find a way to get around the exceptions.
- Since the returned API request has lots of nested data, I had to create several nested data classes to deserialize everything with Gson. Luckily, Kotlin makes it easy - this would have been a total nightmare in Java.

##### Sample Output for Illini Union
![Image Not Available](https://i.imgur.com/qidnz3O.png "Console Output")