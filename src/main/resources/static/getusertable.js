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
                        $('#getResultUsers ul').empty();
                        var custList = "";
                        $.each(result.data,
                            function(i, user) {
                                const userinfo = "First Name  " + user.firstName
                                    + ", Last Name  = " + user.lastName
                                    + ", Password  = " + user.password
                                    + "<br>";
                                $('#getResultUsers .list-group').append(userinfo)
                            });
                        console.log("Success: ", result);
                    } else {
                        $("#getResultUsers").html("<strong>ERROR</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error : function(e) {
                    $("#getResultUsers").html("<strong>Error</strong>");
                    console.log("ERROR: ", e);
                }
            });
        }
    })