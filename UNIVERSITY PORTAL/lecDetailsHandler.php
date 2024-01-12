<?php
	/*** File inserts student details posted into database ***/

	/* Session creation */
	session_start();

	/* import database connector file */
	include 'Connector.php';

	/* validation of posted data plus MYSQL sanitization */
	if(isset($_POST['fname']) && isset($_POST['othername']) && isset($_POST['id']) && isset($_POST['phone']) && isset($_POST['email']) && isset($_POST['nationality']) && isset($_POST['title']) && isset($_POST['yos']) 
		&& isset($_POST['gender']) && isset($_POST['password'])) {

		/* sanitization and variable declarations */
		$fname = filter_var(ucfirst($_POST['fname']),FILTER_SANITIZE_STRING);
		$lname = filter_var(ucfirst($_POST['othername']),FILTER_SANITIZE_STRING);
		$id = filter_var($_POST['id'],FILTER_SANITIZE_STRING);
		$phone = filter_var($_POST['phone'],FILTER_SANITIZE_STRING);
		$email = filter_var(strtolower($_POST['email']),FILTER_SANITIZE_EMAIL);
		$nationality = filter_var(strtoupper($_POST['nationality']),FILTER_SANITIZE_STRING);
		$title = filter_var(strtoupper($_POST['title']),FILTER_SANITIZE_STRING);
		$yos = filter_var($_POST['yos'],FILTER_SANITIZE_STRING);
		$password = filter_var($_POST['password'],FILTER_SANITIZE_STRING);
		$gender = filter_var(strtoupper($_POST['gender']),FILTER_SANITIZE_STRING);
	
		/** ensure no duplicate insert entries **/
		$query1 = "SELECT `ID` FROM staff_details WHERE `ID`= ?";

		/* prepare the statement for execution */
		$stmt1= $conn->prepare($query1);

		/* bind the parameters */
		$stmt1->bind_param("i",$id);

		/* execute the statements */
		$stmt1->execute();

		/* store resultset in buffer to use in validation in line 42 */
		$stmt1->store_result();

		/* checking if the input data is in database already to avoid duplicates */
		if( $stmt1->num_rows() !== 0 ) {

			$data["present"] = "true";
			/* response message encoding */
			echo json_encode($data); // send the account is already in db as JSON 

			/* close statements */
			$stmt1->close();			

		} // end if 

		else {
			/* import database connector file */
			include 'Connector.php';

			/* Update database record using page 4 details in queries */
			$query2="INSERT INTO staff_details (`Password`,`Fname`,`Lname`,`ID`,`Gender`,`Phone`,`Email`,
			   `Nationality`,`Title`,`YoS`) VALUES(?,?,?,?,?,?,?,?,?,?)";

			/* prepare the statement for execution */
			$stmt2= $conn->prepare($query2);

			/* bind the parameters */
			$stmt2->bind_param("sssisisssi",$password,$fname,$lname,$id,$gender,$phone,$email,$nationality,$title,
				$yos);

			/* execute the statements */
			$stmt2->execute();

			/* close statements */
			$stmt2->close();

			/* import database connector file /
			include 'Connector.php'; */

			/* Retrieve the generated staff Id and store in session variable */
			$query3="SELECT `Staff_Id` FROM staff_details WHERE `ID`= ?";

			/* prepare the statement for execution */
			$stmt3= $conn->prepare($query3);

			/* bind the parameters */
			$stmt3->bind_param("i",$id);

			/* execute the statements */
			$stmt3->execute();

			/* bind the result parameters */
            $stmt3->bind_result($staffId);

            while($stmt3->fetch()) {

            	$data["staffId2"] = $staffId;
				/* response message encoding */
				echo json_encode($data); // send staffId as JSON 
            	
            } // end while

			/* close statements */
			$stmt3->close();

			
		} // end else

		/* close DB connection */
		//$conn->close;

	} // end validation if

	//echo "{$_SESSION['staffId']}";

?>	