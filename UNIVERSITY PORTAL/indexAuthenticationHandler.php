<?php 
	/*** File authenticates students in database ***/

	/* Session creation */
	session_start();

	/* import database connector file */
	include 'Connector.php';

	/* validation of posted data plus MYSQL sanitization */
	if(isset($_POST['username']) && isset($_POST['pwd'])) {

		/* sanitization and variable declarations */
		$username = filter_var(strtoupper($_POST['username']),FILTER_SANITIZE_STRING);
		$password = filter_var($_POST['pwd'],FILTER_SANITIZE_STRING);

		/** ensure no duplicate insert entries **/
		$query1 = "SELECT `Adm_no`,`Password` FROM student_details WHERE `Adm_no`= ? AND `Password` = ?";

		/* prepare the statement for execution */
		$stmt1= $conn->prepare($query1);

		/* bind the parameters */
		$stmt1->bind_param("ss",$username,$password);

		/* execute the statements */
		$stmt1->execute();

		/* store resultset in buffer to use in validation in line 50 */
		$stmt1->store_result();

		/* checking if the input data is in database */
		if( $stmt1->num_rows() !== 0 ) {

			$data["valid"] = "present"; // the student is registered
			$data["header"] = "Semester_details.php"; // relocate to this file
			/* response message encoding */
			echo json_encode($data); // send the data as JSON 			

		} // end if  

		else {

			$data["valid"] = "absent"; // the student is not registered
			/* response message encoding */
			echo json_encode($data); // send the data as JSON

		} // end else

		/* close statements */
		$stmt1->close();

	} // end validation if


?>