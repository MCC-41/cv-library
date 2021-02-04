/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

$('document').ready(() => {
    setForms(true);
    $('#Cancel').hide();
    $('#Save').hide();
});
function cancel(){
    setForms(true);
    $('#Cancel').hide();
    $('#Save').hide();
    $('#Edit').show();
}
function btnEdit() {
    setForms(false);
    $('#Edit').hide();
    $('#Cancel').show();
    $('#Save').show();
}
function setForms(isEnabled) {
    $('#name').prop('disabled', isEnabled);
    $('#email').prop('disabled', isEnabled);
    $('#birth').prop('disabled', isEnabled);
    $('#nation').prop('disabled', isEnabled);
    $('#status').prop('disabled', isEnabled);
    $('#gender').prop('disabled', isEnabled);
    $('#religion').prop('disabled', isEnabled);
    $('#photo').prop('disabled', isEnabled);
}
function setForm(title, id, name, institution, year, trainingType, isEnable, file) {
    $('.trainingTitle').text(title);
    $("#id").val(id);
    $("#name").val(name);
    $("#institution").val(institution);
    $("#year").val(year);
    $("#trainingType").val(trainingType);
}
function save() {
    let id = $('#id').val();
    let name = $('#name').val();
    let email = $('#email').val();
    let birth = $('#birth').val();
    let nation = $('#nation').val();
    let status = $('#status').val();
    let gender = $('#gender').val();
    let religion = $('#religion').val();
//    let photo = $('#photo').val();
    let photo = new FormData();
    photo.append('file',$('#photo')[0].files[0]);
    
    console.log(id);
    console.log(name);
    console.log(email);
    console.log(birth);
    console.log(nation);
    console.log(status);
    console.log(gender);
    console.log(religion);
    console.log(photo);
    if(photo===null){
        update2(id, name, email, birth, nation, status, gender, religion, photo);
    }else{
        update(id, name, email, birth, nation, status, gender, religion, photo);
    }
    
}
function update(id, name, email, birth, nation, status, gender, religion, photo) {
    $.ajax({
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        type: 'PUT',
        url: "/employee/" + id + "?" + $.param({name: name,email: email,dateBirth: birth, nation: nation, status: status, gender: gender, religion: religion}),
        data: photo,
        success: function (data) {
            console.log(data);
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            cancel();
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
function update2(id, name, email, birth, nation, status, gender, religion, photo) {
    $.ajax({
        contentType: false,
        processData: false,
        type: 'PUT',
        url: "/employee/" + id + "?" + $.param({name: name,email: email,dateBirth: birth, nation: nation, status: status, gender: gender, religion: religion}),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            cancel();
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

