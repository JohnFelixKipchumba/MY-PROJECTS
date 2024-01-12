<?php 
	/*** File deals with units management ***/

    /* Session creation */
    session_start();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Units Management</title>
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
                    <br/><h3 class="mb-0 text-dark text-center">Fill in the Unit Details</h3><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-12">       
                <!-- Lecturer personal details form -->   
                <form class="needs-validation" id="unit_management" method="POST" role="form" novalidate>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="unitName" >Unit Name</label>
                                <input type="Text" class="form-control text-dark" name="unitName" id="unitName" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>      
                        </div> 
                        <div class="col-lg-2"></div>    
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="unitCode" >Unit Code</label> 
                                <input type="Text" class="form-control text-dark" name="unitCode" id="unitCode" required />
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
                                <label class="form-control-label text-dark" for="courseId" >Course ID</label>
                                <input type="text" class="form-control text-dark" name="courseId" id="courseId" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-8"></div>          
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="yos" >Year</label> 
                                <select class="form-control text-dark" name="yos" id="yos" required>
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
                                <label class="form-control-label text-dark" for="sem" >Semester</label> 
                                <select class="form-control text-dark" name="sem" id="sem" required>
                                    <option value=""></option>
                                    <option value="1">Semester 1</option>
                                    <option value="2">Semester 2</option>
                                </select>
                                <div class="invalid-feedback">
                                    Required Field
                                </div>  
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>      
                    </div>
                    <br/><br/>                
                    <div class="row"> 
                        <div class="col-lg-3">
                            <button type="button" class="btn btn-primary btn-lg btn-block" name="insertUnit" id="insertUnit" >Submit Unit</button>
                        </div>
                        <div class="col-lg-1"></div> 
                        <div class="col-lg-3">
                            <button type="button" class="btn btn-danger btn-lg btn-block" name="deleteUnit" id="deleteUnit" >Delete Unit</button>
                        </div>
                        <div class="col-lg-1"></div>    
                        <div class="col-lg-3"> 
                            <button type="reset" class="btn btn-info btn-lg btn-block" id="clear" >Clear</button>   
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
    </div>

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script> 
</body>
</html>

