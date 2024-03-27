//
// // Для отображения списка пользователей в таблице
// $(document).ready(getAllUsers());
//     function getAllUsers() {
//     $("#mainTableWithUsers").empty();
//     $.ajax({
//         type: 'GET',
//         url: '/admin/user',
//         timeout: 3000,
//         success: function (data) {
//             console.log(data);
//             $.each(data, function (i, user) {
//                 $("#mainTableWithUsers").append($('<tr>').append(
//                         $('<td>').append($('<span>')).text(user.id),
//                         $('<td>').append($('<span>')).text(user.firstName),
//                         $('<td>').append($('<span>')).text(user.lastName),
//                         $('<td>').append($('<span>')).text(user.password),
//                         $('<td>').append($('<button>').text("Edit").attr({
//                             "type": "button",
//                             "class": "btn btn-primary edit",
//                             "data-toggle": "modal",
//                             "data-target": "#myModal",
//
//                         })
//                             .data("user", user)),
//                         $('<td>').append($('<button>').text("Delete").attr({
//                             "type": "button",
//                             "class": "btn btn-danger delete",
//                             "data-toggle": "modal",
//                             "data-target": "#myModalDelete",
//                         })
//                             .data("user", user))
//                     )
//                 );
//             });
//         }
//     });
// }
//
//
// // Для добавления пользователя
// $('.addUser').click(function () {
//     $('#usersTable').trigger('click');
//     let formData = $(".formAddUser").serializeArray();
//     $.ajax({
//         type: 'POST',
//         url: '/admin/create/',
//         data: formData,
//         timeout: 100,
//         success: function () {
//
//             $('.formAddUser')[0].reset();
//             getAllUsers()
//         }
//     })
// });
