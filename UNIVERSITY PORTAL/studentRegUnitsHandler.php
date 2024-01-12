<?php
	/***** File stores to database the selected units for a given year and semester after registration *****/
	
	/* Session creation */
    session_start();
        
    include 'Connector.php'; // import database connector file

    /* Post variables initialization */
    $_POST['coursename'] = $_SESSION['coursename'];
    $_POST['year'] = $_SESSION['year'];
    $_POST['semester'] = $_SESSION['semester'];

    /* validation of posted data plus MYSQL sanitisation */
    if(isset($_POST['coursename']) && isset($_POST['year']) && isset($_POST['semester']) && isset($_POST['check1'])
        && isset($_POST['check2']) && isset($_POST['check3']) && isset($_POST['check4']) && isset($_POST['check5'])
        && isset($_POST['check6']) && isset($_POST['check7']) ) {

        /* sanitization and variable declarations */
        $coursename = filter_var(strtoupper($_POST['coursename']),FILTER_SANITIZE_STRING);
        $year = filter_var($_POST['year'],FILTER_SANITIZE_STRING);
        $semester = filter_var($_POST['semester'],FILTER_SANITIZE_STRING);
        $unit1 = filter_var(strtoupper($_POST['check1']),FILTER_SANITIZE_STRING);
        $unit2 = filter_var(strtoupper($_POST['check2']),FILTER_SANITIZE_STRING);
        $unit3 = filter_var(strtoupper($_POST['check3']),FILTER_SANITIZE_STRING);
        $unit4 = filter_var(strtoupper($_POST['check4']),FILTER_SANITIZE_STRING);
        $unit5 = filter_var(strtoupper($_POST['check5']),FILTER_SANITIZE_STRING);
        $unit6 = filter_var(strtoupper($_POST['check6']),FILTER_SANITIZE_STRING);
        $unit7 = filter_var(strtoupper($_POST['check7']),FILTER_SANITIZE_STRING);
        //$unit8 = filter_var(strtoupper($_POST['check8']),FILTER_SANITIZE_STRING);

        /* Session variables initialisation */
        $regno = $_SESSION['RegNo'];

        /****** insert unit details to database *****/
        $query = "INSERT INTO stud_registered_units (`Adm_no`,`Course_id`,`Yr_study`,`semester`,`Unit_code1`,
            `Unit_code2`,`Unit_code3`,`Unit_code4`,`Unit_code5`,`Unit_code6`,`Unit_code7`,`Date`)  
            VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";

        /* prepare the statement for execution */
        $stmt = $conn->prepare($query);
    
        /* bind the parameters */
        $stmt->bind_param("ssiisssssss",$regno,$coursename,$year,$semester,$unit1,$unit2,$unit3,$unit4,$unit5,
            $unit6,$unit7);

        /* execute the statements */
        $stmt->execute();

        /* close statements */
        $stmt->close();

        /****** getting of serial_no from database ******/
        include 'Connector.php'; // import database connector file

        $query1 = "SELECT `Serial_no` FROM stud_registered_units WHERE `Adm_no` = ?";

        /* prepare the statement for execution */
        $stmt1 = $conn->prepare($query1);
    
        /* bind the parameters */
        $stmt1->bind_param("s",$regno);

        /* execute the statements */
        $stmt1->execute();

        /* store the result */
        $stmt1->store_result();
        $stmt1->bind_result($serialNo);

        $rows = [];
        while($stmt1->fetch()) {
            $rows[] = ['Serial_no'=>$serialNo];

        } // end while

        /* close statements */
        $stmt1->close();

        /******* insertion of exam card no. to database *******/
        include 'Connector.php'; // import database connector file

        $query2 = "UPDATE stud_registered_units SET `Exam_card_no` = generateExamCardNo($serialNo) 
            WHERE `Adm_no` = ? ";

        /* prepare the statement for execution */
        $stmt2 = $conn->prepare($query2);
    
        /* bind the parameters */
        $stmt2->bind_param("s",$regno);

        /* execute the statements */
        $stmt2->execute();

        /* close statements */
        $stmt2->close();


    } // end validation if 

        
?>