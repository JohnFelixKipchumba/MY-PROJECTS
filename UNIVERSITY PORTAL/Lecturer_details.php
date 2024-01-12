<?php 
	/*** File deals with lecturer details ***/

    /* Session creation */
    session_start();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Lecturer Details</title>
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
                    <br/><h3 class="mb-0 text-dark text-center">Fill in your Personal Details</h3><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-12">       
                <!-- Lecturer personal details form -->   
                <form class="needs-validation" id="lecturer_details" method="POST" role="form" novalidate>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="fname" >First Name</label>
                                <input type="Text" class="form-control text-dark" name="fname" id="fname" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>      
                        </div> 
                        <div class="col-lg-2"></div>    
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="othername" >Other Name</label> 
                                <input type="Text" class="form-control text-dark" name="othername" id="othername" required /> 
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
                                <label class="form-control-label text-dark" for="id" >ID</label>
                                <input type="number" class="form-control text-dark" name="id" id="id" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>     
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="phone" >Phone</label> 
                                <input type="number" class="form-control text-dark" name="phone" id="phone" required />
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
                                <label class="form-control-label text-dark" for="email" >Email</label>
                                <input type="email" class="form-control text-dark" name="email" id="email" required />
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
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="yos" >Years of Service</label> 
                                <input type="number" class="form-control text-dark" name="yos" id="yos" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div> 
                        <div class="col-lg-2"></div>     
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="title" >Title</label> 
                                <select class="form-control text-dark" name="title" id="title" required >
                                    <option value=""></option>
                                    <option value="Lecturer">Lecturer</option>
                                    <option value="Dean">Dean</option>
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
                                <label class="form-control-label text-dark" for="nationality" >Nationality</label> 
                                <input type="Text" class="form-control text-dark" name="nationality" id="nationality" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="gender" >Gender</label>
                                <select class="form-control text-dark" name="gender" id="gender" required >
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
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <h5 class="mb-0 text-danger text-left">Create your login Password!</h5>
                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-4">
                            <h5 class="mb-0 text-danger text-left">Your New Staff ID!</h5>
                        </div>
                        <div class="col-lg-2"></div>
                    </div>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="password" >Password</label>
                                <input type="password" class="form-control text-dark" name="password" id="password" required />
                                <div class="invalid-feedback">
                                    Required Field
                                </div>
                            </div>  
                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="form-control-label text-dark" for="staffID" >Staff Id</label>
                                <input type="text" class="form-control text-dark bg-default" name="staffID" id="staffID" value="<?php //echo "{$_SESSION['staffId']}"; ?>" readonly />  
                            </div>
                        </div>
                        <div class="col-lg-2"></div> 
                    </div><br/>
                    <div class="row"> 
                        <div class="col-lg-2"></div>
                        <div class="col-lg-3">
                            <button type="button" class="btn btn-danger btn-lg btn-block" name="submitStaff" id="submitStaff" >Submit</button>
                        </div> 
                        <div class="col-lg-2"></div>    
                        <div class="col-lg-3"> 
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




