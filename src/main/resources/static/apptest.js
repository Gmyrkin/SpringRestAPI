
$(document).ready(getAllUsers());

// Для отображения списка пользователей в таблице
function getAllUsers() {
    $("#mainTableWithUsers").empty();
    $.ajax({
        type: 'GET',
        url: '/admin/user',
        timeout: 3000,
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                $("#mainTableWithUsers").append($('<tr>').append(
                        $('<td>').append($('<span>')).text(user.id),
                        $('<td>').append($('<span>')).text(user.firstName),
                        $('<td>').append($('<span>')).text(user.lastName),
                        $('<td>').append($('<span>')).text(user.password),
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

//Для редактирования пользователя через модальное окно
$(document).on("click", ".edit", function () {
    let user = $(this).data('user');

    $('#idInput').val(user.id);
    $('#firstNameInput').val(user.username);
    $('#lastNameInput').val(user.lastName);
    $('#password').val(user.password);


});

$(document).on("click", ".editUser", function () {
    let formData = $(".formEditUser").serializeArray();
    $.ajax({
        type: 'PUT',
        url: '/admin/update/user/',
        data: formData,
        timeout: 100,
        success: function () {
            getAllUsers();
        }
    });
});

//Для удаления пользователя через модальное окно
$(document).on("click", ".delete", function () {
    let user = $(this).data('user');

    $('#id').val(user.id);
    $('#firstName').val(user.username);
    $('#lastName').val(user.lastName);

    $(document).on("click", ".deleteUser", function () {
        $.ajax({
            type: 'DELETE',
            url: '/admin/delete/user/',
            data: {id: $('#id').val()},
            timeout: 100,
            success: function () {
                getAllUsers();
            }
        });
    });
})

// Для добавления пользователя
$('.addUser').click(function () {
    $('#usersTable').trigger('click');
    let formData = $(".formAddUser").serializeArray();
    $.ajax({
        type: 'POST',
        url: '/admin/create/',
        data: formData,
        timeout: 100,
        success: function () {

            $('.formAddUser')[0].reset();
            getAllUsers()
        }
    })
});

//Для отображения информаций о пользователе на его странице
$(document).ready(getUser());
function getUser() {
    $("#userTable").empty();
    $.ajax({
        type: 'GET',
        url: '/admin/user/',
        timeout: 3000,
        error: function() {
            $('#blockMenuforUser').hide();
        },
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                    $('#menuUser').trigger('click');
                    $('#blockMenuforAdmin').hide();

                $("#userTable").append($('<tr>').append(
                        $('<td>').append($('<span>')).text(user.id),
                        $('<td>').append($('<span>')).text(user.username),
                        $('<td>').append($('<span>')).text(user.lastName),

                    )
                );
            });
        }
    });
}