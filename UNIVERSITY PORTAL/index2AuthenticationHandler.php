<?php 
	/*** File authenticates staff in database ***/

	/* Session creation */
	session_start();

	/* import database connector file */
	include 'Connector.php';

	/* validation of posted data plus MYSQL sanitization */
	if(isset($_POST['staffusername']) && isset($_POST['staffpwd'])) {

		/* sanitization and variable declarations */
		$username = filter_var($_POST['staffusername'],FILTER_SANITIZE_STRING);
		$password = filter_var($_POST['staffpwd'],FILTER_SANITIZE_STRING);

		/** ensure no duplicate insert entries **/
		$query1 = "SELECT `Staff_id`,`Password`,`Title` FROM staff_details WHERE `Staff_id`= ? AND `Password` = ?";

		/* prepare the statement for execution */
		$stmt1= $conn->prepare($query1);

		/* bind the parameters */
		$stmt1->bind_param("ss",$username,$password);

		/* execute the statements */
		$stmt1->execute();

		/* store resultset in buffer to use in validation */
		$stmt1->store_result();
        $stmt1->bind_result($staffid,$staffpwd,$stafftitle);

        $rows = [];
        while($stmt1->fetch()) {
            $rows[] = ['Staff_id'=>$staffid];
            $rows[] = ['Password'=>$staffpwd];
            $rows[] = ['Title'=>$stafftitle];
            //echo $serialNo;

        } // end while

		/* checking if the input data is in database */
		if( $stmt1->num_rows() !== 0 ) {
			/* check the staff title and relocate accordingly */
			if( $stafftitle === "Lecturer" ) {

				$data["valid"] = "present"; // the student is registered
				$data["header"] = "Results_details.php"; // relocate to this file
				/* response message encoding */
				echo json_encode($data); // send the data as JSON

			} // end if

			else if( $stafftitle === "Dean" ) {

				$data["valid"] = "present"; // the student is registered
				$data["header"] = "Units_management.php"; // relocate to this file
				/* response message encoding */
				echo json_encode($data); // send the data as JSON
				 
			} // end else			

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