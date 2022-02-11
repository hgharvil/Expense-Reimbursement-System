console.log("Hello! 2 employeepage.js");
window.onload = function () {

    ajaxGetUserInfo();
    ajaxGetTickets();
}


// async function ajaxGetUserInfo() {
//     const responsePayload = await fetch(`http://localhost:9001/Project01/master/userInfo`);

//     const ourJSON = await responsePayload.json();

//     setName(ourJSON);
// }

function ajaxGetUserInfo() {
    fetch(`http://localhost:9001/Project01/master/userInfo`)
        .then(
            function (daResponse) {
                const convertedResponse = daResponse.json();
                return convertedResponse;
            }

        )
        .then(
            function (daSecondResponse) {
                setName(daSecondResponse);
            }
        )
        .catch(
            (stuff) => { console.log("An issue occured while fetching the tickets"); }
        );

}

function showForm() {
    if (document.getElementById('addReimbursementForm').style.display == 'none') {
        document.getElementById('addReimbursementForm').style.display = 'block';
        document.getElementById('addReimbursementButton').innerHTML = 'Close Reimbursement Form';

    }
    else {
        document.getElementById('addReimbursementForm').style.display = 'none';
        document.getElementById('addReimbursementButton').innerHTML = 'Open Reimbursement Form';
    }
}

function showTable() {
    if (document.getElementById('ticketTable').style.display == 'none') {
        document.getElementById('ticketTable').style.display = 'block';
        document.getElementById('seeTicketsButton').innerHTML = 'Collapse Past Tickets';

    }
    else {
        document.getElementById('ticketTable').style.display = 'none';
        document.getElementById('seeTicketsButton').innerHTML = 'View Past Tickets';
    }
}


function ajaxGetTickets() {
    fetch(`http://localhost:9001/Project01/master/reimbursement/getAllReimbursementFromUser`)
        .then(
            function (daResponse) {
                const convertedResponse = daResponse.json();
                return convertedResponse;
            }

        )
        .then(
            function (daSecondResponse) {
                ourDOMManipulation(daSecondResponse);
            }
        )
        .catch(
            (stuff) => { console.log("An issue occured while fetching the tickets"); }
        );

}

function ourDOMManipulation(ourResponseObject) {
    //we are about to dome some HEAVY DOM Manipulation

    /*
        you COULD check to see if they are logged in as an employee or manager then
        dynamically add new buttons and/or html elements
    */

    for (let i = 0; i < ourResponseObject.length; i++) {
        ////////////CREATE ELEMENTS DYNAMICALLY////////////////
        /* 
        
            <th>Amount</th>
            <th>Date Submitted</th>
            <th>Date Resolved</th>
            <th>Description</th>
            <th>Status</th>
            <th>Type</th>

        */
        //step 1: creaitng our new elements
        let newTR = document.createElement("tr");
        let newTH = document.createElement("th");

        let newTD0 = document.createElement("td");
        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");
        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");
        let newTD6 = document.createElement("td");

        //step 2: populate our creations
        newTH.setAttribute("scope", "row");
        let myTextH = document.createTextNode(ourResponseObject[i].id);
        let myTextD0 = document.createTextNode(ourResponseObject[i].amount);
        // let myTextD1 = document.createTextNode(ourResponseObject[i].submitted);
        let date = new Date(ourResponseObject[i].submitted);
        // console.log("date",date);
        // console.log("date1",d.toLocaleString());
        // console.log("date2",d.toLocateDateString());

        let myTextD1 = document.createTextNode(date.toLocaleString());

        let myTextD2;
        if (ourResponseObject[i].resolved == null) myTextD2 = document.createTextNode("Not resolved yet");
        else{
            let date2 = new Date(ourResponseObject[i].resolved);
            myTextD2 = document.createTextNode(date2.toLocaleString())
        } 

        let myTextD3 = document.createTextNode(ourResponseObject[i].description);

        console.log("Image["+i+"] =",ourResponseObject[i].receipt);
        let myImgTag = document.createElement("img");
        myImgTag.setAttribute('height', 300);
        myImgTag.src = "data:image/jpeg;base64," + ourResponseObject[i].receipt;


        let myTextD5;
        if (ourResponseObject[i].statusId == 1) myTextD5 = document.createTextNode('Pending');
        else if (ourResponseObject[i].statusId == 2) myTextD5 = document.createTextNode('Approved');
        else if (ourResponseObject[i].statusId == 3) myTextD5 = document.createTextNode('Denied');
        
        let myTextD6;
        if (ourResponseObject[i].typeId == 1) myTextD6 = document.createTextNode('Lodging');
        else if (ourResponseObject[i].typeId == 2) myTextD6 = document.createTextNode('Travel');
        else if (ourResponseObject[i].typeId == 3) myTextD6 = document.createTextNode('Food');
        else if (ourResponseObject[i].typeId == 4) myTextD6 = document.createTextNode('Other');

        //all appending
        newTH.appendChild(myTextH);
        newTD0.appendChild(myTextD0);
        newTD1.appendChild(myTextD1);
        newTD2.appendChild(myTextD2);
        newTD3.appendChild(myTextD3);
        newTD4.appendChild(myImgTag);
        newTD5.appendChild(myTextD5);
        newTD6.appendChild(myTextD6);

        newTR.appendChild(newTH);
        newTR.appendChild(newTD0);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD6);

        let newSelection = document.querySelector("#ticketTableBody");
        newSelection.appendChild(newTR);
    }
}

function setName(ourResponseObject){
    document.getElementById("welcomeName").innerText = "Welcome, "+ourResponseObject.name + " "+ourResponseObject.last;

}