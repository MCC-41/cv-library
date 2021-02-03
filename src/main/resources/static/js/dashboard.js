/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let url = "http://localhost:8090";

function getAllEmployee() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee/all',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getAllUser() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/user',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

var allEmployee = getAllEmployee().data.data;
$('#employee').text(allEmployee);

var allUser = getAllUser().data.data;
$('#user').text(allUser);

//function statusCounter(inputs) {
//  let counter = 0;
//  for (const input of inputs) {
//    if (input.status === '0') counter += 1;
//  }
//  return counter;
//}
//
//statusCounter(storage);