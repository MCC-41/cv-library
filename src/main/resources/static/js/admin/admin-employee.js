/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

var table;

$('document').ready(() => {
    window.addEventListener('load', function () {
        var forms = document.getElementsByClassName('needs-validation');
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    save();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
    getAll();
});

function btnAdd() {
    $('.btnModalEmp').text('Add');
    setForm('Add', '', '', '', '', '', '', '', '');
}
function btnEdit(id, name, email, dateBirth, nation, status, gender, religion) {
    $('.btnModalEmp').text('Update');
    setForm('Update', id, name, email, dateBirth, nation, status, gender, religion);
}
function setForm(title, id, name, email, dateBirth, nation, status, gender, religion) {
    $('.empTitle').text(title);
    $('#id').val(id);
    $('#name').val(name);
    $('#email').val(email);
    $('#dateBirth').val(dateBirth);
    $('#nation').val(nation);
    $('#status').val(status);
    $('#gender').val(gender);
    $('#religion').val(religion);
}
function getAll() {
    table = $('#employeeTable').DataTable({
        'sAjaxSource': '/employee/all/',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'name'},
            {'data': 'email'},
            {'data': 'dateBirth'},
            {'data': 'nation'},
            {'data': 'status'},
            {'data': 'gender'},
            {'data': 'religion.name'},
            {'data': 'photo'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#employeeModal" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        email="${row.email}"
                                        dateBirth="${row.dateBirth}"
                                        nation="${row.nation}"
                                        status="${row.status}"
                                        gender="${row.gender}"
                                        religion="${row.religion.id}"
                                        onclick="
                                    btnEdit(
                                        this.getAttribute('id'),
                                        this.getAttribute('name'),
                                        this.getAttribute('email'),
                                        this.getAttribute('dateBirth'),
                                        this.getAttribute('nation'),
                                        this.getAttribute('status'),
                                        this.getAttribute('gender'),
                                        this.getAttribute('religion'),
                                    )"><i class="fa fa-sm fa-edit mx-1"></i></button>
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
    let id = $('#id').val();
    let name = $('#name').val();
    let email = $('#email').val();
    let birth = $('#dateBirth').val();
    let nation = $('#nation').val();
    let status = $('#status').val();
    let gender = $('#gender').val();
    let religion = $('#religion').val();
    let photo = new FormData();
    console.log(id);
//        photo.append('id', id);
    photo.append('name', name);
    photo.append('email', email);
    photo.append('dateBirth', birth);
    photo.append('nation', nation);
    photo.append('status', status);
    photo.append('gender', gender);
    photo.append('religion', religion);
    photo.append('file', $('#photo')[0].files[0]);
    if (id === "") {
        insert(photo);
    } else {
        update(id, photo);
    }
}
function insert(photo) {
    $.ajax({
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        type: 'POST',
        url: "/employee-master",
        data: photo,
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#employeeModal').modal('hide');
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
function update(id, photo) {
    $.ajax({
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        type: 'PUT',
        url: "/employee-master/" + id,
        data: photo,
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#employeeModal').modal('hide');
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
                url: "/employee/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#employeeModal').modal('hide');
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
