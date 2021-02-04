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
    setForm('Add', '', '', '', '', '', false, '');
}
function btnEdit(id, name, institution, year, trainingType, file) {
    $('.btnModalTitle').text('Update');
    setForm('Update', id, name, institution, year, trainingType, true, file);
}
function setForm(title, id, name, institution, year, trainingType, isEnable, file) {
    $('.trainingTitle').text(title);
    $("#id").val(id);
    $("#name").val(name);
    $("#institution").val(institution);
    $("#year").val(year);
    $("#trainingType").val(trainingType);
    $("#fileName").val(file);
}
function getAll() {
    table = $('#trainingTable').DataTable({
        'sAjaxSource': '/training/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'name'},
            {'data': 'institution'},
            {'data': 'year'},
            {'data': 'trainingType.name'},
            {'data': 'file'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#trainingModal" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        institution="${row.institution}"
                                        year="${row.year}"
                                        trainingType="${row.trainingType.id}"
                                        file="${row.file}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('name'),this.getAttribute('institution'),this.getAttribute('year'),this.getAttribute('trainingType'),this.getAttribute('file'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1" 
                                        id="${row.id}" 
                                        onclick="deleted(this.getAttribute('id'))"><i class="fa fa-sm fa-trash mx-1 "></i></button>
                                <button class="btn btn-info" 
                                        id="${row.id}" 
                                        fname="${row.file}"
                                        onclick="download(this.getAttribute('id'),this.getAttribute('fname'))"><i class="fa fa-sm fa-print mx-1 "></i></button>
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
    let year = $("#year").val();
    let type = $("#trainingType").val();
//    let file = $("#fileName").text();
    
    let data = new FormData();
    data.append('file',$('#fileName')[0].files[0]);
//    jQuery.each(jQuery('#fileName')[0].files, function (i, file) {
//        data.append('file', file);
//    });
    console.log(id);
    console.log(name);
    console.log(institution);
    console.log(year);
    console.log(type);
//    console.log(file);
    console.log(data);
    var award = {
        "name": name,
        "institution": institution,
        "year": year,
        "trainingType": {
            "id": type
        }
    };
    if (id === "") {
        insert(name,institution, year, type, data);
    } else {
        update(id, name, institution, year, type, data);
    }
}
function insert(name, institution, year, trainingType,data) {
    $.ajax({
        contentType: false,
        enctype: 'multipart/form-data',
        type: 'POST',
        url: "/training/add?"+$.param({name: name,institution: institution,year: year,trainingType: trainingType}),
        data: data,
        processData: false,
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#trainingModal').modal('hide');
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
function update(id, name, institution, year, type, data) {
    $.ajax({
        contentType: false,
        processData: false,
        type: 'PUT',
        url: "/training/" + id + "?" + $.param({name: name, institution: institution, year: year, trainingType: type}),
        data: data === null ? '' : data,
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#trainingModal').modal('hide');
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
                url: "/training/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#trainingModal').modal('hide');
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
function download(id, file) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/training/"+ id +"/download?"+$.param({file: file}),
        success: function (data) {
//            Swal.fire(
//                    'Added!',
//                    'Your file has been Added.',
//                    'success'
//                    );
        },
        error: function (data) {
            console.log(data);
            Swal.fire(
                    'Failed!',
                    'Your file cannot be downloaded.',
                    'error'
                    );
        }
    });
}
