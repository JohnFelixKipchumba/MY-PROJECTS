<?php
    /*** File deals with the displaying of students transcript for a selected year ***/

    /* Session creation */
    session_start();
                
    require('fpdf/fpdf.php'); // import fpdf package

    class PDF extends FPDF {

        /* function for writing details to a pdf file */
        function printTranscript($header) {

            include 'Connector.php'; // import database connector file

            /* Post variables initialization */
            //$_SESSION['transcriptYear'] = "";
            //$_POST['transcriptYear'] = $_SESSION['transcriptYear'];
            $_POST['transcriptYear'] = 1;

            /* validation of posted data plus MYSQL sanitization */
            if(isset($_POST['transcriptYear'])) {
                /* sanitization and variable declarations */
                $year = filter_var($_POST['transcriptYear'],FILTER_SANITIZE_STRING);
                $regno = $_SESSION['RegNo'];

                /* Session variables initialization */
                //$_SESSION['transcriptYear'] = $year;

                /* select a record from database using page 7 details in query */
                $query="SELECT * FROM view_transcript WHERE `Adm_no` = ? AND `Yr_study` = ? ";

                /* prepare the statement for execution */
                $stmt = $conn->prepare($query);
            
                /* bind the parameters */
                $stmt->bind_param("si",$regno,$year);

                /* execute the statements */
                $stmt->execute();

                /* bind the result parameters */
                $stmt->bind_result($admno,$fname,$lname,$cardNo,$unitCode,$unitName,$grade,$yr);

                /* cycle through the results and store in an array for outputting to pdf file */
                $data = array(array());
                $count = 0; // array index counter
                while($stmt->fetch() && $count < 14) {

                    for($i=0; $i < 7; $i++) {

                        $data[$count][0] = $admno;
                        $data[$count][1] = $fname;
                        $data[$count][2] = $lname;
                        $data[$count][3] = $cardNo;
                        $data[$count][4] = $unitCode;
                        $data[$count][5] = $unitName;
                        $data[$count][6] = $grade;

                    } // end for

                    $count++;

                } // end while

                /* close statements */
                $stmt->close();

                /* close DB connection */
                //$conn->close;

                /* formatting of the pdf file */
                $this->SetFont('Arial','B',24);
                $this->Cell(200,30,'Student Provisional Transcript',0,0,'C',0);
                $this->SetFontSize(14);
                $this->SetFont('Arial','',14);
                $this->SetXY(10,40);
                $this->Write(5,'Registration Number: '.$admno);
                $this->SetXY(10,45);
                $this->Write(5,'Name: '.$fname.' '.$lname);
                $this->SetXY(10,50);
                $this->Write(5,'Year of study: '.$year);
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

                for($k=0; $k < 7; $k++) {
                    $this->Cell($w[0],6,$data[$k][3],'LR',0,'L',$fill);
                    $this->Cell($w[1],6,$data[$k][4],'LR',0,'L',$fill);
                    $this->Cell($w[2],6,$data[$k][5],'LR',0,'L',$fill);
                    $this->Cell($w[3],6,$data[$k][6],'LR',0,'C',$fill);
                    $this->Ln();
                    $fill = !$fill;

                } // end for
                /* closing line */
                $this->Cell(array_sum($w),0,'','T');

            } // end validation if

        } // end function printTranscript           

    } // end class PDF         

    ob_end_clean();
    $pdf = new PDF();
    /* column headings */
    $header = array('Exam Card No','Unit Code','Unit Name','Grade');

    $pdf->SetFont('Arial','',14);
    $pdf->AddPage();   
    $pdf->printTranscript($header);    
    $pdf->Output('Student Transcript.pdf','I');

?>
