/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Swal */

var table;

$('document').ready(() => {
    getAll();
});

function btnAdd() {
    $('.btnModalTitle').text('Add');
    setForm('Add', '', '', false, '');
}
function btnEdit(id, name, type) {
    $('.btnModalTitle').text('Update');
    setForm('Update', id, type, true, name);
}
function setForm(title, id, type, isEnable, name) {
    $('.technicalTitle').text(title);
    $("#id").val(id);
    $("#technicalType").val(type);
    $("#name").val(name);
}
function getAll() {
    table = $('#technicalTable').DataTable({
        'sAjaxSource': '/technical/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'technicalType.name'},
            {'data': 'name'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#technicalModal" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        technicalType="${row.technicalType.name}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('name'),this.getAttribute('technicalType'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1" 
                                        id="${row.id}" 
                                        onclick="deleted(this.getAttribute('id'))"><i class="fa fa-sm fa-trash mx-1 "></i></button>
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
    let name = $("#name").val();
    let technicalType = $("#technicalType").val();
    console.log(technicalType);
    console.log(name);
    var award = {
        "name": name,
        "technicalType": {
            "id": technicalType
        }
    };
    if (id === "") {
        insert(award);
    } else {
        update(id, award);
    }
}
function insert(work) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/technical/add",
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#technicalModal').modal('hide');
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
function update(id, work) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/technical/" + id,
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#technicalModal').modal('hide');
            table.destroy();
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
                url: "/technical/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#technicalModal').modal('hide');
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

