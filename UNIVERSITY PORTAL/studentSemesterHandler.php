<?php 
    /***** File handles student semester registration *******/

    /* Session creation */
    session_start();
        
    include 'Connector.php'; // import database connector file

    /* Session variables declarations */
    $_SESSION['coursename'] = "";
    $_SESSION['year'] = "";
    $_SESSION['semester'] = "";
    $_SESSION['unit1'] = ""; 
    $_SESSION['unit2'] = "";
    $_SESSION['unit3'] = "";
    $_SESSION['unit4'] = "";
    $_SESSION['unit5'] = "";
    $_SESSION['unit6'] = "";
    $_SESSION['unit7'] = "";
    $_SESSION['unit8'] = "";
    $_SESSION['unit1code'] = "";
    $_SESSION['unit2code'] = "";
    $_SESSION['unit3code'] = "";
    $_SESSION['unit4code'] = "";
    $_SESSION['unit5code'] = "";
    $_SESSION['unit6code'] = "";
    $_SESSION['unit7code'] = "";
    $_SESSION['unit8code'] = "";

    /* validation of posted data plus MYSQL sanitisation */
    if(isset($_POST['coursename']) && isset($_POST['year']) && isset($_POST['semester']) ) {

        /* sanitization and variable declarations */
        $coursename = filter_var(strtoupper($_POST['coursename']),FILTER_SANITIZE_STRING);
        $year = filter_var($_POST['year'],FILTER_SANITIZE_STRING);
        $semester = filter_var($_POST['semester'],FILTER_SANITIZE_STRING);

        /* Session variables initialisation */
        $regno = $_SESSION['RegNo'];
        $_SESSION['coursename'] = $coursename;
        $_SESSION['year'] = $year;
        $_SESSION['semester'] = $semester;

        /* insert sem details to database */
        $query = "INSERT INTO stud_sem_register (`Adm_no`,`Course_id`,`Yr_study`,`semester`,`Date`) VALUES(?,?,?,?,now())";

        /* prepare the statement for execution */
        $stmt = $conn->prepare($query);
    
        /* bind the parameters */
        $stmt->bind_param("ssii",$regno,$coursename,$year,$semester);

        /* execute the statements */
        $stmt->execute();

        /* close statements */
        $stmt->close();

        /***** section inserts the units for the registered sem into session variables */
        include 'Connector.php'; // import database connector file

        /* query selects from the view units_offered */
        $query1 = "SELECT * FROM units_offered WHERE Course_id = ? AND Yr_study = ? AND semester = ?;  ";

        /* prepare the statement for execution */
        $stmt1 = $conn->prepare($query1);
    
        /* bind the parameters */
        $stmt1->bind_param("sii",$coursename,$year,$semester);

        /* execute the statements */
        $stmt1->execute();

        /* bind the result parameters */
        $stmt1->bind_result($courseid,$unitCode,$unitName,$yr,$sem);

        /* cycle through the results and store in an array for outputting to session variables */
        $data = array(array());
        $count = 0; // array index counter
        while($stmt1->fetch() && $count < 8) {

            for($i=0; $i < 8; $i++) {

                $data[$count][0] = $courseid;
                $data[$count][1] = $unitCode;
                $data[$count][2] = $unitName;
                $data[$count][3] = $yr;
                $data[$count][4] = $sem;

            } // end for

            $count++;

        } // end while

        /* close statements */
        $stmt1->close();

        /* Session variables initialisation */
        $_SESSION['unit1'] = $data[0][1].$data[0][2]; // concatenation of unitCode and uinitName row 1
        $_SESSION['unit2'] = $data[1][1].$data[1][2]; // concatenation of unitCode and uinitName row 2
        $_SESSION['unit3'] = $data[2][1].$data[2][2]; // concatenation of unitCode and uinitName row 3
        $_SESSION['unit4'] = $data[3][1].$data[3][2]; // concatenation of unitCode and uinitName row 4
        $_SESSION['unit5'] = $data[4][1].$data[4][2]; // concatenation of unitCode and uinitName row 5
        $_SESSION['unit6'] = $data[5][1].$data[5][2]; // concatenation of unitCode and uinitName row 6
        $_SESSION['unit7'] = $data[6][1].$data[6][2]; // concatenation of unitCode and uinitName row 7
        $_SESSION['unit8'] = $data[7][1].$data[7][2]; // concatenation of unitCode and uinitName row 8
        $_SESSION['unit1code'] = $data[0][1]; // unitCode row 1
        $_SESSION['unit2code'] = $data[1][1]; // unitCode row 2
        $_SESSION['unit3code'] = $data[2][1]; // unitCode row 3
        $_SESSION['unit4code'] = $data[3][1]; // unitCode row 4
        $_SESSION['unit5code'] = $data[4][1]; // unitCode row 5
        $_SESSION['unit6code'] = $data[5][1]; // unitCode row 6
        $_SESSION['unit7code'] = $data[6][1]; // unitCode row 7
        $_SESSION['unit8code'] = $data[7][1]; // unitCode row 8

    } // end validation if

    //echo "{$_SESSION['unit1']}";
    //echo "{$_SESSION['unit1code']}";
     
?>