<?php 
	/*** File establishes connection to the required database ***/

	/* connect to the database server */
	$conn = new mysqli('localhost','root','167456','UNIVERSITY_DB');

	/* if error display error message */
	if($conn->errno) {

		printf("Unable to connect to the database!<br /> %s", $conn->error);
		exit();

	} // end if
	else {

		//echo "Connected to database successfully!";

	} // end else

 ?>