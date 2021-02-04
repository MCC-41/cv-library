/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var tableRole;

$('document').ready(() => {
    getAllRole();
});

function getAllRole() {
    tableRole = $('#roleTable').DataTable({
        'sAjaxSource': '/role',
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
                                        data-toggle="modal" data-target="#roleModal"><i class="fas fa-edit"></i></button>
                                <button class="btn btn-danger float-left" 
                                        id="${row.id}"
                                        onclick="deleteRole(this.getAttribute('id'))"><i class="fas fa-trash-alt"></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}

function add(){
    setFormRole('','');
    setEnabledField(false);
}

function setFormRole(id, name) {
    $('#idRole').val(id);
    $('#nameRole').val(name);
    
    
}

function setEnabledField(isEnabled) {
    $('#nameRole').prop('disabled', isEnabled);
}

function saveRole(){
    let id = $('#idRole').val();
    let name = $('#nameRole').val();
    var role = {
        "name":name
    };
    if(id === ""){
        addRole(role);
    } else {
        updateRole(id, role);
    }
    
}
function addRole(role){
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
       url: "/role",
        data: JSON.stringify(role),
        success: function(data){
            Swal.fire(
                    'Added!',
                   'Your file has been Added.',
                    'success');
                $('#roleModal').modal('hide');
                tableRole.destroy();
                getAllRole();
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
    setFormRole(id,name);
    setEnabledField(false);
}

function updateRole(id,role){
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/role/" + id,
        data: JSON.stringify(role),
        success: function(data){
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#roleModal').modal('hide');
            tableRole.destroy;
            getAllRole();
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

function deleteRole(id) {
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
                url: "/role/" + id,
                success: function () {
                    Swal.fire(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                            );
                    $('#roleModal').modal('hide');
                    tableRole.destroy();
                    getAllRole();
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