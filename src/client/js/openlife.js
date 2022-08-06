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

document.getElementById("incrementCounter").addEventListener("click", addIncrementDecrementCounterWidget);
function addIncrementDecrementCounterWidget() {
    //Div
    const widgetDiv = document.createElement("div");
    widgetDiv.setAttribute("id", "container");

    widgetDiv.style.borderRadius = "25px";
    widgetDiv.style.border = "5px solid yellow";
    widgetDiv.style.padding = "20px";
    widgetDiv.style.width = "200px";
    widgetDiv.style.height = "150px";

    //Title Element
    var titleHeader = document.createElement("p");
        titleHeader.innerText = "Counter";
        titleHeader.style.fontSize = "small";

    //Counter Element
    var counterParagraph = document.createElement("span");
        counterParagraph.style.fontSize = "x-large";
    const counterButton = document.createElement("button");
        counterButton.innerText = "+";
        counterButton.style.fontSize = "x-large";

    //Description Element
    var descParagraph = document.createElement("p");
        descParagraph.innerHTML = "This is the increment widget. Use the button to increment the counter.";
        descParagraph.style.fontSize = "small";

    //Widget Setup
    widgetDiv.appendChild(titleHeader);
    widgetDiv.appendChild(counterParagraph);
    widgetDiv.appendChild(counterButton);
    widgetDiv.appendChild(descParagraph);

    gridBox = document.querySelector(".grid-item");
    // gridBox.removeAttribute("id");
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
