/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function add(){
    let id = $("#id").val();
    let name = $("#name").val();
    let position = $("#position").val();
    let jobDesc = $("#jobDesc").val();
    let startDate = $("#startDate").val();
    let endDate = $("#endDate").val();
    var work = {
        "id" : id,
        "companyName" : name,
        "position": position,
        "jobDesc" : jobDesc,
        "startDate" : startDate,
        "endDate" : endDate
    };
    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        url: "/work/add",
        data: JSON.stringify(work),
        success: function (data){
            console.log(data);
        },
        error: function (data) {
            
        }
        
    });
}
