/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tableUserPermission;

$('document').ready(() => {
    getAll();
});

function btnAdd() {
    setForm('', '', '');
}
function btnEdit(id, user, permission) {
    setForm(id, user, permission);
}
function setForm(id, user , permission) {
    $("#id").val(id);
    $("#user").val(user);
    $("#permission").val(permission);


    console.log(id);
    console.log(user);
    console.log(permission);
}
function getAll() {
    tableUserPermission = $('#userPermissionTable').DataTable({
        'sAjaxSource': '/user_permission/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'user.username'},
            {'data': 'permission.name'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#userPermissionModal" 
                                        id="${row.id}"
                                        user="${row.user.id}"
                                        permission="${row.permission.id}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('user'),this.getAttribute('permission'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1" 
                                        id="${row.id}" 
                                        onclick="deleteUserPermission(this.getAttribute('id'))"><i class="fa fa-sm fa-trash mx-1 "></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}

function saveUserPermission() {
    let id = $("#id").val();
    let user = $("#user").val();
    let permission = $("#permission").val();
    console.log(id);
    console.log(user);
    console.log(permission);
    var userpermission = {
        "user": {
            "id": user
        },
        "permission": {
            "id": permission
        }
    };
    if (id === "") {
        insertUserPermission(userpermission);
    } else {
        updateUserPermission(id, userpermission);
    }
}

function insertUserPermission(userpermission) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/user_permission",
        data: JSON.stringify(userpermission),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#userPermissionModal').modal('hide');
            tableUserPermission.destroy();
            getAll();
        },
        error: function (data) {
            Swal.fire(
                    'Failed!',
                    'Your file cannot be Added.',
                    'error'
                    );
        }
    });
}

function updateUserPermission(id, userpermission) {
        console.log(userpermission);
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/user_permission/" + id,
        data: JSON.stringify(userpermission),
        success: function (data) {
            Swal.fire(
                    'Update!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#userPermissionModal').modal('hide');
            tableUserPermission.destroy();
            getAll();
        },
        error: function (data) {
            Swal.fire(
                    'Failed!',
                    'Your file cannot be Updated.',
                    'error'
                    );
        }
    });
}

function deleteUserPermission(id) {
    console.log(id);
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'DELETE',
                url: "/user_permission/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#userPermissionModal').modal('hide');
                    tableUserPermission.destroy();
                    getAll();
                },

                error: function (data) {
                    console.log(data);
                    Swal.fire(
                            'Failed!',
                            'Your file cannot be deleted.',
                            'error'
                            );
                }

            });

        }
    });
}