/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tableUserRole;

$('document').ready(() => {
    getAll();
});

function btnAdd() {
    setForm('', '', '');
}
function btnEdit(id, user, role) {
    setForm(id, user, role);
}
function setForm(id, user , role) {
    $("#id").val(id);
    $("#user").val(user);
    $("#role").val(role);


    console.log(id);
    console.log(user);
    console.log(role);
}
function getAll() {
    tableUserRole = $('#userRoleTable').DataTable({
        'sAjaxSource': '/user_role/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'user.username'},
            {'data': 'role.name'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#userRoleModal" 
                                        id="${row.id}"
                                        user="${row.user.id}"
                                        role="${row.role.id}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('user'),this.getAttribute('role'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1" 
                                        id="${row.id}" 
                                        onclick="deleteUserRole(this.getAttribute('id'))"><i class="fa fa-sm fa-trash mx-1 "></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}

function save() {
    let id = $("#id").val();
    let user = $("#user").val();
    let role = $("#role").val();
    var userrole = {
        "user": {
            "user": user
        },
        "role": {
            "role": role
        }
    };
    if (id === "") {
        insertUserRole(userrole);
    } else {
        updateUserRole(id, userrole);
    }
}

function insertUserRole(userrole) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/user_role",
        data: JSON.stringify(userrole),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#userRoleModal').modal('hide');
            tableUserRole.destroy();
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

function updateUserRole(id, userrole) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/user_role/" + id,
        data: JSON.stringify(userrole),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#trainingModal').modal('hide');
            tableUserRole.destroy();
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

function deleteUserRole(id) {
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
                url: "/user_role/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#userRoleModal').modal('hide');
                    tableUserRole.destroy();
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