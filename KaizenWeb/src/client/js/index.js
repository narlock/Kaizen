// Clock Logic
function showTime(){
    var date = new Date();
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    var session = "AM";
    
    if(h == 0){
        h = 12;
    }
    
    if(h > 12){
        h = h - 12;
        session = "PM";
    }
    
    h = (h < 10) ? "0" + h : h;
    m = (m < 10) ? "0" + m : m;
    s = (s < 10) ? "0" + s : s;
    
    var time = h + ":" + m + ":" + s + " " + session;
    document.getElementById("clock").innerText = time;
    document.getElementById("clock").textContent = time;
    
    setTimeout(showTime, 1000);
    
}

showTime();

window.onload = function() {
    populateKanbanTodoBoard();
    populateKanbanInprogBoard();
    updateHabitStreaks();
    getUpcomingBirthdayContacts();
}

function populateKanbanTodoBoard() {
    var xmlhttp = new XMLHttpRequest();

    //Populate in progress stories
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateKanbanSection(JSON.parse(this.responseText), "kanbanTodo");
        }
    };
    xmlhttp.open("GET", "todoKanbanStories", true);
    xmlhttp.send();
}

function populateKanbanInprogBoard() {
    var xmlhttp = new XMLHttpRequest();

    //Populate in progress stories
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateKanbanSection(JSON.parse(this.responseText), "kanbanInProgress");
        }
    };
    xmlhttp.open("GET", "inprogKanbanStories", true);
    xmlhttp.send();
}

function populateKanbanSection(stories, statusDivId) {
    //Get Parent Div
    parentDiv = document.getElementById(statusDivId);

    //Create Story Div for each of the stories and append to parentDiv
    stories.forEach(function(story) {
        console.log(story);

        //Story Div
        newStoryDiv = document.createElement('div');
            newStoryDiv.classList.add("story");

        //Priority
        newStoryPriorityImg = document.createElement('img');
            newStoryPriorityImg.classList.add("kanban__priority");
            newStoryPriorityImg.setAttribute("id", story.story_id);
            newStoryPriorityImg.setAttribute("src", getImageUrlFromId(story.story_priority));
            newStoryPriorityImg.setAttribute("alt", "Priority Image");
        newStoryDiv.appendChild(newStoryPriorityImg);

        //Story Points
        newStoryPoints = document.createElement('strong');
            newStoryPoints.classList.add("kanban__points");
            newStoryPoints.setAttribute("id", story.story_id);
            newStoryPoints.textContent = story.story_points;
        newStoryDiv.appendChild(newStoryPoints);

        //Story Title
        newStoryTitle = document.createElement('span');
            newStoryTitle.classList.add("kanban__title");
            newStoryTitle.setAttribute("id", story.story_id);
            newStoryTitle.textContent = story.story_title;
        newStoryDiv.appendChild(newStoryTitle);

        parentDiv.appendChild(newStoryDiv);
    });
}

function getImageUrlFromId(id) {
    if(id == 1) {
        return "assets/low.svg";
    } else if(id == 2) {
        return "assets/medium.svg";
    } else if(id == 3) {
        return "assets/high.svg";
    } else if(id == 4) {
        return "assets/major.svg";
    } else {
        return "assets/medium.svg";
    }
}

function updateHabitStreaks() {
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            console.log("Update Habit Streaks SUCCESS Response");
            checkDateForHabit();
        }
    };
    xmlhttp.open("GET", "updateHabitStreaks", true);
    xmlhttp.send();
}

function checkDateForHabit() {
    var xmlhttp = new XMLHttpRequest();

     xmlhttp.onreadystatechange = function() {
         if(this.readyState == 4 && this.status == 200) {
            console.log("Check Date Habit SUCCESS Response");
            populateHabitsTable(); 
         }
     };
     xmlhttp.open("GET", "checkDateForHabits", true);
     xmlhttp.send();
}

function populateHabitsTable() {
     var xmlhttp = new XMLHttpRequest();

     xmlhttp.onreadystatechange = function() {
         if(this.readyState == 4 && this.status == 200) {
            createHabitElements(JSON.parse(this.responseText));
         }
     };
     xmlhttp.open("GET", "dailyDbHabits", true);
     xmlhttp.send();
}

