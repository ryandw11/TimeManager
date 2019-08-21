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
    students = student;
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


var testList = [
    ["Hello World", 2, "None", 0.5],
    ["Hello World", 2, "None", 0.3]
]
displayTable(getListOfStudents(testList))

/**
 * Display the table based off of a list of students.
 * @param {Array} students 
 */
function displayTable(students) {
    var header = "<tr>\
    <th></th>\
    <th>Student Name</th>\
    <th>Grade</th>\
    <th>Class</th>\
    <th>Hours</th>\
    </tr>";

    var table = document.getElementsByTagName("table")[0];
    table.innerHTML = header;
    for (var i = 0; i < students.length; i++) {
        var tableRow = document.createElement("tr");
        for (var x = 0; x < 5; x++) {
            var tableData = document.createElement("td");
            if (x == 0) {
                tableData.innerHTML = `<input type="checkbox" name="${i}" id="${i}">`;
                var input = tableData.childNodes[0];
                input.addEventListener("change", onChange);
            } else if (x == 1) {
                tableData.textContent = students[i].getName();
            } else if (x == 2) {
                tableData.textContent = students[i].getGrade();
            } else if (x == 3) {
                tableData.textContent = students[i].getClass();
            } else if (x == 4) {
                tableData.textContent = students[i].getHours();
            }
            tableRow.appendChild(tableData);
        }
        table.appendChild(tableRow);
    }
}

function onChange(e) {
    var target = e.target;
    studentData.logData("works!!");
    if (e.target.checked) {
        selectedStudents.push(parseInt(target.name));
    } else {
        var index = selectedStudents.indexOf(parseInt(target.name));
        selectedStudents.splice(index, 1);
    }
    studentData.logData(selectedStudents);
    studentData.onSelect(JSON.stringify(selectedStudents));
}