/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tableUniversity;

$('document').ready(() => {
    getAllUniv();
});

function getAllUniv() {
    tableUniversity = $('#univTable').DataTable({
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
                                        onclick="updateBtnUniversity(this.getAttribute('id'),this.getAttribute('name'))"
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
    setEnabledField(false);
}

function setForm(id, name) {
    $('#idUniversity').val(id);
    $('#nameUniversity').val(name);
}


function setEnabledField(isEnabled) {
    $('#nameUniversity').prop('disabled', isEnabled);
}

function saveUniversity(){
    let id = $('#idUniversity').val();
    let name = $('#nameUniversity').val();
    var university = {
        "name":name
    };
    if(id === ""){
        addUniversity(university);
    } else {
        updateUniversity(id, university);
    }
    
}
function addUniversity(university){
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
                tableUniversity.destroy();
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

function updateBtnUniversity(id, name){
    setForm(id,name);
    console.log(id,name);
    setEnabledField(false);
}

function updateUniversity(id,university){
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/university/" + id,
        data: JSON.stringify(university),
        success: function(data){
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#universityModal').modal('hide');
            tableUniversity.destroy;
            getAllUniv();
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

function deleteUniversity(id) {
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
                    tableUniversity.destroy();
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
