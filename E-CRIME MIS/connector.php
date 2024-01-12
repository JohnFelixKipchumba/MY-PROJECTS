$con=mysqli_connect('localhost','root','','crime-portal');
    if(!$con)
    {
        die('could not connect: '. mysqli_connect_error());
    }