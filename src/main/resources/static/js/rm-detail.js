/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

$('document').ready(() => {
    
});
function text(email){
    console.log(email);
    $('#id').val(email);
}
function send(){
    let email= $('#id').val();
    let memo = $('#memo').val();
    console.log(email);
    var data = {
        "email" : email,
        "memo" : memo
    };
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: '/send',
        data: JSON.stringify(data),
        success: function (data) {
            Swal.fire(
                    'Sended!',
                    'Your memo has been sended.',
                    'success'
                    );
            $('#memoModal').modal('hide');
        },
        error: function (data) {
            Swal.fire(
                    'Failed!',
                    'Your memo cannot be sended.',
                    'error'
                    );
        }
    });
}
function download(id, file) {
    $.ajax({
        contentType: 'application/json',
        url: "/"+id +"/download?"+$.param({file: file}),
        type: 'GET',
        success: function (data) {
//            Swal.fire(
//                    'Added!',
//                    'Your file has been Added.',
//                    'success'
//                    );
        },
        error: function (data) {
            console.log(data);
            Swal.fire(
                    'Failed!',
                    'Your file cannot be downloaded.',
                    'error'
                    );
        }
    });
}