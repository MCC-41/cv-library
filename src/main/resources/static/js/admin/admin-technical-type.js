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

function getAll() {
    table = $('#technicalTypeTable').DataTable({
        'sAjaxSource': '/technical_type',
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
                                        data-toggle="modal" data-target="#technicalTypeModal"><i class="fas fa-edit"></i></button>
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
    setEnabledField(false);
}

function setForm(id, name) {
    $('#id').val(id);
    $('#name').val(name);
}

function setEnabledField(isEnabled) {
    $('#name').prop('disabled', isEnabled);
}

function save(){
    let id = $('#id').val();
    let name = $('#name').val();
    var technicalType = {
        "name":name
    };
    if (id === "") {
        add(technicalType);
    } else {
        update(id, technicalType);
    }
    
}
function add(technicalType){
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/technical_type",
        data: JSON.stringify(technicalType),
        success: function(data){
            Swal.fire(
                    'Added!',
                   'Your file has been Added.',
                    'success');
                $('#technicalTypeModal').modal('hide');
                table.destroy();
                getAll();
                },
        error: function(data){
            Swal.fire(
                    'Failed!',
                    'Your file cannot be Added.',
                    'error'
                    );
        }
        
    });
}

function updateBtn(id, name){
    setForm(id,name);
    setEnabledField(false);
}

function update(id,technicalType){
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/technical_type/" + id,
        data: JSON.stringify(technicalType),
        success: function(data){
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#technicalTypeModal').modal('hide');
            table.destroy;
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
                url: "/technical_type/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#technicalTypeModal').modal('hide');
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