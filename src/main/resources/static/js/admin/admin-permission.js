/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tabelPermission;

$('document').ready(() => {
    getAllPermission();
});

function getAllPermission() {
    tabelPermission = $('#permis').DataTable({
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
                                        onclick="update(this.getAttribute('id'),this.getAttribute('name'))"
                                        data-toggle="modal" data-target="#universityModal"><i class="fas fa-edit"></i></button>
                                <button class="btn btn-danger float-left" 
                                        id="${row.id}"
                                        onclick="deleteUniversity(this.getAttribute('id'))"><i class="fas fa-trash-alt"></i></button>
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
    setEnabledField(false, false);
}

function setForm(id, name) {
    $('#idPermission').val(id);
    $('#namePermission').val(name);
}

//function setFormEdit(id, name) {
//    $('#idLevelEdit').val(id);
//    $('#nameLevelEdit').val(name);
//}

function setEnabledField(isEnabled) {
    $('#btn-save-permission').prop('disabled', isEnabled);
    $('#namePermission').prop('disabled', isEnabled);
}

function addPermission(){
    let id = $('#idPermission').val();
    let name = $('#namePermission').val();
    var permission = {
        "name":name
    };
//    if(id == undefined){
//        console.log(id); 
//    }
//    else{
//        console.log(id);
//    }
    
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
                tabelPermission.destroy();
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

