/***** ````````````````````````````````````````````````````````````````````````````````````````````````
 * File handling Ajax calls and interactivity for all forms 
 * ````````````````````````````````````````````````````````````````````````````````````````````````````
 * **/ 

/**** Applies to all files with class "needs-validation"  ****/
$(document).ready(function() {

        /* JavaScript for disabling form submissions if there are invalid fields */
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                                
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        
                        } // end if   
                        form.classList.add('was-validated');
                    }, false);
                }); // end of filter

}); // end of query


$(document).ready(function() {

/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR INDEX.PHP
 * ----------------------------------------------------------------------------------------------------
 */
    /* Javascript for handling form submission in index.php */        
        $("#login").click(function() {

            /* variables declarations */
            username = $("#username").val(); 
            password = $("#pwd").val();           

            /* making of post ajax call after validating all fields have been filled */
            if( username=="" || password=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "indexAuthenticationHandler.php",
                data: {
                    username: username,
                    pwd: password                  
                },
                cache: false,
                success: function(data,status) { 
                    //alert(data);                  
                    /* retrieve data returned from php file */
                    var obj = JSON.parse(data);
                    var confirmer = obj.valid;
                    var path = obj.header;
                    
                    /* checking if user present in database already */
                    if(confirmer === "present") {
                        location.href = path;  // load php page
                        $("#username").val("");

                    } // end if 
                    else if(confirmer === "absent") {

                        alert("Please Input Correct Details!\nSign Up for New Account IF None!");

                    } // end else

                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form 



 /***---------------------------------------------------------------------------------------------------
 *    SECTION FOR INDEX2.PHP
 * ----------------------------------------------------------------------------------------------------
 */
    /* Javascript for handling form submission in index.php */        
        $("#stafflogin").click(function() {

            /* variables declarations */
            username = $("#staffusername").val(); 
            password = $("#staffpwd").val();           

            /* making of post ajax call after validating all fields have been filled */
            if( username=="" || password=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "index2AuthenticationHandler.php",
                data: {
                    staffusername: username,
                    staffpwd: password                  
                },
                cache: false,
                success: function(data,status) { 
                    //alert(data);                  
                    /* retrieve data returned from php file */
                    var obj = JSON.parse(data);
                    var confirmer = obj.valid;
                    var path = obj.header;
                    
                    /* checking if user present in database already */
                    if(confirmer === "present") {
                        location.href = path;  // load php page
                        $("#staffusername").val("");

                    } // end if 
                    else if(confirmer === "absent") {

                        alert("Please Input Correct Details!\nSign Up for New Account IF None!");

                    } // end else

                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form


/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR LECTURER_DETAILS.PHP
 * ----------------------------------------------------------------------------------------------------
 */
	/* Javascript for handling form submission in Lecturer_details.php */        
 		$("#submitStaff").click(function() {

            /* variables declarations */
            fname = $("#fname").val();
            oname = $("#othername").val();
            id = $("#id").val();
            tel = $("#phone").val();
            email = $("#email").val();
            nationality = $("#nationality").val();
            title = $("#title").val();
            yos = $("#yos").val();
            gender = $("#gender").val(); // get selected gender                         
            password = $("#password").val();

 			/* making of post ajax call after validating all fields have been filled */
            if(fname=="" || oname=="" || gender=="" || yos=="" || tel=="" || email=="" ||
                 title=="" || password=="" || id=="" || nationality=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "lecDetailsHandler.php",
                data: {
                    fname: fname,
                    othername: oname,
                    id: id,
                    phone: tel,
                    email: email,
                    nationality: nationality,
                    title: title,
                    yos: yos,
                    gender: gender,
                    password: password
                },
                cache: false,
                success: function(data,status) {                   
                    //location.reload();  // refresh the page to display change in staffId value
                    /* retrieve data returned from php file */
                    var obj = JSON.parse(data);
                    var newID = obj.staffId2;
                    var confirmer = obj.present;
                    
                    /* checking if user present in database already */
                    if(confirmer == "true") {
                        alert("An Account has these Details!\nPlease Fill in Correct Details");

                    } // end if 
                    else {
                        alert("Data Submitted Successfully!");
                        $("#staffID").val(newID); // set the value field with generated staffId

                    } // end else
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

 		}); // end of submit form


    /* Javascript for handling reset clicks in Lecturer_details.php */        
        $("#clear").click(function() {

            $("#staffId").val(""); // set value to null

        }); // end of reset click      

/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR RESULTS_DETAILS.PHP
 * ----------------------------------------------------------------------------------------------------
 */
    /* Javascript for handling form submission in Results_details.php after submitting */        
        $("#submitResults").click(function() {

            /* variables declarations */
            fname = $("#firstname").val();
            oname = $("#othername").val();
            regno = $("#regno").val();
            unitCode = $("#unitCode").val();
            catMark = $("#catMark").val();
            examMark = $("#examMark").val();
            examCardNo = $("#examCardNo").val();

            /* making of post ajax call after validating all fields have been filled */
            if( regno=="" || unitCode=="" || catMark=="" || examCardNo=="" || examMark=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "lecMarkEntryHandler.php",
                data: {
                    firstname: fname,
                    othername: oname,
                    regno: regno,
                    unitCode: unitCode,
                    catMark: catMark,
                    examMark: examMark,
                    examCardNo: examCardNo
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        alert("Data Submitted Successfully!");
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form


    /* Javascript for handling form submission in Results_details.php after updating */        
        $("#updateResults").click(function() {

            /* variables declarations */
            fname = $("#firstname").val();
            oname = $("#othername").val();
            regno = $("#regno").val();
            unitCode = $("#unitCode").val();
            catMark = $("#catMark").val();
            examMark = $("#examMark").val();
            examCardNo = $("#examCardNo").val();

            /* making of post ajax call after validating all fields have been filled */
            if( regno=="" || unitCode=="" || catMark=="" || examCardNo=="" || examMark=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "lecMarkUpdateHandler.php",
                data: {
                    firstname: fname,
                    othername: oname,
                    regno: regno,
                    unitCode: unitCode,
                    catMark: catMark,
                    examMark: examMark,
                    examCardNo: examCardNo
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        alert("Update Performed Successfully!");
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form       


/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR UNITS_MANAGEMENT.PHP
 * ----------------------------------------------------------------------------------------------------
 */
    /* Javascript for handling form submission in Units_management.php after submitting */        
        $("#insertUnit").click(function() {

            /* variables declarations */
            unitName = $("#unitName").val();
            unitCode = $("#unitCode").val();
            courseId = $("#courseId").val();
            year = $("#yos").val();            
            sem = $("#sem").val();

            /* making of post ajax call after validating all fields have been filled */
            if( unitName=="" || unitCode=="" || courseId=="" || yos=="" || sem=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "unitAdditionHandler.php",
                data: {
                    unitName: unitName,
                    unitCode: unitCode,
                    courseId: courseId,
                    yos: year,                   
                    sem: sem
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        alert("Data Submitted Successfully!");
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form 

    /* Javascript for handling form submission in Units_management.php after updating */        
        $("#deleteUnit").click(function() {

            /* variables declarations */
            unitName = $("#unitName").val();
            unitCode = $("#unitCode").val();
            courseId = $("#courseId").val();
            year = $("#yos").val();            
            sem = $("#sem").val();

            /* making of post ajax call after validating all fields have been filled */
            if( unitName=="" || unitCode=="" || courseId=="" || yos=="" || sem=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "unitUpdateHandler.php",
                data: {
                    unitName: unitName,
                    unitCode: unitCode,
                    courseId: courseId,
                    yos: year,                   
                    sem: sem
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        alert("Data Updated Successfully!");
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form 


/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR STUDENT_DETAILS.PHP
 * ----------------------------------------------------------------------------------------------------
 */
    /* Javascript for handling form submission in Student_details.php */        
        $("#submitStudent").click(function() {

            /* variables declarations */
            fname = $("#studfname").val();
            lname = $("#studlname").val();
            id = $("#studid").val();
            tel = $("#studphone").val();
            email = $("#studemail").val();
            nationality = $("#studnation").val();
            cartegory = $("#studctrg").val();
            yrAdmit = $("#studadyr").val();
            gender = $("#studgender").val(); // get selected gender                         
            password = $("#studpassword").val();
            courseid = $("#studcourseid").val();
            regno = $("#studregno").val();

            /* making of post ajax call after validating all fields have been filled */
            if(fname=="" || lname=="" || gender=="" || yrAdmit=="" || tel=="" || email=="" ||
                 courseid=="" || password=="" || id=="" || nationality=="" || regno=="" || cartegory=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "studentDetailsHandler.php",
                data: {
                    studfname: fname,
                    studlname: lname,
                    studid: id,
                    studphone: tel,
                    studemail: email,
                    studnation: nationality,
                    studctrg: cartegory,
                    studadyr: yrAdmit,
                    studgender: gender,
                    studpassword: password,
                    studcourseid: courseid,
                    studregno: regno
                },
                cache: false,
                success: function(data,status) {                   
                    //alert(data);
                    /* retrieve data returned from php file */
                    var obj = JSON.parse(data);
                    var confirmer = obj.present;
                    
                    /* checking if user present in database already */
                    if(confirmer == "true") {
                        alert("An Account has these Details!\nPlease Fill in Correct Details");

                    } // end if 
                    else {
                        alert("Data Submitted Successfully!");

                    } // end else
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form        


/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR SEMESTER_DETAILS.PHP
 * ----------------------------------------------------------------------------------------------------
 */
        /* Javascript for handling form submission in Semester_details.php after registering for sem */
        $("#Regsem").click(function() {

            /* variables declarations */
            year = $("#year").val();            
            sem = $("#semester").val();
            courseId = $("#coursename").val();

            /* making of post ajax call after validating all fields have been filled */
            if( courseId=="" || year=="" || semester=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "studentSemesterHandler.php",
                data: {
                    coursename: courseId,
                    year: year,                   
                    semester: sem
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        alert("Data Submitted Successfully!");
                        location.reload(); // to reflect new session data
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form

    /* Javascript for handling form submission in Semester_details.php after registering the units */        
        $("#Regunit").click(function() {

            /* variables declarations */
            check1 = $("input[name=check1]:checked").val();            
            check2 = $("input[name=check2]:checked").val();
            check3 = $("input[name=check3]:checked").val();
            check4 = $("input[name=check4]:checked").val();
            check5 = $("input[name=check5]:checked").val();
            check6 = $("input[name=check6]:checked").val();
            check7 = $("input[name=check7]:checked").val();
            check8 = $("input[name=check8] checked").val();

            /* making of post ajax call after validating all fields have been filled */
            if( !check1 && !check2 && !check3 && !check4 && !check5 && !check6 
                && !check7 && !check8 ) {
                alert("Please select units to register!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "studentRegUnitsHandler.php",
                data: {
                    check1: check1,
                    check2: check2,
                    check3: check3,
                    check4: check4,
                    check5: check5,
                    check6: check6,
                    check7: check7,
                    check8: check8
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        alert("Data Submitted Successfully!");
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form 


    /* Javascript for handling form submission in Semester_details.php for generating exam card */        
        $("#GenExamcard").click(function() { 

            $.ajax({
                type: "POST",
                url: "studentExamCardHandler.php",
                data: {  
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        //alert("Data Submitted Successfully!");
                        location.href = "studentExamCardHandler.php";  // load php page with pdf
                    
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form 

/***---------------------------------------------------------------------------------------------------
 *    SECTION FOR RESULTS_VIEWER.PHP
 * ----------------------------------------------------------------------------------------------------
 */
    /* Javascript for handling form submission in Units_management.php after updating */        
        $("#viewTranscript").click(function() {

            /* variables declarations */
            transYr = $("#transcriptYear").val();            

            /* making of post ajax call after validating all fields have been filled */
            if( transYr=="" ) {
                alert("Please fill all the fields!");               
                return false;

            } // end validation if

            $.ajax({
                type: "POST",
                url: "studentTranscriptHandler.php",
                data: {
                    transcriptYear: transYr                  
                },
                cache: false,
                success: function(data,status) { 
                        //alert(data);                  
                        location.href = "studentTranscriptHandler.php";  // load php page with pdf
                        
                }, // end of success function
                error: function(xhr,status,error) {
                    console.error(xhr);
                    alert("An Error Occurred!");

                } // end of error function

            }); // end ajax call

        }); // end of submit form 


/*********************************************************************************************************/

}); // end of jquery


/*********************************************************************************************************/

