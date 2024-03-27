$(document).ready(
    function() {

        // SUBMIT FORM
        $("#userForm").submit(function(event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            const formData = {
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                password: $("#password").val()
            };

            // DO POST
            $.ajax({
                type : "POST",
                // contentType : "application/json",
                url : "admin/create/user",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status == "success") {
                        $("#postResultDiv").html(
                            ""
                            + result.data.firstName
                            +  "Post Successfully! "
                            + " Congrats!" + "");
                    } else {
                        $("#postResultDiv").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error : function(e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        }

    })