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
    $('.btnModalAward').text('Add');
    setForm('Add', '', '', '');
}
function btnEdit(id, name, year) {
    $('.btnModalAward').text('Update');
    setForm('Update', id, name, year);
}
function setForm(title, id, name, year) {
    $('.awardTitle').text(title);
    $("#id").val(id);
    $("#name").val(name);
    $("#year").val(year);
}
function getAll() {
    table = $('#awardTable').DataTable({
        'sAjaxSource': '/award/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'name'},
            {'data': 'year'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#awardModal" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        year="${row.year}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('name'),this.getAttribute('year'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1 deleteEmployeeWork" 
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
    let year = $("#year").val();
    
    var award = {
        "name": name,
        "year": year
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
        url: "/award/add",
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#awardModal').modal('hide');
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
        url: "/award/" + id,
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#awardModal').modal('hide');
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
                url: "/award/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#awardModal').modal('hide');
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
