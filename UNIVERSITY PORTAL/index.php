<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Student Authentication</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
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
          font-family: Arial, Helvetica, sans-serif;
      }
      .btn-rounded{
          border-radius: 30px;
      }
    </style>
</head>
<body class="bg-color">
    <div class="row col-12"> 
        <div class="col-lg-5"></div> 
        <div class="col-lg-2">
            <br/><img  src="logo.png" class="img-rounded" alt="school logo" width="120" height="100" /> 
        </div>  
        <div class="col-lg-5"></div>       
    </div>
    <div class="row col-12">
    <section class="container mt-5">
        <div class="row justify-content-md-center">
            <form class="col-md-6 col-sm-12 bg-white p-5 rounded shadow">
                <div class="col-12 text-center">
                    <h3 class="text-danger"><strong>Student Authentication</strong></h3>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="eg. BED/00700/022" required />
                </div>
                <div class="mb-3">
                    <label for="pwd" class="form-label">Password</label>
                    <input type="password" class="form-control" id="pwd" name="pwd" required />
                </div>
                <div class="text-center mt-3">
                    <button type="button" class="btn btn-primary btn-rounded w-75" id="login" name="login">Log In</button>
                </div> 
                <div class="text-center mt-3">
                    <label class="form-check-label text-danger" for="signup">To Register a New Account</label>
                    <a href="student_details.php" class="btn btn-primary btn-rounded w-75" role="button" id="signup" 
                    name="signup">Sign Up</a>
                </div>
              
            </form>
        </div>
    </section>
    </div>
    <div class="row col-12"></div>

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</body>
</html>



