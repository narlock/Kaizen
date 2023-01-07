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
    updateHabitStreaks(); 
    //Updates the habit streaks in db
    //Updates the dates in db, setting status to incomplete
    //For each if the day is new
    //Populates the table
    
    /**
     * updateHabitStreaks is called, upon returning
     * it will call checkDateForHabit, upon returning
     * it will call populateHabitsTable. This is
     * to ensure that everything is called in order.
     */
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
    /**
     * TODO
     * Populate the habits table
     * 
     * Check/update the streak status of daily habits
     * 
     * Add only the habits that correspond to the current day
     * 
     * If a habit is already complete, make sure it is checked and the
     * attribute 'onclick' = 'false' is added, so it cannot be re-updated.
     */
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