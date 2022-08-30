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

/**
 * Kanban
 */

//Global Variables
var createMode = false;

window.onload = function() {
    populateKanbanBoard();
}

function populateKanbanBoard() {
    var xmlhttp = new XMLHttpRequest();

    //Populate backlog stories
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateKanbanSection(JSON.parse(this.responseText), "kanbanBacklog");
        }
    };
    xmlhttp.open("GET", "backlogKanbanStories", true);
    xmlhttp.send();

    //Populate todo stories

    //Populate in progress stories

    //Populate done stories
}

function populateKanbanSection(stories, statusDivId) {
    console.log("populate story")
    //Get Parent Div
    parentDiv = document.getElementById(statusDivId);

    //Create Story Div for each of the stories and append to parentDiv
    stories.forEach(function(story) {
        console.log(story);

        //Story Div
        newStoryDiv = document.createElement('div');
            newStoryDiv.classList.add("story");
            newStoryDiv.setAttribute("id", story.story_id);
            newStoryDiv.setAttribute("draggable", "true");
            newStoryDiv.setAttribute("ondragstart", "drag(event)");
            newStoryDiv.setAttribute("onclick", "viewStory(event)");

        //Priority
        newStoryPriorityImg = document.createElement('img');
            newStoryPriorityImg.classList.add("kanban__priority");
            newStoryPriorityImg.setAttribute("src", getImageUrlFromId(story.story_priority));
            newStoryPriorityImg.setAttribute("alt", "Priority Image");
        newStoryDiv.appendChild(newStoryPriorityImg);

        //Story Points
        newStoryPoints = document.createElement('strong');
            newStoryPoints.classList.add("kanban__points");
            newStoryPoints.textContent = story.story_points;
        newStoryDiv.appendChild(newStoryPoints);

        //Story Title
        newStoryTitle = document.createElement('span');
            newStoryTitle.classList.add("kanban__title");
            newStoryTitle.textContent = story.story_title;
        newStoryDiv.appendChild(newStoryTitle);

        console.log(newStoryDiv);

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

function drag(ev) {
    console.log(ev);
    ev.dataTransfer.setData("text", ev.target.id);
}

function allowDrop(ev) {
    console.log(ev);
    ev.preventDefault();
}

function drop(ev) {
    console.log(ev);
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    console.log(data);
    console.log(ev.target);

    if(ev.target.classList.contains("kanban__title") || 
    ev.target.classList.contains("kanban__story") ||
    ev.target.classList.contains("kanban__points") ||
    ev.target.classList.contains("kanban__priority") ||
    ev.target.classList.contains("kanban__header") ||
    ev.target.classList.contains("story")) {
        console.log("Invalid container")
    } else {
        ev.target.appendChild(document.getElementById(data));
        //TODO Update story status!!!
    }
}

function viewStory(ev) {
    //Get div of story
    var story;
    if(ev.path[1].classList.contains("story")) {
        //Clicked by either priority, points, or title
        story = ev.path[1];
    } else {
        story = ev.path[0];
    }
    console.log(story);

    //Update display to display contents
    //Will only display if not in create Mode
    //TODO User can update the story through this
    if(createMode == false) {
        var viewStoryDiv = document.getElementById('viewStory');
        //Send AJAX request to get story from database

    }
}