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
    getUpcomingBirthdayContacts();
    getAllContacts();
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
                messengerImgElement.setAttribute('src', '../assets/fb.png');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }

        //If the user has whatsapp, add whatsapp link
        if(contact.contact_whatsapp != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://wa.me/' + contact.contact_whatsapp);
            messengerImgElement = document.createElement('img');
                messengerImgElement.setAttribute('src', '../assets/whatsapp.png');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }

        //If the user has discord, add discord user link
        if(contact.contact_discord != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://discordapp.com/users/' + contact.contact_discord);
            messengerImgElement = document.createElement('img');
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

function getAllContacts() {
    xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            populateAllContacts(JSON.parse(this.responseText));
        }
    };
    xmlhttp.open("GET", "getAllContacts", true);
    xmlhttp.send();
}

function populateAllContacts(contacts) {
    console.log(contacts);
    tableBody = document.getElementById('allContactsBody');

    contacts.forEach(function(contact) {
        tableRow = document.createElement('tr');
            tableRow.classList.add(contact.contact_id);

        nameElement = document.createElement('td');
            nameElement.textContent = contact.contact_name;
        tableRow.appendChild(nameElement);

        birthday = new Date(contact.contact_birthday);
        birthdayString = birthday.getFullYear() + "-" + (birthday.getMonth()+1) + "-" + birthday.getDate(); //yyyy-mm-dd
        birthdayElement = document.createElement('td');
            birthdayElement.textContent = birthdayString;
        tableRow.appendChild(birthdayElement);

        phoneElement = document.createElement('td');
            phoneElement.textContent = contact.contact_phone;
        tableRow.appendChild(phoneElement);
        
        contactElement = document.createElement('td');
        //If the user has facebook, add messenger link
        if(contact.contact_fb != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://m.me/' + contact.contact_fb);
            messengerElement.setAttribute('target', '_blank');
            messengerImgElement = document.createElement('img');
                messengerImgElement.setAttribute('src', '../assets/fb.png');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }

        //If the user has whatsapp, add whatsapp link
        if(contact.contact_whatsapp != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://wa.me/' + contact.contact_whatsapp);
            messengerElement.setAttribute('target', '_blank');
            messengerImgElement = document.createElement('img');
                messengerImgElement.setAttribute('src', '../assets/whatsapp.png');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }

        //If the user has discord, add discord user link
        if(contact.contact_discord != "") {
            messengerElement = document.createElement('a');
            messengerElement.setAttribute('href', 'https://discordapp.com/users/' + contact.contact_discord);
            messengerElement.setAttribute('target', '_blank');
            messengerImgElement = document.createElement('img');
                messengerImgElement.setAttribute('src', '../assets/discord.svg');
            messengerElement.appendChild(messengerImgElement);
            contactElement.appendChild(messengerElement);
        }
        tableRow.appendChild(contactElement);

        noteElement = document.createElement('td');
            noteElement.textContent = contact.contact_note;
        tableRow.appendChild(noteElement);

        optionsElement = document.createElement('td');
            optionsDeleteButton = document.createElement('button');
            optionsDeleteButton.classList.add('btn');
            optionsDeleteButton.classList.add('btn-danger');
            optionsDeleteButton.textContent = "Delete";
            optionsDeleteButton.setAttribute('onclick', 'deleteUser(' + contact.contact_id + ')');
        optionsElement.appendChild(optionsDeleteButton);
        tableRow.appendChild(optionsElement);

        tableBody.appendChild(tableRow);
    });
}

function deleteUser(id) {
    if (confirm("Are you sure you would like to delete this contact?") == true) {
        $.ajax({
            type: 'POST',
            url: 'deleteContactById',
            data: {
              'id': id
            },
            success: function(entry) {
                elementsToDelete = document.getElementsByClassName(id.toString());
                var arr = Array.prototype.slice.call( elementsToDelete )
                console.log(arr);
                arr.forEach(function(element) {
                    element.remove();
                });
            }
        });
    }
    
}