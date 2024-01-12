<?php
	/**** File registers a new unit into database *****/
	
	/* Session creation */
	session_start();

	/* import database connector file */
	include 'Connector.php';

	/* validation of posted data plus MYSQL sanitization */
	if(isset($_POST['unitName']) && isset($_POST['unitCode']) && isset($_POST['courseId']) && isset($_POST['yos']) && isset($_POST['sem'])) {

		/* sanitization and variable declarations */
		$unitName = filter_var(ucfirst($_POST['unitName']),FILTER_SANITIZE_STRING);
		$courseId = filter_var(strtoupper($_POST['courseId']),FILTER_SANITIZE_STRING);
		$unitCode = filter_var(strtoupper($_POST['unitCode']),FILTER_SANITIZE_STRING);
		$yos = filter_var($_POST['yos'],FILTER_SANITIZE_STRING);
		$sem = filter_var($_POST['sem'],FILTER_SANITIZE_STRING);

		/* Update database record using page 6 details in queries */
		$query="DELETE FROM unit_info WHERE `Unit_code` = ?";

		/* prepare the statement for execution */
		$stmt= $conn->prepare($query);

		/* bind the parameters */
		$stmt->bind_param("s",$unitCode);

		/* execute the statements */
		$stmt->execute();

		/* close statements */
		$stmt->close();

	} // end validation if

?>



