<?php 
	/*** File deals with viewing of results ***/

    /* Session creation */
    session_start();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>View Transcript</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="bootstrap-4.6.1-dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <!-- JQuery library load -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-3.6.0.js"></script>
    <!-- JQuery file -->
    <script type="text/javascript" src="FormsHandler.js"></script>

    <style>
          .bg-color{
              background-color: #f9fbfd;
          }
    </style>

</head>
<body>
    <div class="main-content" id="panel">
        <!-- Page content -->
        <div class="container-fluid mt--6 bg-color">
            <div class="row">
                <div class="col-lg-12">
                    <nav class="navbar navbar-default bg-warning" role="navigation">
                        <div class="navbar-header">
                            <!--<div class="row">-->
                            <ul class="nav nav-pills">
                                
                                <div class="col-lg-4">                       
                                    <li class="nav-item"><a class="nav-link btn-block btn-primary" role="button" href="student_details.php">Home</a></li>
                                </div>
                                <div class="col-lg-4"> 
                                    <li class="nav-item"><a class="nav-link btn-block btn-primary" role="button" href="Semester_details.php">Semester</a></li>
                                </div> 
                                <div class="col-lg-4">
                                    <li class="nav-item"><a class="nav-link btn-block btn-primary" role="button" href="Results_viewer.php">Results</a></li> 
                                </div>
                                
                            </ul><br>
                            <!--</div>-->
                        </div>
                    </nav>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <h3 class="mb-0 text-dark text-center">Choose Year to View Results</h3><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-12">       
                <!-- Student transcript details form -->   
                <form class="needs-validation" id="student_transcript" method="POST" role="form" novalidate>
                    <div class="row">
                        <div class="col-lg-3"></div> 
                        <div class="col-lg-6">
                            <div class="form-group">
                                <select class="form-control text-dark" name="transcriptYear" id="transcriptYear" required >
                                    <option value=""></option>
                                    <option value="1">Year 1</option>
                                    <option value="2">Year 2</option>
                                    <option value="3">Year 3</option>
                                    <option value="4">Year 4</option>
                                </select><br/><br/>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>      
                        </div>
                        <div class="col-lg-3"></div>
                    </div> 
                    <div class="row">   
                        <div class="col-lg-3"></div> 
                        <div class="col-lg-6">     
                            <button type="button" class="btn btn-primary btn-lg btn-block" id="viewTranscript" value="" >View Transcript</button><br/>
                        </div>
                        <div class="col-lg-3"></div>
                    </div>    
                </form>
                <footer class="footer bg-success">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="hidden"></div>
                        </div>
                    </div>
                </footer>
                </div>
            </div>
        </div>
    </div>

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>

