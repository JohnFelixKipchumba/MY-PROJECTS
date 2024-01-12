<?php
	/**** File registers student exam and cat marks into database *****/
	
	/* Session creation */
	session_start();

	/* import database connector file */
	include 'Connector.php';

	/* validation of posted data plus MYSQL sanitization */
	if(isset($_POST['firstname']) && isset($_POST['othername']) && isset($_POST['regno']) && isset($_POST['unitCode']) && isset($_POST['catMark']) && isset($_POST['examMark']) && isset($_POST['examCardNo'])) {

		/* sanitization and variable declarations */
		$fname = filter_var(ucfirst($_POST['firstname']),FILTER_SANITIZE_STRING);
		$lname = filter_var(ucfirst($_POST['othername']),FILTER_SANITIZE_STRING);
		$regno = filter_var(strtoupper($_POST['regno']),FILTER_SANITIZE_STRING);
		$unitCode = filter_var(strtoupper($_POST['unitCode']),FILTER_SANITIZE_STRING);
		$catMark = filter_var($_POST['catMark'],FILTER_SANITIZE_STRING);
		$examMark = filter_var($_POST['examMark'],FILTER_SANITIZE_STRING);
		$examCardNo = filter_var(strtoupper($_POST['examCardNo']),FILTER_SANITIZE_STRING);

		/* Update database record using page 5 details in queries */
		$query="INSERT INTO results_details (`Exam_card_no`,`Adm_no`,`Unit_code`,`CAT_mark`,`Exam_mark`,`Total`,`Grade`,`serial_no`) VALUES(?,?,?,?,?,calcTotal(`CAT_mark`,`Exam_mark`),calcGrade(`CAT_mark`,`Exam_mark`),generateSerialNo(`Exam_card_no`,`Unit_code`))";

		/* prepare the statement for execution */
		$stmt= $conn->prepare($query);

		/* bind the parameters */
		$stmt->bind_param("sssii",$examCardNo,$regno,$unitCode,$catMark,$examMark);

		/* execute the statements */
		$stmt->execute();

		/* close statements */
		$stmt->close();

	} // end validation if

?>



