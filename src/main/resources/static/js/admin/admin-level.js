/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tableLevel;

$('document').ready(() => {
    getAllLevel();
});

function getAllLevel() {
    tableLevel = $('#levelTable').DataTable({
        'sAjaxSource': '/level',
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
                                        onclick="updateBtnLevel(this.getAttribute('id'),this.getAttribute('name'))"
                                        data-toggle="modal" data-target="#levelModal"><i class="fas fa-edit"></i></button>
                                <button class="btn btn-danger float-left" 
                                        id="${row.id}"
                                        onclick="deleteLevel(this.getAttribute('id'))"><i class="fas fa-trash-alt"></i></button>
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
    $('#idLevel').val(id);
    $('#nameLevel').val(name);
}

function setEnabledField(isEnabled) {
    $('#nameLevel').prop('disabled', isEnabled);
}

function saveLevel(){
    let id = $('#idLevel').val();
    let name = $('#nameLevel').val();
    var level = {
        "name":name
    };
    if (id === "") {
        addLevel(level);
    } else {
        updateLevel(id, level);
    }
    
}
function addLevel(level){
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/level",
        data: JSON.stringify(level),
        success: function(data){
            Swal.fire(
                    'Added!',
                   'Your file has been Added.',
                    'success');
                $('#levelModal').modal('hide');
                tableLevel.destroy();
                getAllLevel();
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

function updateBtnLevel(id, name){
    setForm(id,name);
    console.log(name);
    console.log(id);
    setEnabledField(false);
}

function updateLevel(id,level){
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/level/" + id,
        data: JSON.stringify(level),
        success: function(data){
            Swal.fire(
                    'Update!',
                    'Your file has been Update',
                    'success'
                    );
            $('#levelModal').modal('hide');
            tableLevel.destroy;
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

function deleteLevel(id) {
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