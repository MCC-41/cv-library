/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */

function logout() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You want to log out right now",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'yes, logout'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: "/logout/",
                success: function () {
                    location.href = "login";
                },
                error: function () {
                    location.href = "login";
                }
            });
        }
    });
}

