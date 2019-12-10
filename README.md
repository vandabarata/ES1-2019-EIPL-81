# ES1-2019-EIPL-81
Repository made for the project of Software Engineering I at our CS course in ISCTE-IUL

[![University](https://img.shields.io/badge/University-ISCTE--IUL-blue?style=for-the-badge)](https://www.iscte-iul.pt/)
![Project subject](https://img.shields.io/badge/Subject-Software%20Engineering%20I-green?style=for-the-badge)

[![Version Management](https://img.shields.io/badge/Version%20Management-Git-red?logo=git&style=for-the-badge)](https://github.com/vsfba1-iscteiul/ES1-2019-EIPL-81)
![IDE](https://img.shields.io/badge/IDE-Eclipse-4c1094?style=for-the-badge&logo=eclipse)

[![Project Management](https://img.shields.io/badge/Project%20Management-Trello-blue?logo=trello&style=for-the-badge)](https://trello.com/esieipl81)

[![Trello Boards - click for access](https://img.shields.io/badge/Trello-Boards-blue?logo=trello&style=for-the-badge)](https://trello.com/esieipl81)

![GitHub license](https://img.shields.io/github/license/vsfba1-iscteiul/ES1-2019-EIPL-81)
![GitHub commit activity](https://img.shields.io/github/commit-activity/w/vsfba1-iscteiul/ES1-2019-EIPL-81)
![GitHub last commit](https://img.shields.io/github/last-commit/vsfba1-iscteiul/ES1-2019-EIPL-81)
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/vsfba1-iscteiul/ES1-2019-EIPL-81)

![GitHub issues](https://img.shields.io/github/issues/vsfba1-iscteiul/ES1-2019-EIPL-81)
![GitHub closed issues](https://img.shields.io/github/issues-closed/vsfba1-iscteiul/ES1-2019-EIPL-81)
![GitHub pull requests](https://img.shields.io/github/issues-pr/vsfba1-iscteiul/ES1-2019-EIPL-81)
![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/vsfba1-iscteiul/ES1-2019-EIPL-81)

![ESI Group ID](https://img.shields.io/badge/Group-EI--PL%2081-82a1bf?style=for-the-badge)

![GitHub contributors](https://img.shields.io/github/contributors/vsfba1-iscteiul/ES1-2019-EIPL-81?style=for-the-badge&logo=github)
* Vanda Barata, 81996, vsfba1@iscte-iul.pt
* Franciele Faccin, 79879, ftfni@iscte-iul.pt
* Lino Silva, 82454, lrnsa@iscte-iul.pt
* Diego Souza, 82113, djsar@iscte-iul.pt
* João Miguel Louro, 82544, jmalo1@iscte-iul.pt
* Hugo Barroca, 82695, hmpdb@iscte-iul.pt
_____

![Project Functionalities](https://img.shields.io/badge/Project-Functionalities-green?style=for-the-badge&logo=read-the-docs)

Our project consists on a tool to check code quality, based on several code stats given in a specific Excel file. Said file is in the root of our project. User then has access to 2 rules that exist by default. New rules can be added with any valid conditions, based on the given metrics. The 2 default rules can also have their thresholds edited for different results.


### How to use
* The user should start by importing the given excel file

* A frame will then appear with the information taken from that file. 
Other than the excel visualization window, the frame also has the following:
    * A dropdown with the list of available rules
    * An edit button (to edit existing rules)
    * An add button (to add new rules)
    * A button labeled "Check quality"
    
* If the user clicks on the "Add" button, a rule edition frame opens in 'Basic Mode'. This mode was made to make it easier for users to add simple rules from scratch. There is also an 'Advanced Mode' available, if the user wants to create a more complex rule.
* If the user clicks on the "Edit" button, the rule edition frame will open in 'Advanced Mode' with the content of the selected rule.
* When the user clicks on "Check quality", our tool will then go through every single rule and calculate the relevant metrics and results for each one. 
* This will open a new window with the rules' results, as well as the stats for DCI, DII, ADCI, ADII for the default rules.

#### Rule Edition Frame
* Basic Mode

This frame contains a text box on top for the user to write or edit the rule's name (default rules can't have their name changed).
* The rest of the frame functions as such:
    * The first condition starts with an IF
    * User can then select from one of the available metrics from a dropdown box
    * Then comes the logical operator, also available from a predefined selection in a dropdown box
    * Lastly, there is a text input which only accepts numerical values (for the threshold)
    * The user must then click on "Add" to add this condition line to form the rule's conditions
    * After the addition of the first condition, there is now a selection for "AND" or "OR" for new conditions
    * User can keep adding conditions to the rule as they see fit, following the same process
    * If the user clicks on "Clear Rule Conditions", all the conditions of the rule being edited are cleared and user can start anew
    * If the user selects "Delete Rule", the selected rule is deleted. 
    * If the user selects "Save Rule", and rule has defined name and conditions are set, then the new rule is saved
    * After the deletion or saving of the rule, a confirmation popup is shown and the rule edition frame closes
    * There is also the option of switiching between advanced and basic mode for the edition of a new rule


* Advanced Mode

This frame is much less populated, sharing the rule's name text input box and the buttons to clear rule conditions, delete rule and save rule, which work the exact same way. For a default rule, there is a validation done at the time of saving which checks if only the thresholds have been modified, and nothing else. There are also validations to check if the rule's conditions are in a valid format. 
The delete button checks if the rule is default or not - default rules are never allowed to be deleted.


![Missing Features](https://img.shields.io/badge/Project-Missing%20features-red?style=for-the-badge&logo=read-the-docs)

We don't think anything is missing when compared to the requirements gathered from the Project Specifications Document given to us by the teachers. As such, we believe we have a fully functional tool to check code quality based on personalised rules, and the stats taken from the given excel file for analysis.

That being said, there is a known issue we couldn't fix - for the rule validation on save, for the advanced mode, we check if the condition is a valid JS expression, regardless of being a comparison or not. That means that, if the user makes an assignment (=) instead of a comparison (==), the rule is saved and calculated, with the affected metrics overwritten by the assignment. 
Since we're using a JS Engine to calculate the conditions result, an assignment on its own returns "undefined", which is converted to "false" in Java. This makes the results less reliable if the user makes a mistake at the time of writing the rule, but we didn't have enough time to find a way to alert the user or catch the error when executing.
We don't find this critical, however, since the advanced mode is supposed to be used by a user who knows what they're doing and is not meant for less experienced users.
