/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let urlDomain = "http://localhost:8020/";
let id = $("#id").val();

$(document).ready( function () {
    load();
    
    $('#btn-add').on('click',function(){
        setForm("","","");
        isEnabled(false,false);
        $('#regionForm').modal("show");

        $('#btn-save').show();
        $('#form')[0].reset();
    });
    
    $('#btn-save').on('click',function(e){
        e.preventDefault();
        if(id == undefined){
            saveCountry(urlDomain+"country"); 
        }
        else{
            updateCountry(urlDomain+"country/update/"+id);
        }
    })
    
    $('#region').select2();
});


saveCountry = (url) => {
    var data = $('#form').serialize();
    $.ajax({
            url:url,
            type:"POST",
            data:data,
            dataType: "JSON",
            success:function(response){
                if(response.success == true){
                    Swal.fire(
                        'Save Success!',
                        response.message,
                        'success'
                      );
                    table.destroy();
                    load();
                      
                }
            }
        })
}

updateCountry = (url) => {
    var data = $('#form').serialize();
    $.ajax({
            url:url,
            type:"POST",
            data:data,
            dataType: "JSON",
            success:function(response){
                if(response.success == true){
                    Swal.fire(
                        'Update Success!',
                        response.message,
                        'success'
                      );
                    table.destroy();
                    load();
                      
                }
            }
        })
}

setForm = (id,name,region) => {
     $('#name').val(name);
     $('#id').val(id);
     $('.region').val(region);
     $('#region').trigger('change');
}

isEnabled = (isEnable,isUpdate) => {
    $('#name').prop("disabled",isEnable);
    $('#region').prop("disabled",isEnable);
    $('#id').prop("readonly",isUpdate);
}

load = () => {
    table = $('#myTable').DataTable({
        'sAjaxSource': urlDomain+"country",
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'name'},
            {'data': 'region.name'},
            {
                'render': function (data, type, row, meta) {
                    return '\
                        <a href="#" class="btn btn-sm btn-info"\n\
                            onclick="detail(\'' + row.id +'\',\'country\',true)">View</a>&nbsp;\n\
                        <a href="#" class="btn btn-sm btn-warning"\n\
                            onclick="detail(\'' + row.id +'\',\'country\',false)">Edit</a>&nbsp;\n\
                        <a href="#" class="btn btn-sm btn-danger"\n\
                            onclick="deletes(\'' + row.id +'\',\'country\')">Delete</a>&nbsp;\n\
                    ';
                }
            }
        ]
    });            
}

detail = (cId,url,status) =>{
    
    $('#regionForm').modal('show');
    id = cId;
    
    $.ajax({
       url:urlDomain+url+"/"+cId,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           
            if(status != true ){
                $('#btn-save').show();
                isEnabled(false,true);
            }
            else{
                 $('#btn-save').hide();
                 isEnabled(true,true);
            }

            setForm(response.id,response.name,response.region.id)
       }
    })
}

deletes = (id,url) => {
    
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!',
        timer:"2000"
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                   url:urlDomain+url+"/delete/"+id,
                   method:"GET",
                   dataType: "JSON",
                   success:function(response){
                       console.log(response);
                       if(response.success == true){
                           Swal.fire(
                            'Delete Success!',
                             response.message,
                            'success'
                          )
                          table.destroy();
                          load();
                       }
                   }
                })
            }
        })
}

