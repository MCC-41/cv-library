/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Swal */

var tableMajor;

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

function getAll() {
    tableMajor = $('#majorTable').DataTable({
        'sAjaxSource': '/major/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'name'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                
                                <button class="btn btn-warning float-left mr-1" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        onclick="edit(this.getAttribute('id'),this.getAttribute('name'))"
                                        data-toggle="modal" data-target="#majorModal"><i class="fas fa-edit"></i></button>
                                <button class="btn btn-danger float-left" 
                                        id="${row.id}"
                                        onclick="deleted(this.getAttribute('id'))"><i class="fas fa-trash-alt"></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}

function add() {
    setForm('', '');
}

function setForm(id, name) {
    $('#id').val(id);
    $('#name').val(name);
}
function save() {
    let id = $('#id').val();
    let name = $('#name').val();
    var major = {
        "name": name
    };
    if (id === "") {
        addMajor(major);
    } else {
        updateMajor(id, major);
    }

}
function addMajor(major) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/major",
        data: JSON.stringify(major),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success');
            $('#majorModal').modal('hide');
            tableMajor.destroy();
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

function edit(id, name) {
    setForm(id, name);
}

function updateMajor(id, major) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/major/" + id,
        data: JSON.stringify(major),
        success: function (data) {
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#majorModal').modal('hide');
            tableMajor.destroy();
            getAll();
        },
        error: function (data) {
            Swal.fire(
                    'Failed!',
                    'Your file cannot be Update.',
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
                url: "/major/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#majorModal').modal('hide');
                    tableMajor.destroy();
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

