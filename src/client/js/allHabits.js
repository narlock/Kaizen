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
     * Check/update the streak status of ALL habits
     * 
     */
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