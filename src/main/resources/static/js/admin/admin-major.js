/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var table;

$('document').ready(() => {
    getAll();
});

function getAll() {
    table = $('#majorTable').DataTable({
        'sAjaxSource': '/major',
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
                                        onclick="updateBtnMajor(this.getAttribute('id'),this.getAttribute('name'))"
                                        data-toggle="modal" data-target="#majorModal"><i class="fas fa-edit"></i></button>
                                <button class="btn btn-danger float-left" 
                                        id="${row.id}"
                                        onclick="deletedMajor(this.getAttribute('id'))"><i class="fas fa-trash-alt"></i></button>
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
    $('#idMajor').val(id);
    $('#nameMajor').val(name);
}

function setEnabledField(isEnabled) {
    $('#nameMajor').prop('disabled', isEnabled);
}

function saveMajor(){
    let id = $('#idMajor').val();
    let name = $('#nameMajor').val();
    var major = {
        "name":name
    };
    if(id===""){
        addMajor(major);
    }
    else {
        updateMajor(id, major)
    }
    
}
function addMajor(major){
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
       url: "/major",
        data: JSON.stringify(major),
        success: function(data){
            Swal.fire(
                    'Added!',
                   'Your file has been Added.',
                    'success');
                $('#majorModal').modal('hide');
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

function updateBtnMajor(id, name){
    setForm(id,name);
    console.log(id,name);
    setEnabledField(false);
}

function updateMajor(id,major){
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/major/" + id,
        data: JSON.stringify(major),
        success: function(data){
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#majorModal').modal('hide');
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

function deletedMajor(id) {
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

