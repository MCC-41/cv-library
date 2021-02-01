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
    setForm('Add', '', '', '');
}
function btnEdit(id, name, institution, startDate, endDate) {
    $('.btnModalTitle').text('Update');
    setForm('Update', id, name, institution,startDate, endDate);
}
function setForm(title, id, name, institution,startDate, endDate) {
    $('.organizationTitle').text(title);
    $("#id").val(id);
    $("#name").val(name);
    $("#institution").val(institution);
    $("#startDate").val(startDate);
    $("#endDate").val(endDate);
    
    console.log(name);
    console.log(institution);
    console.log(startDate);
    console.log(endDate);
}
function getAll() {
    table = $('#organizationTable').DataTable({
        'sAjaxSource': '/organization/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'name'},
            {'data': 'institution'},
            {'data': 'startDate'},
            {'data': 'endStart'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#organizationModal" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        institution="${row.institution}"
                                        start="${row.startDate}"
                                        end="${row.endStart}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('name'),this.getAttribute('institution'),this.getAttribute('start'),this.getAttribute('end'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
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
    let institution = $("#institution").val();
    let startDate = $("#startDate").val();
    let endDate = $("#endDate").val();
    console.log(id);
    console.log(name);
    console.log(institution);
    console.log(startDate);
    console.log(endDate);
    var award = {
        "name": name,
        "institution": institution,
        "startDate": startDate,
        "endStart" : endDate
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
        url: "/organization/add",
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#organizationModal').modal('hide');
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
        url: "/organization/" + id,
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#organizationModal').modal('hide');
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
                url: "/organization/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#organizationModal').modal('hide');
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