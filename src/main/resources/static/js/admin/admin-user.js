/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('.userisVerified').select2();
    $('.userEmployee').select2({
        ajax: {
            url: '/user/employee',
            type: 'GET',
            dataType: 'json',
            delay: 250,
            processResults: function (response) {
                var res = response.data.map(function (item) {
                    return {id: item.id, text: item.name};
                });
                return {
                    results: res
                };
            },
            cache: true
        }
    });
    $('.userStatus').select2({
        ajax: {
            url: '/user/status',
            type: 'GET',
            dataType: 'json',
            delay: 250,
            processResults: function (response) {
                var res = response.data.map(function (item) {
                    return {id: item.id, text: item.name};
                });
                return {
                    results: res
                };
            },
            cache: true
        }
    });
});
function btnAdd() {
    setEnabledField(false);
    setForm('', '', '', '');
}
function btnEdit(employee, username, status, isVerified) {
    setEnabledField(false);
//    setForm(employee, username, status, isVerified);
    console.log(employee);
    $('#employee').change(function (){
        $('#employee').val(employee);
        $('#employee').select2().trigger('change');
    });
}
function setForm(employee, username, status, isVerified) {
    $('#employee').val(employee);
//    $('#employee').select2().trigger('change');
    $('#username').val(username);
    $('#status').val(status);
    $('#isVerified').val(isVerified);
    
}
function setEnabledField(isEnabled) {
    $('#save').prop('disabled', isEnabled);
    $('#employee').prop('disabled', isEnabled);
    $('#username').prop('disabled', isEnabled);
    $('#status').prop('disabled', isEnabled);
    $('#isVerified').prop('disabled', isEnabled);
}
function btnSave() {
    let a = $("#employee").val();
    console.log(a);
}
function add() {
    let id = $("#employee").val();
    console.log(id);
    if (id === null) {
        var user = {
            "id": id
        };
        $.ajax({
            contentType: 'application/json',
            type: 'POST',
            url: "/user/add",
            data: JSON.stringify(user),
            success: function (data) {
                Swal.fire(
                        'Added!',
                        'Your file has been Added.',
                        'success'
                        );
                $('#regionModal').modal('hide');
//            tabel.destroy();
//            getAll();
            },

            error: function (data) {
//            console.log(data);
                Swal.fire(
                        'Failed!',
                        'Your file cannot be Added.',
                        'error'
                        );
            }

        });
    } else {

    }

}
$('.deleteUser').click(function () {
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
            Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                    );
        }
    });
});