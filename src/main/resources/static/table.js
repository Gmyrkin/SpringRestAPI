$(document).ready(getAllUsers());

// Для отображения списка пользователей в таблице
function getAllUsers() {
    $("#table").empty();
    $.ajax({
        type: 'POST',
        url: '/api/user',
        timeout: 3000,
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                $("#table").append($('<tr>').append(
                        $('<td>').append($('<span>')).text(user.id),
                        $('<td>').append($('<span>')).text(user.firstName),
                        $('<td>').append($('<span>')).text(user.lastName),
                        $('<td>').append($('<span>')).text(user.password),
                        $('<td>').append($('<span>')).text(user.role),


                        $('<td>').append($('<button>').text("Edit").attr({
                            "type": "button",
                            "class": "btn btn-primary edit",
                            "data-toggle": "modal",
                            "data-target": "#myModal",

                        })
                            .data("user", user)),


                        $('<td>').append($('<button>').text("Delete").attr({
                            "type": "button",
                            "class": "btn btn-danger delete",
                            "data-toggle": "modal",
                            "data-target": "#myModalDelete",
                        })
                            .data("user", user))
                    )
                );
            });
        }
    });
}