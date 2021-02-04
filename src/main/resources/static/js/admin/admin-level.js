/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

var tableLevel;

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
    getAllLevel();
});

function getAllLevel() {
    tableLevel = $('#levelTable').DataTable({
        'sAjaxSource': '/level/all',
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
                                        data-toggle="modal" data-target="#levelModal"><i class="fas fa-edit"></i></button>
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
    var level = {
        "name": name
    };
    if (id === "") {
        insert(level);
    } else {
        update(id, level);
    }

}
function insert(level) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/level",
        data: JSON.stringify(level),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success');
            $('#levelModal').modal('hide');
            tableLevel.destroy();
            getAllLevel();
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
}

function update(id, level) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/level/" + id,
        data: JSON.stringify(level),
        success: function (data) {
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#levelModal').modal('hide');
            tableLevel.destroy();
            getAllLevel();
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
                url: "/level/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#levelModal').modal('hide');
                    tableLevel.destroy();
                    getAllLevel();
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