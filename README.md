# My Personal Project: *Automobile Dealership Manager*
## What will the project do?
This application will serve as a virtual multi-brand automobile dealership which keeps track of vehicles in a 
dealership. It lets you add a vehicle to your dealership and add information about its price, features, model, mileage,
usage, body type, color, etc. You can view a listing of the added vehicles and see which of them have been sold. 
Another possible feature could be displaying a roster of vehicles using pictures or objects with specifications
depending on the type of car. 

## Who will use it?
This application could be useful to automobile dealerships or anyone in general who has interest in cars, or someone 
who wants to start or manage a similar business. 

## Why is this project of interest to you?
I have always been fond of cars and how they function. I think this project would be a great opportunity to learn more 
about them and how a business revolving around them works. 

## User Stories
- As a user, I want to be able to add a car to my dealership.
- As a user, I want to be able to mark a car as sold in my dealership.
- As a user, I want to be able to view a list or number of all cars/sold cars in my dealership.
- As a user, I want to be able to select a car in my dealership and sell it, remove it or get information about it.
- As a user, I want to be able to save my dealership to file.
- As a user, I want to be able to load my dealership from file.

## Instructions for Grader
- You can generate the first required event related to adding Cars to the Dealership by clicking on "Add" button either 
in the "Main Menu" tab or in the "Directory" tab. A dialog box will appear with text fields where you can enter 
information about the car you want to add. Once you have entered the information, you can select "OK" and the car will
be added to the dealership.
- You can generate the first required event related to adding Cars to the Dealership by clicking on the "Sell Car" 
button in the "Directory" tab. You need to select a car from the table displayed above the buttons first. Once 
selected, you can click the "Sell Car" button which will change the status of the selected car from "In Sale" to "Sold" 
if not already sold and remove the image icon related to the car from the warehouse. If the car has already been sold, 
a dialog box will be displayed with an error message.
- You can locate my visual component in the "Warehouse" tab. In this tab, car icons have been placed. Each car icon 
corresponds to a specific car in the dealership with a unique icon for each of the fuel types - plain car icon for
petrol cars, electric car icon for electric cars and car icon with smoke for diesel cars. On removing or selling a car
from the "Directory" tab, the corresponding image icon will be removed.
- You can save the state of my application by clicking on the "Save" button in the "Main Menu" tab. You will again be 
asked whether you want to save the application when you click on the "Quit" button in the "Main Menu" tab or when you
are exiting the application in which case a dialog box will appear which asks you for one last time if you would like 
to save the application before closing it. After the application has been saved, a dialog box appears that confirms 
that the application has been.
- You can reload the state of my application by selecting the "Yes" option when you run the application and the 
graphical user interface appears for the first time. If you select the "No" option, you will be asked to choose a brand 
for a new dealership, which should be strictly alphabetical, and a new dealership with no cars will be instantiated.

## References
- Images
  - https://thenounproject.com/icon/electric-vehicle-215005/
  - https://thenounproject.com/icon/electric-vehicle-215004/
  - https://www.dreamstime.com/diesel-car-concept-automobile-eps-vector-icon-image126441071
  - https://www.cleanpng.com/png-hamburger-button-computer-icons-menu-2094870/download-png.html
  - https://toppng.com/show_download/222962/folder-icon-png-transparent-black-and-white-folder-ico/large
  - https://www.subpng.com/png-y3550x/download.html

## Phase 4: Task 2
Thu Dec 01 23:32:04 PST 2022\
Created a new HONDA dealership


Thu Dec 01 23:32:18 PST 2022\
Added Crv (2008), Petrol, to the dealership


Thu Dec 01 23:32:32 PST 2022\
Added Civic (2019), Diesel, to the dealership


Thu Dec 01 23:32:53 PST 2022\
Added Insight (2022), Electric, to the dealership


Thu Dec 01 23:33:00 PST 2022\
Sold Civic (2019), Diesel


Thu Dec 01 23:33:03 PST 2022\
Removed Crv (2008), Petrol, from the dealership

## Phase 4: Task 3
In my project, I have followed a very basic design with some repetition among various classes. If I had more time, I 
would definitely change a couple of things in my project in order to improve its design.
- I would include checked exceptions in place of the REQUIRES clause in the constructor for the class Car.
- I would implement the Singleton Design Pattern in the class Dealership. Since the application revolves around just 
one instance of Dealership, using this design pattern would ensure that only one instance of dealership can be used 
across the application.
- I would create a super class for all the tabs in my graphical user interface so that there is less repetition between
the classes HomeTab, DirectoryTab and WarehouseTab.
