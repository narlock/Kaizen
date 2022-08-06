/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}
  
// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
    var openDropdown = dropdowns[i];
    if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
    }
    }
}
}

//Clock
function startTime() {
    const today = new Date();
    let h = today.getHours();
    let m = today.getMinutes();
    let s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('clock').innerHTML = h + ":" + m + ":" + s;
    setTimeout(startTime, 1000);
  }
  
  function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
  }

//IncrementCounter
document.getElementById("incrementCounter").addEventListener("click", addIncrementDecrementCounterWidget);
function addIncrementDecrementCounterWidget() {
    //Div
    const widgetDiv = document.createElement("div");
    widgetDiv.setAttribute("id", "container");

    widgetDiv.style.background = "white";
    widgetDiv.style.borderRadius = "25px";
    widgetDiv.style.border = "5px solid blue";
    widgetDiv.style.padding = "20px";
    widgetDiv.style.width = "200px";
    widgetDiv.style.height = "150px";

    //Title Element
    var titleHeader = document.createElement("p");
        titleHeader.innerHTML = "<b>Counter</b>";
        titleHeader.style.fontSize = "medium";

    //Counter Element
    var counterParagraph = document.createElement("span");
        counterParagraph.innerHTML = "0";
        counterParagraph.style.fontSize = "x-large";
        counterParagraph.style.paddingRight = "20px";
    const counterButton = document.createElement("button");
        counterButton.innerText = "+";
        counterButton.style.fontSize = "x-large";

    //Description Element
    var descParagraph = document.createElement("p");
        descParagraph.innerHTML = "Use the button to increment the counter.";
        descParagraph.style.fontSize = "small";

    //Widget Setup
    widgetDiv.appendChild(titleHeader);
    widgetDiv.appendChild(counterParagraph);
    widgetDiv.appendChild(counterButton);
    widgetDiv.appendChild(descParagraph);

    gridBox = document.querySelector(".grid-item");
    gridBox.removeAttribute("class");
    //Removing this attribute allows the next grid-item to be used

    gridBox.style.backgroundColor = "rgba(255, 255, 255, 0.8)";
    gridBox.style.border = "1px solid rgba(0,0,0,0.8)";
    gridBox.style.padding = "20px";
    gridBox.style.textAlign = "center";

    gridBox.appendChild(widgetDiv);

    //Counter functionality
    var count = 0;
    counterButton.addEventListener("click", function() {
        console.log(count);
        console.log(counterParagraph);
        count = count + 1;
        counterParagraph.innerHTML = count;
    });
}
