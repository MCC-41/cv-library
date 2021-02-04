/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

var table;

$(document).ready(function () {
//    $('#employee').val(null).trigger('change');
//    window.addEventListener('load', function () {
//        var forms = document.getElementsByClassName('needs-validation');
//        var validation = Array.prototype.filter.call(forms, function (form) {
//            form.addEventListener('submit', function (event) {
//                if (form.checkValidity() === false) {
//                    event.preventDefault();
//                    event.stopPropagation();
//                } else {
//                    btnSave();
//                }
//                form.classList.add('was-validated');
//
//            }, false);
//        });
//    }, false);
    $('#employee').select2({
        ajax: {
            url: '/user/employee',
            type: 'GET',
            dataType: 'json',
            delay: 250,
            processResults: function (response) {
                var res = response.map(function (item) {
                    return {id: item.id, text: item.name};
                });
                return {
                    results: res
                };
            }
        }
    });
    getAll();
});
function getAll() {
    table = $('#userTable').DataTable({
        'sAjaxSource': '/user/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'username'},
            {'data': 'status.name'},
            {'data': 'isVerified'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" data-toggle="modal" data-target="#userModal"
                                    id="${row.id}"
                                    idStatus="${row.status.id}"
                                    isVerified="${row.isVerified}"
                                    onclick="btnEdit(this.getAttribute('id'),this.getAttribute('idStatus'),this.getAttribute('isVerified'))"><i class="fa fa-sm fa-edit  mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1 deleteUser"
                                    id="${row.id}"
                                    onclick="deleted(this.getAttribute('id'))"><i class="fa fa-sm fa-trash  mx-1"></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}
function btnAdd() {
    $('#exampleModalLabel').text('Create');
    setEnabledField(false);
    setForm('', '', '');
    $('#statusItem').hide();
    $('#verifItem').hide();
    $('#employeeItem').show();
}
function btnEdit(id, idStatus, isVerified) {
    $('#exampleModalLabel').text('Update');
    $("#id").val(id);
    $('#status').val(idStatus);
    $('#isVerified').val(isVerified);
    $('#statusItem').show();
    $('#verifItem').show();
    $('#employeeItem').hide();
    setEnabledField(false);
}
function setForm(employee, status, isVerified) {
    $('#employee').val(employee);
    $('#status').val(status);
    $('#isVerified').val(isVerified);

}
function setEnabledField(isEnabled) {
    $('#save').prop('disabled', isEnabled);
    $('#employee').prop('disabled', isEnabled);
    $('#username').prop('disabled', isEnabled);
    $('#status').prop('disabled', isEnabled);
    $('#isVerified').prop('disabled', isEnabled);
}
function btnSave() {
    let id = $("#employee").val();
    let a = $("#id").val();
    let b = $('#status').val();
    let c = $('#isVerified').val();
    console.log(a);
    console.log(b);
    console.log(c);
    if (b === null||c === null) {
        add(id);
    } else {
        edit(a, b, c);
    }
}
function add(id) {
    var user = {
        "id": id
    };
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/user/" + id,
        data: JSON.stringify(user),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#userModal').modal('hide');
            table.destroy();
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
function edit(id, status, verif) {
    var user = {
        "id": id,
        "isVerified": verif,
        "status": {
            "id": status
        }
    };
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/user/" + id,
        data: JSON.stringify(user),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#userModal').modal('hide');
            table.destroy();
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
function deleted(id) {
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
                url: "/user/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#userModal').modal('hide');
                    table.destroy();
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