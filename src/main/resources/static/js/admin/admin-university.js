/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tabelUniversity;

$('document').ready(() => {
    getAllUniv();
});

function getAllUniv() {
    tabelUniversity = $('#univTable').DataTable({
        'sAjaxSource': '/university',
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
    $('#id').val(id);
    $('#name').val(name);
}

//function setFormEdit(id, name) {
//    $('#idLevelEdit').val(id);
//    $('#nameLevelEdit').val(name);
//}

function setEnabledField(isEnabled) {
    $('#btn-save').prop('disabled', isEnabled);
    $('#name').prop('disabled', isEnabled);
}

function addUniversity(){
    let id = $('#idUniversity').val();
    let name = $('#nameUniversity').val();
    var university = {
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
       url: "/university",
        data: JSON.stringify(university),
        success: function(data){
            Swal.fire(
                    'Added!',
                   'Your file has been Added.',
                    'success');
                $('#universityModal').modal('hide');
                tabelUniversity.destroy();
                getAllUniv();
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
