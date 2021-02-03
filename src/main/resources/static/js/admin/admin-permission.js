/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tablePermission;

$('document').ready(() => {
    getAllPermission();
});

function getAllPermission() {
    tablePermission = $('#permissionTable').DataTable({
        'sAjaxSource': '/permission',
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
                                        data-toggle="modal" data-target="#permissionModal"><i class="fas fa-edit"></i></button>
                                <button class="btn btn-danger float-left" 
                                        id="${row.id}"
                                        onclick="deletePermission(this.getAttribute('id'))"><i class="fas fa-trash-alt"></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}

function add(){
    setFormPermission('','');
    setEnabledField(false);
}

function setFormPermission(id, name) {
    $('#idPermission').val(id);
    $('#namePermission').val(name);
}

function setEnabledField(isEnabled) {
    $('#namePermission').prop('disabled', isEnabled);
}

function savePermission(){
    let id = $('#idPermission').val();
    let name = $('#namePermission').val();
    var permission = {
        "name":name
    };
    if(id === ""){
        addPermission(permission);
    } else {
        updatePermission(id, permission);
    }
    
}

function addPermission(permission){
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
       url: "/permission",
        data: JSON.stringify(permission),
        success: function(data){
            Swal.fire(
                    'Added!',
                   'Your file has been Added.',
                    'success');
                $('#permissionModal').modal('hide');
                tablePermission.destroy();
                getAllPermission();
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
    setFormPermission(id,name);
    console.log(id,name);
    setEnabledField(false);
}

function updatePermission(id, permission){
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/permission/" + id,
        data: JSON.stringify(permission),
        success: function(data){
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#permissionModal').modal('hide');
            tablePermission.destroy;
            getAllPermission();
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

function deletePermission(id) {
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
                url: "/permission/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#permissionModal').modal('hide');
                    tablePermission.destroy();
                    getAllPermission();
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
