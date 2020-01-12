/**
 * The code to control the hour UI
 * @author Ryandw11
 */

/**
 * The Hour class.
 */
class Hour {
    constructor(hour) {
        this.id = hour[0];
        this.time = hour[1];
        this.desc = hour[2];
    }

    getTime() {
        return this.time;
    }

    getDesc() {
        return this.desc;
    }

    getId() {
        return this.id;
    }
}

var hours = [];

function loadData() {
    var hour = hourData.getData();
    hours = getListOfHours(JSON.parse(hour));
    displayTable(getListOfHours(JSON.parse(hour)));
}

/**
 * Convert a multi array into the list of hours.
 * @param {Array} stu 
 */
function getListOfHours(hr) {
    var output = [];
    for (var e in hr) {
        output.push(new Hour(hr[e]));
    }
    return output;
}


// var testList = [
//     [0, 10.4, "This is a test!"],
//     [1, 3, "This is a test 2!"]
// ]
// displayTable(getListOfHours(testList))

/**
 * Display the table based off of a list of hours.
 * @param {Array} hours 
 */
function displayTable(std) {
    var header = "<tr>\
    <th>Hours</th>\
    <th>Description</th>\
    <th></th>\
    </tr>";

    var table = document.getElementsByTagName("table")[0];
    table.innerHTML = header;
    for (var i = 0; i < std.length; i++) {
        var tableRow = document.createElement("tr");
        for (var x = 0; x < 3; x++) {
            var tableData = document.createElement("td");
            if (x == 0) {
                tableData.textContent = std[i].getTime();
            } else if (x == 1) {
                tableData.textContent = std[i].getDesc();
            } else if (x == 2) {
                let anchor = document.createElement("a");
                anchor.addEventListener("click", e => {
                    hourData.onViewMore(JSON.stringify(e.target.dataset.id));
                });
                anchor.textContent = "View More";
                anchor.dataset.id = i;
                tableData.appendChild(anchor);
            }
            tableRow.appendChild(tableData);
        }
        table.appendChild(tableRow);
    }
    if (std.length < 1) {
        var row = document.createElement("tr");
        row.appendChild(document.createElement("td"));
        row.appendChild(document.createElement("td"));
        var text = document.createElement("td");
        text.textContent = "No Hours Found";
        row.appendChild(text);
        row.appendChild(document.createElement("td"));
        row.appendChild(document.createElement("td"));
        row.style.backgroundColor = "white";
        table.appendChild(row);
    }
}