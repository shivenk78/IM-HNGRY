# IM-HNGRY
 Get to food, faster!

 ### Overview & Project Goal
 For this project, the idea is to solve the age-old college student problem of finding something to eat after a party, study session, or just when you're HNGRY. The aim is to have an application (for now, mostly backend, possibly using Ktor or similar) to fetch the restaurants closest to you which are currently open, in order of least time to eat. That is, combining factors like average wait times, type of restaurant, and distance to you, the app will try to determine which is the fastest food you can get when you are really just HNGRY.

### Current Status
Right now, the app fetches the closest restaurants from the Google Places API and outputs key information about each one to the console.

### Next Steps
I would like to take this from a basic console application and make it into either a website or cross-platform webapp. I also want to add DoorDash/GrubHub/etc. integration to allow food to be ordered, and be able to sort by wait times. Wait times are not available on the Google Places API yet, but there is a Python library to do so, so I may use that in the future.
