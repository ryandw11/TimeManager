/**
 * The code to control the UI
 * @author Ryandw11
 */

/**
 * The Student class.
 */
class Student {
    constructor(stu) {
        this.name = stu[0];
        this.grade = stu[1];
        this.class = stu[2];
        this.hours = stu[3];
    }

    getName() {
        return this.name;
    }

    getGrade() {
        return this.grade;
    }

    getClass() {
        return this.class;
    }

    getHours() {
        return this.hours;
    }
}

var students = [];
var selectedStudents = [];


// window.addEventListener("load", loadData);

function loadData() {
    var student = studentData.getData();
    students = getListOfStudents(JSON.parse(student));
    displayTable(getListOfStudents(JSON.parse(student)));
}

/**
 * Convert a multi array into a list of students.
 * @param {Array} stu 
 */
function getListOfStudents(stu) {
    // studentData.logData(stu);
    var output = [];
    for (var e in stu) {
        // studentData.logData(stu[e]);
        output.push(new Student(stu[e]));
    }
    // stu.forEach(e => {
    //     output.push(new Student(e));
    // });
    return output;
}


// var testList = [
//     ["Hello World", 2, "None", 0.5],
//     ["Hello World", 2, "None", 0.3]
// ]
// displayTable(getListOfStudents(testList))

/**
 * Display the table based off of a list of students.
 * @param {Array} students 
 */
function displayTable(std) {
    var header = "<tr>\
    <th></th>\
    <th>Student Name</th>\
    <th>Grade</th>\
    <th>Class</th>\
    <th>Hours</th>\
    </tr>";

    var table = document.getElementsByTagName("table")[0];
    table.innerHTML = header;
    for (var i = 0; i < std.length; i++) {
        var tableRow = document.createElement("tr");
        for (var x = 0; x < 5; x++) {
            var tableData = document.createElement("td");
            if (x == 0) {
                tableData.innerHTML = `<input type="checkbox" name="${students.indexOf(std[i])}" id="${students.indexOf(std[i])}">`;
                var input = tableData.childNodes[0];
                input.addEventListener("change", onChange);
            } else if (x == 1) {
                tableData.textContent = std[i].getName();
            } else if (x == 2) {
                tableData.textContent = std[i].getGrade();
            } else if (x == 3) {
                tableData.textContent = std[i].getClass();
            } else if (x == 4) {
                tableData.textContent = std[i].getHours();
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
        text.textContent = "No Students Found";
        row.appendChild(text);
        row.appendChild(document.createElement("td"));
        row.appendChild(document.createElement("td"));
        row.style.backgroundColor = "white";
        table.appendChild(row);
    }
}

function onChange(e) {
    var target = e.target;
    if (e.target.checked) {
        selectedStudents.push(parseInt(target.name));
    } else {
        var index = selectedStudents.indexOf(parseInt(target.name));
        selectedStudents.splice(index, 1);
    }
    studentData.onSelect(JSON.stringify(selectedStudents));
}

function sort(name, clazz, grade, hour1, hour2) {
    let newList = sortData(name, clazz, grade, hour1, hour2);
    displayTable(newList);
}

function sortData(name, clazz, grade, hour1, hour2) {
    var storedData = [];
    if (name != null && name != "" && name != " ") {
        for (let i in students) {
            if (students[i].getName().toLowerCase().includes(name.toLowerCase())) {
                storedData.push(students[i]);
            }
        }
    }
    else {
        storedData = students.slice(0);
    }
    if (clazz != null) {
        const tempData = storedData.slice(0);
        for (let i in tempData) {
            if (tempData[i].getClass() != clazz) {
                storedData.splice(storedData.indexOf(tempData[i]), 1);
            }
        }
    }
    if (grade != 0 && grade != null) {
        const tempData = storedData.slice(0);
        for (let i in tempData) {
            if (tempData[i].getGrade() !== grade) {
                storedData.splice(storedData.indexOf(tempData[i]), 1);
            }
        }
    }
    if (hour1 != null && hour2 != null) {
        const tempData = storedData.slice(0);
        for (let i in tempData) {
            if (!(parseFloat(tempData[i].getHours()) < parseFloat(hour1)) || !(parseFloat(tempData[i].getHours()) > parseFloat(hour2))) {
                storedData.splice(storedData.indexOf(tempData[i]), 1);
            }
        }
    }
    return storedData;
}