function createHabitElements(habits) {
    table = document.getElementById('habitsTableBody');

    //Create Table Row
    habits.forEach(function(habit) {
        //habitRow
        habitRow = document.createElement('tr');

        //checkbox th
        checkBoxTh = document.createElement('th');
            checkBoxTh.setAttribute('scope', 'row');
        checkBox = document.createElement('input');
            checkBox.setAttribute('type', 'checkbox');
            checkBox.setAttribute('id', habit.habit_id);
            checkBox.classList.add('habits__checkbox');
            if(habit.habit_status == 0) {
                checkBox.setAttribute('onclick', 'updateHabit(event)');
            } else {
                checkBox.setAttribute('onclick', 'return false');
                checkBox.checked = true;
            }
            
        checkBoxTh.appendChild(checkBox);
        habitRow.appendChild(checkBoxTh);

        //td habit title
        habitTitle = document.createElement('td');
            habitTitle.textContent = habit.habit_title;
        habitRow.appendChild(habitTitle);

        //Streak Number
        streakElement = document.createElement('td');
            if(habit.habit_streak > 0) {
                streakElement.textContent = "🔥 " + habit.habit_streak;
            } else {
                streakElement.textContent = "None";
            }
        habitRow.appendChild(streakElement);

        table.appendChild(habitRow);
    });
}

function updateHabit(event) {
    //Get the checkbox
    checkBox = event.path[0];

    //Once a habit is checked, it cannot be unchecked!
    checkBox.setAttribute('onclick', 'return false');

    //Id of checkbox corresponds to habit_id in db
    id = checkBox.getAttribute('id');
    console.log(id);

    /*
     *  Will update the status of the event to complete
     *
     *  Will check previous day with current day, if they
     *  are less than 24 hours apart and do not appear on
     *  the same calendar day, then the streak will be incremented
     * 
     *  If the previous day is more than 24 hours apart,
     *  the streak will equal 0.
     */
    $.ajax({
        type: 'POST',
        url: 'checkHabit',
        data: {
          'id': id
        },
        success: function(habit) {
            console.log("SUCCESSful Update in DB");
        }
    });
}

function getUpcomingBirthdayContacts() {
    xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateBirthdayContacts(JSON.parse(this.responseText));
        }
    };
    xmlhttp.open("GET", "getContactsUpcomingBirthday", true);
    xmlhttp.send();
}

function populateBirthdayContacts(contacts) {
    console.log(contacts);
    tableBody = document.getElementById('birthdayContactsBody');

    contacts.forEach(function(contact) {
        tableRow = document.createElement('tr');
            tableRow.classList.add(contact.contact_id);

        nameElement = document.createElement('td');
            nameElement.textContent = contact.contact_name;
        tableRow.appendChild(nameElement);

        daysUntilElement = document.createElement('td');
        daysUntilBirthday = Math.abs(contact.diffDays - 30);
        if(daysUntilBirthday == 0) {
            //Birthday is today!
            daysUntilElement.textContent = "Today!";
        } else {
            //Birthday is in x days
            daysUntilElement.textContent = "In " + daysUntilBirthday + " days.";
        }
        tableRow.appendChild(daysUntilElement);

        birthday = new Date(contact.contact_birthday);
        birthdayString = birthday.getFullYear() + "-" + (birthday.getMonth()+1) + "-" + birthday.getDate(); //yyyy-mm-dd
        birthdayElement = document.createElement('td');
            birthdayElement.textContent = birthdayString;
        tableRow.appendChild(birthdayElement);

        contactElement = document.createElement('td');
        //If the user has facebook, add messenger link
        if(contact.contact_fb != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://m.me/' + contact.contact_fb);
            messengerImgElement = document.createElement('img');
                messengerImgElement.classList.add('social__img');
                messengerImgElement.setAttribute('src', '../assets/fb.png');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }

        //If the user has whatsapp, add whatsapp link
        if(contact.contact_whatsapp != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://wa.me/' + contact.contact_whatsapp);
            messengerImgElement = document.createElement('img');
                messengerImgElement.classList.add('social__img');
                messengerImgElement.setAttribute('src', '../assets/whatsapp.png');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }

        //If the user has discord, add discord user link
        if(contact.contact_discord != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://discordapp.com/users/' + contact.contact_discord);
            messengerImgElement = document.createElement('img');
                messengerImgElement.classList.add('social__img');
                messengerImgElement.setAttribute('src', '../assets/discord.svg');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }
        tableRow.appendChild(contactElement);

        tableBody.appendChild(tableRow);
    });

    if(document.getElementById('birthdayContactsBody').childElementCount == 0) {
        //Display that there are no upcoming birthdays
        noBirthdaysElement = document.createElement('strong');
            noBirthdaysElement.textContent = "No upcoming birthdays";
            noBirthdaysElement.style.color = "red";
        tableBody.appendChild(noBirthdaysElement);
    }
}