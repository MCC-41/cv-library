/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

var table;

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
    let photo = $('#photo').val();
    console.log(id);
    console.log(name);
    console.log(email);
    console.log(birth);
    console.log(nation);
    console.log(status);
    console.log(gender);
    console.log(religion);
    console.log(photo);
    
    var employee = {
        "name": name,
        "institution": institution,
        "year": year,
        "trainingType": {
            "id": type
        },
        "file": file
    };

//    update(id, employee);
}
function update(id, employee) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/employee/" + id,
        data: JSON.stringify(employee),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
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

