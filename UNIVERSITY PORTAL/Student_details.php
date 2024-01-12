<?php 
	/*** File deals with student details ***/

    /* Session creation */
    session_start();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Student Details</title>
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
                    <br/><h3 class="mb-0 text-dark text-center">Fill in your Personal  Details</h3><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-12">       
                <!-- Student personal details form -->   
                <form class="needs-validation" id="student_details" method="POST" role="form" novalidate>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studfname" >First Name</label>
                                <input type="Text" class="form-control text-dark" name="studfname" id="studfname" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>      
                        </div> 
                        <div class="col-lg-2"></div>    
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studlname" >Other Name</label> 
                                <input type="Text" class="form-control text-dark" name="studlname" id="studlname" required /> 
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div> 
                        </div> 
                        <div class="col-lg-2"></div>      
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studid" >ID</label>
                                <input type="number" class="form-control text-dark" name="studid" id="studid" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>     
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studphone" >Phone</label> 
                                <input type="number" class="form-control text-dark" name="studphone" id="studphone" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>      
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studemail" >Email</label>
                                <input type="email" class="form-control text-dark" name="studemail" id="studemail" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>  
                            </div>
                        </div> 
                        <div class="col-lg-2"></div>    
                        <div class="col-lg-4"> 
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studregno" >Registration Number</label>
                                <input type="Text" class="form-control text-dark" name="studregno" id="studregno" placeholder="eg. ABC/00107/019" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>      
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studctrg" >Cartegory</label>
                                <select class="form-control text-dark" name="studctrg" id="studctrg" required >
                                    <option value=""></option>
                                    <option value="KUCCPS">KUCCPS</option>
                                    <option value="SELF-SPONSORED">SELF-SPONSORED</option>
                                </select>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>     
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studcourseid" >Course Name</labe> 
                                <select class="form-control text-dark" name="studcourseid" id="studcourseid" required >
                                    <option value=""></option>
                                    <option value="CCS">Computer Science</option>
                                    <option value="ARC">Acturial Science</option>
                                    <option value="BEC">Economics</option> 
                                    <option value="AFD">Agricultural Extension</option> 
                                    <option value="GNR">Geography And Natural Resources Management</option>
                                    <option value="CMR">Criminology</option>
                                    <option value="PLS">Political Science</option>
                                    <option value="CIT">Information Technology</option>
                                </select>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>     
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studadyr" >Admission Year</label>
                                <input type="date" class="form-control text-dark" name="studadyr" id="studadyr" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>     
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studnation" >Nationality</label> 
                                <input type="Text" class="form-control text-dark" name="studnation" id="studnation" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>      
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studgender" >Gender</label>
                                <select class="form-control text-dark" name="studgender" id="studgender" required >
                                    <option value=""></option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>
                        </div> 
                        <div class="col-lg-2"></div>   
                        <div class="col-lg-4"></div> 
                        <div class="col-lg-2"></div>    
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <h5 class="mb-0 text-danger text-left">Create your login Password!</h5>
                        </div>
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="studpassword" >Password</label>
                                <input type="password" class="form-control text-dark" name="studpassword" 
                                    id="studpassword" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                    </div><br/>
                    <div class="row">
                        <div class="col-lg-1"></div> 
                        <div class="col-lg-4">
                            <button type="button" class="btn btn-danger btn-lg btn-block" name="submitStudent" id="submitStudent" >Submit</button>
                        </div>   
                        <div class="col-lg-1"></div>  
                        <div class="col-lg-4"> 
                            <button type="reset" class="btn btn-info btn-lg btn-block" id="clear" >Clear</button>   
                        </div>  
                        <div class="col-lg-2"></div> 
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
    </div>

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script> 
</body>
</html>

