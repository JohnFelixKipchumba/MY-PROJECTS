/********************************************************************************** 
* File containing various Javascript codes for handling events in the
* QBPL web pages.
***********************************************************************************
*/

/* Function handling focus event for 5_Homepage.html images */
	function changeImage(image) {
		var img_id= document.getElementById(image);
		switch(image) {
			case "img_1":
				img_id.src="assets/bitu5.jpg";
				break;
			case "img_2":
				img_id.src="assets/bitu8.jpg";
				break;
			case "img_3":
			    img_id.src="assets/bitu7.jpg";
			    break;
			case "img_4":
				img_id.src="assets/quality-bitumen-manufacturers-slow-medium-fast-curing-bitumen-cutback-kenya.jpg";
				break;	    			
		} // end switch

	} // end function changeImage

/* Function handling form actions for 5_Contacts.html page before submitting */	
	function formHandler() {
		var nameField= document.getElementById("name");
		var emailField= document.getElementById("email");
		var subjectField= document.getElementById("subject");
		/* if fields not set alert */
		if(nameField.value == "") {
			var message= "Your Name is Required to send.";
			alert(message);
			nameField.className="fields";
		} // end if 
		else if(emailField.value == "") {
			var message= "Your Email is Required to send.";
			alert(message);
			emailField.className="fields";
		} // end if
		else if(subjectField.value == "") {
			var message= "Your Subject title is Required to send.";
			alert(message);
			subjectField.className="fields";
		} // end if
		else {
			alert("Message Sent Successfully!");
		}
	} // end of form handler


