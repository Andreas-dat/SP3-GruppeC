# Use Cases:
## UC: LOGIN
The user is prompted to enter username and password <p>
program invokes UC: choose profile

**rainy day**<p>
user types invalid username or password, repeats x amount of times before user is locked out for x amount of times. user is informed.

## UC: REGISTER USER
user is prompted to enter name, date of birth, email, username, password <p>
standard profiles are created from user information <p>
invokes UC: SAVE USER INFORMATION<p>
program invokes UC: Log In

**rainy day**<p>
user types invalid birthdate in age<p>
user types invalid password<p>
user types invalid information

## UC: START SERVICE
program loads data<p>
user is prompted to either log in or create new user

**rainy day**<p>
user types invalid choice, is prompted again<p>
data is unable to be loaded/doesn't exist, user is informed<p>


## UC: CREATE PROFILE
user is prompted to enter a profile name and whether the profile is a child profile or not

**rainy day**<p>
user tries to enter invalid characters in name<p>
user enters invalid information based on rules.

## UC: CHOOSE PROFILE 
prompts the user to choose between available profiles, edit profile or create new profile

## UC: EDIT PROFILE
edits chosen profile

## UC: DELETE PROFILE
deletes chosen profile

## UC: MAIN MENU
user is given options to show all movies, watched movies, search by characteristics, plan to watch

## UC: FIND MEDIA BY CHARACTERISTICS
user is prompted to enter which type of search (title, category, year, rating)<p>
user types keyword

**rainy day**<p>
user types invalid keyword, try again
user search does not exist

## UC: PROFILE LISTS
filter media by users list (Has watched/Plan to watch)

**rainy day** <p>
user dost have a list, a msg is displayed telling them they don't have a list



## UC: DISPLAY MEDIA LISTS
displays list of media (all media, category, year, has watched, plan to watch)

## UC: DISPLAY MEDIA INFO
displays info about media and promts the user to make choice:<p>
invoke UC: PLAY MEDIA<p>
invoke UC: ADD TO LIST<p>
invoke UC: REMOVE FROM LIST<p>


## UC: PLAY MEDIA
media plays<p>
media is added to profiles hasWatched list

**rainy day**<p>
user wants to stop playback

## UC: EDIT MEDIA LIST

### ADD TO LIST
user can add media to a list

**rainy day**<p>
item is already on list
user wants to remove media from hasWatched

### REMOVE FROM LIST
user can remove media from list, only if it is already added

## UC: LOGOUT
saves user password, username, and lists

**rainy day**
file does not exist, create new file


-----

## UC: ADD/REMOVE CATEGORY

## ADD MEDIA

