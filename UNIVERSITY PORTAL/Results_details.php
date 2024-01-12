<?php 
    /*** File deals with lecture input of student results ***/

    /* Session creation */
    session_start();

?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Results Details</title>
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
	<!-- Main content -->
    <div class="main-content" id="panel">
    	<!-- Page content -->
        <div class="container-fluid mt--6 bg-color">
            <div class="row">
            	<div class="col-12">
                    <br/><h3 class="mb-0 text-dark text-center">STUDENT RESULTS DETAILS</h3><br/>
                </div>
            </div>
            <div class="row">
            	<div class="col-12">
            	<!-- Student results details form  -->
                <form class="needs-validation" id="results_details" method="POST" role="form" novalidate>
                	<div class="row">
                        <div class="col-lg-3">
                			<div class="form-group">
                				<label class="form-control-label text-dark" for="firstname" >First Name</label> 
             	 				<input type="text" class="form-control text-dark" id="firstname" placeholder="" required/> 
             	 				<div class="invalid-feedback">
                            		Required Field
                	 			</div>
                	 		</div>
                	 	</div>
                	 	<div class="col-3"></div>		
                        <div class="col-lg-3">
                        	<div class="form-group">	
                	 			<label class="form-control-label text-dark" for="othername" >Other Name</label> 
            	 				<input type="text" class="form-control text-dark" id="othername" placeholder="" required/> 
                	 			<div class="invalid-feedback">
                            		Required Field
                        		</div>
                        	</div>  
                		</div>
                		<div class="col-3"></div>
                	</div>
                	<div class="row">
                        <div class="col-lg-3">
                			<div class="form-group">
                				<label class="form-control-label text-dark" for="regno" >Registration No</label> 
             	 				<input type="text" class="form-control text-dark" id="regno" placeholder="" required/> 
             	 				<div class="invalid-feedback">
                            		Required Field
                	 			</div>
                	 		</div>
                	 	</div>
                	 	<div class="col-3"></div>		
                        <div class="col-lg-3">
                        	<div class="form-group">	
                	 			<label class="form-control-label text-dark" for="unitCode" >Unit Code</label> 
            	 				<input type="text" class="form-control text-dark" id="unitCode" placeholder="" required/> 
                	 			<div class="invalid-feedback">
                            		Required Field
                        		</div>
                        	</div>  
                		</div>
                		<div class="col-lg-3"></div>
                	</div>
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="examCardNo" >Exam Card No</label> 
                                <input type="text" class="form-control text-dark" name="examCardNo" id="examCardNo" required/> 
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9"></div>       
                    </div>
                	<h4 class="heading-small text-danger mb-4">Input Student CAT Mark</h4>
                	<div class="row">
                		<div class="col-lg-3">
                			<div class="form-group">
                				<!-- <label class="form-control-label text-dark" for="catMark" >CAT Mark</label> --> 
             	 				<input type="text" class="form-control text-dark" id="catMark" placeholder="" required/> 
             	 				<div class="invalid-feedback">
                            		Required Field
                	 			</div>
                	 		</div>
                	 	</div>
                	 	<div class="col-9"></div>	
                	</div>
                	<h4 class="heading-small text-danger mb-4">Input Student Exam Mark</h4>
                	<div class="row">
                		<div class="col-lg-3">
                			<div class="form-group">
                				<!-- <label class="form-control-label text-dark" for="examMark" >Exam Mark</label> --> 
             	 				<input type="text" class="form-control text-dark" id="examMark" placeholder="" required/><br/> 
             	 				<div class="invalid-feedback">
                            		Required Field
                	 			</div>
                	 		</div>
                	 	</div>
                	 	<div class="col-9"></div>	
                	</div>
                	<div class="row">
                        <div class="col-lg-3">
                            <button type="button" id="submitResults" name="submitResults" class="btn btn-primary btn-lg btn-block" value="" >Submit Results</button>
                        </div>
                        <div class="col-lg-1"></div>
                        <div class="col-lg-3">
                            <button type="button" id="updateResults" name="updateResults" class="btn btn-danger btn-lg btn-block" >Update Results</button>
                        </div>
                        <div class="col-lg-1"></div>
                        <div class="col-lg-3">
                        	<button type="reset" id="clearResults" name="clearResults" class="btn btn-info btn-lg btn-block" >Clear</button>
                        </div>	
                        <div class="col-lg-1"></div>
                	</div>	
                </form>
                <br/>
                </div>
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

	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>

