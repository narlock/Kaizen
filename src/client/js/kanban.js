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
    populateKanbanBacklogBoard();
    populateKanbanTodoBoard();
    populateKanbanInprogBoard();
    populateKanbanDoneBoard();
}

function populateKanbanBacklogBoard() {
    var xmlhttp = new XMLHttpRequest();

    //Populate backlog stories
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateKanbanSection(JSON.parse(this.responseText), "backlog");
        }
    };
    xmlhttp.open("GET", "backlogKanbanStories", true);
    xmlhttp.send();
}

function populateKanbanTodoBoard() {
    var xmlhttp = new XMLHttpRequest();

    //Populate todo stories
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateKanbanSection(JSON.parse(this.responseText), "todo");
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
            populateKanbanSection(JSON.parse(this.responseText), "inprog");
        }
    };
    xmlhttp.open("GET", "inprogKanbanStories", true);
    xmlhttp.send();
}
    
function populateKanbanDoneBoard() {
    var xmlhttp = new XMLHttpRequest();

    //Populate done stories
    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateKanbanSection(JSON.parse(this.responseText), "done");
        }
    };
    xmlhttp.open("GET", "doneKanbanStories", true);
    xmlhttp.send();
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
            newStoryPriorityImg.setAttribute("id", story.story_id);
            newStoryPriorityImg.setAttribute("src", getImageUrlFromId(story.story_priority));
            newStoryPriorityImg.setAttribute("alt", "Priority Image");
            newStoryPriorityImg.setAttribute("onclick", "viewStory(event)");
        newStoryDiv.appendChild(newStoryPriorityImg);

        //Story Points
        newStoryPoints = document.createElement('strong');
            newStoryPoints.classList.add("kanban__points");
            newStoryPoints.setAttribute("id", story.story_id);
            newStoryPoints.setAttribute("onclick", "viewStory(event)");
            newStoryPoints.textContent = story.story_points;
        newStoryDiv.appendChild(newStoryPoints);

        //Story Title
        newStoryTitle = document.createElement('span');
            newStoryTitle.classList.add("kanban__title");
            newStoryTitle.setAttribute("id", story.story_id);
            newStoryTitle.setAttribute("onclick", "viewStory(event)");
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
    ev.dataTransfer.setData("id", ev.target.id);
    ev.dataTransfer.setData("status", ev.path[1].getAttribute('id'));
    // console.log(ev.path[1].getAttribute('id'));
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("id");
    console.log("Dragged from " + ev.dataTransfer.getData("status"));
    console.log("Dragged to " + ev.path[0].getAttribute('id'));

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
        //should be able to get id of the div, which is id of the story
        $.ajax({
            type: 'POST',
            url: 'updateKanbanStory',
            data: {
              'id': data,
              'newStatus': ev.path[0].getAttribute('id')
            },
            success: function(msg) {
              console.log(msg);
            }
        });
    }
}

function viewStory(ev) {
    if(createMode == true) { return; }
    document.getElementById('kanbanUpdateForm').style.display = 'block';
    document.getElementById('createStory').style.display = 'none';

    var id = ev.path[0].getAttribute('id');
    console.log(id);

    params = {
        "id": id
    }

    if(createMode == false) {
        //get elements of the update story form and update
        //the story information
        var xmlhttp = new XMLHttpRequest();

        //Populate done stories
        xmlhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                enterUpdateMode(JSON.parse(this.responseText), id);
            }
        };
        xmlhttp.open("POST", "kanbanStory");
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify({ "id": id } ));
    }
}

function enterUpdateMode(story, id) {
    var idElement = document.getElementById('idInput');
    idElement.setAttribute('value', id);
    idElement.readOnly = true;
    document.getElementById("idInputDiv").style.display = "none";
    
    var titleElement = document.getElementById('updateTitle');
    console.log(titleElement);
    titleElement.value = story.story_title;

    if(story.story_status == "backlog") {
        document.getElementById("updateStatus").selectedIndex = 0;
    } else if(story.story_status == "todo") {
        document.getElementById("updateStatus").selectedIndex = 1;
    } else if(story.story_status == "inprog") {
        document.getElementById("updateStatus").selectedIndex = 2;
    } else {
        document.getElementById("updateStatus").selectedIndex = 2;
    }
    
    console.log(story.story_priority);
    if(story.story_priority == 4) {
        document.getElementById("updatePriority").selectedIndex = 0;
    } else if(story.story_priority == 3) {
        document.getElementById("updatePriority").selectedIndex = 1;
    } else if(story.story_priority == 2) {
        document.getElementById("updatePriority").selectedIndex = 2;
    } else if(story.story_priority == 1) {
        document.getElementById("updatePriority").selectedIndex = 3;
    }

    if(story.story_points == 1) {
        document.getElementById("updatePoints").selectedIndex = 0;
    } else if(story.story_points == 2) {
        document.getElementById("updatePoints").selectedIndex = 1;
    } else if(story.story_points == 3) {
        document.getElementById("updatePoints").selectedIndex = 2;
    } else if(story.story_points == 4) {
        document.getElementById("updatePoints").selectedIndex = 3;
    } else if(story.story_points == 5) {
        document.getElementById("updatePoints").selectedIndex = 4;
    }

    document.getElementById('updateDesc').textContent = story.story_desc;

    //Make delete button
    var updateButtonsDiv = document.getElementById('updateButtons');
    if(updateButtonsDiv.childNodes.length >= 3) {
        for(var i = 2; i < updateButtonsDiv.childNodes.length; i++) {
            $(updateButtonsDiv).children().eq(i).remove();
        }
    }

    var deleteByIdButton = document.createElement('a');
        deleteByIdButton.classList.add("btn");
        deleteByIdButton.classList.add("btn-danger");
        deleteByIdButton.setAttribute('id', 'deleteButtonElement')
        deleteByIdButton.setAttribute('onclick', `deleteStory(${story.story_id})`);
        deleteByIdButton.style.color = "white";
        deleteByIdButton.style.cursor = "pointer";
        deleteByIdButton.textContent = "Delete";
    updateButtonsDiv.appendChild(deleteByIdButton);
}

function enterCreateStoryMode() {
    console.log("Hello");
    createMode = true;
    document.getElementById('kanbanCreateForm').style.display = 'block';
    document.getElementById('createStory').style.display = 'none';
}

function exitCreateStoryMode() {
    createMode = false;
    document.getElementById('kanbanCreateForm').style.display = null;
    document.getElementById('createStory').style.display = 'flex';
}

function exitUpdateStoryMode() {
    document.getElementById('kanbanUpdateForm').style.display = 'none';
    document.getElementById('createStory').style.display = 'flex';
}

function deleteStory(id) {
    console.log(id);

    $.ajax({
        type: 'POST',
        url: 'deleteKanbanStory',
        data: {
          'id': id
        },
        success: function(msg) {
            updateAfterDelete(msg);
        }
    });
}

function updateAfterDelete(id) {
    document.getElementById('kanbanUpdateForm').style.display = 'none';
    document.getElementById('createStory').style.display = 'flex';
    document.getElementById(id.toString()).remove();
}