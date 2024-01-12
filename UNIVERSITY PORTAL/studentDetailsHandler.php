<?php
	/*** File inserts student details posted into database ***/

	/* Session creation */
	session_start();

	/* import database connector file */
	include 'Connector.php';

	/* Session variables declarations */
	$_SESSION['RegNo'] = "";
	$_SESSION['courseid'] = "";
	$_SESSION['fname'] = "";
	$_SESSION['lname'] = "";

	/* validation of posted data plus MYSQL sanitization */
	if(isset($_POST['studfname']) && isset($_POST['studlname']) && isset($_POST['studgender']) && 
		isset($_POST['studid']) && isset($_POST['studphone']) && isset($_POST['studemail']) && 
		isset($_POST['studnation']) && isset($_POST['studctrg']) && isset($_POST['studadyr']) && 
		isset($_POST['studcourseid']) && isset($_POST['studregno']) && isset($_POST['studpassword'])) {

		/* sanitization and variable declarations */
		$fname = filter_var(ucfirst($_POST['studfname']),FILTER_SANITIZE_STRING);
		$lname = filter_var(ucfirst($_POST['studlname']),FILTER_SANITIZE_STRING);
		$id = filter_var($_POST['studid'],FILTER_SANITIZE_STRING);
		$gender = filter_var(strtoupper($_POST['studgender']),FILTER_SANITIZE_STRING);
		$phone = filter_var($_POST['studphone'],FILTER_SANITIZE_STRING);
		$email = filter_var(strtolower($_POST['studemail']),FILTER_SANITIZE_EMAIL);
		$nationality = filter_var(strtoupper($_POST['studnation']),FILTER_SANITIZE_STRING);
		$courseid = filter_var(strtoupper($_POST['studcourseid']),FILTER_SANITIZE_STRING);
		$carteg = filter_var(strtoupper($_POST['studctrg']),FILTER_SANITIZE_STRING);
		$admYr = filter_var($_POST['studadyr'],FILTER_SANITIZE_STRING);
		$regno = filter_var(strtoupper($_POST['studregno']),FILTER_SANITIZE_STRING);
		$password = filter_var($_POST['studpassword'],FILTER_SANITIZE_STRING);
		$gender = filter_var($_POST['studgender'],FILTER_SANITIZE_STRING);

		/* Session variables initialisation */
		$_SESSION['RegNo'] = $regno;
		$_SESSION['courseid'] = $courseid;
		$_SESSION['fname'] = $fname;
		$_SESSION['lname'] = $lname;
	
		/** ensure no duplicate insert entries **/
		$query1 = "SELECT `Adm_no` FROM student_details WHERE `Adm_no`= ?";

		/* prepare the statement for execution */
		$stmt1= $conn->prepare($query1);

		/* bind the parameters */
		$stmt1->bind_param("s",$regno);

		/* execute the statements */
		$stmt1->execute();

		/* store resultset in buffer to use in validation in line 50 */
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

			/* Update database record using page 2 details in queries */
			$query2="INSERT INTO student_details (`Adm_no`,`Password`,`ID`,`Fname`,`Lname`,`Gender`,`Phone`,`Email`,`Nationality`,`Course_id`,`Cartegory`,`Admission_yr`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

			/* prepare the statement for execution */
			$stmt2= $conn->prepare($query2);

			/* bind the parameters */
			$stmt2->bind_param("ssisssissssi",$regno,$password,$id,$fname,$lname,$gender,$phone,$email,$nationality,$courseid,$carteg,$admYr);

			/* execute the statements */
			$stmt2->execute();

			$data["present"] = "false";
			/* response message encoding */
			echo json_encode($data); // send the account is already in db as JSON

			/* close statements */
			$stmt2->close();

		} // end else

		/* close DB connection */
		//$conn->close;

	} // end validation if

?>	