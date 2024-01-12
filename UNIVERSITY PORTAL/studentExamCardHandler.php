<?php
    /*** File generates the exam card after successfull unit and semester registration ***/

    /* Session creation */
    session_start();

    require('fpdf/fpdf.php'); // import fpdf package

    class Pdf extends FPDF {

        /* function generates student's exam card */
        function printExamCard($header) {

            include 'Connector.php'; // import database connector file

            /* Post variables initialization */
            $_POST['coursename'] = $_SESSION['coursename'];
            $_POST['year'] = $_SESSION['year'];
            $_POST['semester'] = $_SESSION['semester'];

            /* validation of posted data plus MYSQL sanitisation */
            if(isset($_POST['coursename']) && isset($_POST['year']) && isset($_POST['semester']) ) {

                /* sanitization and variable declarations */
                $coursename = filter_var(strtoupper($_POST['coursename']),FILTER_SANITIZE_STRING);
                $year = filter_var($_POST['year'],FILTER_SANITIZE_STRING);
                $semester = filter_var($_POST['semester'],FILTER_SANITIZE_STRING);

                /* Session variables initialization */
                $regno = $_SESSION['RegNo'];
                $fname = $_SESSION['fname'];
                $lname = $_SESSION['lname'];
                                                            
                /* select a record from database using page 7 details in query */
                $query3="SELECT `Adm_no`, `Course_name`, `Yr_study`, `semester`, `Exam_card_no`, `Unit_code`, `Unit_name` FROM view_examcard WHERE `Adm_no` = ? AND `Yr_study` = ? AND `semester` = ? ";

                /* prepare the statement for execution */
                $stmt3 = $conn->prepare($query3);
                               
                /* bind the parameters */
                $stmt3->bind_param("sii",$regno,$year,$semester);

                /* execute the statements */
                $stmt3->execute();

                /* bind the result parameters */
                $stmt3->bind_result($admno,$coursename,$yr,$sem,$cardNo,$unitCode,$unitName);

                /* cycle through the results and store in an array for outputting to pdf file */
                $data = array(array());
                $count = 0; // array index counter
                while($stmt3->fetch() && $count < 8) {

                    for($i=0; $i < 8; $i++) {

                        $data[$count][0] = $admno;
                        $data[$count][1] = $coursename;
                        $data[$count][2] = $yr;
                        $data[$count][3] = $sem;
                        $data[$count][4] = $cardNo;
                        $data[$count][5] = $unitCode;
                        $data[$count][6] = $unitName;
                        $data[$count][7] = "--------";

                    } // end for 

                    $count++;

                } // end while                   

                /* close statements */
                $stmt3->close();

                /* formatting of the pdf file */
                $this->SetFont('Arial','B',24);
                $this->Cell(200,30,'Student Exam Card',0,0,'C',0);
                $this->SetFontSize(14);
                $this->SetFont('Arial','',14);
                $this->SetXY(10,40);
                $this->Write(5,'Registration Number: '.$admno);
                $this->SetXY(10,45);
                $this->Write(5,'Name: '.$fname.' '.$lname);
                $this->SetXY(10,50);
                $this->Write(5,'Course Name: '.$coursename);
                $this->SetXY(10,55);
                $this->Write(5,'Year of study: '.$yr);
                $this->SetXY(100,55);
                $this->Write(5,'Semester: '.$sem);
                $this->SetXY(10,70);

                /* colors, line width and bold font */
                $this->SetFillColor(255,0,0);
                $this->SetTextColor(255);
                $this->SetDrawColor(128,0,0);
                $this->SetLineWidth(.3);
                $this->SetFont('','B');
                /* header */
                $w = array(40,35,80,20);
                for($i=0; $i < count($header); $i++) {
                    $this->Cell($w[$i],7,$header[$i],1,0,'C',true);
                } // end for

                $this->Ln();
                /* color and font restoration */
                $this->SetFillColor(224,235,255);
                $this->SetTextColor(0);
                $this->SetFont('');
                /* data */
                $fill = false;


                for($j=0; $j < 7; $j++) { 
                    
                    $this->Cell($w[0],6,$data[$j][4],'LR',0,'L',$fill);
                    $this->Cell($w[1],6,$data[$j][5],'LR',0,'L',$fill);
                    $this->Cell($w[2],6,$data[$j][6],'LR',0,'L',$fill);
                    $this->Cell($w[3],6,$data[$j][7],'LR',0,'C',$fill);
                    $this->Ln();
                    $fill = !$fill;
                
                } // end for
                /* closing line */
                $this->Cell(array_sum($w),0,'','T');

            } // end validation if

        } // end function printExamCard 

    } // end class Pdf

    ob_end_clean();
    $pdf = new PDF();
    /* column headings */
    $header = array('Exam Card No','Unit Code','Unit Name','Sign');

    $pdf->SetFont('Arial','',14);
    $pdf->AddPage();   
    $pdf->printExamCard($header);    
    $pdf->Output('Student Exam Card.pdf','I');

?>