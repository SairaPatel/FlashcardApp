# Flashcard App #


## Background and Inspiration ##
This is a simple flashcard desktop application based on a previous VB project that I made to help with studying for A-levels.

This application was designed to suit my own preferred study technique of writing long/detailed flashcards
and then studying them by jotting down my answers (as opposed to the traditional _"think of/say the answer to yourself"_ method).

After creating and using my initial VB application for a couple of years, I discovered the flashcard app/website [_Anki_](https://apps.ankiweb.net/) 
and I have taken some inspiration from Anki to design this version of the application. 
Specifically, my old application only organised cards by assigning each one into a set. However, _Anki_ does this as well as allowing each card to be assigned multiple tags.


## About ##
This is a Java Swing application which uses MySQL to store a database of flashcards. It allows the user to **create, edit and study their own flashcards**. 

### Edit ###
For organisation, each flashcard belongs to a **_set_** and can also have multiple **_tags_**.

<img src="https://github.com/user-attachments/assets/56592c4d-8a56-4c0d-a6d8-d3479335f143" alt="Edit Card Properties Panel" width="380"/>
<img src="https://github.com/user-attachments/assets/391177c0-9487-48dd-88dc-0bc272491e75" alt="Edit Card Content Panel" width="380"/>

### Home ###

A user can **filter** their flashcard database **by _set_, _tag_ and _keyword_** before studying them.
The user can choose to study a group of flashcards **in a random order or in order of their _knowledge ratings_**.


<img src="https://github.com/user-attachments/assets/5d78579b-f727-422f-b2ae-207ce513ce54" alt="Home Page with All Cards" width="380"/>
<img src="https://github.com/user-attachments/assets/1b8f3ad5-e24a-47dc-9472-108cb781a11a" alt="Home Page with Filtered Cards" width="380"/>



### Study ###
During study mode, the user can **view the card's _front_** content (questions/prompts) and has a **space to type their thoughts/answers**.

![image](https://github.com/user-attachments/assets/6e39e4c1-d87b-425e-b276-acff7e7bf10e)

They can then **click to reveal the card's _back_ contents (answers)**.

![image](https://github.com/user-attachments/assets/17ba72ff-dbed-482a-8b59-96a5e7285529)

After studying each card, the user can **rate their _knowledge_** of the card which will be stored for next time. 


## Possible Future Changes ##
- Add ability to change filter mode from using AND to OR (at the moment the default is AND. i.e. all filters must apply for a card to be part of the results)
- Add an "untagged" tag automatically to cards with no tags, so that they can be filtered separately
  - since, currently they are excluded if the user selects nothing and clicks 'Apply Filters'
  - this would also allow the elimination of some methods: since 'fetching all cards' could be dealt with in terms of 'fetching filtered cards'
- Make the rating of a card visible on the home page
- Maybe colour code ratings/add a progress bar to allow more visualisation of the knowledge level/progress of a card
- Add a progress bar/score to indicate the collective knowledge level of the currently displayed/filtered cards
- Add an additional filter for knowledge level

