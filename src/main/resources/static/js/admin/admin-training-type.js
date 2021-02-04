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

function getAll() {
    table = $('#trainingTypeTable').DataTable({
        'sAjaxSource': '/training_type',
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
                                        onclick="updateBtn(this.getAttribute('id'),this.getAttribute('name'))"
                                        data-toggle="modal" data-target="#trainingTypeModal"><i class="fas fa-edit"></i></button>
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
    setEnabledField(false);
}

function setForm(id, name) {
    $('#id').val(id);
    $('#name').val(name);
}


function setEnabledField(isEnabled) {
    $('#name').prop('disabled', isEnabled);
}


function save() {
    let id = $('#id').val();
    let name = $('#name').val();
    var trainingType = {
        "name": name
    };
    if (id === "") {
        add(trainingType);
    } else {
        update(id, trainingType);
    }

}
function add(trainingType) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/training_type",
        data: JSON.stringify(trainingType),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success');
            $('#trainingTypeModal').modal('hide');
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

function updateBtn(id, name) {
    setForm(id, name);
    setEnabledField(false);
}

function update(id, trainingType) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/training_type/" + id,
        data: JSON.stringify(trainingType),
        success: function (data) {
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#trainingTypeModal').modal('hide');
            table.destroy();
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
                url: "/training_type/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#trainingTypeModal').modal('hide');
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