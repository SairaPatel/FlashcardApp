# Flashcard App #

## Background and Inspiration ##

This is a simple flashcard desktop application based on a previous VB project that I made to help with studying for A-levels.

This application was designed to suit my own preferred study technique of writing long/detailed flashcards
and then studying them by jotting down my answers (as opposed to the traditional _"think of/say the answer to yourself"_ method).

After creating and using my initial VB application for a couple of years, I discovered the flashcard app/website [_Anki_](https://apps.ankiweb.net/) 
and I have taken some inspiration from _Anki_ to design this version of my application. 
Specifically, my old application only organised cards by assigning each one into a _set_. However, _Anki_ has the additionaly ability to assign multiple _tags_ to each card for more complex filtering.


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

During study mode, the user can **view the card's _front_ content (i.e. questions/prompts)** and has a **space to type their thoughts/answers**.

![image](https://github.com/user-attachments/assets/6e39e4c1-d87b-425e-b276-acff7e7bf10e)

They can then **click to reveal the card's _back_ contents (i.e. answers/info)**.

![image](https://github.com/user-attachments/assets/17ba72ff-dbed-482a-8b59-96a5e7285529)

After studying each card, the user can **rate their _knowledge_** of the card which will be stored for next time. 

## Implementation ##

### Database ### 

The database consists of 2 relations: _Cards_ and _CardTags_.  


**_Cards_** stores all the main properties of a flashcard (Title, Set, Front, Back...).
```
CREATE TABLE Cards (
	CardID int NOT NULL AUTO_INCREMENT,
	Title varchar(255) DEFAULT 'Untitled',
	CardSet varchar(255) DEFAULT 'Set 1',
	Front varchar(3000) DEFAULT '',
	Back varchar(6000) DEFAULT '',
	Rating float(3) DEFAULT 0,
	PRIMARY KEY (CardID)
	
	);
```

**_CardTags_** is used to stores pairs of Cards and Tag names.
```
CREATE TABLE CardTags(
	CardID int,
	Tag varchar(255),
	PRIMARY KEY(CardID, Tag),
	FOREIGN KEY (CardID) REFERENCES Cards(CardID) ON DELETE CASCADE
);
```

> When I first designed and created the database, I included a separate relation, _Tags_, which the _CardTags_ relation referenced, rather than simply storing the TagName in _CardTags_. I did this with the idea of potentially atatching other attributes to a _Tag_ in the future (such as a Tag Description or Tag Groups). However, I later decided that these ideas were unncessary and hence restructured the database to remove the redundant _Tag_ relation.

### Classes ###

- **_App_**
  - The root _JFrame_ for the application
  - Uses _CardLayout_ to display/switch between the _HomePage_, _EditCardPage_ and _LearnPage_ panels
- **_HomePage_**
  - _JPanel_ that contains controls for filtering and viewing cards
  - Handles navigation to the Edit/Learn pages as well as deletion and insertion of cards
- **_EditCardPage_**
  - _JPanel_ that contains controls for editing a card. Includes instances of:
      - **_EditCardContentPanel_**
        - _JPanel_ with controls for editing the front and back of the card
      - **_EditCardPropertiesPanel_**
        - _JPanel_ with controls for editing the general properties of a card and it's tags
- **_LearnPage_**
  - _JPanel_ with controls for studying and rating cards. Includes an instance of:
      - **_LearnCardPanel_**
          - _JPanel_ with controls for viewing and learning a single card
- **_Controller_**
    - Class that contains attributes and methods for managing the overall flow of data in the application
    - Maintains a list of the current _Cards_ that are being viewed/learned/edited:
        - **_Card_**
            - Class that models a Flashcard, with attributes for each of it's properties
    - Accesses the database using:
        - **_DB_**
            - Class with methods for querying the database
        
  


## Possible Future Changes ##

- Add ability to change filter mode from using AND to OR (at the moment the default is AND. i.e. all filters must apply for a card to be part of the results)
- Make _knowledge rating_ more visible to user
  - Display the rating of each card on the home page
  - Add an additional filter for knowledge rating
  - Maybe colour code ratings/add a progress bar to allow more visualisation of the knowledge level/progress of a card
  - Add a progress bar/score to indicate the collective knowledge level of the currently displayed/filtered cards


