GET: $(document).ready(
    function() {

        // GET REQUEST
        $("#getALlUsers").click(function(event) {
            event.preventDefault();
            ajaxGet();
        });

        // DO GET
        function ajaxGet() {
            $.ajax({
                type : "GET",
                url : "admin/user",
                success : function(result) {
                    if (result.status == "success") {
                        $('#getResultDiv ul').empty();
                        var custList = "";
                        $.each(result.data,
                            function(i, user) {
                                var users = "First Name  " + user.firstName
                                    + ", Last Name  = " + user.lastName
                                    + ", Password  = " + user.password
                                    + "<br>";
                                $('#getResultDiv .list-group').append(users)
                            });
                        console.log("Success: ", result);
                    } else {
                        $("#getResultDiv").html("<strong>Error</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error : function(e) {
                    $("#getResultDiv").html("<strong>Error</strong>");
                    console.log("ERROR: ", e);
                }
            });
        }
    })