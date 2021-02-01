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
    setForm('Add', '', '', '', '', '', '');
}
function btnEdit(id, university, major, level, year, ipk) {
    $('.btnModalTitle').text('Update');
    setForm('Update', id, university, major, level, year, ipk);
}
function setForm(title, id, university, major, level, year, ipk) {
    $('.educationTitle').text(title);
    $("#id").val(id);
    $("#university").val(university);
    $("#major").val(major);
    $("#level").val(level);
    $("#year").val(year);
    $("#ipk").val(ipk);
}
function getAll() {
    table = $('#educationTable').DataTable({
        'sAjaxSource': '/education/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'university.name'},
            {'data': 'major.name'},
            {'data': 'level.name'},
            {'data': 'year'},
            {'data': 'ipk'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#educationModal" 
                                        id="${row.id}"
                                        university="${row.university.id}"
                                        major="${row.major.id}"
                                        level="${row.level.id}"
                                        year="${row.year}"
                                        ipk="${row.ipk}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('university'),this.getAttribute('major'),this.getAttribute('level'),this.getAttribute('year'),this.getAttribute('ipk'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger mx-1" 
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
    let university = $("#university").val();
    let major = $("#major").val();
    let level = $("#level").val();
    let year = $("#year").val();
    let ipk = $("#ipk").val();

    var data = {
        "university": {
            "id": university
        },
        "major": {
            "id": major
        },
        "level": {
            "id": level
        },
        "year": year,
        "ipk": ipk
    };
    if (id === "") {
        insert(data);
    } else {
        update(id, data);
    }
}
function insert(data) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/education/add",
        data: JSON.stringify(data),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#educationModal').modal('hide');
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
function update(id, data) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/education/" + id,
        data: JSON.stringify(data),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#educationModal').modal('hide');
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
                url: "/education/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#educationModal').modal('hide');
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
