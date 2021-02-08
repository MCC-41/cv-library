/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Swal */

var tableUniv;

$('document').ready(() => {
    window.addEventListener('load', function () {
        var forms = document.getElementsByClassName('needs-validation');
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }else{
                    save();
                }
                form.classList.add('was-validated');
                
            }, false);
        });
    }, false);
    getAllUniv();
});

function getAllUniv() {
    tableUniv = $('#univTable').DataTable({
        'sAjaxSource': '/university/all',
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
                                        data-toggle="modal" data-target="#universityModal"><i class="fas fa-edit"></i></button>
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

function add(){
    setForm('','');
}
function edit(id, name){
    console.log(id, name);
    setForm(id, name);
}
function setForm(id, name) {
    $('#id').val(id);
    $('#name').val(name);
    console.log(id,name);
}
function setEnabledField(isEnabled) {
    $('#nameUniversity').prop('disabled', isEnabled);
}

function save(){
    let id = $('#id').val();
    let name = $('#name').val();
    console.log(id);
    console.log(name);
    var value = {
        "name":name
    };
    if(id === ''){
        insert(value);
    }
    else{
        update(id, value);
    }
}
function insert(value) {
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/university",
        data: JSON.stringify(value),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Added.',
                    'success'
                    );
            $('#universityModal').modal('hide');
            tableUniv.destroy();
            getAllUniv();
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
function update(id, value) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/university/" + id,
        data: JSON.stringify(value),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#universityModal').modal('hide');
            tableUniv.destroy();
            getAllUniv();
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
                url: "/university/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#universityModal').modal('hide');
                    tableUniv.destroy();
                    getAllUniv();
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