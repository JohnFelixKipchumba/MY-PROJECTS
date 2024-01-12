<?php 
	/*** File deals with student semester details ***/

    /* Session creation */
    session_start();

?>    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Semester Details</title>
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
                    <h3 class="mb-0 text-dark text-center">Register for the Semester</h3><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-12">       
                <!-- Student sem, unit and exam card registrations form -->
                <form class="needs-validation" id="student_semReg" method="POST" role="form" novalidate>
                    <div class="row">
                        <div class="col-lg-1"></div> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="Year" >Year of Study</label>
                                <select class="form-control text-dark" name="year" id="year" required >
                                    <option value=""></option>
                                    <option value="1">Year 1</option>
                                    <option value="2">Year 2</option>
                                    <option value="3">Year 3</option>
                                    <option value="4">Year 4</option>
                                </select>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>
                        </div> 
                        <div class="col-lg-2"></div>    
                        <div class="col-lg-4"> 
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="semester" >Semester</label>
                                <select class="form-control text-dark" name="semester" id="semester" required >
                                    <option value=""></option>
                                    <option value="1">Semester 1</option>
                                    <option value="2">Semester 2</option>     
                                </select>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>
                        </div> 
                        <div class="col-lg-1"></div>      
                    </div>
                    <div class="row">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-4">                
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="coursename" >Course Name</label>
                                <select class="form-control text-dark" name="courseName" id="coursename" required >
                                    <option value=""></option>
                                    <option value="CCS">Computer Science</option>
                                    <option value="ARC">Acturial Science</option>
                                    <option value="BEC">Economics</option> 
                                    <option value="AFD">Agricultural Extension</option> 
                                    <option value="GNR">Geography And Natural Resources Management</option>
                                    <option value="CMR">Criminology</option>
                                    <option value="PLS">Political Science</option>
                                    <option value="CIT">Information Technology</option>
                                </select><br/>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7"></div>
                    </div>
                    <div class="row">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label class="form-control-label text-danger" for="Regsem" >Click to register for the semester</label>
                                <button type="button" class="btn btn-success btn-lg btn-block" id="Regsem" name="Regsem">Register Semester</button>
                            </div>
                        </div>
                        <div class="col-lg-5"></div>
                    </div>
                    <!-- create a horizontal rule -->
                    <hr/><br/>

                    <div class="row">
                        <div class="col-12">
                            <h3 class="mb-0 text-dark text-center">Select Units to Register</h3><br/>
                        </div>
                    </div>

                    <div class="row" id="checkers" >
                		<!-- this section is made visible after registering for the semester -->
                        <div class="col-12">
                            <div class="checkbox"> 
                                <div class="row"> 
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-4">
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check1" id="check1" value="<?php echo "{$_SESSION['unit1code']}"; ?>" required /><?php echo "{$_SESSION['unit1']}"; ?></label>	
                                    </div>
                                    <div class="invalid-feedback">
                                            Required Field
                                        </div> 
                                    <div class="col-lg-2"></div>     
                                    <div class="col-lg-4"> 
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check2" id="check2" value="<?php echo "{$_SESSION['unit2code']}"; ?>" required /><?php echo "{$_SESSION['unit2']}"; ?></label>
                                        <div class="invalid-feedback">
                                            Required Field
                                        </div> 
                                    </div> 
                                    <div class="col-lg-1"></div>     
                                </div>

                                <div class="row">
                                    <div class="col-lg-1"></div> 
                                    <div class="col-lg-4">
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check3" id="check3" value="<?php echo "{$_SESSION['unit3code']}"; ?>" required /><?php echo "{$_SESSION['unit3']}"; ?></label>
                                        <div class="invalid-feedback">
                                            Required Field
                                        </div>	
                                    </div> 
                                    <div class="col-lg-2"></div>    
                                    <div class="col-lg-4"> 
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check4" id="check4" value="<?php echo "{$_SESSION['unit4code']}"; ?>" required /><?php echo "{$_SESSION['unit4']}"; ?></label>
                                        <div class="invalid-feedback">
                                            Required Field
                                        </div> 
                                    </div> 
                                    <div class="col-lg-1"></div>      
                                </div>
                                
                                <div class="row">
                                    <div class="col-lg-1"></div> 
                                    <div class="col-lg-4">
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check5" id="check5" value="<?php echo "{$_SESSION['unit5code']}"; ?>" required /><?php echo "{$_SESSION['unit5']}"; ?></label>
                                        <div class="invalid-feedback">
                                            Required Field
                                        </div>	
                                    </div> 
                                    <div class="col-lg-2"></div>    
                                    <div class="col-lg-4"> 
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check6" id="check6" value="<?php echo "{$_SESSION['unit6code']}"; ?>" required /><?php echo "{$_SESSION['unit6']}"; ?></label>
                                        <div class="invalid-feedback">
                                            Required Field
                                        </div> 
                                    </div> 
                                    <div class="col-lg-1"></div>      
                                </div>

                                <div class="row"> 
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-4">
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check7" id="check7" value="<?php echo "{$_SESSION['unit7code']}"; ?>" required /><?php echo "{$_SESSION['unit7']}"; ?></label>
                                        <div class="invalid-feedback">
                                            Required Field
                                        </div>  
                                    </div> 
                                    <div class="col-lg-2"></div>     
                                    <div class="col-lg-4"> 
                                        <label class="form-control-label text-dark"><input type="checkbox" name="check8" id="check8" value="<?php echo "{$_SESSION['unit8code']}"; ?>" /><?php echo "{$_SESSION['unit8']}"; ?></label> 
                                    </div> 
                                    <div class="col-lg-1"></div>    
                                </div>
                            </div>
                    	</div><br/>
                    </div>    
                    <div class="row">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label class="form-control-label text-danger" for="Regunit" >Click to register the selected units</label>
                                <button type="button" class="btn btn-success btn-lg btn-block" id="Regunit" value="">Register Units</button><br/>
                            </div>
                        </div>
                        <div class="col-lg-5"></div>
                    </div>
                    <!-- create a horizontal rule -->
                    <hr/><br/>

                    <div class="row">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label class="form-control-label text-danger" for="GenExamcard" >Click to generate exam card</label>
                                <button type="button" class="btn btn-primary btn-lg btn-block" id="GenExamcard" value="">Exam Card</button>                            
                            </div>
                        </div>
                        <div class="col-lg-5"></div>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>




