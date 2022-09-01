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
    populateHabitsTable();
}

function populateHabitsTable() {
    /**
     * TODO
     * Populate the habits table
     * 
     * Check/update the streak status of daily habits
     * 
    */
     var xmlhttp = new XMLHttpRequest();

     xmlhttp.onreadystatechange = function() {
         if(this.readyState == 4 && this.status == 200) {
            createHabitElements(JSON.parse(this.responseText));
         }
     };
     xmlhttp.open("GET", "dbHabits", true);
     xmlhttp.send();
}

function createHabitElements(habits) {
    table = document.getElementById('habitsTableBody');

    //Create Table Row
    habits.forEach(function(habit) {
        //habitRow
        habitRow = document.createElement('tr');
            habitRow.setAttribute('id', habit.habit_id);

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

        //Occurrence
        occurrenceElement = document.createElement('td');
            occurrenceElement.textContent = convertHabitStringToFullString(habit.habit_occurrence);
        habitRow.appendChild(occurrenceElement);

        //Options
        optionsTd = document.createElement('td');
        updateButton = document.createElement('button');
            updateButton.textContent = "Update";
            updateButton.classList.add('btn');
            updateButton.classList.add('btn-primary');
            updateButton.setAttribute('onclick', `enterUpdateHabitMode(${habit.habit_id})`);
        optionsTd.appendChild(updateButton);
        deleteButton = document.createElement('button');
            deleteButton.textContent = "Delete";
            deleteButton.classList.add('btn');
            deleteButton.classList.add('btn-danger');
            deleteButton.setAttribute('onclick', `deleteHabit(${habit.habit_id})`);
        optionsTd.appendChild(deleteButton);
        habitRow.appendChild(optionsTd);

        table.appendChild(habitRow);
    });
}

function convertHabitStringToFullString(str) {
    let occurrence = "";
    if(str.includes("m")) { occurrence = occurrence.concat("Monday, "); }
    if(str.includes("t")) { occurrence = occurrence.concat("Tuesday, "); }
    if(str.includes("w")) { occurrence = occurrence.concat("Wednesday, "); }
    if(str.includes("h")) { occurrence = occurrence.concat("Thursday, "); }
    if(str.includes("f")) { occurrence = occurrence.concat("Friday, "); }
    if(str.includes("s")) { occurrence = occurrence.concat("Saturday, "); }
    if(str.includes("u")) { occurrence = occurrence.concat("Sunday, "); }
    occurrence = occurrence.slice(0, -2);

    if(str == "mtwhf") { occurrence = "Weekdays"; }
    if(str == "su") { occurrence = "Weekends"; }
    if(str == "mtwhfsu") { occurrence = "Everyday"; }

    return occurrence;
}

var createMode = false;

function enterCreateHabitMode() {
    createMode = true;
    document.getElementById('habitCreateForm').style.display = 'block';
    document.getElementById('buttonMenu').style.display = 'none';
}

function exitCreateHabitMode() {
    document.getElementById('habitCreateForm').style.display = null;
    document.getElementById('buttonMenu').style.display = 'flex';
}

function enterUpdateHabitMode(id) {
    console.log(id);
}

function deleteHabit(id) {
    console.log(id);
    $.ajax({
        type: 'POST',
        url: 'deleteHabit',
        data: {
          'id': id
        },
        success: function(msg) {
            document.getElementById(id.toString()).remove();
        }
    });

}
