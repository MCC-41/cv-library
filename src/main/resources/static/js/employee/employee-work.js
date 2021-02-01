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
    $('.btnModalWork').text('Add');
    setForm('Add', '', '', '', '', '', '');
}
function btnEdit(id, name, position, job, startDate, endDate) {
    $('.btnModalWork').text('Update');
    setForm('Update', id, name, position, job, startDate, endDate);
}
function setForm(title, id, name, position, job, startDate, endDate) {
    $('.workTitle').text(title);
    $("#id").val(id);
    $("#companyName").val(name);
    $("#position").val(position);
    $("#jobDesc").val(job);
    $("#startDate").val(startDate);
    $("#endDate").val(endDate);
}
function getAll() {
    table = $('#workTable').DataTable({
        'sAjaxSource': '/work/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'companyName'},
            {'data': 'position'},
            {'data': 'jobDesc'},
            {'data': 'startDate'},
            {'data': 'endDate'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#workModal" 
                                        id="${row.id}"
                                        name="${row.companyName}"
                                        position="${row.position}"
                                        jobDesc="${row.jobDesc}"
                                        startDate="${row.startDate}"
                                        endDate="${row.endDate}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('name'),this.getAttribute('position'),this.getAttribute('jobDesc'),this.getAttribute('startDate'),this.getAttribute('endDate'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1 deleteEmployeeWork" 
                                        id="${row.id}" 
                                        onclick="deleteWork(this.getAttribute('id'))"><i class="fa fa-sm fa-trash mx-1 "></i></button>
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
    let name = $("#companyName").val();
    let position = $("#position").val();
    let jobDesc = $("#jobDesc").val();
    let startDate = $("#startDate").val();
    let endDate = $("#endDate").val();
    
    var work = {
        "companyName": name,
        "position": position,
        "jobDesc": jobDesc,
        "startDate": startDate,
        "endDate": endDate
    };
    if (id === "") {
        insert(work);
    } else {
        update(id, work);
    }
}
function insert(work) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/work/add",
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#workModal').modal('hide');
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
        url: "/work/" + id,
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#workModal').modal('hide');
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
function deleteWork(id) {
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
                url: "/work/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#workModal').modal('hide');
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